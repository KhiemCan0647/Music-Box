package ui;

import model.MusicStaff;
import ui.tools.Tool;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MusicBoxReload extends MusicBoxApp {
    public MusicBoxReload(MusicStaff staff) throws FileNotFoundException  {
        initializeFieldsReload(staff);
        super.initializeGraphics();
        super.initializeInteraction();
        repaint();
        revalidate();
        //validate();
    }

    protected void initializeFieldsReload(MusicStaff staff){
        activeTool = null;
        currentStaff = staff;
        tools = new ArrayList<Tool>();
    }
}