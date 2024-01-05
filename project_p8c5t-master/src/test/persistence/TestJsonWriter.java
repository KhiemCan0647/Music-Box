package persistence;

import model.*;

//import org.junit.Test;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

class TestJsonWriter extends TestJson {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Track tr = new Track("My track");
            JsonWriter writer = new JsonWriter("data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTrack() {
        try {
            Track tr = new Track("My track");
            JsonWriter writer =
                    new JsonWriter("data/testWriterEmptyTrack.json");
            writer.open();
            writer.writeTrack(tr);
            writer.close();

            JsonReader reader =
                    new JsonReader("data/testWriterEmptyTrack.json");
            tr = reader.readTrack();
            assertEquals("My track", tr.getTrackName());
            assertEquals(0, tr.getTrackSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTrack() {
        try {
            Track tr = new Track("My track");
            tr.addNote(new Note(25));
            tr.addNote(new Note(24));
            JsonWriter writer =
                    new JsonWriter("data/testWriterGeneralTrack.json");
            writer.open();
            writer.writeTrack(tr);
            writer.close();

            JsonReader reader =
                    new JsonReader("data/testWriterGeneralTrack.json");
            tr = reader.readTrack();
            assertEquals("My track", tr.getTrackName());
            List<Note> notes = tr.getNotes();
            assertEquals(2, notes.size());
            checkNote(25, "White", "25.C5.wav", notes.get(0));
            checkNote(24, "White", "24.B4.wav", notes.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

