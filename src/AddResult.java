import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddResult {
    // left panel
    private JPanel leftPanel;
    // add a Student Button
    private JButton addAStudentButton;
    // add result Button
    private JButton addResultButton;
    // All registered students Button
    private JButton registeredStudentsButton;
    // All Students result
    private JButton allStudentsResultButton;
    // Logout Button
    private JButton logoutButton;
    // Main Panel
    private JPanel mainPanel;
    // Roll number TextField
    private JTextField rollNumberField;
    // Physics Marks TextField
    private JTextField physicsField;
    // Maths Marks  textField
    private JTextField mathsField;
    // em Marks textField
    private JTextField emField;
    // os Marks textField
    private JTextField osField;
    // dbms Marks TextField
    private JTextField dbmsField;
    // Label for respective textFields
    private JLabel roll;
    private JLabel physicsLabel;
    private JLabel mathsLabel;
    private JLabel emLabel;
    private JLabel osLabel;
    private JLabel dbmsLabel;
    // Save button to save result in Database
    private JButton save;
    AddResult(){
        //ActionListener for Add a Student Button
        addAStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Getting Root Component
                JFrame c = (JFrame) SwingUtilities.getRoot(AddResult.this.mainPanel);
                // Removing all components from root component
                c.getContentPane().removeAll();
                // Setting the contentPane to AdminHome
                c.setContentPane(new AdminHome().getMainPanel());
                // Revalidate the frame so that the content got updated
                c.revalidate();
            }
        });
        // Action Listener for Add Result Button
        addResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Getting Root Component
                JFrame c = (JFrame) SwingUtilities.getRoot(AddResult.this.getMainPanel());
                // Removing all components from root component
                c.getContentPane().removeAll();
                // Setting the contentPane to AddResult
                c.setContentPane(new AddResult().getMainPanel());
                // Revalidate the frame so that the content got updated
                c.revalidate();
            }
        });
        // Action Listener for Registered Students  Button
        registeredStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Getting Root Component
                JFrame c = (JFrame) SwingUtilities.getRoot(AddResult.this.getMainPanel());
                // Removing all components from root component
                c.getContentPane().removeAll();
                // Setting the contentPane to Registered Student
                c.setContentPane(new RegisteredStudents().getMainPanel());
                // Revalidate the frame so that the content got updated
                c.revalidate();
            }
        });
        // Action Listener for All Students Result Button
        allStudentsResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Getting Root Component
                JFrame c = (JFrame) SwingUtilities.getRoot(AddResult.this.getMainPanel());
                // Removing all components from root component
                c.getContentPane().removeAll();
                // Setting the contentPane to Students Result
                c.setContentPane(new StudentsResult().getMainPanel());
                // Revalidate the frame so that the content got updated
                c.revalidate();
            }
        });
        // Action Listener for LogOut Button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Getting Root Component
                JFrame c = (JFrame) SwingUtilities.getRoot(AddResult.this.getMainPanel());
                // Removing all components from root component
                c.getContentPane().removeAll();
                // Setting the contentPane to Index
                c.setContentPane(new Index().getMainPanel());
                // Revalidate the frame so that the content got updated
                c.revalidate();
            }
        });
        // Action Listener for Save Button
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fetching the data from the respective textfields
                String rollNumber = rollNumberField.getText();
                String physics = physicsField.getText();
                String maths = mathsField.getText();
                String em = emField.getText();
                String os = osField.getText();
                String dbms = dbmsField.getText();
                // Setting up JDBC for dataBase Connection
                Connection con=null;
                try {
                    // Dynamic Loading of Driver Class
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    // Setting up the connection using the URl , User , Password
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srp","root","MySql@123");
                    // Preparing the SQL Query
                    PreparedStatement pstm = con.prepareStatement("Select * from students where rollNumber=?");
                   // Setting the parameter value
                    pstm.setString(1,rollNumber);
                    // Executing the Query which returns ResultSet
                    ResultSet rs = pstm.executeQuery();
                    // If there is a record then enter the if block
                    if(rs.next()){
                        // Preparing SQl for inserting record in the database's results table
                        pstm = con.prepareStatement("Insert into results (rollNumber,physics,maths,em,os,dbms) values(?,?,?,?,?,?)");
                        // Setting the parameters values
                        pstm.setString(1,rollNumber);
                        pstm.setString(2,physics);
                        pstm.setString(3,maths);
                        pstm.setString(4,em);
                        pstm.setString(5,os);
                        pstm.setString(6,dbms);
                        // Executing the Update Statement
                        pstm.executeUpdate();
                        // Conformation Message
                        JOptionPane.showMessageDialog(null,"Result Added Successfully");

                    }else{
                        // Roll not registered yet
                        JOptionPane.showMessageDialog(null,"Roll Number not registered!!");
                    }

                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }finally {
                    try {
                        // closing the connection to free the resource
                        con.close();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });
    }
    // Getter Function to get the main panel of the class
    JPanel getMainPanel(){
        return this.mainPanel;
    }
}
