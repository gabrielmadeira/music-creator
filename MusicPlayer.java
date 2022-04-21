import java.util.*;
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

    private int volume = ORIGINAL_VOLUME;
    private int bpm = ORIGINAL_BPM;
    private int octave = ORIGINAL_OCTAVE;
    private int instrument = ORIGINAL_INSTRUMENT;

    private List<String> instrumentList = new ArrayList<String>(Arrays.asList("PIANO", "ELECTRIC_PIANO", "CHURCH_ORGAN",
            "GUITAR", "VIOLIN", "TRUMPET", "TROMBONE", "CLARINET", "FLUTE", "STEEL_DRUMS"));
    // "BANJO", "XYLOPHONE",
    // "ACOUSTIC_BASS","CELLO","TUBA","TENOR_SAX","HELICOPTER","TENOR_SAX","HELICOPTER""CELLO","TUBA","ACCORDIAN",
    private List<String> noteList = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));

    private String music = "I[" + instrument + "] T" + bpm; // TODO implement volume
    Player player = new Player();

    public int getVolume() {
        return volume;
    }

    public boolean setVolume(int value, boolean reset) {
        if (reset) {
            volume = ORIGINAL_VOLUME;
            return true;
        } else if ((value <= MAX_VOLUME) && (value >= MIN_VOLUME)) {
            volume = value;
            return true;
        }
        return false;
    }

    public void resetVolume() {
        volume = ORIGINAL_VOLUME;
    }

    public boolean doubleVolume() {
        if ((volume * 2) > MAX_VOLUME)
            return false;
        volume *= 2;
        return true;
    }

    public int getBPM() {
        return bpm;
    }

    public boolean setBPM(int value, boolean reset) {
        if (reset) {
            bpm = ORIGINAL_BPM;
            return true;
        } else if ((value <= MAX_BPM) && (value >= MIN_BPM)) {
            bpm = value;
            music += " T" + bpm;
            return true;
        }
        return false;
    }

    public boolean increaseBPM(int qtd) {
        if ((bpm + qtd) > MAX_BPM)
            return false;
        bpm += qtd;
        music += " T" + bpm;
        return true;
    }

    public boolean decreaseBPM(int qtd) {
        if ((bpm - qtd) < MIN_BPM)
            return false;
        bpm -= qtd;
        music += " T" + bpm;
        return true;
    }

    public int getOctave() {
        return octave;
    }

    public boolean setOctave(int value, boolean reset) {
        if (reset) {
            octave = ORIGINAL_OCTAVE;
            return true;
        } else if ((value <= MAX_OCTAVE) && (value >= MIN_OCTAVE)) {
            octave = value;
            return true;
        }
        return false;
    }

    public boolean increaseOctave() {
        if (octave < MAX_OCTAVE) {
            octave++;
            return true;
        }
        return false;
    }

    public boolean decreaseOctave() {
        if (octave > MIN_OCTAVE) {
            octave--;
            return true;
        }
        return false;
    }

    public int getInstrument() {
        return instrument;
    }

    public boolean setInstrument(int instrumentNumber, boolean reset) {
        if (reset) {
            instrument = ORIGINAL_INSTRUMENT;
        } else if (instrumentNumber >= MIN_INSTRUMENT && instrumentNumber <= MAX_INSTRUMENT) {
            instrument = instrumentNumber;
        } else {
            return false;
        }
        music += " I" + instrument;
        return true;
    }

    public boolean changeInstrument(int instrumentNumber) {
        if (instrumentNumber < 0 || instrumentNumber >= instrumentList.size())
            return false;
        music += " I[" + instrumentList.get(instrumentNumber) + "]";
        return true;
    }

    public boolean addNote(String note) {
        if (!noteList.contains(note)) {
            return false;
        }
        music += " " + note;
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

    public void playSequence() {
        System.out.println(music);
        player.play(music);
    }

    public void resetSequence() {
        bpm = ORIGINAL_BPM;
        volume = ORIGINAL_VOLUME;
        octave = ORIGINAL_OCTAVE;
        instrument = ORIGINAL_INSTRUMENT;

        music = "I" + instrument + " T" + bpm; // TODO implement volume
    }

    public List<String> getNoteList() {
        return noteList;
    }
}
