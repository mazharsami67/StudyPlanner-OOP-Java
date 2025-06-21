import java.util.List;

public class ProgressTracker {
    public static double calculateCompletionRate(List<Subject> subjects) {
        int total = 0;
        int completed = 0;

        for (Subject s : subjects) {
            for (Topic t : s.getTopicList()) {
                total++;
                if (t.getStatus().equalsIgnoreCase("Completed")) {
                    completed++;
                }
            }
        }
        if (total == 0) return 0;
        return (completed * 100.0) / total;
    }

    public static void getPendingTopics(List<Subject> subjects) {
        for (Subject s : subjects) {
            for (Topic t : s.getTopicList()) {
                if (!t.getStatus().equalsIgnoreCase("Completed")) {
                    System.out.println("Pending: " + t.getTaskName() + " in " + s.getSubjectName());
                }
            }
        }
    }
}