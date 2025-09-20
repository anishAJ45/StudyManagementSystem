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
                System.out.println("Thanks for using Study Manager!");
                break;
            }
        }
    }

    static void showMenu() {
        System.out.println("\n--- STUDY MANAGER ---");
        System.out.println("1. Manage Subjects");
        System.out.println("2. Manage Tasks");
        System.out.println("3. Study Timer");
        System.out.println("4. View Progress");
        System.out.println("5. Exit");
        System.out.print("Choice: ");
    }

    static void manageSubjects() {
        System.out.println("\n--- Subjects ---");
        System.out.println("1. View  2. Add  3. Remove");
        
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
                System.out.println("Subject added!");
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
                    System.out.println("Subject removed!");
                }
            } else {
                System.out.println("Invalid choice! Please select 1-3.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a number.");
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
        }
    }

    static void manageTasks() {
        System.out.println("\n--- Tasks ---");
        System.out.println("1. View  2. Add");

        try {
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                viewTasks();
            } else if (choice == 2) {
                addTask();
            } else {
                System.out.println("Invalid choice! Please select 1-2.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a number.");
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
        }
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet. Add some tasks to get started!");
        } else {
            System.out.println("\nYour Tasks:");
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
            System.out.println("Task cannot be empty! Please enter a valid task.");
            return;
        }

        tasks.add(task);
        taskDone.add(false);
        System.out.println("✓ Task added successfully: \"" + task + "\"");
    }

    static void studyTimer() {
        if (subjects.isEmpty()) {
            System.out.println("Add subjects first!");
            return;
        }

        System.out.println("\n--- Study Timer ---");
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

            System.out.println("\nStudying " + subject + " for " + minutes + " minutes...");
            System.out.println("Press Enter when done studying.");

            // Try to read next line, but don't fail if there's no input
            try {
                sc.nextLine();
            } catch (Exception inputError) {
                // No input available, continue anyway
            }

            // Update study time
            studyTime.put(subject, studyTime.get(subject) + minutes);
            System.out.println("Study time updated! Total for " + subject + ": " + studyTime.get(subject) + " minutes");

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

        // Show task completion
        if (!tasks.isEmpty()) {
            int completed = 0;
            for (boolean done : taskDone) {
                if (done) completed++;
            }
            double completionRate = (double) completed / tasks.size() * 100;
            String completionBar = createProgressBar(completed, tasks.size(), 15);
            System.out.printf("\nTasks     [%s] %d/%d (%.1f%%)\n",
                completionBar, completed, tasks.size(), completionRate);
        } else {
            System.out.println("\nTasks: 0/0 completed");
        }
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
}
