package Graphics;

import Vehicles.*;
import system.Agency;
import system.CareTaker;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Integer.parseInt;

public class MainFrame extends JFrame implements Observer {
    private static MainFrame myFrame = null;
    public static MainFrame singletonFrame(){
        if (myFrame == null){
            myFrame = new MainFrame();
        }
        return myFrame;
    }
    private JButton buyButton;

    private JButton loadStateButton;
    private JButton testButton;

    public JButton getFinishButton() {
        return finishButton;
    }

    private JButton finishButton;

    public Agency getA() {
        return a;
    }
    private final Agency a;

    private final ExecutorService executor = Executors.newFixedThreadPool(7);

    public GalleryPanel getGallery() {
        return gallery;
    }

    private GalleryPanel gallery = null;

    public ReportFrame getReport() {
        return report;
    }

    private int doneWithThreads = 0;
    public void increaseThreadCount(){
        doneWithThreads++;
        System.out.println(doneWithThreads+" add reg");
    }
    public void decreaseThreadCount(){
        doneWithThreads--;
        System.out.println(doneWithThreads + " delete reg");
    }

    public int getDoneWithBuildThreads() {
        return doneWithBuildThreads;
    }

    private int doneWithBuildThreads = 0;
    public void increaseBuildThreadCount(){
        doneWithBuildThreads++;
        System.out.println(doneWithBuildThreads+" add build");
    }
    public void decreaseBuildThreadCount(){
        doneWithBuildThreads--;
        System.out.println(doneWithBuildThreads + " delete build");
    }

    private final CareTaker careTaker;
    private ReportFrame report;

    private JPanel leftPanel, rightPanel, mainPanel;

    /**
     Returns the "Buy" button instance variable.
     @return the buyButton instance variable.
     */
    public JButton getBuyButton() {
        return buyButton;
    }
    /**
     Returns the test button associated with this object.
     @return the test button associated with this object.
     */
    public JButton getTestButton() {
        return testButton;
    }
    /**
     Returns the instance of the MainFrame object.
     @return the instance of the MainFrame object.
     */
    public MainFrame getThis(){
        return this;
    }
    /**
     Constructs a new MainFrame object.
     Initializes the agency name, sets the window title, icon, and size.
     Sets the window layout to BorderLayout and adds a start panel to the frame.
     */
    private MainFrame(){
        a = new Agency("A&L",this);
        careTaker = new CareTaker();
        setTitle(a.getName() + " Agency");
        ImageIcon image = new ImageIcon("Pictures\\carLogo.png");
        setIconImage(image.getImage());
        setResizable(false);
        setLayout(new BorderLayout());
        setIconImage(image.getImage());
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                if(doneWithThreads + doneWithBuildThreads == 0){
                    int choice = JOptionPane.showOptionDialog(null,
                            "Good Bye! See you again soon!",
                            "Goodbye", JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,new ImageIcon("Pictures\\goodbye.png"),
                            null, 0);
                    if (choice == 0) {
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        e.getWindow().dispose();
                        if(report != null){
                            executor.shutdown();
                            report.dispose();
                        }

                    }
                } else {
                    errorOptionPane("There are action still going on!");
                    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        JPanel startPanel = startPanel();
        add(startPanel);
        setVisible(true);

    }

    /**
     * Set the first "welcome" panel with hello message and buttons to start build
     */
    public JPanel startPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        JButton startButton = new JButton("START");
        startButton.setFocusable(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == startButton){
                    getThis().getContentPane().removeAll();
                    mainPanel = vehiclePanel();
                    getThis().add(mainPanel);
                    getThis().revalidate();
                    getThis().repaint();
                    getThis().setVisible(true);
                }
            }
        });

        JLabel titleLabel = new JLabel("Welcome to A&L Vehicle Agency!");
        Font font = new Font("Arial", Font.BOLD, 28);
        titleLabel.setFont(font);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(500,50));
        titlePanel.setBackground(Color.LIGHT_GRAY);;
        titlePanel.add(titleLabel,BorderLayout.NORTH);

        JLabel startMsg = new JLabel("Click 'START' to build your agency");
        startMsg.setFont(new Font("Arial",Font.PLAIN, 20));

        new JLabel("Lior Engel: 351006783");

        JLabel vehicleLabel = new JLabel(new ImageIcon("Pictures\\carLogo.png"));
        vehicleLabel.setPreferredSize(new Dimension(700,700));

        JButton exitButton = new JButton("Exit");
        exitButton.setFocusable(false);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(startMsg);
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(vehicleLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
    /**
     Returns a JPanel for the vehicle selection screen.
     The panel contains two sub-panels: a left panel for vehicle categories, and a right panel for vehicle details.
     @return a JPanel for the vehicle selection screen
     */
    public JPanel vehiclePanel(){
        JPanel panel = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        panel.setLocation(screenSize.width/2-400,screenSize.height/2-300);
        panel.setSize(800,600);
        panel.setLayout(new BorderLayout());

        leftPanel = leftPanel();
        rightPanel = rightPanel();

        panel.add(leftPanel,BorderLayout.WEST);
        panel.add(rightPanel,BorderLayout.CENTER);
        return panel;
    }

    /**
     Returns a JPanel object that represents the right panel of the main frame.
     @return a JPanel object that represents the right panel of the main frame
     */
    public JPanel rightPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(getWidth() * 2 / 3, getHeight() - 100));
        panel.setBackground(Color.GRAY);
        return panel;
    }

    /**
     Creates a scrolling panel for the right panel of the MainFrame.
     First, it clears the existing content of the right panel.
     Then, it creates a new GalleryPanel instance and adds it to the right panel using a JScrollPane.
     A border is set for the JScrollPane.
     Additionally, a JPanel containing "Test Drive" and "Buy" buttons is created and added to the bottom of the right panel.
     */

    public void scrollerPanel(){
        clearPanel(rightPanel);
        gallery = new GalleryPanel(a,this);
        rightPanel.setLayout(new BorderLayout());
        JScrollPane scroller = new JScrollPane(gallery, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Border border = new LineBorder(Color.GRAY, 4, true);

        scroller.setBorder(border);
        rightPanel.add(scroller);

        buyButton = buyButton(gallery);
        testButton = testButton(gallery);
        JLabel sentenceLabel = new JLabel();
        sentenceLabel.setText("<html><u>The total travel distance</u>:</html>");
        sentenceLabel.setFont(new Font("Arial", Font.BOLD, 25));
        sentenceLabel.setForeground(Color.DARK_GRAY);

        JLabel kmLabel = new JLabel();
        kmLabel.setText(String.valueOf(a.getTotalAgencyTravel()));
        kmLabel.setFont(new Font("Arial", Font.BOLD, 25));
        kmLabel.setForeground(Color.DARK_GRAY);

        JPanel actionKmPanel = new JPanel();
        actionKmPanel.setLayout(new GridLayout(2,1));
        JPanel actionPanek = new JPanel();
        JPanel kmPanel = new JPanel();
        kmPanel.setBackground(Color.GRAY);
        kmPanel.add(sentenceLabel);
        kmPanel.add(kmLabel);
        kmPanel.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.darkGray));

        actionPanek.add(testButton);
        actionPanek.add(buyButton);
        actionPanek.setBackground(Color.darkGray);

        actionKmPanel.add(kmPanel);
        actionKmPanel.add(actionPanek);
        rightPanel.add(actionKmPanel,BorderLayout.SOUTH);
        rightPanel.revalidate();
        rightPanel.repaint();

        if (report != null){
            report.allImagesPanelScroller(a);
        }
    }
    /**
     Creates a "Test drive" JButton that is disabled by default, and triggers a TestDialog instance to open when clicked.
     When the dialog is closed, it refreshes the scrollerPanel with updated information.
     @param gallery The GalleryPanel instance that is used to interact with the vehicle objects.
     @return A JButton with the text "Test drive".
     */
    public JButton testButton(GalleryPanel gallery){
        JButton testButton = new JButton("Test drive");
        testButton.setEnabled(false);
        testButton.setFocusable(false);
        testButton.setPreferredSize(new Dimension(150,40));
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == testButton){
                    IVehicle v = gallery.getLastSelected();
                    new Thread(() -> {
                        increaseThreadCount();
                        synchronized (a.getResetLock()) {
                            synchronized (v) {
                                new TestDialog(getThis(), v, a, executor);
                            }
                        }
                        decreaseThreadCount();
                    }).start();
                }
            }
        });
        return testButton;
    }
    /**
     * Creates a "Buy" button that is disabled by default and only becomes enabled when a vehicle is selected from the gallery panel.
     * When clicked, the button deletes the selected vehicle from the inventory and updates the gallery panel to reflect the changes.
     * @param gallery the GalleryPanel object representing the gallery panel containing the selected vehicle
     * @return a JButton object that serves as the "Buy" button
     */
    public JButton buyButton(GalleryPanel gallery){
        JButton buyButton = new JButton("Buy");
        buyButton.setEnabled(false);
        buyButton.setFocusable(false);
        buyButton.setPreferredSize(new Dimension(150,40));
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == buyButton){
                    IVehicle v = gallery.getLastSelected();
                    JButton vButton = gallery.getLastSelectedButton();
                    if( ((StatusDecorator)((ColorDecorator) v).getVehicle()).getStatus().equals("Available")){
//                    if(!v.isInBuyProcess() ){
                        ((StatusDecorator)((ColorDecorator) v).getVehicle()).setStatus("Purchasing");

                        new Thread(() -> {
//                            synchronized (a.getResetLock()){
                            increaseThreadCount();
                                synchronized (v) {
                                    try {
                                        ((StatusDecorator)((ColorDecorator) v).getVehicle()).setStatus("Purchasing");
                                        scrollerPanel();
                                        JDialog dialog = new JDialog();
                                        dialog.setResizable(false);
                                        dialog.setDefaultCloseOperation(0);
                                        dialog.setTitle("Processing");
                                        JPanel panel = new JPanel();
                                        JLabel label1 = new JLabel("Purchase processing");
                                        panel.add(label1);
                                        panel.setPreferredSize(new Dimension(200,30));
                                        dialog.add(panel);
                                        dialog.pack();
                                        dialog.setLocationRelativeTo(getThis());
                                        dialog.setVisible(true);
                                        Thread.sleep((new Random().nextInt(6) + 5) * 1000);
                                        dialog.dispose();
                                    } catch (InterruptedException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    new PurchasingDialog(getThis(), v, a, vButton);
                                }
                                decreaseThreadCount();
                            }).start();
//                        }
                    } else if(((StatusDecorator)((ColorDecorator) v).getVehicle()).getStatus().equals("Purchasing")) errorOptionPane("This car is middle in purchasing process");
                }
            }
        });
        return buyButton;
    }


    /**
     Creates a JPanel with a light gray background and a BorderLayout layout. The preferred size of the panel is set to one
     third the width and height of the container. A finishPanel is created and added to the SOUTH position of the panel.
     @return a JPanel with a light gray background and a BorderLayout layout
     */
    public JPanel leftPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(getWidth() / 3, getHeight()));
        JPanel finish = finishPanel(panel);
        panel.add(finish, BorderLayout.SOUTH);
        return panel;
    }


    /**
     Creates a JPanel with a GridLayout layout. The panel has a light gray background. A buttonPanel containing the "Add Vehicle"
     JButton, the JButtons added to the panel.
     @return a JPanel with a GridLayout layout consisting of 7 rows and 1 column
     */
    public JPanel menuButtonPanel(){
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(9,1);
        layout.setHgap(5);
        layout.setVgap(5);
        panel.setLayout(layout);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(buttonPanel(addMoreVehicleButton()));
        panel.add(buttonPanel(resetButton()));
        panel.add(buttonPanel(flagButton()));
        panel.add(buttonPanel(reportButton()));
        panel.add(buttonPanel(saveStateButton()));
        panel.add(buttonPanel(loadStateButton()));
        return panel;
    }

    /**
     Creates a JPanel with a light gray background and adds the specified JButton to it.
     @param button the JButton to be added to the panel
     @return a JPanel with a light gray background and the specified JButton added to it
     */
    public JPanel buttonPanel(JComponent button){
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(button);
        return panel;
    }
    /**
     * Creates and returns a JButton for saving the state of the application.
     * @return the JButton for saving the state
     */
    public JButton saveStateButton(){
        JButton saveStateButton = new JButton("Save Button");
        saveStateButton.setFocusable(false);
        saveStateButton.setPreferredSize(new Dimension(150,40));
        saveStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(doneWithThreads + doneWithBuildThreads == 0){

                    System.out.println("saved");
                    careTaker.add(a.saveStateToMemento());
                    loadStateButton.setText("Load Button (" + careTaker.getMementoList().size() + ")");
                    loadStateButton.setEnabled(true);
                    successOptionPane("saved","Agency", "Save");
                } else errorOptionPane("There are action still going on!");
            }
        });
        return saveStateButton;
    }
    /**
     * Creates and returns a JButton for loading a previously saved state of the application.
     * @return the JButton for loading a state
     */
    public JButton loadStateButton(){
        loadStateButton = new JButton("Load Button (" + careTaker.getMementoList().size() + ")");
        loadStateButton.setFocusable(false);
        loadStateButton.setPreferredSize(new Dimension(150,40));
        if(careTaker.getMementoList().isEmpty()){
            loadStateButton.setEnabled(false);
        }
        loadStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(doneWithThreads + doneWithBuildThreads == 0){
                System.out.println("restored");
                a.getStateFromMemento(careTaker.get(loadStateButton));
                loadStateButton.setText("Load Button (" + careTaker.getMementoList().size() + ")");
                successOptionPane("loaded","Agency","Load");
                } else errorOptionPane("There are action still going on!");
            }
        });
        return loadStateButton;
    }

    /**
     Creates a JButton with the text "Reset Flag" and an action listener that checks if there are any sea vehicles in the
     collection. If there are sea vehicles, a FlagDialog is created and displayed. If there are no sea vehicles, an error
     message dialog is displayed.
     @return a JButton with the text "Reset Flag"
     */
    public JButton flagButton(){
        JButton flagButton = new JButton("Reset Flag");
        flagButton.setFocusable(false);
        flagButton.setPreferredSize(new Dimension(150,40));
        flagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == flagButton){
                    if(seaVehicleExists()){
                        new Thread(()->{
                            increaseThreadCount();
                            synchronized (a.getResetLock()){
                                FlagDialog flagDialog = new FlagDialog(a, getThis());
                                flagDialog.setVisible(true);
                                scrollerPanel();
                            }
                            decreaseThreadCount();
                        }).start();
                    }
                    else {
                        new InputErrorOption( "There are no sea vehicles");
                    }
                }
            }
        });
        return flagButton;
    }

    /**
     * Check if the agency have any sea vehicles
     * @return true if they exists
     */
    public boolean seaVehicleExists(){
        boolean exists = false;
        for (int i = 0; i < a.getSize(); i++) {
            if (a.getVehicles()[i].getSuperClassName().equals("SeaVehicle")){
                exists = true;
            }
        }
        return exists;
    }
    /**
     Creates a JButton with the text "Reset Travel" and an action listener that checks if there are any vehicles in the
     collection, and if so, resets the KM of each vehicle to zero and displays a confirmation dialog with the name of the
     collection and a success message. If there are no vehicles in the collection, an error message dialog is displayed.
     @return a JButton with the text "Reset Travel"
     */
    public JButton resetButton(){
        JButton resetKM = new JButton("Reset Travel");
        resetKM.setFocusable(false);
        resetKM.setPreferredSize(new Dimension(150,40));
        resetKM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == resetKM) {
                    if(a.getSize() == 0){
                        new InputErrorOption("There are no vehicles to reset");
                    }
                    else {
                        new Thread(()->{
                            increaseThreadCount();
                            synchronized (a.getResetLock()){
                                a.resetTravel();
                                ImageIcon icon = new ImageIcon("Pictures\\success.png");
                            }
                            decreaseThreadCount();
                        }).start();
                        scrollerPanel();
                    }
                }
            }
        });
        return resetKM;
    }

    /**
     Creates a JButton with the text "Add Vehicle" and an action listener that clears the rightPanel and leftPanel,
     sets the layout of the leftPanel to BorderLayout, and adds the finishPanel.
     @return a JButton with the text "Add Vehicle"
     */
    public JButton addMoreVehicleButton(){
        JButton button = new JButton("Add Vehicles");
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(150,40));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == button){
                    System.out.println("add " + a.getVehicles().length);
                    gallery = null;
                    clearPanel(rightPanel);
                    clearPanel(leftPanel);
                    leftPanel.setLayout(new BorderLayout());
                    leftPanel.add(finishPanel(leftPanel),BorderLayout.SOUTH);
                }
            }
        });
        return button;
    }
    /**
     * Creates and returns a JButton for generating a report.
     *
     * @return the JButton for generating a report
     */
    public JButton reportButton(){
        JButton reportButton = new JButton("Report");
        reportButton.setFocusable(false);
        reportButton.setPreferredSize(new Dimension(150,40));
        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == reportButton){
                    if (report == null) {
                        report = new ReportFrame(a);
                        report.addWindowListener(new java.awt.event.WindowAdapter() {
                            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                                report = null;
                            }
                        });
                        report.setVisible(true);
                    }
                }
            }
        });
        return reportButton;
    }

    /**
     * Creates a panel with the finish button at the bottom of the page
     * @param panel with the finish button
     * @return panel with the finish button
     */
    public JPanel finishPanel(JPanel panel){
        finishButton = new JButton("FINISH");
        finishButton.setPreferredSize(new Dimension(150,40));
        finishButton.setFocusable(false);
        finishButton.setBounds(panel.getPreferredSize().width / 2 - finishButton.getPreferredSize().width / 2,
                panel.getPreferredSize().height - finishButton.getPreferredSize().height,
                finishButton.getPreferredSize().width,
                finishButton.getPreferredSize().height);
        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == finishButton){
                        clearPanel(rightPanel);
                        clearPanel(leftPanel);
                        scrollerPanel();
                        leftPanel.add(exitPanel(leftPanel),BorderLayout.SOUTH);
                        leftPanel.add(menuButtonPanel(),BorderLayout.NORTH);
                }
            }
        });
        panel.setLayout(new BorderLayout());
        panel.add(panelButtonsVehicles(),BorderLayout.NORTH);
        JPanel finishPanel = new JPanel();
        finishPanel.setBackground(Color.darkGray);
        finishPanel.add(finishButton);
        return finishPanel;
    }

    /**
     Creates a JPanel that contains an exit button which, when clicked, displays a confirmation dialog and then
     closes the current JFrame and clears the mainPanel and leftPanel.
     @param panel the panel to which the exit button will be added
     @return a JPanel containing an exit button
     */
    public JPanel exitPanel(JPanel panel){
        JButton exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(new Dimension(150,40));
        exitButton.setFocusable(false);
        exitButton.setBounds(panel.getPreferredSize().width / 2 - exitButton.getPreferredSize().width / 2,
                panel.getPreferredSize().height - exitButton.getPreferredSize().height,
                exitButton.getPreferredSize().width,
                exitButton.getPreferredSize().height);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == exitButton){
                    if (doneWithThreads == 0) {
                            ImageIcon icon = new ImageIcon("Pictures\\goodbye.png");
                            int choice = JOptionPane.showOptionDialog(null,
                                "Good Bye! See you again soon!",
                                "Goodbye", JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE, icon,
                                null, 0);
                            if (choice == 0) {
                                    clearPanel(mainPanel);
                                    clearPanel(leftPanel);
                                    dispose();
                            }
                    } else errorOptionPane("There are action still going on!");
                }
            }
        });
        JPanel finishPanel = new JPanel();
        finishPanel.setBackground(Color.darkGray);
        finishPanel.add(exitButton);
        return finishPanel;
    }

    /**
     * Shows a success message dialog with the given message and type.
     * @param msg the message to display in the dialog.
     * @param type of the vehicle.
    */
    public void successOptionPane(String msg, String type){
        ImageIcon icon = new ImageIcon("Pictures\\success.png");
        JOptionPane.showOptionDialog(null,
                type + " " + msg,
                "Success", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, icon,
                null, 0);
        if(gallery != null){
            scrollerPanel();
        }
        if (report != null){
            report.allImagesPanelScroller(a);
        }
    }
    public void successOptionPane(String msg, String type, String title){
        ImageIcon icon = new ImageIcon("Pictures\\success.png");
        JOptionPane.showOptionDialog(null,
                type + " " + msg,
                title, JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, icon,
                null, 0);
        if(gallery != null){
            scrollerPanel();
        }
        if (report != null){
            report.allImagesPanelScroller(a);
        }
    }
    /**
     * Returns a JPanel containing buttons for each type of vehicle.
     * @return a JPanel containing buttons for each type of vehicle.
   */
    public JPanel panelButtonsVehicles(){
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(9,1);
        layout.setHgap(5);
        layout.setVgap(5);
        panel.setLayout(layout);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(buttonPanel("Jeep"));
        panel.add(buttonPanel("Frigate"));
        panel.add(buttonPanel("Spy Glider"));
        panel.add(buttonPanel("Game Glider"));
        panel.add(buttonPanel("Amphibious"));
        panel.add(buttonPanel("Bicycle"));
        panel.add(buttonPanel("Cruise"));
        panel.add(buttonPanel("Hybrid Airplane"));
        panel.add(buttonPanel("Electric Bicycle"));
        return panel;
    }
    /**
     Returns a JPanel containing a single JButton with the specified text and a light gray background.
     @param text the text to be displayed on the button
     @return a JPanel containing a single JButton with the specified text and a light gray background
     */
    public JPanel buttonPanel(String text){
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        JButton button = buttonVehicle(text);
        panel.add(button);
        return panel;
    }

    /**
    Creates a new JButton object for a specific vehicle type and sets its properties.
    @param text the text to be displayed on the button
    @return the created JButton object
    */
    public JButton buttonVehicle(String text){
        JButton button = new JButton();
        button.setText(text);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(150,40));
        button.addActionListener(e -> {
            if(e.getSource() == button){
                clearPanel(rightPanel);
                rightPanel.add(new DataPanel(a, button, getThis()), BorderLayout.CENTER);
            }
        });
        return button;
    }

    /**
     * Clear and reset the given panel
     * @param panel to reset
     */
    public void clearPanel(JPanel panel){
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }
    public static void main(String[] args){
        singletonFrame();
    }

    /**
     * Displays an error message dialog with the given message.
     *
     * @param s the error message
     */
    public void errorOptionPane(String s) {
        ImageIcon icon = new ImageIcon("Pictures\\error.png");
        JOptionPane.showOptionDialog(null,
                s, "Oops", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, icon,
                null, 0);
    }
    /**
     * Creates and returns a JDialog for displaying an updating message.
     * @param msg the message to be displayed
     * @return the created JDialog
     */
    public JDialog updatingDialog(String msg){
        JDialog dialog = new JDialog();
        dialog.setIconImage(new ImageIcon("Pictures\\carLogo.png").getImage());
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(0);
        dialog.setTitle(msg);
        JPanel panel = new JPanel();
        ImageIcon image = new ImageIcon("Pictures\\loadingBar.png");
        JLabel label1 = new JLabel(image);
        JLabel label2 = new JLabel("Updating database… Please wait");
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(2,1));
        panel.add(label2);
        panel.add(label1);
        panel.setPreferredSize(new Dimension(230,90));

        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        return dialog;
    }

    /**
     * Updating the Agency total kilometer
     * @param km the distance to update
     */
    @Override
    public void update(int km) {
        a.addToTotalAgencyTravel(km);
    }
}



