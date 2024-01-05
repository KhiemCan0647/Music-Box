//package ui.tools;
//
//import model.*;
//import ui.*;
//import persistence.*;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//
//
//public class LoadStaffTool extends Tool {
//
//    MusicBoxApp newBox = super.box;
//    JButton newButton = super.button;
//    JsonReader newReader = super.jsonReaderStaff;
//    MusicStaff newStaff = newBox.getCurrentStaff();
//
//    public LoadStaffTool(MusicBoxApp box, JComponent parent) {
//        super(box, parent);
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  creates a new "Play The Whole Drawing" button and invokes addToParent() on the
//    //           parent passed to this method
//    @Override
//    protected JButton createButton(JComponent parent) {
//        newButton = new JButton("Load the saved staff");
//        newButton = super.customizeButton(newButton);
//        //super.addToParent(parent);
//        return newButton;
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  constructs an ActionListener which is then added to this object's JButton
//    @Override
//    protected void addListener() {
//        newButton.addActionListener(new LoadStaffToolClickHandler());
//    }
//
//    // EFFECTS: initiates the playing of the DrawingPlayer
//    private void loadMusicStaff() {
//        {
//            try {
//                newStaff = newReader.readStaff();
//                System.out.println("Loaded " + "the last staff" + " from " + super.SAVED_STAFF);
//                //newStaff.paintComponent();
//                //newStaff.paintComponent();
//                //newBox = new MusicBoxApp();
//                newBox.loadOldStaff(newStaff);
//                newBox.repaint();
//                newBox.revalidate();
//                //newBox.refresh(newStaff);
//                //return newStaff;
//            } catch (IOException e) {
////                System.out.println("Unable to read from file: " + super.SAVED_STAFF);
////                return null;
//            }
//        }
//    }
//
//    private class LoadStaffToolClickHandler implements ActionListener {
//        // EFFECTS: sets active tool to the PlayDrawing Tool
//        //          called by the framework when the tool is clicked
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            loadMusicStaff();
//        }
//    }
//}
