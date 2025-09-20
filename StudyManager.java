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
        
        System.out.println("Study Management System");
        System.out.println("Welcome to your study tracker!");
    }
}
