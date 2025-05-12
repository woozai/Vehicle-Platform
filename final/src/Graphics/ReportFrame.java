package Graphics;

import Vehicles.ColorDecorator;
import Vehicles.StatusDecorator;
import Vehicles.IVehicle;
import system.Agency;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ReportFrame extends JFrame{
    JPanel scrollPanel;

    public JFrame getThis(){
        return this;
    }
    /**
     * Constructs a ReportFrame object.
     * @param a the agency object associated with the report
     */
    public ReportFrame(Agency a){
        super("Report");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon image = new ImageIcon("Pictures\\carLogo.png");
        setIconImage(image.getImage());
        setResizable(false);
        setLocation(screenSize.width/2-175,screenSize.height/2-200);
        setLayout(new BorderLayout());
        setIconImage(image.getImage());
        setSize(350,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        scrollPanel = new JPanel();
        allImagesPanelScroller(a);
    }
    /**
     * Refreshes the scroll panel to display all the vehicle images.
     * @param a the agency object associated with the report
     */
    public void allImagesPanelScroller(Agency a){
        scrollPanel.removeAll();
        scrollPanel.revalidate();
        scrollPanel.repaint();
        JPanel backPanel = new JPanel();
        backPanel.setBackground(Color.GRAY);
        for (IVehicle vehicle : a.getVehicles()) {
            JPanel panel = imagePanel(vehicle);
            panel.setBackground(Color.GRAY);
            backPanel.add(panel);
        }
        backPanel.revalidate();
        backPanel.repaint();
        backPanel.setLayout(new GridLayout(a.getSize(),1));

        JScrollPane scroller = new JScrollPane(backPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setBounds(0,0,300,350);


        scrollPanel.setLayout(new BorderLayout());
        scrollPanel.add(scroller);
        add(scrollPanel);
        revalidate();
        repaint();
    }
    /**
     * Creates a JPanel to display the image of a vehicle.
     * @param v the vehicle object associated with the image
     * @return the created JPanel for displaying the vehicle image
     */
    public JPanel imagePanel(IVehicle v){
        JPanel panel = new JPanel();
        BufferedImage buttonIcon;
        try {
            buttonIcon = ImageIO.read(new File(v.getVehicleImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image dimg = buttonIcon.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon myImg = new ImageIcon(dimg);
        JLabel label = new JLabel(myImg);
        label.setBorder(new LineBorder(((ColorDecorator) v).getColor(), 4, true));
        panel.add(label);
        panel.setToolTipText("<html>" + ((ColorDecorator) v).getVehicle() + "</html>");
        return panel;
    }
}
