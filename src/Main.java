import javax.swing.*;
//main class the entry point of the application
// add keys
// last try
public class Main extends JFrame {
    Main(){
        // Setting the title of application
        super("Student Result Portal");
        // sets the content to Index
        this.setContentPane(new Index().getMainPanel());
        // set the bounds
        this.setBounds(200,200,500,500);
        // default close setting
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // make it visible
        this.setVisible(true);
    }
    public static void main(String[] args) {
        Main m = new Main();
    }
}