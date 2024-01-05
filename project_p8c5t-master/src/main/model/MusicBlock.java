package model;

import org.json.JSONObject;
import persistence.*;
import ui.*;

import java.awt.*;

public class MusicBlock implements Writable {
    public Color PLAYING_COLOR;
    public int x;
    public int y;
    private static final int BLOCK_HEIGHT = MusicBoxApp.INTERACTIVE_HEIGHT / 25;
    private static final int BLOCK_WIDTH = BLOCK_HEIGHT;

    private Note note;

    private boolean selected;
    private int playLineCoord;


    public MusicBlock(Point centre) {// the block is placed at the centre of the rectangle
        this((int) centre.getX(), (int) centre.getY()); // calls the below constructor
        selected = false;
        playLineCoord = 0;
        PLAYING_COLOR = new Color(248, 198, 123);
    }


    public MusicBlock(int x, int y) { // no matter the mouse click, a music at that place must be made
        int a = x % BLOCK_WIDTH;
        int b = y % BLOCK_HEIGHT;
        if (0 < a && a < BLOCK_WIDTH) {
            a = BLOCK_WIDTH;
        } else {
            a = 0;
        }
        if (0 < b && b < BLOCK_HEIGHT) {
            b = BLOCK_HEIGHT;
        } else {
            b = 0;
        }
        int c = 0;
        if (b != 0) {
            c = 1;
        }

        this.x = (x / BLOCK_WIDTH) * BLOCK_WIDTH + a - BLOCK_WIDTH / 2;
        this.y = (y / BLOCK_HEIGHT) * BLOCK_HEIGHT + b - BLOCK_HEIGHT / 2;
        note = new Note((y / BLOCK_HEIGHT) + c);
    }

    // getters
    public int getBlockWidth() {
        return BLOCK_WIDTH;
    }

    // setters !!!
    public void setPlayLineCoord(int playLineCoord) {
        this.playLineCoord = playLineCoord;
    }

    // EFFECTS: return true iff the given x value is within the bounds of the Music BLock
    public boolean containsX(double n) {
        return ((this.x - BLOCK_WIDTH / 2) < n) && (n < (this.x + BLOCK_WIDTH / 2));
    }

    // EFFECTS: return true iff the given y value is within the bounds of the Shape
    public boolean containsY(double n) {
        return ((this.y - BLOCK_HEIGHT / 2) < n) && (n < (this.y + BLOCK_HEIGHT / 2));
    }

    // EFFECTS: return true if the given Point (x,y) is contained within the bounds of this Block
    public boolean contains(Point point) {
        double point_x = point.getX();
        double point_y = point.getY();

        return containsX(point_x) && containsY(point_y);
    }

    // EFFECTS: draws this block on the staff, if the Block is selected then it is filled in
    //          else, the block is unfilled (white)
    public void draw(Graphics g) {
        Color save = g.getColor();
        if (selected) {
            g.setColor(PLAYING_COLOR);
        } else {
            g.setColor(Color.red);
        }
        fillGraphics(g);// fill based on the graphic's color
        g.setColor(save);
        drawGraphics(g);

    }

    // MODIFIES: this
    // EFFECTS:  selects this Block, plays associated Note
    public void selectAndPlay() {
        if (!selected) {
            selected = true;
            playMusicBlock();
        }
    }

    // MODIFIES: this
    // EFFECTS:  unselects this Shape, stops playing associated sound
    public void unselect() {
        if (selected) {
            selected = false;
        }
    }

    //EFFECTS: draws the block
    private void drawGraphics(Graphics g) {
        g.drawRect(x - BLOCK_WIDTH / 2, y - BLOCK_HEIGHT / 2,
                BLOCK_WIDTH, BLOCK_HEIGHT);
    }

    //EFFECTS: fills the block
    private void fillGraphics(Graphics g) {
        g.fillRect(x - BLOCK_WIDTH / 2, y - BLOCK_HEIGHT / 2,
                BLOCK_WIDTH, BLOCK_HEIGHT);
    }

    //EFFECTS; clears the block
    private void clearGraphics(Graphics g){
        g.clearRect(x - BLOCK_WIDTH / 2, y - BLOCK_HEIGHT / 2,
                BLOCK_WIDTH, BLOCK_HEIGHT);
    }


    // EFFECTS: starts playing this block, where sound is dependent on the area/coordinates of the block
    public void playMusicBlock() {
        note.playNote();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("x_position", Integer.toString(x));
        json.put("y_position", Integer.toString(y));
        return json;
    }


}

