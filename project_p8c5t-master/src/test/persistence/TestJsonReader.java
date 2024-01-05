package persistence;

import model.Note;
import model.Track;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestJsonReader extends TestJson {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("data/noSuchFile.json");
        try {
            Track tr = reader.readTrack();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTrack() {
        JsonReader reader = new JsonReader("data/testReaderEmptyTrack.json");
//        JsonReader reader = new JsonReader("./data/testReaderEmptyTrack.json");

        try {
            Track tr = reader.readTrack();
            assertEquals("My track", tr.getTrackName());
            assertEquals(0, tr.getTrackSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTrack() {
        JsonReader reader = new JsonReader("data/testReaderGeneralTrack.json");
        try {
            Track tr = reader.readTrack();
            assertEquals("My track", tr.getTrackName());
            List<Note> notes = tr.getNotes();
            assertEquals(2, notes.size());
            checkNote(1, "White", "1.C3.wav", notes.get(0));
            checkNote(2, "Black", "2.C#3.wav", notes.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}