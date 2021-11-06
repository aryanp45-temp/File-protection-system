import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;


class Main extends JFrame {
    Main() {
        setTitle("File Protection System");
        setSize(600, 600);
        setLocation(610, 190);
        setResizable(false);
         //Designing Encryption Panel
        JPanel enPanel = new JPanel();
        enPanel.setLayout(null);

        JLabel fileLabel = new JLabel("File: ");
        fileLabel.setBounds(100, 100, 100, 20);

        JButton openBut = new JButton("Choose File");
        openBut.setBounds(200, 100, 120, 30);
        openBut.addActionListener(new EventsOnEn());

        JLabel keyLabel = new JLabel("Key: ");
        keyLabel.setBounds(100, 200, 100, 20);

        JTextField keyField = new JTextField(20);
        keyField.setBounds(200, 200, 120, 30);

        JButton enButton = new JButton("Encrypt File");
        enButton.setBounds(150, 250, 130, 35);
        enButton.addActionListener(new EventsOnEn());

        enPanel.add(fileLabel);
        enPanel.add(keyLabel);
        enPanel.add(openBut);
        enPanel.add(keyField);
        enPanel.add(enButton);
        add(enPanel);
    }

    public static void main(String args[]) {
        Main m = new Main();
        m.setDefaultCloseOperation(3);

        m.setVisible(true);

    }

}

class EventsOnEn implements ActionListener {
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "Choose File") {
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int r = j.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                System.out.println(j.getSelectedFile().getAbsolutePath());
            } else {
                System.out.println("the user cancelled the operation");
            }
        } else if (e.getActionCommand() == "Encrypt File") {
            JOptionPane.showMessageDialog(null, "Logic to encrypt", "Message Box", JOptionPane.INFORMATION_MESSAGE );
            
        }
    }
}