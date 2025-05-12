package Graphics;

import Vehicles.*;
import system.Agency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutorService;

import static java.lang.Integer.parseInt;

public class TestDialog extends JDialog implements Subject {
    public JTextField getTextInput() {
        return textInput;
    }

    private JTextField textInput;
    private JButton confirm;
    private JButton cancel;

    public TestDialog getThis(){
        return this;
    }
    private final MainFrame frame;
    private int travelToGo;


    /**
     * A dialog window used for testing a vehicle in the system.
     * Displays a panel with a distance input field and a button to initiate the test drive.
     * Upon test drive completion, the dialog is closed and the test drive data is stored in the vehicle object.
     *
     * @param frame    The parent frame of the dialog window.
     * @param v        the vehicle that was clicked when clicking on test drive
     * @param executor
     */
    public TestDialog(MainFrame frame, IVehicle v, Agency a, ExecutorService executor){
        super(frame, v.getVehicleType() + "Test Drive", false);
        this.frame = frame;
        setResizable(false);
        ImageIcon image = new ImageIcon("Pictures\\carLogo.png");
        setIconImage(image.getImage());
        add(KmPanel(v, a, executor));
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    /**
     * Creates a JPanel for entering the travel distance and performing an action.
     * @param v the vehicle object associated with the action
     * @param a the agency object associated with the action
     * @return  the created JPanel for entering the travel distance and performing the action
     */
    private JPanel KmPanel(IVehicle v, Agency a, ExecutorService executor){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        JLabel kmLabel = new JLabel("Insert the travel distance:");
        kmLabel.setHorizontalAlignment(JLabel.CENTER);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        textInput = new JTextField("");
        textInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // ignore event
                }

            }
        });


        textInput.setPreferredSize(new Dimension(30,30));
        confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == confirm){
                    confirm.setEnabled(false);
                    cancel.setEnabled(false);
                    textInput.setEnabled(false);
                    setDefaultCloseOperation(0);
                    if(textInput.getText().equals("")) {
                        new InputErrorOption("Enter Kilometer");
                    }
                    else {
                        executor.submit(
                            new Thread(() -> {
                                synchronized (v) {
                                        setDefaultCloseOperation(0);
                                        if (a.findVehicle(v) == -1) {
                                            frame.errorOptionPane("Vehicle does not exist, cannot complete the test drive");
                                            dispose();
                                        } else {
                                            myAction(v, progressBar, a);
                                        }
                                    }

                            })
                        );
                    }
                }
            }
        });
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == cancel){
                    dispose();
                }
            }
        });

        JPanel panel1 = new JPanel();
        panel1.add(kmLabel);
        panel1.add(textInput);
        panel.add(panel1);

        JPanel panel2 = new JPanel();
        panel.add(progressBar);
        panel2.add(confirm);
        panel2.add(cancel);
        panel.add(panel2);
        return panel;
    }


    /**
     * Performs an action involving a vehicle, progress bar, agency, and main frame.
     * @param v the vehicle object involved in the action
     * @param progressBar the progress bar object to be filled during the action
     * @param a the agency object associated with the action
     */
    public void myAction(IVehicle v, JProgressBar progressBar, Agency a){
        travelToGo = parseInt(textInput.getText());
        fill(travelToGo * 100, progressBar);
        JDialog dialog = frame.updatingDialog("Test Drive Updating");
        dialog.setLocationRelativeTo(getThis());
        dialog.setVisible(true);
        try {
            frame.increaseThreadCount();
            ((StatusDecorator)((ColorDecorator) v).getVehicle()).setStatus("Traveling");
            frame.scrollerPanel();
            Thread.sleep(travelToGo * 100L);
            ((StatusDecorator)((ColorDecorator) v).getVehicle()).setStatus("Available");
            frame.decreaseThreadCount();
            a.setMovement(travelToGo, v);
            dialog.dispose();
            notifyObserver();
            frame.successOptionPane("travel distance updated","Vehicle");
//                   frame.scrollerPanel();
            dispose();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Fills the given progress bar over a specific time.
     * @param givenTime the total time (in milliseconds) to fill the progress bar
     * @param progressBar the progress bar object to be filled
     */
    public void fill(int givenTime, JProgressBar progressBar)
    {
        new Thread(()->{
            setDefaultCloseOperation(0);
            int delay = givenTime / 100;
            Timer timer = new Timer(delay, e -> {
                int value = progressBar.getValue();
                if (value < 100) {
                    progressBar.setValue(value + 1);
                } else {
                    ((Timer) e.getSource()).stop();
                    this.dispose();
                }
            });
            timer.start();
        }).start();
    }

    @Override
    public void notifyObserver() {
        frame.update(travelToGo);
    }
}


