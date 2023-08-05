import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
                // finding the root component
                JFrame c = (JFrame) SwingUtilities.getRoot(AdminLogin.this.loginPanel);
                if(user.equals("admin") && pass.equals("password")){
                    c.getContentPane().removeAll();
                    c.setContentPane(new AdminHome().getMainPanel());
                    c.revalidate();
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Username or Password");
                   // resetting the username and password textfields
                    username.setText("");
                    password.setText("");
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
