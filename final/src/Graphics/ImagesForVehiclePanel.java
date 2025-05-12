package Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesForVehiclePanel extends JPanel {

    JPanel uploadPanel;
    String imgPath;
    String imgUploaded;
    String imgSelected;
    JLabel imgLabel;
    JButton[] buttonsArr;
    int buttonsArrSize;
    JPanel imgPanel;


    /**
     Constructs an ImagesForVehiclePanel object with an array of image file paths.
     The panel allows the user to choose between uploading a new image or selecting an existing image.
     If the user chooses to select an existing image, the imageButtonsPanel is displayed with the existing images.
     If the user chooses to upload an image, a button is displayed to allow the user to upload the image.
     @param imageList an array of image file paths
     */
    public ImagesForVehiclePanel(String[] imageList) {
        setBackground(Color.GRAY);
        JPanel selectPanel = new JPanel();
        JPanel imgOutputPanel = new JPanel();
        uploadPanel = new JPanel();
        uploadPanel.setBackground(Color.GRAY);
        imgLabel = new JLabel();

        JRadioButton radioButtonUpload = new JRadioButton("Upload Image");
        radioButtonUpload.setBackground(Color.GRAY);
        JRadioButton radioButtonSelect = new JRadioButton("Choose Image");
        radioButtonSelect.setBackground(Color.GRAY);
        JButton uploadImgButton = buttonUploadFile();
        uploadImgButton.setFocusable(false);
        imgPanel = imageButtonsPanel(imageList);



        uploadImgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == uploadImgButton) {
                    JFileChooser fileChooser = new JFileChooser();
                    int response = fileChooser.showOpenDialog(null);
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
                    fileChooser.addChoosableFileFilter(filter);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        String imgPath = file.getAbsolutePath();
                        setUploadPanel(imgPath);
                        validate();
                    }
                }
            }
        });

        radioButtonSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == radioButtonSelect) {
                    setImgPath(getImgSelected());
                    imgPanel.setVisible(true);
                    uploadImgButton.setVisible(false);
                    imgLabel.setVisible(false);
                }
            }
        });
        radioButtonUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == radioButtonUpload) {
                    setImgPath(getImgUploaded());
                    imgLabel.setVisible(true);
                    uploadImgButton.setVisible(true);
                    imgPanel.setVisible(false);
                }
            }
        });

        imgOutputPanel.setPreferredSize(new Dimension(50, 70));
        ButtonGroup group = new ButtonGroup();

        group.add(radioButtonUpload);
        group.add(radioButtonSelect);

        selectPanel.add(radioButtonUpload);
        selectPanel.add(radioButtonSelect);
        selectPanel.setBackground(Color.GRAY);

        imgOutputPanel.add(imgPanel);
        imgOutputPanel.setBackground(Color.GRAY);
        imgOutputPanel.setPreferredSize(new Dimension(50, 70));
        uploadPanel.add(uploadImgButton);

        imgOutputPanel.add(uploadPanel);
        selectPanel.setLayout(new FlowLayout());
        this.add(selectPanel);
        this.add(imgOutputPanel);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }
    /**
     * Sets the upload panel with an image specified by the path given as a parameter.
     * @param imgPath the path of the image to be displayed on the upload panel
     */
    public void setUploadPanel(String imgPath){
        imgLabel.setIcon(resizeImage(imgPath, 50, 50));
        imgLabel.setVisible(true);
        uploadPanel.add(imgLabel);
        setImgPath(imgPath);
        setImgUploaded(imgPath);
    }
    /**
     * get the image path
     * @return a String which represents the path of the currently selected image.
     */
    public String getImgPath(){
        return imgPath;
    }
    /**
     * Sets the image path of the panel to the given string.
     * @param imgPath a string representing the image path to be set
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    /**
     * This method returns the path of the image that has been uploaded by the user.
     * @return A String representing the path of the uploaded image.
     */
    public String getImgUploaded() {
        return imgUploaded;
    }

    /**
     * Sets the path of the uploaded image.
     * @param imgUploaded the path of the uploaded image
     */
    public void setImgUploaded(String imgUploaded) {
        this.imgUploaded = imgUploaded;
    }

    /**
     * get the selected image
     * @return the path of the currently selected image.
     */
    public String getImgSelected() {
        return imgSelected;
    }
    /**
     * Sets the path of the currently selected image to the provided parameter.
     * @param imgSelected The path of the currently selected image.
     */
    public void setImgSelected(String imgSelected) {
        this.imgSelected = imgSelected;
    }

    /**
     Resizes an image to the specified height and width.
     @param ImagePath the file path of the image to be resized
     @param height the desired height of the resized image
     @param width the desired width of the resized image
     @return an ImageIcon object representing the resized image
     */
    public ImageIcon resizeImage(String ImagePath, int height, int width) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
    /**
     Creates buttons with images apon them and borders.
     @param path the file path of the image to be used as the icon for the button
     @return a JButton with the specified image icon and ActionListener
     @throws RuntimeException if an IOException occurs when trying to read the image file
     */
    private JButton imageButton(String path) {

        BufferedImage buttonIcon = null;
        try {
            buttonIcon = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image dimg = buttonIcon.getScaledInstance(50, 50,
                Image.SCALE_SMOOTH);
        ImageIcon myImg = new ImageIcon(dimg);
        JButton button = new JButton(myImg);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        Border white = new LineBorder(Color.white, 2, true);
        button.setBorder(white);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == button){
                    setImgPath(path);
                    setImgSelected(path);
                    selectedButton(button);
                }
            }
        });

        return button;
    }

    /**
     *Add button to buttons array
     *@param button The JButton to be added to the array of buttons.
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
     * Sets the border of the selected button to green and resets the border of all other buttons
     * in the buttonsArr array to white.
     * @param button the button that was selected
     */
    public void selectedButton(JButton button){

        Border white = new LineBorder(Color.white, 2, true);
        Border green = new LineBorder(Color.green, 2, true);
        button.setBorder(green);
        for (int i = 0; i < buttonsArrSize; i++) {
            if(buttonsArr[i]!=button){
                buttonsArr[i].setBorder(white);
            }
        }
    }
    /**
     * Creates a JPanel with image buttons based on the provided path list.
     *
     * @param pathList an array of image paths for the buttons
     * @return the JPanel containing the image buttons
     */
    private JPanel imageButtonsPanel(String[] pathList)  {
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        for (String s : pathList) {
            JButton button = imageButton(s);
            addToButtons(button);
            panel.add(button);
        }
        panel.setLayout(new FlowLayout());
        panel.setVisible(false);
        return panel;
    }
    /**
     * Creates and returns a JButton for uploading an image.
     *
     * @return the JButton for uploading an image
     */
    private JButton buttonUploadFile(){
        JButton uploadImgButton = new JButton("Upload Image");
        uploadImgButton.setVisible(false);
        return uploadImgButton;
    }
}
