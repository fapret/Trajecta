# Faculty model authoring

Open any frontend page and expand **Model administration** in the sidebar. Select
a workspace, create a faculty and career, create a grade scale, then create a plan.
Credit plans contain subjects (which may have nested subjects); subject plans
reference curricular units directly. Units belong to a faculty and can be shared
by multiple plans/subjects.

Create units before adding prerequisites that reference them. Use **Edit curricular
unit** to add those requirements later, adjust memberships, or choose a scale
override. Unit IDs and scale names are stable identities and cannot be renamed.

## HTTP contracts

All routes are relative to `/curricula_microservice`. Include `uuid` as a query
parameter and `faculty` for faculty-scoped operations. New clients send
`Content-Type: application/json`. Existing form/query parameter requests remain
supported; subject creation continues accepting its existing JSON fields.

| Route | Methods | Creation / update fields |
| --- | --- | --- |
| `/Faculty` | GET, POST | `name` |
| `/Faculty/Carrera` | GET, POST | `name` |
| `/Faculty/Carrera/Plan` | GET, POST, PUT | `career`, `plan` (year), `planType` (0 credits, 1 subjects), `minCred`, `gradeScale`; PUT changes the selected plan's `gradeScale` |
| `/Faculty/Carrera/Plan/Subjects` | GET, POST | `career`, `plan`, `subjectID` (integer), `subjectName`, `subjectCredits`, `Parent` (optional parent ID, -1 means none), `curricularUnits` (array of unit IDs) |
| `/Faculty/ucs` | GET, POST, PUT | `id`, `name`, `cred`, `valid`, `gradeScale`, `requirements`, `memberships` |
| `/Faculty/ucs/course` | GET, POST | `id` (unit), `year`, `edition` (defaults to 1 on creation) |
| `/Faculty/ucs/evaluation` | POST | `id` (unit), `type`, `date` (`YYYY-MM-DD`); `year` and `edition` required except for exams |
| `/Faculty/grade-scales` | GET, POST, PUT | `name`, `examApprovalGrade`, `courseApprovalGrade`, `coursePartialApprovalGrade`, `partialApprovalGrade`, `tutoringApprovalGrade` |

Evaluation types are `ExamEvaluation`, `CourseEvaluation`, `PartialEvaluation`,
and `TutoringEvaluation`. Dates are interpreted at midnight in America/Montevideo.
Duplicates are rejected by unit/course identity, evaluation type and date, or
plan year within a career. EMF business IDs must be unique in the workspace's
model resource, including across object types. Human-facing names/IDs with
spaces or URI delimiters receive a separate safe XMI serialization ID.

GET `/Faculty/ucs` lists units; add `withName=true` for ID/name pairs or
`curricularUnit=<id>` for details. GET subjects requires `career` and `plan`;
add `subject=<id>` for details. GET scales lists full scale objects; add
`name=<scale>` for one scale. Scale responses include directly referencing
`plans` and `curricularUnits`. Plan/unit details retain their existing field
names and additionally expose `GradeScale`; unit details add `Memberships`,
`Valid`, and each course includes `TutoringEvaluation` dates.

### Updating a unit

```json
{
  "id": "ALG2",
  "name": "Algebra II",
  "cred": 10,
  "valid": true,
  "gradeScale": null,
  "requirements": {"SomeOf": [2, {"Coursed": "ALG1"}, {"Exam": "MAT1"}]},
  "memberships": [
    {"career": "Computing", "plan": 2026, "subject": 101},
    {"career": "Computing", "plan": 2027}
  ]
}
```

For credit plans, each membership specifies its subject ID. For subject plans,
omit `subject`. On PUT, omitted properties preserve existing values;
`requirements: null` removes prerequisites, `gradeScale: null` removes the
override, and `memberships: []` removes all memberships. Replacing memberships
does not move the unit out of its faculty or recreate courses/evaluations.

Requirements accept exactly one operator per object:

```json
{"NOT": {"RegisteredTo": "ALG1"}}
{"Coursed": "ALG1"}
{"Exam": "ALG1"}
{"CreditsOnPlan": "2026,Computing,60"}
{"CreditsOnSubject": "101,20"}
{"SomeOf": [1, {"Exam": "ALG1"}, {"Exam": "MAT1"}]}
```

All references must already exist in the faculty. Credit-plan references use the
existing comma-separated format, so career names used in that expression cannot
contain commas. Legacy unit creation may still send the raw requirement object
as its body with unit details in query parameters.

### Grading

Scales are owned by faculties; cross-faculty assignments are rejected. Plans
require a scale on creation; units may override it. Effective thresholds use the
evaluated unit's override, its student plan inscription's scale, then legacy
defaults (exam 3, full course 6, course eligibility 3, partial 60, tutoring 0).
Thresholds accept finite decimal values. The existing student model stores
integer grades; its representation is unchanged.

Editing a shared scale affects subsequent checks of existing grades without
rewriting those grades. A course eligibility pass does not imply full unit
approval; partial/tutoring passes do not automatically aggregate into course
approval. Credit requirements count each completed unit once. Approved-evaluation
queries include all four supported evaluation types, retaining the existing
`id`, `type`, and `grade` response fields.

Creation returns 201; updates and reads return 200. Errors contain an `error`
message and use 400 for invalid input, 404 for missing objects, and 409 for
duplicates. Validation failures leave the saved model unchanged. Saves use a
sibling temporary file and atomic replacement where supported. New authoring
operations are serialized within the servlet process; this is not a distributed
lock across multiple Tomcat instances or external model writers.

## Verification

```sh
ant -f proyecto/java_microservice/build.xml test build-war
```

Tests create isolated workspaces under the service's `build` directory and use
the real servlet boundaries and EMF serialization. `trajecta.workspaces` is an
optional JVM system property overriding the normal `~/workspaces` directory.
For optional local browser smoke tests, run `AuthoringIntegrationTest --serve`
with `build/test-classes`, `build/classes`, and `WEB-INF/lib/*` on the classpath.
Its loopback-only servlet adapter uses port 8080 and disposable fixture data;
it is excluded from the WAR. Serve `proyecto/front` with a static web server.
