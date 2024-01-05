package ui.tools;

import model.*;
import ui.*;
import persistence.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


public class SaveStaffTool extends Tool {

    MusicBoxApp newBox = super.box;
    JButton newButton = super.button;
    JsonWriter newWriter = super.jsonWriterStaff;

    public SaveStaffTool(MusicBoxApp box, JComponent parent) {
        super(box, parent);
    }

    // MODIFIES: this
    // EFFECTS:  creates a new "Play The Whole Drawing" button and invokes addToParent() on the
    //           parent passed to this method
    @Override
    protected JButton createButton(JComponent parent) {
        newButton = new JButton("Save the current staff");
        newButton = super.customizeButton(newButton);
        return newButton;
    }

    // MODIFIES: this
    // EFFECTS:  constructs an ActionListener which is then added to this object's JButton
    @Override
    protected void addListener() {
        newButton.addActionListener(new LoadStaffToolClickHandler());
    }

    // EFFECTS: initiates the playing of the DrawingPlayer
    private void saveMusicStaff() {
        MusicStaff staff = newBox.getCurrentStaff();
        try {
            newWriter.open();
            newWriter.writeStaff(staff);
            newWriter.close();
            System.out.println("Saved " + "the current Staff" + " to " + super.SAVED_STAFF);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + super.SAVED_STAFF);
        }


    }

    private class LoadStaffToolClickHandler implements ActionListener {
        // EFFECTS: sets active tool to the PlayDrawing Tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            saveMusicStaff();
        }
    }
}

