package ui.tools;

import model.*;
import ui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class PlayStaffTool extends Tool {

    MusicBoxApp newBox = super.box;
    JButton newButton = super.button;

    public PlayStaffTool(MusicBoxApp box, JComponent parent) {
        super(box, parent);
    }

    // MODIFIES: this
    // EFFECTS:  creates a new "Play The Whole Drawing" button and invokes addToParent() on the
    //           parent passed to this method
    @Override
    protected JButton createButton(JComponent parent) {
        newButton = new JButton("Play the staff");
        newButton = super.customizeButton(newButton);
        //super.addToParent(parent);
        return newButton;
    }

    // MODIFIES: this
    // EFFECTS:  constructs an ActionListener which is then added to this object's JButton
    @Override
    protected void addListener() {
        newButton.addActionListener(new PlayTrackToolClickHandler());
    }

    // EFFECTS: initiates the playing of the DrawingPlayer
    private void playMusicTrack() {
        MusicStaff staff = newBox.getCurrentStaff();
        ArrayList<MusicBlock> listOfBlocks = (ArrayList) staff.getBlocks();
        for (MusicBlock b : listOfBlocks) {
            try {
                Thread.sleep(650);
                b.playMusicBlock();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    private class PlayTrackToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the PlayDrawing Tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            playMusicTrack();
        }
    }
}
