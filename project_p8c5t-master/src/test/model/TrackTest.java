////Questions for TA: what can't I declare those testTrack, testTrack3... inside @Before each, why doesn't it work?
//// i.e.
//// in vice versa, why cant i put the instantiation of testTrack to outside beforeEach?
//
//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TrackTest {
//    private Track testTrack;
//    private Track testTrack2;
//    private Track testTrack3;
//    private Track testTrack4;
//    private Note n0;
//    private Note n1;
//    private Note n2;
//    private Note n3;
//
//    @BeforeEach
//    void beforeEach() {
//        testTrack = new Track("testTrack");
//        testTrack2 = new Track("testTrack2");
//        testTrack3 = new Track("testTrack3");
//        testTrack4 = new Track("testTrack4");
//        n0 = new Note(1);
//        n1 = new Note(2);
//        n2 = new Note(3);
//        n3 = new Note(4);
//        testTrack.addNote(n1);
//        testTrack.addNote(n2);
//        testTrack.addNote(n3);
//        testTrack2.addNote(n0);
//        testTrack2.addNote(n1);
//        testTrack3.addNote(n1);
//        testTrack3.addNote(n2);
//        testTrack3.addNote(n3);
//        testTrack4.addNote(n3);
//        testTrack4.addNote(n1);
//        testTrack4.addNote(n2);
//        testTrack4.addNote(n3);
//    }
//
//    @Test
//    void testConstructor() {
//
//        assertEquals(3, (testTrack.getNotes().size()));
//        assertEquals(2, (testTrack2.getNotes()).size());
//        assertEquals((testTrack.getNotes()), (testTrack3.getNotes()));
//
//    }
//
//    @Test
//    void testAddNote() {
//        assertEquals(5, (testTrack4.addNote(n3)).size());
//    }
//
//    @Test
//    void testContainNote() {
//        assertTrue(testTrack.containNote(n1));
//        assertFalse(testTrack.containNote(n0));
//    }
//
//    @Test
//    void testGetNote() {
//        assertEquals(testTrack.getNotes(), testTrack3.getNotes());
//    }
//
//    @Test
//    void testRemoveNote() {
//        assertEquals(testTrack.getNotes(), testTrack4.removeNote(n3));
//    }
//
//    @Test
//    void testGetNextNote() {
//        assertEquals(n3, testTrack4.getNextNote());
//        assertEquals(n0, testTrack2.getNextNote());
//    }
//
//    @Test
//    void testPlayTrack() {
//        for (int i = 0; i < testTrack.getNotes().size(); i++) {
//            File msPath = new File(testTrack.getNotes().get(i).getFilename());
//            assertTrue(msPath.exists());
//        }
//    }
//}
//
