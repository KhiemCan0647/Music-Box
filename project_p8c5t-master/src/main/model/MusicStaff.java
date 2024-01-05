package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.*;
import ui.MusicBoxApp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MusicStaff extends JPanel implements Writable {

    private static final int MUSIC_LINES_SPACE = MusicBoxApp.INTERACTIVE_HEIGHT / 25;
    private List<MusicBlock> blocks;
    private int playLineColumn;


    public MusicStaff() {
        super();
        blocks = new ArrayList<MusicBlock>();
        setBackground(Color.white);
    }

    // getters
    public List<MusicBlock> getBlocks() {
        return this.blocks;
    }

    public int getPlayLineColumn() {
        return this.playLineColumn;
    }

    // setters
    public void setPlayLineColumn(int plc) {
        playLineColumn = plc;
    }

    // EFFECTS: return true if the given block b is contained in Drawing
    public boolean containsBLock(MusicBlock b) {
        return blocks.contains(b);
    }

    // EFFECTS: paints grid, playback line, and all blocks in drawing
    //          Note to students: calls to repaint gets here via the Java graphics framework
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHorizontalNotesLines(g);
        drawVerticalNotesLines(g);
        for (MusicBlock b : blocks) {
            b.draw(g);
        }
    }

    // EFFECTS: draws grid with lines GRIDSPACE apart, and draws red line at its current position
    private void drawHorizontalNotesLines(Graphics g) {
        Color save = g.getColor();
        g.setColor(new Color(10, 10, 10));
        for (int y = 0; y < getHeight(); y += MUSIC_LINES_SPACE) {
            g.drawLine(0, y, getWidth(), y);
        }
        if (0 < playLineColumn && playLineColumn < getWidth()) {
            g.setColor(Color.GREEN);
            g.drawLine(playLineColumn, 0, playLineColumn, getHeight());
        }
        g.setColor(save);
    }

    private void drawVerticalNotesLines(Graphics g) {
        Color save = g.getColor();
        g.setColor(new Color(10, 10, 10));
        for (int x = 0; x < getWidth(); x += MUSIC_LINES_SPACE) {
            g.drawLine(x, 0, x, getHeight());
        }
        g.setColor(save);
    }

    // MODIFIES: this
    // EFFECTS:  adds the given block to the staff
    public void addBlock(MusicBlock b) {
        blocks.add(b);
    }


    // MODIFIES: this
    // EFFECTS:  removes block from the staff
    public void removeBlock(MusicBlock b) {
        blocks.remove(b);
        repaint();
    }

    // EFFECTS: returns the block at a given Point in the staff, if any
    public MusicBlock getBlocksAtPoint(Point point) {
        for (MusicBlock b : blocks) {
            if (b.contains(point)) {
                return b;
            }
        }
        return null;
    }

    // EFFECTS: returns all blocks at given column corresponding to an x-coordinate
    public List<MusicBlock> getBlocksAtColumn(double x) {
        List<MusicBlock> blocksAtColumn = new ArrayList<MusicBlock>();
        for (MusicBlock b : blocks) {
            if (b.containsX(x)) {
                blocksAtColumn.add(b);
            }
        }
        return blocksAtColumn;
    }

    //EFFECTS: return true if the current column has more than 1 block
    public boolean noBlockAtColumn(double column) {
        List<MusicBlock> blocksAtColumn = new ArrayList<MusicBlock>();
        for (MusicBlock b : blocks) {
            if (b.containsX(column)) {
                blocksAtColumn.add(b);
            }
        }
        if (blocksAtColumn.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("blocks", blocksToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray blocksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (MusicBlock t : blocks) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}

