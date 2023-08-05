// using the rs2xml jar file to populate the jtable with ResultSet
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisteredStudents {
    // declaration of buttons,panels,table
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JButton addAStudentButton;
    private JButton addResultButton;
    private JButton registeredStudentsButton;
    private JButton allStudentsResultButton;
    private JButton logoutButton;
    private JTable table1;
    RegisteredStudents(){
        // Establishing database connection
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srp","root","MySql@123");
            PreparedStatement pstm = con.prepareStatement("select * from students");
            ResultSet rs = pstm.executeQuery();
            // populating the JTable with ResultSet using rs2xml utility
            // which sets the data model of the JTable
            table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // closing the connection
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        //Adding ActionListener for respective Buttons
        addAStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(RegisteredStudents.this.mainPanel);
                c.getContentPane().removeAll();
                c.setContentPane(new AdminHome().getMainPanel());
                c.revalidate();
            }
        });
        addResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(RegisteredStudents.this.getMainPanel());
                c.getContentPane().removeAll();
                c.setContentPane(new AddResult().getMainPanel());
                c.revalidate();
            }
        });
        registeredStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(RegisteredStudents.this.getMainPanel());
                c.getContentPane().removeAll();
                c.setContentPane(new RegisteredStudents().getMainPanel());
                c.revalidate();
            }
        });
        allStudentsResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(RegisteredStudents.this.getMainPanel());
                c.getContentPane().removeAll();
                c.setContentPane(new StudentsResult().getMainPanel());
                c.revalidate();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(RegisteredStudents.this.getMainPanel());
                c.getContentPane().removeAll();
                c.setContentPane(new Index().getMainPanel());
                c.revalidate();
            }
        });
    }
    // Getter function to return the main panel
    JPanel getMainPanel(){
        return this.mainPanel;
    }
}
