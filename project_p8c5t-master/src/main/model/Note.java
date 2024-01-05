package model;

import org.json.JSONObject;
import persistence.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

// Represent a note on the music staff
public class Note implements Writable {
    private final int position;
    // the position of the note, ranging from 1 to 25 (C note at the ledger below the music staff)
    // to the ledger above the music staff
    private String color;
    // the color of the note, either black or white
    private String fileName;

    Integer[] bwNotePosition = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    Integer[] blackNotePosition = {2, 4, 7, 9, 11, 14, 16, 19, 21, 23};
    String[] bwFileName = {"project_p8c5t-master/target/1.C3.wav", "project_p8c5t-master/target/2.C#3.wav",
            "project_p8c5t-master/target/3.D3.wav", "project_p8c5t-master/target/4.D#3.wav", "project_p8c5t-master/target/5.E3.wav",
            "project_p8c5t-master/target/6.F3.wav", "project_p8c5t-master/target/7.F#3.wav",
            "project_p8c5t-master/target/8.G3.wav", "project_p8c5t-master/target/9.G#3.wav", "project_p8c5t-master/target/10.A3.wav",
            "project_p8c5t-master/target/11.A#3.wav", "project_p8c5t-master/target/12.B3.wav", "project_p8c5t-master/target/13.C4.wav",
            "project_p8c5t-master/target/14.C#4.wav", "project_p8c5t-master/target/15.D4.wav", "project_p8c5t-master/target/16.D#4.wav",
            "project_p8c5t-master/target/17.E4.wav", "project_p8c5t-master/target/18.F4.wav", "project_p8c5t-master/target/19.F#4.wav",
            "project_p8c5t-master/target/20.G4.wav", "project_p8c5t-master/target/21.G#4.wav", "project_p8c5t-master/target/22.A4.wav",
            "project_p8c5t-master/target/23.A#4.wav", "project_p8c5t-master/target/24.B4.wav", "project_p8c5t-master/target/25.C5.wav"};
    List<Integer> bwNote = Arrays.asList(bwNotePosition);
    List<Integer> blackNote = Arrays.asList(blackNotePosition);
    List<String> bwFile = Arrays.asList(bwFileName);


    // REQUIRES: number must be in the range of 1-25
    // EFFECTS: the number dictates the position of notes on the music staff, the color of the notes, and
    // the sound of that note
    public Note(int number) {
        if (number > 25) {
            position = 0;
            fileName = "project_p8c5t-master/target/Alarm.wav";
            color = "Red";
        } else {
            position = number;
            fileName = bwFile.get(number - 1);
            if (blackNote.contains(number)) {
                color = "Black";
            } else {
                color = "White";
            }
        }

    }

    //    EFFECTS: return the note position on the music staff
    public int getPosition() {
        return position;
    }

    //    EFFECTS: return the color of the note
    public String getColor() {
        return color;
    }


    // EFFECTS: return the note's file name
    public String getFilename() {
        return fileName;
    }

    //    EFFECTS: Play the note corresponding audio file
    public void playNote() {
        try {
            File musicPath = new File(fileName);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

            } else {
                File emptyMusicPath = new File("project_p8c5t-master/target/Alarm.wav");
                AudioInputStream nullAudioInput = AudioSystem.getAudioInputStream(emptyMusicPath);
                Clip clip2 = AudioSystem.getClip();
                clip2.open(nullAudioInput);
                clip2.start();
                JOptionPane.showMessageDialog(null, "Audio file not found");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("position", Integer.toString(position));
        json.put("color", color);
        json.put("fileName", fileName);
        return json;
    }
}


