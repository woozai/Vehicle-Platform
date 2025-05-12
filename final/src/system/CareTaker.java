package system;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    public List<Memento> getMementoList() {
        return mementoList;
    }

    private final List<Memento> mementoList = new ArrayList<Memento>(3);

    /**
     Adds a memento object to the list of mementos.
     @param state the memento object to be added
     */
    public void add(Memento state){
        if (mementoList.size() == 3){
            mementoList.remove(0);
        }
        mementoList.add(state);

    }

    /**
     Retrieves and removes the most recent memento from the memento list.
     @param loadButton the load button associated with the memento list
     @return the most recent memento object
     */
    public Memento get(JButton loadButton){
        Memento memento = mementoList.get(mementoList.size() - 1);
        mementoList.remove(mementoList.size() - 1);
        if (mementoList.isEmpty()){
            loadButton.setEnabled(false);
        }
        return memento;
    }
}
