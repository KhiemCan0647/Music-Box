package ui.tools;


import model.*;
import ui.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class PlayBlockTool extends Tool {
    MusicBoxApp newBox = super.box;
    JButton newButton = super.button;

    public PlayBlockTool(MusicBoxApp newBox, JComponent parent) {
        super(newBox, parent);
    }

    // EFFECTS: selects the figure containing point of mouse press
    @Override
    public void mouseClickedInStaffArea(MouseEvent e) {
        playBlockAt(e.getPoint());
    }

    // MODIFIES: this
    // EFFECTS:  creates a new "Play Block" button and invokes addToParent() on the
    //           parent passed to this method
    @Override
    protected JButton createButton(JComponent parent) {
        newButton = new JButton("Play a Note (individually)");
        newButton = super.customizeButton(newButton);
        //super.addToParent(parent);
        return newButton;
    }

    // MODIFIES: this
    // EFFECTS:  sets the activeTool in button to this when clicked
    @Override
    protected void addListener() {
        newButton.addActionListener(new PlayShapeToolClickHandler());
    }

    // EFFECTS: creates a BlockPlayer playing the current block and starts it playing
    private void playBlockAt(Point p) {
        MusicBlock block = newBox.getBlockInStaff(p);
        if (block != null) {
            block.playMusicBlock();
        }
    }

    private class PlayShapeToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the PlayShape tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            newBox.setActiveTool(PlayBlockTool.this);
        }
    }
}
