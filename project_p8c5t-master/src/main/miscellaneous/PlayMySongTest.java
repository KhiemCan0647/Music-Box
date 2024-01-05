//package miscellaneous;
//
//import model.Note;
//import model.Track;
//
//import java.util.Arrays;
//import java.util.List;
//
//// Play the Anthem of Vietnam, by converting the music staff to "Note".
//public class PlayMySongTest {
//    public static void main(String[] args)
//            throws InterruptedException {
//        Track anthemTrack = new Track("anthemTrack");
//        Integer[] vaTrack = {6, 8, 6, 11, 11, 13, 11, 15, 15, 13, 11, 8, 11, 11, 8, 6, 3, 6, 11, 13, 15, 15, 15,
//                13, 11, 18, 15, 11, 13, 13, 15, 10, 6, 11, 15, 16, 18, 18, 20, 18, 15, 15, 13, 11, 6, 10, 10, 13, 11,
//                15, 16, 18, 18, 20, 18, 13, 15, 13, 11, 11, 6, 18, 15, 11, 20, 18, 15, 13, 6, 13,
//                13, 15, 11};
//        List<Integer> vnAnthem = Arrays.asList(vaTrack);
//        for (int i = 0; i < vnAnthem.size(); i++) {
//            anthemTrack.addNote(new Note(vnAnthem.get(i)));
//        }
//
//        for (int i = 0; i < anthemTrack.getNotes().size(); i++) {
//            Thread.sleep(650);
//            anthemTrack.getNotes().get(i).playNote();
////            (notesToPlay[i]).playMusic();
//        }
//    }
//}
