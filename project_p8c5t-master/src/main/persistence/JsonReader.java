package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads track from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads track from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Track readTrack() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTrack(jsonObject);
    }

    // EFFECTS: reads track from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MusicStaff readStaff() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStaff(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses track from JSON object and returns it
    private Track parseTrack(JSONObject jsonObject) {
        String name = jsonObject.getString("trackName");
        Track tr = new Track(name);
        addNotes(tr, jsonObject);
        return tr;
    }

    // EFFECTS: parses track from JSON object and returns it
    private MusicStaff parseStaff(JSONObject jsonObject) {
        MusicStaff staff = new MusicStaff();
        addBlocks(staff, jsonObject);
        return staff;
    }

    // MODIFIES: tr
    // EFFECTS: parses notes from JSON object and adds them to track
    private void addNotes(Track tr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("notes");
        for (Object json : jsonArray) {
            JSONObject nextNote = (JSONObject) json;
            add1Note(tr, nextNote);
        }
    }

    // MODIFIES: tr
    // EFFECTS: parses notes from JSON object and adds them to track
    private void addBlocks(MusicStaff staff, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("blocks");
        for (Object json : jsonArray) {
            JSONObject nextBlock = (JSONObject) json;
            add1Block(staff, nextBlock);
        }
    }

    // MODIFIES: tr
    // EFFECTS: parses note from JSON object and adds it to track
    private void add1Note(Track tr, JSONObject jsonObject) {
        String position = jsonObject.getString("position");
        Note note = new Note(Integer.parseInt(position));
        tr.addNoteNoReturn(note);
    }

    // MODIFIES: tr
    // EFFECTS: parses note from JSON object and adds it to track
    private void add1Block(MusicStaff staff, JSONObject jsonObject) {
        String x_position = jsonObject.getString("x_position");
        String y_position = jsonObject.getString("y_position");
        MusicBlock block = new MusicBlock(Integer.parseInt(x_position), Integer.parseInt(y_position));
        staff.addBlock(block);
    }
}
