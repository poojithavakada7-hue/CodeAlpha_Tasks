import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentGradeTracker extends JFrame implements ActionListener {

    JTextField nameField, marksField;
    JTextArea outputArea;
    JButton addButton, reportButton;

    ArrayList<String> names = new ArrayList<>();
    ArrayList<Double> marks = new ArrayList<>();

    StudentGradeTracker() {

        setTitle("Student Grade Tracker");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        getContentPane().setBackground(new Color(230, 242, 255));

        JLabel title = new JLabel("Student Grade Tracker");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(0, 102, 204));
        add(title);

        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(nameLabel);

        nameField = new JTextField(20);
        nameField.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(nameField);

        JLabel marksLabel = new JLabel("Marks:");
        marksLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(marksLabel);

        marksField = new JTextField(20);
        marksField.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(marksField);

        addButton = new JButton("Add Student");
        reportButton = new JButton("Show Report");

        addButton.setBackground(new Color(40, 167, 69));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 15));

        reportButton.setBackground(new Color(0, 123, 255));
        reportButton.setForeground(Color.WHITE);
        reportButton.setFont(new Font("Arial", Font.BOLD, 15));

        add(addButton);
        add(reportButton);

        outputArea = new JTextArea(18, 45);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        outputArea.setBackground(new Color(248, 249, 250));

        add(new JScrollPane(outputArea));

        addButton.addActionListener(this);
        reportButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {

            String name = nameField.getText().trim();
            String markText = marksField.getText().trim();

            if (name.isEmpty() || markText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter both Name and Marks.");
                return;
            }

            try {
                double mark = Double.parseDouble(markText);

                if (mark < 0 || mark > 100) {
                    JOptionPane.showMessageDialog(this,
                            "Marks must be between 0 and 100.");
                    return;
                }

                names.add(name);
                marks.add(mark);

                JOptionPane.showMessageDialog(this,
                        "Student Added Successfully!");

                nameField.setText("");
                marksField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter valid numeric marks.");
            }
        }

        if (e.getSource() == reportButton) {

            if (names.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No student data available.");
                return;
            }

            outputArea.setText("");

            double total = 0;
            double highest = marks.get(0);
            double lowest = marks.get(0);

            outputArea.append("====== Student Report ======\n\n");

            for (int i = 0; i < names.size(); i++) {

                outputArea.append("Name : " + names.get(i)
                        + "\tMarks : " + marks.get(i) + "\n");

                total += marks.get(i);

                if (marks.get(i) > highest)
                    highest = marks.get(i);

                if (marks.get(i) < lowest)
                    lowest = marks.get(i);
            }

            double average = total / marks.size();

            outputArea.append("\n------------------------------");
            outputArea.append("\nAverage Marks : " + average);
            outputArea.append("\nHighest Marks : " + highest);
            outputArea.append("\nLowest Marks  : " + lowest);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentGradeTracker());
    }
}