package persistence;

import model.Note;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJson {
    protected void checkNote(Integer p, String c, String file, Note n) {
        assertEquals(p, n.getPosition());
        assertEquals(c, n.getColor());
        assertEquals(file, n.getFilename());
    }
}