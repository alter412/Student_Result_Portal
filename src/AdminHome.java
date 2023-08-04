import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminHome {
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
        addAStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(AdminHome.this.mainPanel);
                c.getContentPane().removeAll();
                c.setContentPane(new AdminHome().getMainPanel());
                c.revalidate();
            }
        });
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
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(AdminHome.this.getMainPanel());
                c.getContentPane().removeAll();
                c.setContentPane(new Index().getMainPanel());
                c.revalidate();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course =  courseName.getSelectedItem().toString();
                String branch = branchName.getSelectedItem().toString();
                String rollNumber = roll.getText();
                String name = nameField.getText();
                String gender = genderOptions.getSelectedItem().toString();
                String fatherName = father.getText();
                Connection con=null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/srp","root","MySql@123");
                    PreparedStatement pstm = con.prepareStatement("Insert into students (course,branch,rollNumber,name,gender,fatherName) values (?,?,?,?,?,?)");
                    pstm.setString(1,course);
                    pstm.setString(2,branch);
                    pstm.setString(3,rollNumber);
                    pstm.setString(4,name);
                    pstm.setString(5,gender);
                    pstm.setString(6,fatherName);

                    pstm.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record added successfully.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Error Occured");
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"Class Not found");
                    throw new RuntimeException(ex);
                }finally {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
    JPanel getMainPanel(){
        return this.mainPanel;
    }
}
