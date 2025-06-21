import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudyPlan {
    private List<Subject> subjectList;
    private Date startDate;
    private Date endDate;

    public StudyPlan(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.subjectList = new ArrayList<>();
    }

    public void addSubject(Subject subject) {
        subjectList.add(subject);
    }

    public Date getStartDate(){
        return startDate;
    }

    public Date getEndDate(){
        return endDate;
    }

    public void viewPlan() {
        for (Subject s : subjectList) {
            System.out.println("Subject: " + s.getSubjectName());
            for (Topic t : s.getTopicList()) {
                System.out.println(" - Topic: " + t.getTaskName() + " [" + t.getStatus() + "]");
            }
        }
    }
}