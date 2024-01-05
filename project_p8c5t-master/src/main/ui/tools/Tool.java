package ui.tools;


import ui.*;
import persistence.*;
import model.*;

import javax.swing.*;
import java.awt.event.MouseEvent;


public abstract class Tool {

    protected JButton button;
    protected MusicBoxApp box;
    private boolean active;

    protected static final String SAVED_STAFF = "project_p8c5t-master/data/savedStaff.json";
    protected JsonWriter jsonWriterStaff;
    protected JsonReader jsonReaderStaff;
    protected MusicStaff toolStaff;

    public Tool(MusicBoxApp box, JComponent parent) {
        this.box = box;
        jsonWriterStaff = new JsonWriter(SAVED_STAFF);
        jsonReaderStaff = new JsonReader(SAVED_STAFF);
        toolStaff = new MusicStaff();
        this.button = createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
    }

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    // getters
    public boolean isActive() {
        return active;
    }

    // EFFECTS: sets this Tool's active field to true
    public void activate() {
        active = true;
    }

    // EFFECTS: sets this Tool's active field to false
    public void deactivate() {
        active = false;
    }

    // EFFECTS: creates button to activate tool
    protected abstract JButton createButton(JComponent parent);

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: default behaviour does nothing
    public void mouseClickedInStaffArea(MouseEvent e) {
    }

    // EFFECTS: default behaviour does nothing
    public void mousePressedInStaffArea(MouseEvent e) {
    }

    // EFFECTS: default behaviour does nothing
    public void mouseReleasedInStaffArea(MouseEvent e) {
    }


}
