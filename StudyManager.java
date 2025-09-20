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
}
