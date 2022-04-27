import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Controller {
    final String musicFile = "music.txt";
    MusicPlayer player = new MusicPlayer();

    public void executeSequence(String sequence, Boolean play) {
        player.resetSequence();

        ArrayList<String> fourChars = new ArrayList<String>(
                Arrays.asList("BPM+", "BPM-", "bpm+", "bpm-"));

        HashMap<Character, Integer> instruments = new HashMap<Character, Integer>();
        instruments.put('0', -1);
        instruments.put(';', 76);
        instruments.put(',', 20);
        instruments.put(' ', 1);
        instruments.put((char) 10, 15);

        int i = 0;
        Boolean isLastNote = false;
        while (i < sequence.length()) {
            System.out.println(i);
            Boolean isCurNote = false;
            if ((i + 4 < sequence.length()) && fourChars.contains(sequence.substring(i, i + 4))) {
                int toAdd = 50;
                if (sequence.charAt(i) == 'b')
                    toAdd = 10;
                if (sequence.charAt(i + 3) == '-')
                    toAdd *= -1;

                player.setBPM(player.getBPM() + toAdd);
                i += 4;
            } else {
                char curChar = sequence.charAt(i);

                if (curChar >= 'A' && curChar <= 'G') {
                    isCurNote = true;
                    player.addNote(Character.toString(curChar));
                } else if (instruments.containsKey(curChar)) {
                    int instrument = instruments.get(curChar);
                    if (instrument == -1)
                        player.resetInstrument();
                    else
                        player.setInstrument(instrument);
                } else if (Character.isDigit(curChar))
                    player.setInstrument(curChar - '0');
                else if (curChar == '+')
                    player.setVolume(player.getVolume() * 2);
                else if (curChar == '-')
                    player.resetVolume();
                else if (curChar == '!')
                    player.setOctave(player.getOctave() + 1);
                else if (curChar == '?')
                    player.setOctave(player.getOctave() - 1);
                else if (curChar == '.')
                    player.resetOctave();
                else if (isLastNote) {
                    if ((curChar >= 'H' && curChar <= 'M') || (curChar >= 'h' && curChar <= 'm'))
                        player.setFlat();
                    else if ((curChar >= 'T' && curChar <= 'Z') || (curChar >= 't' && curChar <= 'z'))
                        player.setSharp();
                    else
                        player.repeatLastCommand();
                } else
                    player.addSilence();
                i++;
            }
            isLastNote = isCurNote;
        }
        if (play) {
            player.playSequence();
        }
    }

    public String loadSequence() {
        try {
            Path fileName = Path.of(musicFile);
            return Files.readString(fileName);
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

    public boolean saveSequence(String sequence) {
        try {
            OutputStream os = new FileOutputStream(musicFile);
            Writer wr = new OutputStreamWriter(os);
            BufferedWriter br = new BufferedWriter(wr);

            br.write(sequence);
            br.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public boolean saveSequenceMIDI() {
        return player.saveMIDI();
    }

}