import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

class Main extends JFrame {
    Main() {
        setTitle("File Protection System");
        setSize(600, 600);
        setLocation(610, 190);
        setResizable(false);
        // Designing Encryption Panel
        JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP);
        JPanel enPanel = new JPanel();
        enPanel.setLayout(null);

        JButton enButton = new JButton("Encrypt File");
        enButton.setBounds(150, 250, 130, 35);
        enButton.addActionListener(new EventsOnEn());

        enPanel.add(new FileLabel());
        enPanel.add(new KeyLabel());
        enPanel.add(new ChooseFileButton());
        enPanel.add(new KeyTextField());
        enPanel.add(enButton);

        //Designing Decryption Panel
        JPanel dePanel = new JPanel();
        dePanel.setLayout(null);

        JButton deButton = new JButton("Decrypt File");
        deButton.setBounds(150, 250, 130, 35);
        deButton.addActionListener(new EventsOnEn());

        dePanel.add(new FileLabel());
        dePanel.add(new ChooseFileButton());
        dePanel.add(new KeyLabel());
        dePanel.add(new KeyTextField());
        dePanel.add(deButton);





        //Designing Backup Panel
        JPanel bePanel = new JPanel();
        bePanel.setLayout(null);
        


    
        pane.addTab("Encryption", enPanel);
        pane.addTab("Decryption", dePanel);
        pane.addTab("Backup Files", bePanel);
        add(pane);
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
            //Logic to encrypt file
            JOptionPane.showMessageDialog(null, "Logic to encrypt", "Message Box", JOptionPane.INFORMATION_MESSAGE);
        }else if(e.getActionCommand() == "Decrypt File"){
            //Logic to Decrypt File
            JOptionPane.showMessageDialog(null, "Logic to Decrypt", "Message Box", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

class ChooseFileButton extends JButton {
    ChooseFileButton(){
        super("Choose File");
        setBounds(200, 100, 120, 30);
        addActionListener(new EventsOnEn());

    }
}

class FileLabel extends JLabel{
    FileLabel(){
        super("File: ");
        setBounds(100, 100, 100, 20);
    }
}

class KeyLabel extends JLabel{
    KeyLabel(){
        super("Key: ");
        setBounds(100, 200, 100, 20);
    }
}

class KeyTextField extends JTextField{
    KeyTextField(){
        super(20);
        setBounds(200, 200, 120, 30);
    }
}