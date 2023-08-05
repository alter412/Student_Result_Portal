import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class StudentSearch {
    // Declaration of the panels, buttons, textfields
    private JPanel mainPanel;
    private JLabel enterRollLabel;
    private JTextField rollNumber;
    private JButton search;
    private JButton backButton;
    private Image img;
    StudentSearch(){
        // ActionListener for back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(StudentSearch.this.mainPanel);
                c.getContentPane().removeAll();
                c.setContentPane(new Index().getMainPanel());
                c.revalidate();
            }
        });
        // ActionListener for search button
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // fetching the entered roll number
                String roll = rollNumber.getText();
                // Establishing the database connection
                Connection con = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srp","root","MySql@123");
                    // If the roll number is present in results table thrn it is valid otherwise it is invalid
                    PreparedStatement pstm = con.prepareStatement("select * from results where rollNumber=?");
                    pstm.setString(1,roll);
                    ResultSet rs = pstm.executeQuery();
                    if(rs.next()){
                        JFrame c = (JFrame) SwingUtilities.getRoot(StudentSearch.this.getMainPanel());
                        c.getContentPane().removeAll();
                        // Sending the roll number to StudentHome through parametrised constructor
                        c.setContentPane(new StudentHome(roll).getMainPanel());
                        c.revalidate();
                    }else {
                        // If roll number is present in students table but not in results then result not registered
                        // message displayed
                        pstm = con.prepareStatement("select * from students where rollNumber=?");
                        pstm.setString(1,roll);
                        rs = pstm.executeQuery();
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null,"Result of the given roll number is not present.");
                        }else{
                            // Else incorrect roll number entered
                            JOptionPane.showMessageDialog(null,"Incorrect Roll Number");
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }finally {
                    // closing the database connection
                    try {
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        try {
            // Reading the img to set the background
            img = ImageIO.read(new File("src/img.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPanel = new JPanel(){
            // overriding the paintComponent method to draw the image
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img,0,0,getWidth(),getHeight(),this);
            }
        };
    }
}
