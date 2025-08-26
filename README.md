## Study Manager (Console)

Simple Java console app to manage study subjects, tasks, and track study time with a basic progress view.

### Features
- **Subjects**: add, view, and remove subjects
- **Tasks**: add tasks and mark them as done
- **Study timer**: log minutes studied per subject
- **Progress**: see per-subject totals vs. default goals and task completion

### Requirements
- Java 8 or later (JDK)
- Windows users can optionally use `demo.bat`

### Get started
From the project root (`java project/`):

```bash
javac StudyManager.java
java StudyManager
```

On Windows, you can also double‑click or run:

```bat
demo.bat
```

### How it works
When the app starts, it creates default subjects `Math`, `Science`, and `English`, each with a default goal of 60 minutes. You can manage subjects and tasks, start a study session to log minutes, and view your overall progress.

Main menu:
- 1: Manage Subjects (view/add/remove)
- 2: Manage Tasks (view/add/mark done)
- 3: Study Timer (select subject and log minutes)
- 4: View Progress (per‑subject totals and task completion)
- 5: Exit

### Notes
- Data is kept in memory only; it resets each time you run the program.
- Input expects numbers for menu choices and minutes; invalid input is handled with a warning.

### Example session
```text
=== Study Management System ===
--- STUDY MANAGER ---
1. Manage Subjects
2. Manage Tasks
3. Study Timer
4. View Progress
5. Exit
Choice: 3

--- Study Timer ---
1. Math
2. Science
3. English
Select subject (1-3): 1
Study time in minutes: 25
Studying Math for 25 minutes...
Press Enter when done studying.
Study time updated! Total for Math: 25 minutes
```

After logging time, you can view progress:

```text
--- STUDY MANAGER ---
1. Manage Subjects
2. Manage Tasks
3. Study Timer
4. View Progress
5. Exit
Choice: 4

--- Progress ---
Math: 25/60 min (41.7%)
Science: 0/60 min (0.0%)
English: 0/60 min (0.0%)

Tasks: 0/0 completed
```

### Troubleshooting
- "command not found": Ensure `javac` and `java` are on your PATH (install JDK).
- Compilation errors: Delete any old `.class` files and recompile.

### License
MIT


