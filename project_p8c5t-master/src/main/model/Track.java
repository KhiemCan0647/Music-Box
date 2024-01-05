package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.*;

import java.util.ArrayList;
import java.util.List;

// Track is a collection of Note, which can modified and played based on Notes
public class Track implements Writable {
    private String trackName;
    private List<Note> notes;

    public Track(String trackName) {
        this.trackName = trackName;
        notes = new ArrayList<Note>();/// should i use linked or array?
    }

    //get the track's name
    public String getTrackName() {
        return trackName;
    }

    //getter
    public List<Note> getNotes() {
        return this.notes;
    }

    //EFFECTS: return true if the given Note n is contained in Track
    public boolean containNote(Note n) {
        return notes.contains(n);
    }

    //MODIFIES: this
    //EFFECT: add a given note to the track and return the whole track
    public List<Note> addNote(Note n) {
        notes.add(n);
        return notes;
    }

    //MODIFIES: this
    //EFFECT: add a given Note to the track
    public void addNoteNoReturn(Note n) {
        notes.add(n);
    }


    //MODIFIES: this
    //EFFECTS: removes note from the track
    public List<Note> removeNote(Note n) {
        notes.remove(n);
        return notes;
    }

    //EFFECTS: play the whole track
    public void playTrack() {
        for (int i = 0; i < notes.size(); i++) {
            try {
                Thread.sleep(650);
                notes.get(i).playNote();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
//    You can also use this version
//    public void playTrack() throws InterruptedException {
//        for (int i = 0; i < notes.size(); i++) {
//            Thread.sleep(650);
//            notes.get(i).playNote();
//        }
//    }

    public Note getNextNote() {

        return notes.remove(0);

    }

    public Integer getTrackSize() {
        return notes.size();
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("trackName", trackName);
        json.put("notes", notesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray notesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Note t : notes) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}





