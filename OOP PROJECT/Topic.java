import java.util.Date;

public class Topic extends Task {
    private String difficultyLevel;
    private int estimatedTime;
    private String status;

    public Topic(String taskName, Date dueDate, String difficultyLevel, int estimatedTime) {
        super(taskName, dueDate);
        this.difficultyLevel = difficultyLevel;
        this.estimatedTime = estimatedTime;
        this.status = "Not Started";
    }

    @Override
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public String getStatus() {
        return status;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }
}

