import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

public class AdminLogin {
    // declaration of textfields,buttons,labels
    private JTextField username;
    private JLabel usernameLabel;
    private JPasswordField password;
    private JLabel passwordLabel;
    private JButton login;
    private JPanel loginPanel;
    private JButton back;
    private Image img;
    AdminLogin(){
        // Actionlistener for login button
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // fetching the username and password
                String user = username.getText();
                String pass = new String(password.getPassword());
                Connection con = null;
                // Establishing database connection
                try {
                    // Dynamic loading of driver class
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srp","root","MySql@123");
                    PreparedStatement pstm = con.prepareStatement("Select * from auth where username=? and password=?");
                    pstm.setString(1,user);
                    pstm.setString(2,pass);
                    ResultSet rs=pstm.executeQuery();
                    // if a record is present with username and password then the login is successful
                    if(rs.next()){
                        // finding the root component
                        JFrame c = (JFrame) SwingUtilities.getRoot(AdminLogin.this.loginPanel);
                        c.getContentPane().removeAll();
                        c.setContentPane(new AdminHome().getMainPanel());
                        c.revalidate();
                    } else{
                        JOptionPane.showMessageDialog(null,"Incorrect Username or Password");
                        // resetting the username and password textfields
                        username.setText("");
                        password.setText("");
                    }
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }finally {
                    try {
                        // closing the database connection
                        con.close();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });
        // back button actionlistener
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c=(JFrame)SwingUtilities.getRoot(AdminLogin.this.loginPanel);
                c.getContentPane().removeAll();
                c.setContentPane(new Index().getMainPanel());
                c.revalidate();
            }
        });
    }
    // Getter for returning the main Panel
    JPanel adminLoginGetter(){
        return this.loginPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        // reading the image to set the background
        try {
            img = ImageIO.read(new File("src/img.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loginPanel = new JPanel(){
            // overriding the paintComponent method to draw the image
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img,0,0,getWidth(),getHeight(),this);
            }
        };
    }
}
