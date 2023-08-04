import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentHome {
    private JPanel mainPanel;
    private JLabel courseLabel;
    private JTextField course;
    private JTextField branch;
    private JLabel branchLabel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JLabel genderLabel;
    private JTextField gender;
    private JLabel nameField;
    private JTextField name;
    private JLabel rollLabel;
    private JTextField rollNumber;
    private JLabel fatherLabel;
    private JTextField fatherName;
    private JLabel physicsLabel;
    private JLabel maxMarks;
    private JLabel passMarks;
    private JLabel marksObtained;
    private JLabel mathsLabel;
    private JLabel emLabel;
    private JLabel osLabel;
    private JLabel dbmsLabel;
    private JTextField physics;
    private JTextField maths;
    private JTextField em;
    private JTextField os;
    private JTextField dbms;
    private JLabel physicsMax;
    private JLabel mathsMax;
    private JLabel emMax;
    private JLabel osMax;
    private JLabel dbmsMax;
    private JLabel physicsPass;
    private JLabel mathsPass;
    private JLabel emPass;
    private JLabel osPass;
    private JLabel dbmsPass;
    private JLabel totalLabel;
    private JTextField total;
    private JButton back;
    StudentHome(String roll){
        rollNumber.setText(roll);
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srp","root","MySql@123");
            PreparedStatement pstm = con.prepareStatement("Select * from students inner join results on students.rollNumber=results.rollNumber where results.rollNumber=?");
            pstm.setString(1,roll);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                String nameGet = rs.getString("name");
                String courseGet = rs.getString("course");
                String branchGet = rs.getString("branch");
                String genderGet = rs.getString("gender");
                String fatherGet = rs.getString("fatherName");

                String physicsGet = rs.getString("physics");
                String mathsGet = rs.getString("maths");
                String osGet = rs.getString("os");
                String emGet = rs.getString("em");
                String dbmsGet = rs.getString("dbms");

                name.setText(nameGet);
                course.setText(courseGet);
                branch.setText(branchGet);
                gender.setText(genderGet);
                fatherName.setText(fatherGet);

                physics.setText(physicsGet);
                maths.setText(mathsGet);
                os.setText(osGet);
                em.setText(emGet);
                dbms.setText(dbmsGet);

                int totalMarks = Integer.parseInt(physicsGet)+Integer.parseInt(mathsGet)+Integer.parseInt(osGet)+Integer.parseInt(emGet)+Integer.parseInt(dbmsGet);
                total.setText(Integer.toString(totalMarks));
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame c = (JFrame) SwingUtilities.getRoot(StudentHome.this.mainPanel);
                c.getContentPane().removeAll();
                c.setContentPane(new StudentSearch().getMainPanel());
                c.revalidate();
            }
        });
    }

    JPanel getMainPanel(){
        return this.mainPanel;
    }
}
