import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Subject {
    private String subjectName;
    private Date examDate;
    private List<Topic> topicList;

    public Subject(String subjectName, Date examDate) {
        this.subjectName = subjectName;
        this.examDate = examDate;
        this.topicList = new ArrayList<>();
    }

    public void addTopic(Topic topic) {
        topicList.add(topic);
    }

    public double getSubjectProgress() {
        if (topicList.isEmpty()) return 0;
        int completed = 0;
        for (Topic t : topicList) {
            if (t.getStatus().equalsIgnoreCase("Completed")) {
                completed++;
            }
        }
        return (completed * 100.0) / topicList.size();
    }

    public Date getDate(){
        return examDate;
    }
    public String getSubjectName() {
        return subjectName;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }
}
