import javax.swing.*;

public class Main extends JFrame {
    Main(){
        this.setContentPane(new Index().getMainPanel());
        this.setBounds(200,200,500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        Main m = new Main();
    }
}