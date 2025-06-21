import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private List<Subject> subjects;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.subjects = new ArrayList<>();
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void viewProgress() {
        for (Subject subject : subjects) {
            System.out.println("Subject: " + subject.getSubjectName());
            System.out.println("Progress: " + subject.getSubjectProgress() + "%");
            System.out.println();
        }
    }
}
