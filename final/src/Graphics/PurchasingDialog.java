package Graphics;

import Vehicles.ColorDecorator;
import Vehicles.StatusDecorator;
import Vehicles.IVehicle;
import system.Agency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchasingDialog extends JDialog{

    private final MainFrame frame;
    /**
     * Constructs a PurchasingDialog object.
     * @param frame the main frame object associated with the dialog
     * @param v the vehicle object associated with the purchase process
     * @param a the agency object associated with the purchase process
     * @param vButton the button object associated with the vehicle
     */
    public PurchasingDialog(MainFrame frame, IVehicle v, Agency a, JButton vButton) {
        super(frame, "Purchase process", false);
        this.frame = frame;
        add(askPanel(v, a, vButton));
        pack();
        //**
        setDefaultCloseOperation(0);
        //**
        setLocationRelativeTo(null);
        setVisible(true);

    }

    /**
     * Creates a JPanel for asking confirmation to buy a vehicle.
     * @param v the vehicle object associated with the confirmation
     * @param a the agency object associated with the confirmation
     * @param vButton the button object associated with the vehicle
     * @return the created JPanel for asking confirmation to buy the vehicle
     */
    public JPanel askPanel(IVehicle v, Agency a, JButton vButton){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));

        JLabel askLabel = new JLabel("Are you sure you want to buy this vehicle?");
        askLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(askLabel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(cancelButton(v));
        buttonsPanel.add(confirmButton(a, v, vButton));
        panel.add(buttonsPanel);
        return panel;
    }
    /**
     * Creates a cancel button for canceling the buying process of a vehicle.
     * @param v the vehicle object associated with the cancel button
     * @return the created cancel button
     */
    private JButton cancelButton(IVehicle v) {
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cancel) {
                    synchronized (v){
                        ((StatusDecorator)((ColorDecorator) v).getVehicle()).setStatus("Available");
                        frame.scrollerPanel();
                        dispose();
                    }
                }
            }
        });
        return cancel;
    }

    /**
     * Creates a confirm button for confirming the purchase of a vehicle.
     * @param a the agency object associated with the confirm button
     * @param v the vehicle object associated with the confirm button
     * @param vButton the button object associated with the vehicle
     * @return the created confirm button
     */
    public JButton confirmButton(Agency a, IVehicle v, JButton vButton){
        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == confirm){
                    try {
                        ((StatusDecorator)((ColorDecorator) v).getVehicle()).setStatus("Purchasing");
                        frame.scrollerPanel();
                        a.deleteVehicle(v, vButton);

                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    dispose();
                }
            }
        });
        return confirm;

    }
}


