import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class StudentSearch {
    private JPanel mainPanel;
    private JLabel enterRollLabel;
    private JTextField rollNumber;
    private JButton search;
    private JButton backButton;
    private Image img;
    StudentSearch(){
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(StudentSearch.this.mainPanel);
                c.getContentPane().removeAll();
                c.setContentPane(new Index().getMainPanel());
                c.revalidate();
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roll = rollNumber.getText();
                Connection con = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srp","root","MySql@123");
                    PreparedStatement pstm = con.prepareStatement("select * from results where rollNumber=?");
                    pstm.setString(1,roll);
                    ResultSet rs = pstm.executeQuery();
                    if(rs.next()){
                        //con.close();
                        JFrame c = (JFrame) SwingUtilities.getRoot(StudentSearch.this.getMainPanel());
                        c.getContentPane().removeAll();
                        c.setContentPane(new StudentHome(roll).getMainPanel());
                        c.revalidate();
                    }else {
                        pstm = con.prepareStatement("select * from students where rollNumber=?");
                        pstm.setString(1,roll);
                        rs = pstm.executeQuery();
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null,"Result of the given roll number is not present.");
                        }else{
                            JOptionPane.showMessageDialog(null,"Incorrect Roll Number");
                        }
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        try {
            img = ImageIO.read(new File("src/img.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img,0,0,getWidth(),getHeight(),this);
            }
        };
    }
}
