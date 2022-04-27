import java.io.File;
import java.util.*;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.*;

public class MusicPlayer {
    final int MIN_VOLUME = 4080;
    final int MAX_VOLUME = 16320;
    final int ORIGINAL_VOLUME = 4080;
    final int MIN_BPM = 10;
    final int MAX_BPM = 210;
    final int ORIGINAL_BPM = 110;
    final int MIN_OCTAVE = 0;
    final int MAX_OCTAVE = 10;
    final int ORIGINAL_OCTAVE = 5;
    final int MIN_INSTRUMENT = 0;
    final int MAX_INSTRUMENT = 127;
    final int ORIGINAL_INSTRUMENT = 57; // Trumpet;

    final String midiFile = "music.mid";

    private int volume = ORIGINAL_VOLUME;
    private int bpm = ORIGINAL_BPM;
    private int octave = ORIGINAL_OCTAVE;
    private int instrument = ORIGINAL_INSTRUMENT;

    private List<String> noteList = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));

    private String music = "I[" + instrument + "] T" + bpm + " :CON(935, " + volume + ") ";
    Player player = new Player();

    public int getVolume() {
        return volume;
    }

    public boolean setVolume(int value) {
        if ((value <= MAX_VOLUME) && (value >= MIN_VOLUME)) {
            volume = value;
            music += " :CON(935, " + volume + ") ";
            return true;
        }
        return false;
    }

    public void resetVolume() {
        volume = ORIGINAL_VOLUME;
        music += " :CON(935, " + volume + ") ";
    }

    public int getBPM() {
        return bpm;
    }

    public boolean setBPM(int value) {
        if ((value <= MAX_BPM) && (value >= MIN_BPM)) {
            bpm = value;
            music += " T" + bpm;
            return true;
        }
        return false;
    }

    public int getOctave() {
        return octave;
    }

    public boolean setOctave(int value) {
        if ((value <= MAX_OCTAVE) && (value >= MIN_OCTAVE)) {
            octave = value;
            return true;
        }
        return false;
    }

    public void resetOctave() {
        octave = ORIGINAL_OCTAVE;
    }

    public int getInstrument() {
        return instrument;
    }

    public boolean setInstrument(int instrumentNumber) {
        if (instrumentNumber >= MIN_INSTRUMENT && instrumentNumber <= MAX_INSTRUMENT) {
            instrument = instrumentNumber;
            music += " I" + instrument;
            return true;
        }
        return false;
    }

    public void resetInstrument() {
        instrument = ORIGINAL_INSTRUMENT;
    }

    public boolean addNote(String note) {
        if (!noteList.contains(note)) {
            return false;
        }
        music += " " + note + octave;
        return true;
    }

    public void addSilence() {
        music += " R";
    }

    public void repeatLastCommand() {
        String lastCommand = "";
        boolean foundSpace = false;
        int i = music.length() - 1;
        while (!foundSpace) {
            if (music.charAt(i) == ' ') {
                foundSpace = true;
            } else {
                lastCommand += music.charAt(i);
                i--;
            }
        }
        lastCommand = new StringBuilder(lastCommand).reverse().toString();
        music += " " + lastCommand;
    }

    public boolean saveMIDI() {
        try {
            MidiFileManager.savePatternToMidi(new Pattern(music), new File(midiFile));
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public void playSequence() {
        System.out.println(music);
        player.play(music);
    }

    public void resetSequence() {
        bpm = ORIGINAL_BPM;
        volume = ORIGINAL_VOLUME;
        octave = ORIGINAL_OCTAVE;
        instrument = ORIGINAL_INSTRUMENT;

        music = "I" + instrument + " T" + bpm + " :CON(935, " + volume + ") ";
    }

    public void setFlat() {
        int pos = music.length() - 1;
        music = music.substring(0, pos) + 'b' + music.charAt(pos);
    }

    public void setSharp() {
        int pos = music.length() - 1;
        music = music.substring(0, pos) + '#' + music.charAt(pos);
    }

}
