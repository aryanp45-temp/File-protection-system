import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LoginPanel extends JFrame {
    LoginPanel() {
        setSize(600,300);
        setTitle("File Protection System");
        setLocation(610,190);
        // setResizable(false);
        // Designing Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);

        JLabel userLabel= new JLabel("Username: ");
        userLabel.setBounds(50,30,100,30);
        
        JLabel warningJLabel = new JLabel("Warning if Password is Incorrect!!");
        warningJLabel.setBounds(150, 100, 400, 15);
        warningJLabel.setForeground(Color.RED);

        JTextField userTextField = new JTextField();
        userTextField.setBounds(150,30,150,30);

        JLabel passLabel= new JLabel("Password: ");
        passLabel.setBounds(50,70,100,30);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150,70,150,30);

        JButton logiButton = new JButton(new ImageIcon("Login.jpg"));
        logiButton.setBounds(90, 120, 90, 30);
        logiButton.setBorder(new RoundedBorder(20));

        JButton forgotPassButton = new JButton("Forgot Password??");
        forgotPassButton.setBounds(350, 150, 180, 25);
        forgotPassButton.setBorder(new RoundedBorder(20));

        JButton createAccButton = new JButton("Create Account");
        createAccButton.setBounds(40, 180, 150, 25);
        createAccButton.setBorder(new RoundedBorder(20));

        loginPanel.add(userLabel);
        loginPanel.add(userTextField);
        loginPanel.add(passLabel);
        loginPanel.add(passwordField);
        loginPanel.add(logiButton);
        loginPanel.add(forgotPassButton);
        loginPanel.add(createAccButton);
        loginPanel.add(warningJLabel);
        add(loginPanel);
    }

    public static void main(String[] args) {
        LoginPanel a= new LoginPanel();
        a.setVisible(true);
        a.setDefaultCloseOperation(3);
        // m.setDefaultCloseOperation(3);

    }

}

 class RoundedBorder implements Border {

    private int radius;
    RoundedBorder(int radius) {
        this.radius = radius;
    }
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}
