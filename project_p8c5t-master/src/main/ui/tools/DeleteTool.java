package ui.tools;

import model.MusicBlock;
import ui.MusicBoxApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class DeleteTool extends Tool {

    private MusicBlock blockToDelete;
    MusicBoxApp newBox = super.box;
    JButton newButton = super.button;

    public DeleteTool(MusicBoxApp box, JComponent parent) {
        super(box, parent);
        blockToDelete = null;
    }

    // MODIFIES: this
    // EFFECTS:  constructs a delete button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
    @Override
    protected JButton createButton(JComponent parent) {
        newButton = new JButton("Delete a note");
        //super.addToParent(parent);
        return newButton;
    }

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        newButton.addActionListener(new DeleteToolClickHandler());
    }

    @Override
    public void mouseClickedInStaffArea(MouseEvent e) {
        deleteBlockAt(e.getPoint());
    }

    // EFFECTS: creates a BlockPlayer playing the current block and starts it playing
    private void deleteBlockAt(Point p) {
        MusicBlock block = newBox.getBlockInStaff(p);
        if (block != null) {
            newBox.getCurrentStaff().removeBlock(block);
        }
    }

    private class DeleteToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            newBox.setActiveTool(DeleteTool.this);
        }
    }
}
