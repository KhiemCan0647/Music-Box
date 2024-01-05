//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class NoteTest {
//    private Note testNote;
//    private Note testNote2;
//    private Note testNote5;
//    private Note testNote3;
//    private Note testNote4;
//    private Track track1;
//    private Integer number;
//
//    @BeforeEach
//    void runBefore() {
//        testNote = new Note(1);
//        testNote2 = new Note(2);
//        testNote3 = new Note(3);
//        testNote4 = new Note(4);
//        testNote5 = new Note(5);
//        track1 = new Track("track1");
//        track1.addNote(testNote);
//        track1.addNote(testNote2);
//        track1.addNote(testNote5);
//    }
//
//    @Test
//    void testConstructor() {
//        assertEquals(1, testNote.getPosition());
//        assertEquals("White", testNote.getColor());
//        assertEquals("1.C3.wav", testNote.getFilename());
//        //assertEquals(testNote2.playMusic(),testNote3.playMusic());
//    }
//
//    @Test
//    void testGetPosition() {
//        assertEquals(1, testNote.getPosition());
//        assertEquals(2, testNote2.getPosition());
//    }
//
//    @Test
//    void testGetColor() {
//        assertEquals("White", testNote.getColor());
//        assertEquals("Black", testNote2.getColor());
//        assertEquals("White", testNote3.getColor());
//    }
//
//    @Test
//    void testGetFilename() {
//        assertEquals("1.C3.wav", testNote.getFilename());
//        assertEquals("2.C#3.wav", testNote2.getFilename());
//    }
//
//    @Test
//    void testPlayNote() {
//        File msPath = new File(testNote.getFilename());
//        assertTrue(msPath.exists());
//        File msPath5 = new File(testNote5.getFilename());
//        assertTrue(msPath5.exists());
//    }
//}
//
