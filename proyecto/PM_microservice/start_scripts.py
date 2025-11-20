'''
Created on 10 oct. 2025

@author: sndc3
'''
import subprocess
import sys

scripts = [
    "alignement_viewer.py",
    "basic_stats.py",
    "conformanceTest.py",
    "CU_stats.py",
    "Discover_stats.py",
    "discoverer_threaded.py",
    "listDiscovers.py",
    "Plan_stats.py",
    "viewer_threaded.py"
]

def run_all():
    for script in scripts:
        print(f"🔵 Starting {script}...")
        subprocess.Popen([sys.executable, script])  # run in parallel

if __name__ == "__main__":
    run_all()