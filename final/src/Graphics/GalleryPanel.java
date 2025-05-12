package Graphics;

import Vehicles.ColorDecorator;
import Vehicles.StatusDecorator;
import Vehicles.IVehicle;
import system.Agency;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GalleryPanel extends JPanel {

    private final MainFrame main;
    private JButton lastSelectedButton;
    private int buttonsArrSize;
    private IVehicle lastSelected;
    private JButton[] buttonsArr;
    private final Agency a;
    /**
     * Creates a new JPanel to hold the gallery of buttons for each vehicle in the provided Agency.
     * @param a the Agency whose vehicles will be displayed in the gallery.
     */
    public GalleryPanel(Agency a, MainFrame frame){
        this.a = a;
        main = frame;
        setBackground(Color.GRAY);
        add(galleryPanel());
    }

    /**
     This method creates a gallery panel that displays all the vehicles in the given Agency.
     @return a JPanel object that displays all the vehicles in the Agency
     */
    public JPanel galleryPanel(){

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);

        panel.setLayout(new GridLayout(a.getSize(),1));
        for (IVehicle vehicle : a.getVehicles()) {
            JButton button = imageButton(vehicle);
            addToButtons(button);
            JPanel panelWithButton = new JPanel();
            panelWithButton.setBackground(Color.GRAY);
            panelWithButton.add(button);
            panel.add(panelWithButton);
        }
        panel.revalidate();
        panel.repaint();
        panel.setLayout(new GridLayout(a.getSize(),1));
        return panel;
    }
    /**
     * Returns the last selected vehicle.
     * @return the last selected vehicle.
     */
    public IVehicle getLastSelected() {
        return lastSelected;
    }
    /**
     * Returns the last selected button.
     * @return the last selected button.
     */
    public JButton getLastSelectedButton() {
        return lastSelectedButton;
    }
    /**
     * Returns the MainFrame of the application.
     * @return the MainFrame of the application.
     */

    /**
     * Creates and returns a JButton containing an image of the provided Vehicle.
     * @param v the Vehicle whose image will be displayed in the button.
     * @return a JButton containing an image of the provided Vehicle.
     */
    private JButton imageButton(IVehicle v) {
        BufferedImage buttonIcon;
        try {
            buttonIcon = ImageIO.read(new File(v.getVehicleImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image dimg = buttonIcon.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon myImg = new ImageIcon(dimg);
        JButton button = new JButton(myImg);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        Border white = new LineBorder(((ColorDecorator) v).getColor(), 3, true);
        button.setBorder(white);
        button.setToolTipText("<html>" + ((ColorDecorator) v).getVehicle() + "</html>");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == button){
                    selectedButton(button, v);
                    lastSelected = v;
                    lastSelectedButton = button;
//                    button.setToolTipText("<html>" + v + "</html>");
                    main.getBuyButton().setEnabled(true);
                    main.getTestButton().setEnabled(true);
                }
            }
        });
        return button;
    }

    /**
     Adds the given button to the array of buttons.
     @param button the button to be added to the array
     */
    public void addToButtons(JButton button){
        buttonsArrSize += 1;
        JButton[] arr = new JButton[buttonsArrSize];
        if (buttonsArr != null){
            if (buttonsArrSize - 1 >= 0) System.arraycopy(buttonsArr, 0, arr, 0, buttonsArrSize - 1);
        }
        arr[buttonsArrSize - 1] = button;
        buttonsArr = arr;
    }
    /**
     Finds the index of a JButton in the buttonsArr array.
     @param button The button to be found.
     @return The index of the button in the array, or -1 if the button is not found or is null.
     */
    public int findButton(JButton button){
        if (button == null)
            return -1;
        for (int i = 0; i < buttonsArrSize; i++) {
            if (((button).equals(buttonsArr[i]))) {
                return i;
            }
        }
        return -1;
    }
    /**
     Deletes a button from the array of buttons and updates the size of the array accordingly.
     @param button the button to be deleted
     */
    public void deleteButtons (JButton button) {
        int index = findButton(button);
        if(index != -1)
        {
            buttonsArrSize -= 1;
            JButton[] newButtonArr = new JButton[buttonsArrSize];
            if (index >= 0) System.arraycopy(buttonsArr, 0, newButtonArr, 0, index);
            if (buttonsArrSize - index  >= 0)
                System.arraycopy(buttonsArr, index + 1, newButtonArr, index, buttonsArrSize - index);
            buttonsArr = newButtonArr;
        }
    }
    /**
     Sets the border of the selected button to green and the borders of all other buttons to white.
     @param button The button that has been selected.
     */
    public void selectedButton(JButton button, IVehicle v){
        Border selectedCarColor = new LineBorder(((ColorDecorator) v).getColor(), 6, true);
        button.setBorder(selectedCarColor);
        for (int i = 0; i < buttonsArrSize; i++) {
            if(buttonsArr[i]!=button){
                buttonsArr[i].setBorder(new LineBorder(((ColorDecorator)( a.getVehicles()[i])).getColor(), 3, true));
            }
        }
    }
}
