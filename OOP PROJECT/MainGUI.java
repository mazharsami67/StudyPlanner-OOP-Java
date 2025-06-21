import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainGUI extends JFrame {
    private User user;
    private Subject currentSubject;

    private JTextField subjectField;
    private JTextField examDateField;

    private JTextField topicNameField;
    private JTextField topicDueDateField;
    private JTextField topicDifficultyField;
    private JTextField topicTimeField;

    private JComboBox<String> statusDropdown;
    private JTextField updateTopicField;

    private JTextArea outputArea;

    public MainGUI(User user) {
        super("Exam Preparation Planner - " + user.getName());
        this.user = user;
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(12, 2, 5, 5));

        // Subject input
        subjectField = new JTextField();
        examDateField = new JTextField();

        inputPanel.add(new JLabel("Subject Name:"));
        inputPanel.add(subjectField);

        inputPanel.add(new JLabel("Exam Date (dd-MM-yyyy):"));
        inputPanel.add(examDateField);

        JButton addSubjectBtn = new JButton("Add Subject");
        addSubjectBtn.addActionListener(e -> addSubject());
        inputPanel.add(addSubjectBtn);
        inputPanel.add(new JLabel(""));

        // Topic input
        topicNameField = new JTextField();
        topicDueDateField = new JTextField();
        topicDifficultyField = new JTextField();
        topicTimeField = new JTextField();

        inputPanel.add(new JLabel("Topic Name:"));
        inputPanel.add(topicNameField);

        inputPanel.add(new JLabel("Due Date (dd-MM-yyyy):"));
        inputPanel.add(topicDueDateField);

        inputPanel.add(new JLabel("Difficulty Level:"));
        inputPanel.add(topicDifficultyField);

        inputPanel.add(new JLabel("Estimated Time (hours):"));
        inputPanel.add(topicTimeField);

        JButton addTopicBtn = new JButton("Add Topic");
        addTopicBtn.addActionListener(e -> addTopic());
        inputPanel.add(addTopicBtn);

        JButton viewProgressBtn = new JButton("View Progress");
        viewProgressBtn.addActionListener(e -> showProgress());
        inputPanel.add(viewProgressBtn);

        // Topic status update
        updateTopicField = new JTextField();
        statusDropdown = new JComboBox<>(new String[]{"Not Started", "In Progress", "Completed"});

        inputPanel.add(new JLabel("Topic Name to Update:"));
        inputPanel.add(updateTopicField);
        inputPanel.add(new JLabel("Select New Status:"));
        inputPanel.add(statusDropdown);

        JButton updateStatusBtn = new JButton("Update Topic Status");
        updateStatusBtn.addActionListener(e -> updateTopicStatus());
        inputPanel.add(updateStatusBtn);

        JButton generateReportBtn = new JButton("Generate Full Report");
        generateReportBtn.addActionListener(e -> generateReport());
        inputPanel.add(generateReportBtn);

        add(inputPanel, BorderLayout.NORTH);

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private void addSubject() {
        try {
            String name = subjectField.getText().trim();
            Date examDate = new SimpleDateFormat("dd-MM-yyyy").parse(examDateField.getText().trim());
            currentSubject = new Subject(name, examDate);
            user.addSubject(currentSubject);
            outputArea.append("✅ Subject added: " + name + "\n");
        } catch (Exception ex) {
            outputArea.append("❌ Invalid subject data!\n");
        }
    }

    private void addTopic() {
        if (currentSubject == null) {
            outputArea.append("❌ Add/select a subject first.\n");
            return;
        }

        try {
            String topicName = topicNameField.getText().trim();
            Date dueDate = new SimpleDateFormat("dd-MM-yyyy").parse(topicDueDateField.getText().trim());
            String difficulty = topicDifficultyField.getText().trim();
            int estTime = Integer.parseInt(topicTimeField.getText().trim());

            Topic topic = new Topic(topicName, dueDate, difficulty, estTime);
            currentSubject.addTopic(topic);
            outputArea.append("✅ Topic added: " + topicName + " to subject " + currentSubject.getSubjectName() + "\n");
        } catch (Exception ex) {
            outputArea.append("❌ Invalid topic data!\n");
        }
    }

    private void showProgress() {
        outputArea.append("\n📊 Progress Report:\n");
        for (Subject s : user.getSubjects()) {
            outputArea.append("Subject: " + s.getSubjectName() + "\n");
            outputArea.append("Progress: " + s.getSubjectProgress() + "%\n");
            for (Topic t : s.getTopicList()) {
                outputArea.append(" - " + t.getTaskName() + " | Status: " + t.getStatus() + "\n");
            }
            outputArea.append("\n");
        }
    }

    private void updateTopicStatus() {
        if (currentSubject == null) {
            outputArea.append("❌ No subject selected.\n");
            return;
        }

        String topicNameToUpdate = updateTopicField.getText().trim();
        String newStatus = (String) statusDropdown.getSelectedItem();

        List<Topic> topics = currentSubject.getTopicList();
        boolean found = false;

        for (Topic t : topics) {
            if (t.getTaskName().equalsIgnoreCase(topicNameToUpdate)) {
                t.updateStatus(newStatus);
                outputArea.append("✅ Updated status of " + topicNameToUpdate + " to " + newStatus + "\n");
                found = true;
                break;
            }
        }

        if (!found) {
            outputArea.append("❌ Topic not found.\n");
        }
    }

    private void generateReport() {
        outputArea.append("\n📄 Full Study Report:\n");
        for (Subject s : user.getSubjects()) {
            outputArea.append("Subject: " + s.getSubjectName() + "\n");
            for (Topic t : s.getTopicList()) {
                outputArea.append(" - " + t.getTaskName() + " | Status: " + t.getStatus()
                        + " | Due: " + t.getDueDate() + " | Difficulty: " + t.getDifficultyLevel() + "\n");
            }
            outputArea.append("Progress: " + s.getSubjectProgress() + "%\n\n");
        }
    }
}
