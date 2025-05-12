package Graphics;

import system.Agency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlagDialog extends JDialog{
    JComboBox<Object> flag;


    String[] flagLst = {"Israel", "Germany", "USA", "Italy", "Greece", "Somalia", "Pirates"};
    ImageIcon[] flagIconList = {
            new ImageIcon("Pictures\\flags\\israel.png"),
            new ImageIcon("Pictures\\flags\\germany.png"),
            new ImageIcon("Pictures\\flags\\usa.png"),
            new ImageIcon("Pictures\\flags\\italy.png"),
            new ImageIcon("Pictures\\flags\\greece.png"),
            new ImageIcon("Pictures\\flags\\somalia.png"),
            new ImageIcon("Pictures\\flags\\pirates.png"),
    };

    /**
     A dialog box for selecting a flag from a dropdown list.
     @param a The agency object to be used for flag selection.
     @param frame The MainFrame object to be used as the parent of the dialog box.
     */
    FlagDialog(Agency a, MainFrame frame){
        super(frame, "Test Drive",false);
        flag = new JComboBox<Object>();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/2-this.getSize().width/2,screenSize.height/2-this.getSize().height/2);
        JPanel flagPanel = questionDropImagePanel(flag);
        JButton cancel = cancelButton();
        JButton confirm = confirmButton(a);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancel);
        buttonPanel.add(confirm);

        setTitle("Flag Choice");

        add(flagPanel,BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();

    }

    /**
     Creates and returns a new JButton object with "Cancel" text and an action listener that disposes the dialog
     when the button is clicked.
     @return JButton object with "Cancel" text and an action listener
     */
    private JButton cancelButton() {
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cancel) {
                    dispose();
                }
            }
        });
        return cancel;
    }
    /**
     Returns a JButton object that confirms the user's flag choice when clicked.
     @param a an Agency object representing the car rental agency
     @return a JButton object with an ActionListener that changes all vehicle's sea flags to the selected flag and disposes of the FlagDialog window
     */
    private JButton confirmButton(Agency a){
        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == confirm){
                    new Thread(() -> {
                        synchronized (a.getResetLock()){
                            dispose();
                            a.changeAllSeaFlags(flag.getSelectedItem().toString());
                    }
                    }).start();
                }
            }
        });
        return confirm;

    }
    /**
     * Creates a JPanel with a label and a JComboBox that displays a list of image flags.
     * @param cb The JComboBox to display the list of image flags.
     * @return A JPanel containing a label and the given JComboBox.
     */
    public JPanel questionDropImagePanel(JComboBox cb){
        JPanel panel = new JPanel();
        cb.setPreferredSize(new Dimension(75,40));
        cb.setModel(loadImages());
        getContentPane().add(cb);

        JLabel label = new JLabel("Choose flag");
        label.setPreferredSize(new Dimension(100,30));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(new Color(0x22AF44));
        label.setSize(600,30);


        panel.setBounds(0,0,250,250);
        panel.add(label);
        panel.add(cb);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        return panel;
    }
    /**
     * Loads a list of flag icons into a DefaultComboBoxModel object.
     * Each icon is associated with a flag name.
     * @return the loaded DefaultComboBoxModel object
     */
    private DefaultComboBoxModel<Icon> loadImages(){
        DefaultComboBoxModel<Icon> dm = new DefaultComboBoxModel<Icon>();
        for (int i = 0; i < flagLst.length; i++) {
            flagIconList[i].setDescription(flagLst[i]);
            dm.addElement(flagIconList[i]);
        }
        return dm;
    }
}
