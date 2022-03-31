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
    // ?,. -> aleatória
    // NL<#instrumento> -> trocar instrumento
    // BPM+/BPM- -> aumenta/diminui 50 bpm
    // bpm+/bpm- -> aumenta/diminui 10 bpm
    // outros caracteres -> NOP

    private String randomNote() {
        Random rand = new Random();
        return player.getNoteList().get(rand.nextInt(player.getNoteList().size()));
    }

    public void executeSequence(String sequence) {
        player.resetSequence();

        int i = 0;
        while (i < sequence.length()) {
            char currentChar = sequence.charAt(i);
            char currentCharUpper = Character.toUpperCase(currentChar);
            if (player.getNoteList().contains(String.valueOf(currentCharUpper)) && currentCharUpper != 'B') {
                player.addNote(String.valueOf(currentCharUpper));
            } else if (currentChar == 'B') {
                if ((i + 3 < sequence.length()) && (sequence.charAt(i + 1) == 'P') && (sequence.charAt(i + 2) == 'M')) {
                    if (sequence.charAt(i + 3) == '+') {
                        player.increaseBPM(50);
                        i += 3;
                    } else if (sequence.charAt(i + 3) == '-') {
                        player.decreaseBPM(50);
                        i += 3;
                    } else
                        player.addNote("B");
                } else
                    player.addNote("B");
            } else if (currentChar == 'b') {
                if ((i + 3 < sequence.length()) && (sequence.charAt(i + 1) == 'p') && (sequence.charAt(i + 2) == 'm')) {
                    if (sequence.charAt(i + 3) == '+') {
                        player.increaseBPM(10);
                        i += 3;
                    } else if (sequence.charAt(i + 3) == '-') {
                        player.decreaseBPM(10);
                        i += 3;
                    } else
                        player.addNote("B");
                } else
                    player.addNote("B");
            } else if (currentChar == '+') {
                player.doubleVolume();
            } else if (currentChar == '-') {
                player.resetVolume();
            } else if ((currentChar == '.') || (currentChar == '?')) {
                player.addNote(randomNote());
            } else if (currentChar == 'T') {
                if (sequence.charAt(i + 1) == '+') {
                    player.increaseOctave();
                    i++;
                } else if (sequence.charAt(i + 1) == '-') {
                    player.decreaseOctave();
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
                player.changeInstrument(sequence.charAt(i + 2) - '0');
                i += 2;
                // TODO pause
            } else
                // NOP
                player.repeatLastCommand();
            i++;
        }

        player.playSequence();
    }

    public void loadSequence(String sequence) {
        // TODO load file
    }

    public void saveSequence(String sequence) {
        // TODO save file
    }

}