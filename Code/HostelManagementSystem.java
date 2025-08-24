import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class Student {
    private String name;
    private String rollNo;
    private String roomNo;
    private String phoneNumber;
    private String emailAddress;
    private String permanentAddress;

    public Student(String name, String rollNo, String roomNo, String phoneNumber, String emailAddress, String permanentAddress) {
        this.name = name;
        this.rollNo = rollNo;
        this.roomNo = roomNo;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.permanentAddress = permanentAddress;
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }
}

public class HostelManagementSystem extends JFrame implements ActionListener {
    private JTextField nameField, rollNoField, roomNoField, phoneNumberField, emailField, searchNameField;
    private JButton submitButton, showRecordsButton, searchButton;
    private JTextArea recordArea;
    private ArrayList<Student> studentRecords;

    // Define constant for field length
    private static final int FIELD_LENGTH = 20;

    public HostelManagementSystem() {
        setTitle("Hostel Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        inputPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        nameField = new JTextField(FIELD_LENGTH);
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(new JLabel("Roll No:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        rollNoField = new JTextField(FIELD_LENGTH);
        inputPanel.add(rollNoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Room No:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        roomNoField = new JTextField(FIELD_LENGTH);
        inputPanel.add(roomNoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Phone Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        phoneNumberField = new JTextField(FIELD_LENGTH);
        inputPanel.add(phoneNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Email Address:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        emailField = new JTextField(FIELD_LENGTH);
        inputPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        submitButton = new JButton("Submit");
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        inputPanel.add(submitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        inputPanel.add(new JLabel("Search by Name"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        searchNameField = new JTextField(FIELD_LENGTH);
        inputPanel.add(searchNameField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        inputPanel.add(searchButton, gbc);


        JPanel recordsPanel = new JPanel(new BorderLayout());
        recordsPanel.setBackground(Color.WHITE);
        recordArea = new JTextArea(10, 30);
        recordArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recordArea);
        recordsPanel.add(new JLabel("Student Records:"), BorderLayout.NORTH);
        recordsPanel.add(scrollPane, BorderLayout.CENTER);
        showRecordsButton = new JButton("Show Records");
        showRecordsButton.setBackground(Color.ORANGE);
        showRecordsButton.setForeground(Color.WHITE);
        showRecordsButton.addActionListener(this);
        recordsPanel.add(showRecordsButton, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.NORTH);
        add(recordsPanel, BorderLayout.CENTER);

        studentRecords = new ArrayList<>();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            addStudentRecord();
        } else if (e.getSource() == showRecordsButton) {
            displayRecords();
        } else if (e.getSource() == searchButton) {
            searchByname();
        }
    }

    private void addStudentRecord() {
        String name = nameField.getText();
        String rollNo = rollNoField.getText();
        String roomNo = roomNoField.getText();
        String phoneNumber = phoneNumberField.getText();
        String emailAddress = emailField.getText();
        if (!name.isEmpty() && !rollNo.isEmpty() && !roomNo.isEmpty() && !phoneNumber.isEmpty() && !emailAddress.isEmpty()) {
            // Check for duplicate roll numbers
            boolean isDuplicate = studentRecords.stream().anyMatch(student -> student.getRollNo().equals(rollNo));
            if (!isDuplicate) {
                studentRecords.add(new Student(name, rollNo, roomNo, phoneNumber, emailAddress, ""));
                JOptionPane.showMessageDialog(this, "Record added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                nameField.setText("");
                rollNoField.setText("");
                roomNoField.setText("");
                phoneNumberField.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Roll number already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter all details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayRecords() {
        String[] columnNames = {"Name", "Roll No", "Room No", "Phone Number", "Email Address"};
        String[][] rowData = new String[studentRecords.size()][5];

        for (int i = 0; i < studentRecords.size(); i++) {
            Student student = studentRecords.get(i);
            rowData[i][0] = student.getName();
            rowData[i][1] = student.getRollNo();
            rowData[i][2] = student.getRoomNo();
            rowData[i][3] = student.getPhoneNumber();
            rowData[i][4] = student.getEmailAddress();
        }

        JTable table = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(this, scrollPane, "Student Records", JOptionPane.PLAIN_MESSAGE);
    }

    private void searchByname() {
        String searchName = searchNameField.getText();
        if (!searchName.isEmpty()) {
            boolean found = false;
            for (Student student : studentRecords) {
                if (student.getName().equals(searchName)) {
                    JOptionPane.showMessageDialog(this, "Name: " + student.getName() + "\nRoll No: " + student.getRollNo() + "\nRoom No: " + student.getRoomNo() +
                            "\nPhone Number: " + student.getPhoneNumber() + "\nEmail Address: " + student.getEmailAddress(), "Student Details", JOptionPane.PLAIN_MESSAGE);
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Student not found with Name " +searchName , "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a room number to search.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new HostelManagementSystem();
    }
}
