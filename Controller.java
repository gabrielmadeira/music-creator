import java.util.Random;

public class Controller {
    MusicPlayer player = new MusicPlayer();

    // A C D B BPM+ A D B E
    // -> [A, C, D, B, BPM+, A, D, B, E]

    // A,a,B,b,C,c,D,d,E,e,F,f,G,g,
    // ,+,-,I,i,O,o,U,u,T+,T-,?,.,NL[],BPM+,bpm+,BPM-,bpm-
    // + -> dobrar volume
    // - -> reset volume
    // I,i,O,o,U,u -> repetir nota
    // T+/T- -> aumentar/diminuir 1 oitava
    // ? ->dim

    // . -> reseta oitava oitav a oita/!->au/
    // BPM+/BPM- -> aumenta/diminui 50 bpm
    // bpm+/bpm- -> aumenta/diminui 10 bpm
    // outros caracteres -> NOP

    private String randomNote() {
        Random rand = new Random();
        return player.getNoteList().get(rand.nextInt(player.getNoteList().size()));
    }

    public void executeSequence(String sequence) {
        player.resetSequence();
        // TODO refatorar
        int i = 0;
        while (i < sequence.length()) {
            char currentChar = sequence.charAt(i);
            char currentCharUpper = Character.toUpperCase(currentChar);
            if (player.getNoteList().contains(String.valueOf(currentCharUpper)) && currentCharUpper != 'B') {
                player.addNote(String.valueOf(currentCharUpper));
            } else if (currentChar == 'B') {
                if ((i + 3 < sequence.length()) && (sequence.charAt(i + 1) == 'P') && (sequence.charAt(i + 2) == 'M')) {
                    if (sequence.charAt(i + 3) == '+') {
                        player.setBPM(player.getBPM() + 50, false);
                        i += 3;
                    } else if (sequence.charAt(i + 3) == '-') {
                        player.setBPM(player.getBPM() - 50, false);
                        i += 3;
                    } else
                        player.addNote("B");
                } else
                    player.addNote("B");
            } else if (currentChar == 'b') {
                if ((i + 3 < sequence.length()) && (sequence.charAt(i + 1) == 'p') && (sequence.charAt(i + 2) == 'm')) {
                    if (sequence.charAt(i + 3) == '+') {
                        player.setBPM(player.getBPM() + 50, false);
                        i += 3;
                    } else if (sequence.charAt(i + 3) == '-') {
                        player.setBPM(player.getBPM() - 50, false);
                        ;
                        i += 3;
                    } else
                        player.addNote("B");
                } else
                    player.addNote("B");
            } else if (currentChar == '+') {
                player.setVolume(player.getVolume() * 2, false);
            } else if (currentChar == '+') {
                player.setVolume(0, true);
            } else if ((currentChar == '.') || (currentChar == '?')) {
                player.addNote(randomNote());
            } else if (currentChar == 'T') {
                if (sequence.charAt(i + 1) == '+') {
                    player.setOctave(player.getOctave() + 1, false);
                    i++;
                } else if (sequence.charAt(i + 1) == '-') {
                    player.setOctave(player.getOctave() - 1, false);
                    i++;
                } else
                    // NOP
                    player.repeatLastCommand();
            } else if ((currentCharUpper == 'I') || (currentCharUpper == 'O') || (currentCharUpper == 'U')) {
                if ((i > 0)
                        && player.getNoteList().contains(String.valueOf(Character.toUpperCase(sequence.charAt(i - 1)))))
                    player.addNote(String.valueOf(Character.toUpperCase(sequence.charAt(i - 1))));
                else
                    // NOP
                    player.repeatLastCommand();
            } else if ((i + 2 < sequence.length()) && (currentChar == 'N') && (sequence.charAt(i + 1) == 'L')
                    && Character.isDigit(sequence.charAt(i + 2))) {
                player.setInstrument(sequence.charAt(i + 2) - '0', false);
                i += 2;
                // } else if (){
                // player.addSilence(); // TODO regra silêncio
            } else
                // NOP
                player.repeatLastCommand();
            i++;
        }

        player.playSequence();

    }

    public void loadSequence(String sequence) {
        // TODO load txt file
    }

    public void saveSequence(String sequence) {
        // TODO save txt file
    }

    public void saveSequenceMIDI(String sequence) {
        // TODO save MIDI
    }

}