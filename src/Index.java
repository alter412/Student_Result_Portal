import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Index{
    private JButton student;
    private JButton admin;
    private JPanel mainPanel;
    private JLabel title;
    private Image img;
    private Image img2;

    Index(){

        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(Index.this.mainPanel);
                c.getContentPane().removeAll();
                c.setContentPane(new AdminLogin().adminLoginGetter());
                c.revalidate();
            }
        });
        student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame)SwingUtilities.getRoot(Index.this.mainPanel);
                c.getContentPane().removeAll();
                c.setContentPane(new StudentSearch().getMainPanel());
                c.revalidate();
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
                g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);

            }
        };
    }
}
