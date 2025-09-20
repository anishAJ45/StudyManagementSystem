import java.util.*;

public class StudyManager {
    // Data structures for managing subjects, tasks, and study time
    static ArrayList<String> subjects = new ArrayList<>();
    static ArrayList<String> tasks = new ArrayList<>();
    static ArrayList<Boolean> taskDone = new ArrayList<>();
    static HashMap<String, Integer> studyTime = new HashMap<>();
    static HashMap<String, Integer> goals = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize default subjects
        subjects.add("Math");
        subjects.add("Science");
        subjects.add("English");
        
        // Set default goals and study time
        for (String subject : subjects) {
            studyTime.put(subject, 0);
            goals.put(subject, 60);
        }
        
        System.out.println("=== Study Management System ===");
        
        while (true) {
            showMenu();
            int choice = sc.nextInt();
            sc.nextLine();
            
            if (choice == 1) manageSubjects();
            else if (choice == 2) manageTasks();
            else if (choice == 3) studyTimer();
            else if (choice == 4) viewProgress();
            else if (choice == 5) {
                System.out.println("👋 Thanks for using Study Manager! Keep studying! 📚");
                break;
            }
        }
    }

    static void showMenu() {
        System.out.println("\n📚 --- STUDY MANAGER --- 📚");
        System.out.println("1. 📖 Manage Subjects");
        System.out.println("2. ✅ Manage Tasks");
        System.out.println("3. ⏰ Study Timer");
        System.out.println("4. 📊 View Progress");
        System.out.println("5. 🚪 Exit");
        System.out.print("Choice: ");
    }

    static void manageSubjects() {
        System.out.println("\n📖 --- Subjects --- 📖");
        System.out.println("1. 👀 View  2. ➕ Add  3. ❌ Remove");
        
        try {
            int choice = sc.nextInt();
            sc.nextLine();
            
            if (choice == 1) {
                for (int i = 0; i < subjects.size(); i++) {
                    System.out.printf("%d. %s (%d min studied)\n", 
                        i+1, subjects.get(i), studyTime.get(subjects.get(i)));
                }
            } else if (choice == 2) {
                System.out.print("Enter subject name: ");
                String subject = sc.nextLine();
                subjects.add(subject);
                studyTime.put(subject, 0);
                goals.put(subject, 60);
                System.out.println("✅ Subject added successfully!");
            } else if (choice == 3 && !subjects.isEmpty()) {
                for (int i = 0; i < subjects.size(); i++) {
                    System.out.printf("%d. %s\n", i+1, subjects.get(i));
                }
                System.out.print("Remove which subject? ");
                int index = sc.nextInt() - 1;
                if (index >= 0 && index < subjects.size()) {
                    String subject = subjects.get(index);
                    subjects.remove(index);
                    studyTime.remove(subject);
                    goals.remove(subject);
                    System.out.println("❌ Subject removed successfully!");
                }
            } else {
                System.out.println("❌ Invalid choice! Please select 1-3.");
            }
        } catch (Exception e) {
            System.out.println("❌ Invalid input! Please enter a number.");
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
        }
    }

    static void manageTasks() {
        System.out.println("\n✅ --- Tasks --- ✅");
        System.out.println("1. 👀 View  2. ➕ Add  3. ✅ Mark Done  4. ❌ Remove Task");

        try {
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                viewTasks();
            } else if (choice == 2) {
                addTask();
            } else if (choice == 3) {
                markTaskDone();
            } else if (choice == 4) {
                removeTask();
            } else {
                System.out.println("❌ Invalid choice! Please select 1-4.");
            }
        } catch (Exception e) {
            System.out.println("❌ Invalid input! Please enter a number.");
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
        }
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("📝 No tasks yet. Add some tasks to get started!");
        } else {
            System.out.println("\n📋 Your Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                String status = taskDone.get(i) ? "[✓]" : "[ ]";
                System.out.printf("%d. %s %s\n", i+1, status, tasks.get(i));
            }
        }
    }

    static void addTask() {
        System.out.print("Enter task description: ");
        String task = sc.nextLine().trim();

        if (task.isEmpty()) {
            System.out.println("❌ Task cannot be empty! Please enter a valid task.");
            return;
        }

        tasks.add(task);
        taskDone.add(false);
        System.out.println("✓ Task added successfully: \"" + task + "\"");
    }

    static void markTaskDone() {
        if (tasks.isEmpty()) {
            System.out.println("📝 No tasks available. Add some tasks first!");
            return;
        }

        // Show only incomplete tasks
        ArrayList<Integer> incompleteIndexes = new ArrayList<>();
        System.out.println("\n📝 Incomplete Tasks:");
        int displayNum = 1;

        for (int i = 0; i < tasks.size(); i++) {
            if (!taskDone.get(i)) {
                System.out.printf("%d. %s\n", displayNum, tasks.get(i));
                incompleteIndexes.add(i);
                displayNum++;
            }
        }

        if (incompleteIndexes.isEmpty()) {
            System.out.println("🎉 All tasks are already completed! Great job! 🎉");
            return;
        }

        try {
            System.out.print("Mark which task as done (1-" + incompleteIndexes.size() + ")? ");
            int choice = sc.nextInt() - 1;
            sc.nextLine();

            if (choice >= 0 && choice < incompleteIndexes.size()) {
                int actualIndex = incompleteIndexes.get(choice);
                taskDone.set(actualIndex, true);
                System.out.println("✓ Task marked as completed: \"" + tasks.get(actualIndex) + "\"");
            } else {
                System.out.println("❌ Invalid task number!");
            }
        } catch (Exception e) {
            System.out.println("❌ Invalid input! Please enter a number.");
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
        }
    }

    static void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("📝 No tasks to remove.");
            return;
        }

        System.out.println("\n📋 All Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            String status = taskDone.get(i) ? "[✓]" : "[ ]";
            System.out.printf("%d. %s %s\n", i+1, status, tasks.get(i));
        }

        try {
            System.out.print("Remove which task (1-" + tasks.size() + ")? ");
            int index = sc.nextInt() - 1;
            sc.nextLine();

            if (index >= 0 && index < tasks.size()) {
                String removedTask = tasks.get(index);
                tasks.remove(index);
                taskDone.remove(index);
                System.out.println("✓ Task removed: \"" + removedTask + "\"");
            } else {
                System.out.println("❌ Invalid task number!");
            }
        } catch (Exception e) {
            System.out.println("❌ Invalid input! Please enter a number.");
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
        }
    }

    static void studyTimer() {
        if (subjects.isEmpty()) {
            System.out.println("❌ Add subjects first!");
            return;
        }

        System.out.println("\n⏰ --- Study Timer --- ⏰");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.printf("%d. %s\n", i+1, subjects.get(i));
        }
        System.out.print("Select subject (1-" + subjects.size() + "): ");

        try {
            int index = sc.nextInt() - 1;
            sc.nextLine();

            if (index < 0 || index >= subjects.size()) {
                System.out.println("Invalid subject selection!");
                return;
            }

            String subject = subjects.get(index);
            System.out.print("Study time in minutes: ");
            int minutes = sc.nextInt();
            sc.nextLine();

            if (minutes <= 0) {
                System.out.println("Please enter a positive number of minutes!");
                return;
            }

            System.out.println("\n🎯 Studying " + subject + " for " + minutes + " minutes...");
            System.out.println("⏳ Press Enter when done studying.");

            // Try to read next line, but don't fail if there's no input
            try {
                sc.nextLine();
            } catch (Exception inputError) {
                // No input available, continue anyway
            }

            // Update study time
            studyTime.put(subject, studyTime.get(subject) + minutes);
            System.out.println("✅ Study time updated! Total for " + subject + ": " + studyTime.get(subject) + " minutes");

        } catch (Exception e) {
            System.out.println("Invalid input! Please enter numbers only.");
            // Clear the scanner buffer
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
        }
    }

    static void viewProgress() {
        System.out.println("\n--- Progress Dashboard ---");

        if (subjects.isEmpty()) {
            System.out.println("No subjects to show.");
            return;
        }

        System.out.println("\n📊 Study Time Progress:");
        System.out.println("=".repeat(50));

        for (String subject : subjects) {
            int studied = studyTime.get(subject);
            int goal = goals.get(subject);
            double percent = (double) studied / goal * 100;
            String progressBar = createProgressBar(studied, goal, 20);

            System.out.printf("%-10s [%s] %d/%d min (%.1f%%)\n",
                subject, progressBar, studied, goal, percent);
        }

        // Show hourly breakdown if there's study time
        showHourlyBreakdown();

        // Show task completion chart
        if (!tasks.isEmpty()){
            showTaskCompletionChart();
        }

        // Show overall statistics
        showOverallStats();
    }

    static String createProgressBar(int current, int goal, int barLength) {
        int filled = goal > 0 ? (int)((double)current / goal * barLength) : 0;
        if (filled > barLength) filled = barLength;

        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < barLength; i++) {
            if (i < filled) {
                bar.append("█");
            } else {
                bar.append("░");
            }
        }
        return bar.toString();
    }

    static void showHourlyBreakdown() {
        System.out.println("\n⏰ Study Hours Breakdown:");
        System.out.println("-".repeat(30));

        for (String subject : subjects) {
            int minutes = studyTime.get(subject);
            if (minutes > 0) {
                int hours = minutes / 60;
                int remainingMins = minutes % 60;
                String timeBar = createTimeBar(minutes, 120); // Max 2 hours for display

                System.out.printf("%-10s %s %dh %02dm\n",
                    subject, timeBar, hours, remainingMins);
            }
        }
    }

    static String createTimeBar(int minutes, int maxMinutes) {
        int barLength = 15;
        int filled = maxMinutes > 0 ? (int)((double)minutes / maxMinutes * barLength) : 0;
        if (filled > barLength) filled = barLength;

        StringBuilder bar = new StringBuilder("|");
        for (int i = 0; i < barLength; i++) {
            if (i < filled) {
                bar.append("▓");
            } else {
                bar.append("▒");
            }
        }
        bar.append("|");
        return bar.toString();
    }

    static void showTaskCompletionChart() {
        System.out.println("\n✅ Task Completion Chart:");
        System.out.println("-".repeat(25));

        int completed = 0;
        for (boolean done : taskDone) {
            if (done) completed++;
        }

        double completionRate = !tasks.isEmpty() ? (double)completed / tasks.size() * 100 : 0;
        String completionBar = createProgressBar(completed, tasks.size(), 15);

        System.out.printf("Tasks     [%s] %d/%d (%.1f%%)\n",
            completionBar, completed, tasks.size(), completionRate);

        // Show individual task status
        System.out.println("\nTask Status:");
        for (int i = 0; i < tasks.size(); i++) {
            String status = taskDone.get(i) ? "✓" : "○";
            String taskBar = taskDone.get(i) ? "████████████████████" : "░░░░░░░░░░░░░░░░░░░░";
            System.out.printf("  %s [%s] %s\n", status, taskBar.substring(0, 10),
                tasks.get(i).length() > 25 ? tasks.get(i).substring(0, 25) + "..." : tasks.get(i));
        }
    }

    static void showOverallStats() {
        System.out.println("\n📈 Overall Statistics:");
        System.out.println("=".repeat(30));

        int totalStudied = 0;
        int totalGoals = 0;

        for (String subject : subjects) {
            totalStudied += studyTime.get(subject);
            totalGoals += goals.get(subject);
        }

        double overallProgress = totalGoals > 0 ? (double)totalStudied / totalGoals * 100 : 0;
        String overallBar = createProgressBar(totalStudied, totalGoals, 25);

        System.out.printf("Overall   [%s] %.1f%%\n", overallBar, overallProgress);
        System.out.printf("Total Study Time: %d minutes (%d hours %d minutes)\n",
            totalStudied, totalStudied / 60, totalStudied % 60);

        if (!tasks.isEmpty()) {
            int completedTasks = 0;
            for (boolean done : taskDone) {
                if (done) completedTasks++;
            }
            System.out.printf("Tasks Completed: %d/%d\n", completedTasks, tasks.size());
        }
    }
}
