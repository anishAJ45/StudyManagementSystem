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
}
