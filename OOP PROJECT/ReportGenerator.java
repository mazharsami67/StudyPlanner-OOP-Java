// import java.util.List;

public class ReportGenerator {
    public static void generateSummaryReport(User user) {
        System.out.println("  Exam Preparation Report  ");
        user.viewProgress();
        double totalProgress = ProgressTracker.calculateCompletionRate(user.getSubjects());
        System.out.println("Total Completion Rate: " + totalProgress + "%");
    }

    public static void getSubjectReport(Subject subject) {
        System.out.println("Subject: " + subject.getSubjectName());
        for (Topic t : subject.getTopicList()) {
            System.out.println(" - " + t.getTaskName() + " | Status: " + t.getStatus() + " | Due: " + t.getDueDate());
        }
        System.out.println("Progress: " + subject.getSubjectProgress() + "%");
    }
}