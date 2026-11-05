import java.util.*;
import Estudiantes.*;
import asignaturas.*;

/** One source of approval thresholds for queries, prerequisites, and log credits. */
public final class Grading {
    private Grading() {}
    public static CurricularUnit unit(Evaluation evaluation) {
        if (evaluation instanceof ExamEvaluation) return ((ExamEvaluation)evaluation).getCurricularunit();
        if (evaluation instanceof CourseEvaluation) return ((CourseEvaluation)evaluation).getCourse().getCurricularunit();
        if (evaluation instanceof PartialEvaluation) return ((PartialEvaluation)evaluation).getCourse().getCurricularunit();
        if (evaluation instanceof TutoringEvaluation) return ((TutoringEvaluation)evaluation).getCourse().getCurricularunit();
        return null;
    }
    public static GradeScale scale(CurricularUnit cu, asignaturas.Plan plan) {
        if (cu != null && cu.getGradescale() != null) return cu.getGradescale();
        return plan == null ? null : plan.getGradescale();
    }
    public static float threshold(Evaluation evaluation, asignaturas.Plan plan, boolean fullCourse) {
        GradeScale s = scale(unit(evaluation), plan);
        if (evaluation instanceof ExamEvaluation) return s == null ? 3 : s.getExamApprovalGrade();
        if (evaluation instanceof CourseEvaluation) return s == null ? (fullCourse ? 6 : 3)
            : fullCourse ? s.getCourseApprovalGrade() : s.getCoursePartialApprovalGrade();
        if (evaluation instanceof PartialEvaluation) return s == null ? 60 : s.getPartialApprovalGrade();
        if (evaluation instanceof TutoringEvaluation) return s == null ? 0 : s.getTutoringApprovalGrade();
        return Float.POSITIVE_INFINITY;
    }
    public static boolean passed(StudentEvaluation e, asignaturas.Plan plan, boolean fullCourse) {
        return e.getEvaluation() != null && e.getGrade() >= threshold(e.getEvaluation(), plan, fullCourse);
    }
    public static boolean completesUnit(StudentEvaluation e, asignaturas.Plan p) {
        return (e.getEvaluation() instanceof ExamEvaluation || e.getEvaluation() instanceof CourseEvaluation) && passed(e, p, true);
    }
    private static Set<CurricularUnit> completed(PlanInscription inscription) {
        Set<CurricularUnit> units = new HashSet<>();
        for (StudentEvaluation e : inscription.getPlanStudentEvaluation()) if (completesUnit(e, inscription.getPlan())) units.add(unit(e.getEvaluation()));
        return units;
    }
    private static boolean has(Student student, CurricularUnit cu, boolean full) {
        for (PlanInscription p : student.getStudentPlanInscription()) for (StudentEvaluation e : p.getPlanStudentEvaluation()) {
            if (unit(e.getEvaluation()) == cu && (e.getEvaluation() instanceof ExamEvaluation || e.getEvaluation() instanceof CourseEvaluation)
                    && passed(e, p.getPlan(), full)) return true;
        }
        return false;
    }
    private static void subjectUnits(Subject s, Set<CurricularUnit> units) {
        units.addAll(s.getSubjectCurricularUnit()); for (Subject child : s.getGroupOfSubjects()) subjectUnits(child, units);
    }
    public static boolean requirements(Student student, Requirement r) {
        if (r == null) return true;
        if (r instanceof NOT) return !requirements(student, ((NOT)r).getRequirement());
        if (r instanceof Coursed) return has(student, ((Coursed)r).getCurricularUnit(), false);
        if (r instanceof Exam) return has(student, ((Exam)r).getCurricularUnit(), true);
        if (r instanceof RegisteredTo) {
            CurricularUnit cu = ((RegisteredTo)r).getCurricularUnit();
            for (PlanInscription p : student.getStudentPlanInscription()) for (CourseInscription c : p.getPlanCourseInscription())
                if (c.getCUCourse().getCurricularunit() == cu) return true;
            return false;
        }
        if (r instanceof SomeOf) {
            SomeOf s = (SomeOf)r; int count = 0;
            for (Requirement child : s.getRequirement()) if (requirements(student, child)) count++;
            return count >= s.getN();
        }
        if (r instanceof CreditsOnPlan) {
            CreditsOnPlan c = (CreditsOnPlan)r; Set<CurricularUnit> units = new HashSet<>();
            for (PlanInscription p : student.getStudentPlanInscription()) if (p.getPlan() == c.getCreditsPlan()) units.addAll(completed(p));
            return units.stream().mapToInt(CurricularUnit::getCred).sum() >= c.getCred();
        }
        if (r instanceof CreditsOnSubject) {
            CreditsOnSubject c = (CreditsOnSubject)r; Set<CurricularUnit> allowed = new HashSet<>(), units = new HashSet<>();
            subjectUnits(c.getGroupOfSubjects(), allowed);
            // Subject credit requirements use the subject's owning plan context.
            org.eclipse.emf.ecore.EObject owner = c.getGroupOfSubjects().eContainer();
            while (owner != null && !(owner instanceof asignaturas.Plan)) owner = owner.eContainer();
            for (PlanInscription p : student.getStudentPlanInscription()) if (p.getPlan() == owner) units.addAll(completed(p));
            units.retainAll(allowed); return units.stream().mapToInt(CurricularUnit::getCred).sum() >= c.getCred();
        }
        return false;
    }
}
