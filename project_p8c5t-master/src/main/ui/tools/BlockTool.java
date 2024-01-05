package ui.tools;


import model.*;
import ui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class BlockTool extends Tool {
    protected MusicBlock block;
    MusicBoxApp newBox = super.box;
    JButton newButton = super.button;
    MusicStaff newStaff = newBox.getCurrentStaff();

    public BlockTool(MusicBoxApp box, JComponent parent) {
        super(box, parent);
        block = null;
    }

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
    @Override
    protected JButton createButton(JComponent parent) {
        newButton = new JButton("Make a note");
        newButton = super.customizeButton(newButton);
        //super.addToParent(parent);
        return newButton;
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        newButton.addActionListener(new ShapeToolClickHandler());
    }

    // MODIFIES: this
    // EFFECTS:  a shape is instantiated when MouseEvent occurs, and played and
    //           added to the editor's drawing
    @Override
    public void mouseClickedInStaffArea(MouseEvent e) {
        double column = e.getPoint().getX();
        if (!newStaff.noBlockAtColumn(column)) {
            System.out.println("No block left to add at this column");
        } else {
            System.out.println("There can be only 1 block in this column");
            makeBlock(e);
            block.selectAndPlay();
            newStaff.addBlock(block);
            // newBox.addToStaff(block); // why doesnt this work but above does?
        }
    }


    //EFFECTS: Constructs and returns a new block
    protected void makeBlock(MouseEvent e) {
        block = new MusicBlock(e.getPoint());

    }

    private class ShapeToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the shape tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            newBox.setActiveTool(BlockTool.this);
        }
    }
}