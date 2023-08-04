import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddResult {
    private JPanel leftPanel;
    private JButton addAStudentButton;
    private JButton addResultButton;
    private JButton registeredStudentsButton;
    private JButton allStudentsResultButton;
    private JButton logoutButton;
    private JPanel mainPanel;
    private JTextField rollNumberField;
    private JTextField physicsField;
    private JTextField mathsField;
    private JTextField emField;
    private JTextField osField;
    private JTextField dbmsField;
    private JLabel roll;
    private JLabel physicsLabel;
    private JLabel mathsLabel;
    private JLabel emLabel;
    private JLabel osLabel;
    private JLabel dbmsLabel;
    private JButton save;
    AddResult(){
        addAStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(AddResult.this.mainPanel);
                c.getContentPane().removeAll();
                c.setContentPane(new AdminHome().getMainPanel());
                c.revalidate();
            }
        });
        addResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(AddResult.this.getMainPanel());
                c.getContentPane().removeAll();
                c.setContentPane(new AddResult().getMainPanel());
                c.revalidate();
            }
        });
        registeredStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(AddResult.this.getMainPanel());
                c.getContentPane().removeAll();
                c.setContentPane(new RegisteredStudents().getMainPanel());
                c.revalidate();
            }
        });
        allStudentsResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(AddResult.this.getMainPanel());
                c.getContentPane().removeAll();
                c.setContentPane(new StudentsResult().getMainPanel());
                c.revalidate();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(AddResult.this.getMainPanel());
                c.getContentPane().removeAll();
                c.setContentPane(new Index().getMainPanel());
                c.revalidate();
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rollNumber = rollNumberField.getText();
                String physics = physicsField.getText();
                String maths = mathsField.getText();
                String em = emField.getText();
                String os = osField.getText();
                String dbms = dbmsField.getText();
                Connection con=null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srp","root","MySql@123");
                    PreparedStatement pstm = con.prepareStatement("Select * from students where rollNumber=?");
                    pstm.setString(1,rollNumber);
                    ResultSet rs = pstm.executeQuery();
                    if(rs.next()){
                        pstm = con.prepareStatement("Insert into results (rollNumber,physics,maths,em,os,dbms) values(?,?,?,?,?,?)");
                        pstm.setString(1,rollNumber);
                        pstm.setString(2,physics);
                        pstm.setString(3,maths);
                        pstm.setString(4,em);
                        pstm.setString(5,os);
                        pstm.setString(6,dbms);

                        pstm.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Result Added Successfully");

                    }else{
                        JOptionPane.showMessageDialog(null,"Roll Number not registered!!");
                    }

                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
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