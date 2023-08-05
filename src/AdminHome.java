import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminHome {
    // Variable declarations for buttons, panels, textfields, different components
    private JPanel mainPanel;
    private JButton addAStudentButton;
    private JButton addResultButton;
    private JButton registeredStudentsButton;
    private JButton allStudentsResultButton;
    private JButton logoutButton;
    private JComboBox courseName;
    private JComboBox branchName;
    private JTextField nameField;
    private JTextField roll;
    private JComboBox genderOptions;
    private JTextField father;
    private JButton saveButton;
    private JPanel leftPanel;

    AdminHome(){
        //ActionListener  add a Student Button
        addAStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(AdminHome.this.mainPanel);
                c.getContentPane().removeAll();
                c.setContentPane(new AdminHome().getMainPanel());
                c.revalidate();
            }
        });
        //Adding ActionListener for respective Buttons
        addResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(AdminHome.this.getMainPanel());
                c.getContentPane().removeAll();
                c.setContentPane(new AddResult().getMainPanel());
                c.revalidate();
            }
        });
        registeredStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(AdminHome.this.getMainPanel());
                c.getContentPane().removeAll();
                c.setContentPane(new RegisteredStudents().getMainPanel());
                c.revalidate();
            }
        });
        allStudentsResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(AdminHome.this.getMainPanel());
                c.getContentPane().removeAll();
                c.setContentPane(new StudentsResult().getMainPanel());
                c.revalidate();
            }
        });
        // LogOut Button ActionListener
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(AdminHome.this.getMainPanel());
                c.getContentPane().removeAll();
                c.setContentPane(new Index().getMainPanel());
                c.revalidate();
            }
        });
        // Save Button ActionListener
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fetching the filled data from the respective comboboxs,textfields
                String course =  courseName.getSelectedItem().toString();
                String branch = branchName.getSelectedItem().toString();
                String rollNumber = roll.getText();
                String name = nameField.getText();
                String gender = genderOptions.getSelectedItem().toString();
                String fatherName = father.getText();
                Connection con=null;
                try {
                    // Setting DataBase Connection
                    // Dynamic Loading of Driver Class
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    // Setting up the Connection using the URL, User, Password
                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/srp","root","MySql@123");
                    // Preparing SQL statement for inserting record in the students table
                    PreparedStatement pstm = con.prepareStatement("Insert into students (course,branch,rollNumber,name,gender,fatherName) values (?,?,?,?,?,?)");
                    // Setting the parameter Values
                    pstm.setString(1,course);
                    pstm.setString(2,branch);
                    pstm.setString(3,rollNumber);
                    pstm.setString(4,name);
                    pstm.setString(5,gender);
                    pstm.setString(6,fatherName);
                    // executing the update query
                    pstm.executeUpdate();
                    // Conformation message
                    JOptionPane.showMessageDialog(null,"Record added successfully.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Error Occured");
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"Class Not found");
                    throw new RuntimeException(ex);
                }finally {
                    try {
                        // closing the connection to free the resources
                        con.close();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
    // Getter function to return the main panel
    JPanel getMainPanel(){
        return this.mainPanel;
    }
}
