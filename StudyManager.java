import java.util.*;

public class StudyManager {
    static ArrayList<String> subjects = new ArrayList<>();
    static ArrayList<String> tasks = new ArrayList<>();
    static ArrayList<Boolean> taskdone = new ArrayList<>();
    static HashMap<String, Integer> studytime = new HashMap<>();
    static HashMap<String, Integer> goals = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n---- WELCOME TO STUDY MANAGER----");
            System.out.println("1.Add subject");
            System.out.println("2.view subjects");
            System.out.println("3.Add task");
            System.out.println("4.view tasks");
            System.out.println("5.mark task as done");
            System.out.println("6.Add study time");
            System.out.println("7.Exit");
            System.out.println("Enter your choice:");
            int ch = sc.nextInt();
            sc.nextLine();
            if (ch == 1) {
                addSubject();
            } else if (ch == 2) {
                viewSubjects();
            } else if (ch == 3) {
                addTask();
            } else if (ch == 4) {
                viewTasks();
            } else if (ch == 5) {
                markTaskDone();
            } else if (ch == 6) {
                addStudytimer();
            } else if (ch == 7) {
                System.out.println("Have a great day!!");
                break;
            } else {
                System.out.println("You entered an invalid choice pls try again");
            }
        }
    }

    static void addSubject() {
        System.out.print("Enter the subject: ");
        String sub = sc.nextLine();
        subjects.add(sub);
        System.out.println("Subject added successfully!!");
    }

    static void viewSubjects() {
        if (subjects.isEmpty()) {
            System.out.println("No subjects added yet");
        } else {
            for (int i = 0; i < subjects.size(); i++) {
                System.out.println((i + 1) + ". " + subjects.get(i));
            }
        }
    }

    static void addTask() {
        System.out.println("Enter the task:");
        String task = sc.nextLine();
        tasks.add(task);
        taskdone.add(false);
        System.out.println("Task added successfully!!");
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                String status = taskdone.get(i) ? "done" : "Not done";
                System.out.println((i + 1) + ". " + tasks.get(i) + " (" + status + ")");
            }
        }
    }

    static void markTaskDone() {
        viewTasks();
        if (tasks.isEmpty()) {
            return;
        }
        System.out.println("Enter task number:");
        int n = sc.nextInt();
        sc.nextLine();
        if (n > 0 && n <= tasks.size()) {
            taskdone.set(n - 1, true);
            System.out.println("Task marked as done");
        } else {
            System.out.println("Invalid");
        }
    }

    static void addStudytimer() {
        if (subjects.isEmpty()) {
            System.out.println("No subjects added yet!");
            return;
        }
        System.out.println("\n select a subject:");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i));
        }
        System.out.println("Enter subject number:");
        int subno = sc.nextInt() - 1;
        sc.nextLine();
        if (subno < 0 || subno >= subjects.size()) {
            System.out.println("Invalid subject number");
            return;
        }
        System.out.println("Enter the study time in min:");
        int min = sc.nextInt();
        sc.nextLine();
        if (min <= 0) {
            System.out.println("Invalid study time");
            return;
        }
        int currentime = studytime.getOrDefault(subjects.get(subno), 0);
        studytime.put(subjects.get(subno), currentime + min);
        System.out.println("Studying " + subjects.get(subno) + " for " + min + " minutes");
        System.out.println("Total Study time for " + subjects.get(subno) + ": " + studytime.get(subjects.get(subno)) + " minutes");
    }
}