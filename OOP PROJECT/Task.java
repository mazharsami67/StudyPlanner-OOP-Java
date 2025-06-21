import java.util.Date;

public abstract class Task {
    protected String taskName;
    protected Date dueDate;

    public Task(String taskName, Date dueDate) {
        this.taskName = taskName;
        this.dueDate = dueDate;
    }

    public abstract void updateStatus(String newStatus);

    public String getTaskName() {
        return taskName;
    }

    public Date getDueDate() {
        return dueDate;
    }
}

