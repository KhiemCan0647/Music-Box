package ui;

import model.MusicBlock;
import model.MusicStaff;
import model.Note;
import model.Track;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Music Box Application
public class MusicBoxApp extends JFrame {
    protected static final String SAVED_TRACK = "project_p8c5t-master/data/savedTrack.json";
    protected Track msTrack;
    //private List<Note> msNotes;
    protected Scanner input;
    protected JsonWriter jsonWriterTrack;
    protected JsonReader jsonReaderTrack;

    protected static final String SAVED_STAFF2 = "project_p8c5t-master/data/savedStaff.json";
    protected JsonReader jsonReaderStaff2;

    public static final int DISPLAY_WIDTH = 750;
    public static final int INTERACTIVE_HEIGHT = 500;
    public static final int DISPLAY_HEIGHT = INTERACTIVE_HEIGHT + 0;
    protected List<Tool> tools;
    protected Tool activeTool;

    public MusicStaff currentStaff;

    public MusicBoxApp() throws FileNotFoundException {
        super("Music Box App");
        msTrack = new Track("msTrack");
        jsonWriterTrack = new JsonWriter(SAVED_TRACK);
        jsonReaderTrack = new JsonReader(SAVED_TRACK);
        jsonReaderStaff2 = new JsonReader(SAVED_STAFF2);
        initializeFields();
        initializeGraphics();
        initializeInteraction();
        runMusicBox();
    }

//    @Override
//    public JFrame(String title) throws HeadlessException {
//        super(title);
//        frameInit();
//    }

    // getters
    public MusicStaff getCurrentStaff() {
        return currentStaff;
    }

    protected void initializeInteraction() {
        StaffMouseListener dml = new StaffMouseListener();
        addMouseListener(dml);
        //addMouseMotionListener(dml);
    }


    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this MusicBoxApp will operate, and populates the tools to be used
    //           to manipulate this drawing
    protected void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        createTools();
        addNewStaff();
        //loadOldStaff();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  sets activeTool, currentStaff to null, and instantiates MusicStaff and tools with ArrayList
    //           this method is called by the MusicBoxApp constructor
    protected void initializeFields() {
        activeTool = null;
        //currentStaff = new MusicStaff(); // !!! unresolved: currentStaff = null;
        //currentStaff = new MusicStaff();
        try {
            currentStaff = jsonReaderStaff2.readStaff();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tools = new ArrayList<Tool>();
    }

    // MODIFIES: this
    // EFFECTS:  declares and instantiates a MusicStaff (newStaff), and adds it to staffs
    protected void addNewStaff() {
//        MusicStaff newStaff = new MusicStaff();
//        currentStaff = newStaff;
//        add(newStaff, BorderLayout.CENTER);
        add(currentStaff, BorderLayout.CENTER);
        validate();
        //validate();
    }

    //MODIFIES: this
    //EFFECTS: replace the currentStaff with the loaded MusicStaff
    public void loadOldStaff(MusicStaff oldStaff) {
        currentStaff = oldStaff;
        repaint();
        revalidate();
//        try {
//            new MusicBoxReload(oldStaff);
//            repaint();
//            revalidate();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }
//
//    //MODIFIES: this
//    //EFFECTS: renew the window with the loaded staff
//    public void refresh(MusicStaff oldStaff)
//    { currentStaff = oldStaff;
//       }

    // MODIFIES: this
    // EFFECTS:  adds given Block to currentStaff if the added column has no block at all
    public void addToStaff(MusicBlock b) {
        currentStaff.addBlock(b);
    }

    // MODIFIES: this
    // EFFECTS:  removes given Shape from currentDrawing
    public void removeFromStaff(MusicBlock b) {
        currentStaff.removeBlock(b);
    }

    // EFFECTS: if activeTool != null, then mouseClickedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
    protected void handleMouseClicked(MouseEvent e) {
        if (activeTool != null)
            activeTool.mouseClickedInStaffArea(e); //tool
        repaint();
    }

    // MODIFIES: this
    // EFFECTS:  sets the given tool as the activeTool
    public void setActiveTool(Tool anotherTool) {
        if (activeTool != null)
            activeTool.deactivate();
        anotherTool.activate();
        activeTool = anotherTool;
    }

    // EFFECTS: return blocks at given point at the currentStaff
    public MusicBlock getBlockInStaff(Point point) {
        return currentStaff.getBlocksAtPoint(point);
    }

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all tools
    protected void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0, 1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);

        BlockTool blockTool = new BlockTool(this, toolArea);
        tools.add(blockTool);

        DeleteTool deleteTool = new DeleteTool(this, toolArea);
        tools.add(deleteTool);

        PlayBlockTool playBlockTool = new PlayBlockTool(this, toolArea);
        tools.add(playBlockTool);

        PlayStaffTool playStaffTool = new PlayStaffTool(this, toolArea);
        tools.add(playStaffTool);

        SaveStaffTool saveStaffTool = new SaveStaffTool(this, toolArea);
        tools.add(saveStaffTool);

//        LoadStaffTool loadStaffTool = new LoadStaffTool(this, toolArea);
//        tools.add(loadStaffTool);

        setActiveTool(blockTool);
    }

    protected class StaffMouseListener extends MouseAdapter {

        // EFFECTS:Forward mouse clicked event to the active tool
        public void mouseClicked(MouseEvent e) {
            handleMouseClicked(translateEvent(e));
        }

        // EFFECTS: translates the mouse event to current drawing's coordinate system
        private MouseEvent translateEvent(MouseEvent e) {
            return SwingUtilities.convertMouseEvent(e.getComponent(), e, currentStaff);
        }
    }


    //    EFFECTS: run the music box application
    protected void runMusicBox() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you for using this music box!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
//        int i = Integer.parseInt(command);
//        if (i >= 1 && i <= 25) {
//            Note n = new Note(i);
//            addToTrack(n);
//            System.out.println(n); /.this fails probably because of not every command can be converted to int
        String[] noteString =
                {"1", "2", "3", "4", "5",
                        "6", "7", "8", "9", "10",
                        "11", "12", "13", "14", "15",
                        "16", "17", "18", "19", "20",
                        "21", "22", "23", "24", "25"
                };
        boolean member = Arrays.asList(noteString).contains(command);
        if (member) {
            addToTrack(new Note(Integer.parseInt(command)));
        } else if (command.equals("p")) {
            playCurrentNote();
        } else if (command.equals("d")) {
            deleteCurrentNote();
        } else if (command.equals("P")) {
            playWholeTrack();
        } else if (command.equals("s")) {
            saveTrack();
        } else if (command.equals("l")) {
            loadTrack();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes input

    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nEnter 1->25 for notes ranging from C3->C5:");
        System.out.println("\tp -> play the current note");
        System.out.println("\td -> delete the current note");
        System.out.println("\tP -> play the whole track");
        System.out.println("\ts -> save track to file");
        System.out.println("\tl -> load track from file");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: add a new note to the track
    private void addToTrack(Note n) {
        System.out.print("Inserted Notes:\n");
        msTrack.getNotes().add(n);
        //msTrack.addNote(n);
        for (int i = 0; i < msTrack.getNotes().size(); i++) {
            System.out.println(Integer.toString(i + 1) + "." + msTrack.getNotes().get(i).getPosition());
        }
        System.out.println("The most recently inserted note is " + n);
        n.playNote();
        System.out.println("The current notes of the track are: " + msTrack.getNotes());
    }
    // why is i <= msTrack  not valid?


    //EFFECTS: play the latest note had been added
    private void playCurrentNote() {
        if (msTrack.getNotes().size() == 0) {
            System.out.println("The track is empty, please insert a note before playing!");
        } else {
            System.out.println("The current note is now being played: ");
            msTrack.getNotes().get(msTrack.getNotes().size() - 1).playNote();
        }
    }

    //EFFECTS: play all notes in the track
    private void playWholeTrack() {
        if (msTrack.getNotes().size() == 0) {
            System.out.println("The track is empty, please insert a note before playing!");
        } else {
            System.out.println("Enjoy your beautiful song!");
            msTrack.playTrack();
        }
    }

    //MODIFIES: this
    //EFFECTS: delete the last note of the track
    private void deleteCurrentNote() {
        if (msTrack.getNotes().size() == 0) {
            System.out.println("The track is empty, please insert a note before deleting!");
        } else {
            msTrack.getNotes().remove(msTrack.getNotes().size() - 1);
            for (int i = 0; i < msTrack.getNotes().size(); i++) {
                System.out.println(Integer.toString(i + 1) + "." + msTrack.getNotes().get(i).getPosition());
            }
        }
    }

    private void saveTrack() {
        try {
            //msTrack = new Track("msTrack");
            //jsonWriter = new JsonWriter("project_p8c5t-phase2 git redo/data/SAVED_TRACK.json");
            jsonWriterTrack.open();
            jsonWriterTrack.writeTrack(msTrack);
            jsonWriterTrack.close();
            System.out.println("Saved " + msTrack.getTrackName() + " to " + SAVED_TRACK);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + SAVED_TRACK);
        }
    }

    private void loadTrack() {
        try {
            //msTrack = new Track("msTrack");
            msTrack = jsonReaderTrack.readTrack();
            System.out.println("Loaded " + msTrack.getTrackName() + " from " + SAVED_TRACK);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + SAVED_TRACK);
        }
    }
}
//    class MusicBoxReload {
//        private static final String SAVED_STAFF = "data/savedTrack.json";
//        public static final int INTERACTIVE_HEIGHT = 500;
//        public static final int DISPLAY_HEIGHT = INTERACTIVE_HEIGHT + 0;
//        private List<Tool> listOfTools;
//        private Tool anActiveTool;
//
//        public MusicStaff staffToBeReplaced;
//        public MusicBoxReload(MusicStaff replacingStaff) {
//            JFrame("Music Box App");
//            staffToBeReplaced = replacingStaff;
//            listOfTools = new ArrayList<Tool>();
//            anActiveTool = null;
//            initializeGraphics();
//            initializeInteraction();
////        }
//    public void MusicBoxReload(MusicStaff replacingStaff)
//    {
//        JFrame ("Music Box App Reloaded");
//
//    }



//        public void  MusicBoxReload(MusicStaff replacingStaff)
//        {
//            super("Music Box App Reloaded");
//            activeTool = null;
//            currentStaff = replacingStaff;
//            tools = new ArrayList<Tool>();
//            initializeGraphics();
//            initializeInteraction();
//        }




