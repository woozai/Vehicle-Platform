package Graphics;

import javax.swing.*;

public class InputErrorOption extends JOptionPane {
    /**
     Displays an error message dialog box with the specified message.
     The dialog box has a default option and an error message icon.
     @param msg the error message to be displayed.
     */
    public InputErrorOption(String msg){
        ImageIcon icon = new ImageIcon("Pictures\\success.png");
        JOptionPane.showOptionDialog(null,
                msg, "Error", JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,null,
                null, 0);


    }
}
