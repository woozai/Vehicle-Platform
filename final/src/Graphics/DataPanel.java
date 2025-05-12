package Graphics;

import Factories.*;
import system.Agency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.lang.Integer.parseInt;

public class DataPanel extends JPanel{
    public JButton getAddButton() {
        return addButton;
    }

    private final JButton addButton;

    public JTextField getMaxSpeed() {
        return maxSpeed;
    }

    public JTextField getModel() {
        return model;
    }

    public JTextField getAvgFuel() {
        return avgFuel;
    }

    public JTextField getEngLife() {
        return engLife;
    }

    public JTextField getPassNum() {
        return passNum;
    }

    public JTextField getPwrSrc() {
        return pwrSrc;
    }

    public JTextField getWheelNum() {
        return wheelNum;
    }

    public JComboBox<Object> getWind() {
        return wind;
    }

    public JComboBox<Object> getRoadType() {
        return roadType;
    }
    public JComboBox<Object> getColor() {
        return color;
    }

    public JComboBox<Icon> getFlag() {
        return flag;
    }

    private final JTextField maxSpeed, model, avgFuel, engLife, passNum, pwrSrc, wheelNum;

    private final JComboBox<Object> wind, roadType, color;

    private final JComboBox<Icon> flag;
    private final String[] colorsLst = {"Red", "Green", "Purple", "Blue", "Light Gray", "Yellow", "White", "Black"};
    private final String[] windLst = {"True", "False"};
    private final String[] roadLst = {"Dirt", "Paved", "Street"};
    private final String[] flagLst = {"Israel", "Germany", "USA", "Italy", "Greece", "Somalia", "Pirates"};
    private final ImageIcon[] flagIconList = {
            new ImageIcon("Pictures\\flags\\israel.png"),
            new ImageIcon("Pictures\\flags\\germany.png"),
            new ImageIcon("Pictures\\flags\\usa.png"),
            new ImageIcon("Pictures\\flags\\italy.png"),
            new ImageIcon("Pictures\\flags\\greece.png"),
            new ImageIcon("Pictures\\flags\\somalia.png"),
            new ImageIcon("Pictures\\flags\\pirates.png"),
    };
    private final String[] jeepPath = {"Pictures\\jeep\\jeep1.jpg", "Pictures\\jeep\\jeep2.jpg","Pictures\\jeep\\jeep3.jpg"};
    private final String[] frigatePath = {"Pictures\\frigate\\frigate1.jpg", "Pictures\\frigate\\frigate2.jpg","Pictures\\frigate\\frigate3.jpg"};
    private final String[] spyGliderPath = {"Pictures\\spyGlider\\spyglider1.jpg", "Pictures\\spyGlider\\spyglider2.jpg","Pictures\\spyGlider\\spyglider3.jpg"};
    private final String[] gameGliderPath = {"Pictures\\gameGlider\\gameGlider1.jpg", "Pictures\\gameGlider\\gameGlider2.jpg","Pictures\\gameGlider\\gameGlider3.jpg"};
    private final String[] amphibiousPath = {"Pictures\\Amphibious\\amphi1.jpg", "Pictures\\Amphibious\\amphi2.jpg","Pictures\\Amphibious\\amphi3.jpg"};
    private final String[] nonMotorbicyclePath = {"Pictures\\bicycle\\bicycle1.jpg", "Pictures\\bicycle\\bicycle2.jpg","Pictures\\bicycle\\bicycle3.jpg"};
    private final String[] electricBicyclePath = {"Pictures\\electricBike\\ebike1.jpg", "Pictures\\electricBike\\ebike2.jpg","Pictures\\electricBike\\ebike3.jpg"};
    private final String[] hybridAirplanePath = {"Pictures\\hybridAirplane\\hybplane1.jpg", "Pictures\\hybridAirplane\\hybplane2.jpg","Pictures\\hybridAirplane\\hybplane3.jpg"};
    private final String[] cruisePath = {"Pictures\\cruise\\cruise1.jpg", "Pictures\\cruise\\cruise2.jpg","Pictures\\cruise\\cruise3.jpg"};

    private AbstractFactory airFactory, groundFactory, SeaFactory, groundSeaFactory, groundSeaAirFactory;

    private MainFrame frame;


    /**a
     Creates a DataPnel object with specified agency, button and MainFrame parameters.
     @param a the agency parameter to be used
     @param button the button parameter to be used
     @param frame the MainFrame parameter to be used
     */
    public DataPanel(Agency a, JButton button, MainFrame frame){
        this.frame = frame;
        model = new JTextField("");
        maxSpeed = new JTextField("");
        passNum = giveKeyListener();
        wind = new JComboBox<>();
        avgFuel = giveKeyListener();
        engLife = giveKeyListener();
        wheelNum = giveKeyListener();
        pwrSrc = new JTextField("");
        flag = new JComboBox<Icon>();
        roadType = new JComboBox<>();
        color = new JComboBox<>();

        airFactory = FactoryProducer.getFactory("Air", this);
        groundFactory = FactoryProducer.getFactory("Ground", this);
        SeaFactory = FactoryProducer.getFactory("Sea", this);;
        groundSeaFactory = FactoryProducer.getFactory("GroundSea", this);
        groundSeaAirFactory = FactoryProducer.getFactory("GroundSeaAir", this);

        addButton = new JButton("Add Vehicle");
        addButton.setFocusable(false);
        addButton.setPreferredSize(new Dimension(150,44));
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(frame.getWidth() * 2 / 3, frame.getHeight() - 40));
        setBounds(0,0, getPreferredSize().width, getPreferredSize().height);
        setLayout(new BorderLayout());
        setSize(getPreferredSize().width, getPreferredSize().height);
        if(button.getText().equals("Jeep")){
            add(jeepSetup(a),BorderLayout.NORTH);
        }
        else if(button.getText().equals("Frigate")){
            add(frigateSetup(a), BorderLayout.NORTH);
        }
        else if(button.getText().equals("Spy Glider")){
            add(spyGliderSetup(a), BorderLayout.NORTH);
        }
        else if(button.getText().equals("Game Glider")){
            add(gameGliderSetup(a), BorderLayout.NORTH);
        }
        else if(button.getText().equals("Amphibious")){
            add(amphibiousSetup(a), BorderLayout.NORTH);
        }
        else if(button.getText().equals("Bicycle")){
            add(nonMotorBicycleSetup(a), BorderLayout.NORTH);
        }
        else if(button.getText().equals("Cruise")){
            add(cruiseSetup(a), BorderLayout.NORTH);
        }
        else if(button.getText().equals("Electric Bicycle")){
            add(electricBicycleSetup(a), BorderLayout.NORTH);
        }
        else if(button.getText().equals("Hybrid Airplane")){
            add(hybridAirplaneSetup(a), BorderLayout.NORTH);
        }

        JPanel addPanel = new JPanel();

        addPanel.add(addButton);
        addPanel.setBackground(Color.darkGray);
        add(addPanel, BorderLayout.SOUTH);

    }
    /**
     Sets up a panel for creating a new Jeep vehicle with appropriate input fields and labels.
     @param a the Agency object to add the created Jeep vehicle to
     @return a JPanel object representing the created setup panel
     */
    private JPanel jeepSetup(Agency a) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel[] questionsArr = {
                questionLabel("Model:"),
                questionLabel("Max speed:"),
                questionLabel("Average fuel:"),
                questionLabel("Engine life:")
        };
        JComponent[] inputArr = {
                textFieldInsert(model,false),
                textFieldInsert(maxSpeed,true),
                textFieldInsert(avgFuel,true),
                textFieldInsert(engLife,true),
        };
        updatePanel(panel, questionsArr, inputArr, gbc);

        ImagesForVehiclePanel imagesAllPanel = new ImagesForVehiclePanel(jeepPath);
        imagesAllPanel.setBackground(Color.GRAY);
        panel.add(imagesAllPanel, gbc);

        groundFactory.getVehicle("Jeep",model ,maxSpeed , avgFuel, imagesAllPanel, a);

        return panel;
    }
    /**
     * This method sets up the JPanel used for creating a new Frigate vehicle.
     * It takes an Agency object as an argument and returns a JPanel.
     * @param a: an Agency object used for adding the new Frigate to the Agency.
     * @return panel: a JPanel object that includes input fields for creating a new Frigate object.
     */
    private JPanel frigateSetup(Agency a) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel[] questionsArr ={
                questionLabel("Model:"),
                questionLabel("Max speed:"),
                questionLabel("Passengers:"),
                questionLabel("Wind direction:")
        };
        JComponent[] inputArr = {
                textFieldInsert(model,false),
                textFieldInsert(maxSpeed,true),
                textFieldInsert(passNum,true),
                dropDown(wind, windLst)
        };
        updatePanel(panel, questionsArr, inputArr, gbc);

        ImagesForVehiclePanel imagesAllPanel = new ImagesForVehiclePanel(frigatePath);
        imagesAllPanel.setBackground(Color.GRAY);
        panel.add(imagesAllPanel, gbc);
        SeaFactory.getVehicle("Frigate", model, maxSpeed, passNum, imagesAllPanel, a);
        return panel;
    }

    /**
     Creates and returns a JPanel that allows the user to input information for a new Spy Glider vehicle to be added to the agency.
     @param a The agency to which the new Spy Glider will be added.
     @return JPanel containing input fields and an "add" button to add the new Spy Glider to the agency.
     */
    private JPanel spyGliderSetup(Agency a) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel[] questionsArr ={
                questionLabel("Power source:")
        };
        JComponent[] inputArr = {
                textFieldInsert(pwrSrc,false)
        };
        updatePanel(panel, questionsArr, inputArr, gbc);

        ImagesForVehiclePanel imagesAllPanel = new ImagesForVehiclePanel(spyGliderPath);
        imagesAllPanel.setBackground(Color.GRAY);
        panel.add(imagesAllPanel, gbc);

        airFactory.getVehicle("SpyGlider", model, maxSpeed, passNum, imagesAllPanel, a);

        return panel;
    }
    /**
     Creates a JPanel for setting up a new GameGlider vehicle for an agency.
     @param a The agency for which the GameGlider is being set up.
     @return A JPanel containing the setup components for the GameGlider.
     */
    private JPanel gameGliderSetup(Agency a) {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel[] questionsArr = {};
        JComponent[] inputArr = {};
        updatePanel(panel, questionsArr, inputArr, gbc);


        ImagesForVehiclePanel imagesAllPanel = new ImagesForVehiclePanel(gameGliderPath);
        panel.add(imagesAllPanel, gbc);

        airFactory.getVehicle("GameGlider", model, maxSpeed, passNum, imagesAllPanel, a);
        return panel;
    }
    /**
     This method creates a JPanel that allows the user to input information for an amphibious vehicle.
     The method then creates an instance of the Amphibious class using the user input,
     adds the Amphibious object to the Agency instance.
     @param a The Agency instance to add the Amphibious vehicle to
     @return A JPanel that allows the user to input information for an amphibious vehicle
     */
    private JPanel amphibiousSetup(Agency a) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel[] questionsArr = {
                questionLabel("Model:"),
                questionLabel("Max speed:"),
                questionLabel("Passengers num:"),
                questionLabel("Wind direction:"),
                questionLabel("Flag:"),
                questionLabel("Wheels:"),
                questionLabel("Average fuel:"),
                questionLabel("Engine life:")
        };
        JComponent[] inputArr = {
                textFieldInsert(model,false),
                textFieldInsert(maxSpeed,true),
                textFieldInsert(passNum,true),
                dropDown(wind, windLst),
                dropDownFlag(flag),
                textFieldInsert(wheelNum,true),
                textFieldInsert(avgFuel,true),
                textFieldInsert(engLife,true),
        };

        updatePanel(panel, questionsArr, inputArr, gbc);

        ImagesForVehiclePanel imagesAllPanel = new ImagesForVehiclePanel(amphibiousPath);
        panel.add(imagesAllPanel, gbc);

        groundSeaFactory.getVehicle("Amphibious", model, maxSpeed, passNum, imagesAllPanel, a);
        return panel;
    }
    /**
     * Creates a JPanel for setting up a HybridAirplane in the given Agency.
     *
     * @param a the Agency in which the HybridAirplane is being set up
     * @return the JPanel for setting up the HybridAirplane
     */
    private JPanel hybridAirplaneSetup(Agency a){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel[] questionsArr = {
                questionLabel("Model:"),
                questionLabel("Max speed:"),
                questionLabel("Passengers num:"),
                questionLabel("Wind direction:"),
                questionLabel("Flag:"),
                questionLabel("Wheels:"),
                questionLabel("Average fuel:"),
                questionLabel("Engine life:")
        };
        JComponent[] inputArr = {
                textFieldInsert(model,false),
                textFieldInsert(maxSpeed,true),
                textFieldInsert(passNum,true),
                dropDown(wind, windLst),
                dropDownFlag(flag),
                textFieldInsert(wheelNum,true),
                textFieldInsert(avgFuel,true),
                textFieldInsert(engLife,true),
        };

        updatePanel(panel, questionsArr, inputArr, gbc);

        ImagesForVehiclePanel imagesAllPanel = new ImagesForVehiclePanel(hybridAirplanePath);
        panel.add(imagesAllPanel, gbc);

        groundSeaAirFactory.getVehicle("HybridAirplane", model, maxSpeed, passNum, imagesAllPanel, a);
        return panel;
    }
    /**
     Creates a JPanel for setting up a Bicycle vehicle with the specified agency.
     @param a the agency to add the new vehicle to
     @return a JPanel containing the setup interface for the Bicycle vehicle
     */
    private JPanel nonMotorBicycleSetup(Agency a) {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel[] questionsArr = {
                questionLabel("Model:"),
                questionLabel("Max speed:"),
                questionLabel("Passengers:"),
                questionLabel("Road Type:")
        };
        JComponent[] inputArr = {
                textFieldInsert(model,false),
                textFieldInsert(maxSpeed,true),
                textFieldInsert(passNum,true),
                dropDown(roadType, roadLst)

        };
        updatePanel(panel, questionsArr, inputArr, gbc);

        ImagesForVehiclePanel imagesAllPanel = new ImagesForVehiclePanel(nonMotorbicyclePath);
        imagesAllPanel.setBackground(Color.GRAY);
        panel.add(imagesAllPanel, gbc);
        groundFactory.getVehicle("NonMotorBicycle",model ,maxSpeed , passNum, imagesAllPanel, a);
        return panel;
    }
    /**
     * Creates a JPanel for setting up an ElectricBicycle in the given Agency.
     *
     * @param a the Agency in which the ElectricBicycle is being set up
     * @return the JPanel for setting up the ElectricBicycle
     */
    private JPanel electricBicycleSetup(Agency a){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel[] questionsArr = {
                questionLabel("Model:"),
                questionLabel("Max speed:"),
                questionLabel("Passengers:"),
                questionLabel("Road Type:"),
                questionLabel("Engine life:")
        };
        JComponent[] inputArr = {
                textFieldInsert(model,false),
                textFieldInsert(maxSpeed,true),
                textFieldInsert(passNum,true),
                textFieldInsert(engLife,true),
                dropDown(roadType, roadLst)

        };
        updatePanel(panel, questionsArr, inputArr, gbc);

        ImagesForVehiclePanel imagesAllPanel = new ImagesForVehiclePanel(electricBicyclePath);
        imagesAllPanel.setBackground(Color.GRAY);
        panel.add(imagesAllPanel, gbc);

        groundFactory.getVehicle("ElectricBicycle",model ,maxSpeed , passNum, imagesAllPanel, a);
        return panel;
    }
    /**
     Creates a JPanel for setting up a new Cruise object to add to the Agency.
     @param a The Agency object to add the newly created Cruise object to.
     @return A JPanel containing fields for entering information about the new Cruise object.
     */
    private JPanel cruiseSetup(Agency a) {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel[] questionsArr = {
                questionLabel("Model:"),
                questionLabel("Max speed:"),
                questionLabel("Passengers:"),

                questionLabel("Average fuel:"),
                questionLabel("Engine life:"),
                questionLabel("Flag:")
        };
        JComponent[] inputArr = {
                textFieldInsert(model,false),
                textFieldInsert(maxSpeed,true),
                textFieldInsert(passNum,true),
                textFieldInsert(avgFuel,true),
                textFieldInsert(engLife,true),
                dropDownFlag(flag)

        };
        updatePanel(panel, questionsArr, inputArr, gbc);

        ImagesForVehiclePanel imagesAllPanel = new ImagesForVehiclePanel(cruisePath);
        imagesAllPanel.setBackground(Color.GRAY);
        panel.add(imagesAllPanel, gbc);

        SeaFactory.getVehicle("Cruise",model ,maxSpeed , passNum, imagesAllPanel, a);
        return panel;
    }
    /**
     Helper method to update a panel with a set of JLabel questions and JComponent answers.
     @param panel The JPanel to be updated.
     @param questions An array of JLabel questions.
     @param answers An array of JComponent answers.
     @param gbc GridBagConstraints object to specify layout constraints.
     */
    private void updatePanel(JPanel panel, JLabel[] questions,JComponent[] answers, GridBagConstraints gbc){
        int index = 0;
        panel.setBackground(Color.GRAY);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        for (int i = 0; i < questions.length; i++) {
            index = i;
            gbc.gridx=0;
            gbc.gridy=i;
            panel.add(questions[i],gbc);
            gbc.gridx=1;
            panel.add(answers[i], gbc);
        }
        gbc.gridx = 0;
        gbc.gridy = index + 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
    }
    /**
     Inserts a JTextField into a panel and returns the updated JTextField.
     @param textField the JTextField object to insert.
     @param isInt a boolean indicating whether the text field only accepts integers or not.
     @return the updated JTextField object.
     */
    public JTextField textFieldInsert(JTextField textField,boolean isInt){
        if (isInt){
            textField.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                        e.consume();  // ignore event
                    }
                }
            });
        }
        textField.setPreferredSize(new Dimension(150,30));
        return textField;
    }
    /**
     Returns a JLabel object with the specified text as its content.
     The label has a preferred size of 150 by 30 pixels and is left aligned.
     @param text the text to be displayed on the label
     @return a JLabel object with the specified text, preferred size and alignment
     */
    public JLabel questionLabel(String text){
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(150,30));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    /**
     * This method returns a new instance of a JTextField that has a KeyListener attached to it.
     * The KeyListener is used to restrict the input to only numeric characters and the backspace key.
     * @return a new instance of JTextField with a KeyListener that only allows numeric input and backspace key.
     */
    public JTextField giveKeyListener(){
        JTextField textField = new JTextField("");
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // ignore event
                }
            }
        });
        return textField;
    }
    /**
     Creates and returns a default combo box model containing flag icons.
     @return a DefaultComboBoxModel containing flag icons
     */
    private DefaultComboBoxModel<Icon> loadImages(){
        DefaultComboBoxModel<Icon> dm = new DefaultComboBoxModel<Icon>();
        for (int i = 0; i < flagLst.length; i++) {
            flagIconList[i].setDescription(flagLst[i]);
            dm.addElement(flagIconList[i]);
        }
        return dm;
    }

    /**
     Sets the properties for a JComboBox of type Icon.
     @param cb The JComboBox to be configured.
     @return The configured JComboBox.
     */
    public JComboBox<Icon> dropDownFlag(JComboBox<Icon> cb){
        cb.setPreferredSize(new Dimension(150,40));
        cb.setModel(loadImages());
        return cb;
    }
    /**
     Populates a given JComboBox with items from a given String array.
     @param cb the JComboBox to be populated
     @param lst the String array containing the items to be added to the JComboBox
     @return the populated JComboBox
     */
    public JComboBox<Object> dropDown(JComboBox<Object> cb, String[] lst){
        cb.setPreferredSize(new Dimension(150,30));
        for (String s : lst) {
            cb.addItem(s);
        }
        return cb;
    }

}
