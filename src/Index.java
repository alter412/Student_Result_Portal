import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Index{
    // Student Button
    private JButton student;
    // Admin Button
    private JButton admin;
    // Main Panel
    private JPanel mainPanel;
    // Title of thr panel
    private JLabel title;
    // background image
    private Image img;

    Index(){
        // ActionListener for Admin Button
        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Getting the Root Component
                JFrame c = (JFrame) SwingUtilities.getRoot(Index.this.mainPanel);
                // Clearing the frame , removing all the components
                c.getContentPane().removeAll();
                // Setting the contentpane to AdminLogin
                c.setContentPane(new AdminLogin().adminLoginGetter());
                // revalidate the frame to update the new content
                c.revalidate();
            }
        });
        // Student Button ActionListener
        student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Getting the root Component
                JFrame c = (JFrame)SwingUtilities.getRoot(Index.this.mainPanel);
                // Clearing the frame, removing all the components
                c.getContentPane().removeAll();
                // Setting the contentPane to StudentSearch
                c.setContentPane(new StudentSearch().getMainPanel());
                // revalidate the frame to update the new content
                c.revalidate();
            }
        });
    }
    // Getter function to get the mainPanel
    JPanel getMainPanel(){
        return this.mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        try {
            // reading the image from the location
            img = ImageIO.read(new File("src/img.png"));
        } catch (IOException e) {
            // handing the exception
            throw new RuntimeException(e);
        }
        // Overriding the paintComponent method to display the image as background
        mainPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);

            }
        };
    }
}
