import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class AdminLogin {
    private JTextField username;
    private JLabel usernameLabel;
    private JPasswordField password;
    private JLabel passwordLabel;
    private JButton login;
    private JPanel loginPanel;
    private JButton back;
    private Image img;
    AdminLogin(){
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = new String(password.getPassword());
                JFrame c = (JFrame) SwingUtilities.getRoot(AdminLogin.this.loginPanel);
                if(user.equals("admin") && pass.equals("password")){
                    c.getContentPane().removeAll();
                    c.setContentPane(new AdminHome().getMainPanel());
                    c.revalidate();
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Username or Password");
                    username.setText("");
                    password.setText("");
                }



            }
        });
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
    JPanel adminLoginGetter(){
        return this.loginPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        try {
            img = ImageIO.read(new File("src/img.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loginPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img,0,0,getWidth(),getHeight(),this);
            }
        };
    }
}
