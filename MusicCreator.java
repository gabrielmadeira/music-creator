import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.geometry.*;

public class MusicCreator extends Application {
    Controller control = new Controller();

    private void setAddTextButtomAction(Button button, TextArea textArea, String text) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.setText(textArea.getText() + text);
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.HORIZONTAL);

        GridPane leftPane = new GridPane();
        leftPane.setPadding(new Insets(10));
        splitPane.getItems().add(leftPane);

        GridPane rightPane = new GridPane();
        rightPane.setPadding(new Insets(10));
        splitPane.getItems().add(rightPane);

        Scene scene = new Scene(splitPane, 650, 450);

        splitPane.setStyle("-fx-padding: 8;" + "-fx-border-style: solid inside;" + "-fx-border-width: 4;"
                + "-fx-border-radius: 6;" + "-fx-border-color: green;");
        // root.setVgap(50);
        Text text = new Text();

        text.setText("Music Creator - Crie sua música!");
        text.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 20));

        leftPane.add(text, 0, 0, 4, 1);

        primaryStage.setTitle("Music Creator");

        TextArea tf1 = new TextArea();
        tf1.setPrefRowCount(5);

        Button SaveMIDIButtom = new Button("Salvar .MIDI");
        Button SaveTxtButtom = new Button("Salvar .txt");
        Button LoadTxtButtom = new Button("Carregar .txt");
        Button PlayButtom = new Button("Tocar");

        Label NotesLabel = new Label("Adicionar notas:");
        Button CButtom = new Button("Dó");
        setAddTextButtomAction(CButtom, tf1, "C");
        Button DButtom = new Button("Ré");
        setAddTextButtomAction(DButtom, tf1, "D");
        Button EButtom = new Button("Mi");
        setAddTextButtomAction(EButtom, tf1, "E");
        Button FButtom = new Button("Fá");
        setAddTextButtomAction(FButtom, tf1, "F");
        Button GButtom = new Button("Sol");
        setAddTextButtomAction(GButtom, tf1, "G");
        Button AButtom = new Button("Lá");
        setAddTextButtomAction(AButtom, tf1, "A");
        Button BButtom = new Button("Si");
        setAddTextButtomAction(BButtom, tf1, "B");

        Label OctavesLabel = new Label("Editar oitava:");
        Button IncOctaveButtom = new Button("+");
        setAddTextButtomAction(IncOctaveButtom, tf1, "T+");
        Button DecOctaveButtom = new Button("-");
        setAddTextButtomAction(DecOctaveButtom, tf1, "T-");
        Button ResetOctaveButtom = new Button("⟲");
        setAddTextButtomAction(ResetOctaveButtom, tf1, ".");

        Label BPMLabel = new Label("Editar BPM:");
        Button Dec50BPMButtom = new Button("-50");
        setAddTextButtomAction(Dec50BPMButtom, tf1, "BPM-");
        Button Dec10BPMButtom = new Button("-10");
        setAddTextButtomAction(Dec10BPMButtom, tf1, "bpm-");
        Button Inc10BPMButtom = new Button("+10");
        setAddTextButtomAction(Inc10BPMButtom, tf1, "bpm+");
        Button Inc50BPMButtom = new Button("+50");
        setAddTextButtomAction(Inc50BPMButtom, tf1, "BPM+");

        Label VolumeLabel = new Label("Editar volume atual:");
        Button ResetVolumeButtom = new Button("⟲");
        setAddTextButtomAction(ResetVolumeButtom, tf1, "-");
        Button DoubleVolumeButtom = new Button("2x");
        setAddTextButtomAction(DoubleVolumeButtom, tf1, "+");

        Label InstrumentLabel = new Label("Escolher instrumento:");
        Button DefaultInstrumentButtom = new Button("Default");
        setAddTextButtomAction(DefaultInstrumentButtom, tf1, "0");
        Button PanFluteInstrumentButtom = new Button("Pan Flute");
        setAddTextButtomAction(PanFluteInstrumentButtom, tf1, ";");
        Button ChurchOrganInstrumentButtom = new Button("Church Organ");
        setAddTextButtomAction(ChurchOrganInstrumentButtom, tf1, ",");
        Button Piano1InstrumentButtom = new Button("Piano 1");
        setAddTextButtomAction(Piano1InstrumentButtom, tf1, " ");
        Button TubularBellsInstrumentButtom = new Button("Tubular Bells");
        setAddTextButtomAction(TubularBellsInstrumentButtom, tf1,  ""+(char)10);

        Label OthersLabel = new Label("Outros:");
        Button SilenceButtom = new Button("Silêncio");
        setAddTextButtomAction(SilenceButtom, tf1, ""); // TODO comando silencio
        Button ContinueButtom = new Button("Continuar");
        setAddTextButtomAction(ContinueButtom, tf1, "N");
        Button FlatButtom = new Button("Bemol");
        setAddTextButtomAction(FlatButtom, tf1, "H");
        Button SharpButtom = new Button("Sustenido");
        setAddTextButtomAction(SharpButtom, tf1, "T");
        Button RepeatButtom = new Button("Repetir");
        setAddTextButtomAction(RepeatButtom, tf1, "I");

        leftPane.add(tf1, 0, 2, 4, 3);
        leftPane.add(SaveMIDIButtom, 0, 5);
        leftPane.add(SaveTxtButtom, 1, 5);
        leftPane.add(LoadTxtButtom, 2, 5);
        leftPane.add(PlayButtom, 3, 5);
        leftPane.setHgap(5);
        leftPane.setVgap(10);

        GridPane notesPane = new GridPane();
        notesPane.add(NotesLabel, 0, 0, 3, 1);
        notesPane.add(CButtom, 0, 1);
        notesPane.add(DButtom, 1, 1);
        notesPane.add(EButtom, 2, 1);
        notesPane.add(FButtom, 3, 1);
        notesPane.add(GButtom, 4, 1);
        notesPane.add(AButtom, 5, 1);
        notesPane.add(BButtom, 6, 1);
        notesPane.setHgap(5);
        rightPane.add(notesPane, 0, 0);

        GridPane octavesPane = new GridPane();
        octavesPane.add(OctavesLabel, 0, 0, 3, 1);
        octavesPane.add(DecOctaveButtom, 0, 1);
        octavesPane.add(IncOctaveButtom, 1, 1);
        octavesPane.add(ResetOctaveButtom, 2, 1);
        octavesPane.setHgap(5);
        rightPane.add(octavesPane, 0, 1);

        GridPane BPMPane = new GridPane();
        BPMPane.add(BPMLabel, 0, 0, 3, 1);
        BPMPane.add(Dec50BPMButtom, 0, 1);
        BPMPane.add(Dec10BPMButtom, 1, 1);
        BPMPane.add(Inc10BPMButtom, 2, 1);
        BPMPane.add(Inc50BPMButtom, 3, 1);
        BPMPane.setHgap(5);
        rightPane.add(BPMPane, 0, 2);

        GridPane volumePane = new GridPane();
        volumePane.add(VolumeLabel, 0, 0, 3, 1);
        volumePane.add(ResetVolumeButtom, 0, 1);
        volumePane.add(DoubleVolumeButtom, 1, 1);
        volumePane.setHgap(5);
        rightPane.add(volumePane, 0, 3);

        GridPane instrumentPane = new GridPane();
        instrumentPane.add(InstrumentLabel, 0, 0, 3, 1);
        instrumentPane.add(DefaultInstrumentButtom, 0, 1);
        instrumentPane.add(PanFluteInstrumentButtom, 1, 1);
        instrumentPane.add(ChurchOrganInstrumentButtom, 2, 1);
        instrumentPane.add(Piano1InstrumentButtom, 3, 1);
        instrumentPane.add(TubularBellsInstrumentButtom, 0, 2, 2, 1);
        instrumentPane.setHgap(5);
        instrumentPane.setVgap(5);
        rightPane.add(instrumentPane, 0, 4);

        GridPane othersPane = new GridPane();
        othersPane.add(OthersLabel, 0, 0, 3, 1);
        othersPane.add(SilenceButtom, 0, 1);
        othersPane.add(ContinueButtom, 1, 1);
        othersPane.add(FlatButtom, 2, 1);
        othersPane.add(SharpButtom, 3, 1);
        othersPane.add(RepeatButtom, 0, 2);
        othersPane.setHgap(5);
        othersPane.setVgap(5);
        rightPane.add(othersPane, 0, 5);

        rightPane.setVgap(10);

        primaryStage.setScene(scene);
        primaryStage.show();

        Text t = new Text();
        PlayButtom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (tf1.getText().isEmpty()) {
                    if (t.getText().isEmpty() == false) {
                        leftPane.getChildren().remove(t);
                    }
                    t.setText("Please enter a music string");
                    // root.addRow(5, t);
                    leftPane.add(t, 0, 6, 4, 1);
                    return;
                } else {
                    leftPane.getChildren().remove(t);
                    System.out.println(tf1.getText());
                    control.executeSequence(tf1.getText(),true);
                }
            }
        });

        SaveTxtButtom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tf1.getText().isEmpty()) {
                    if (t.getText().isEmpty() == false) {
                        leftPane.getChildren().remove(t);
                    }
                    t.setText("Please enter a music string");
                    // root.addRow(5, t);
                    leftPane.add(t, 0, 6, 4, 1);
                    return;
                } else {
                    leftPane.getChildren().remove(t);
                    if (control.saveSequence(tf1.getText())){
                        t.setText("Music file saved succesfully");
                    }else{
                        t.setText("Error on saving music file");
                    }

                    leftPane.add(t, 0, 6, 4, 1);
                }
            }
        });

        LoadTxtButtom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                leftPane.getChildren().remove(t);
                String loaded = control.loadSequence();
                if (loaded != ""){
                    t.setText("Music text loaded succesfully");
                    tf1.setText(loaded);
                }else{
                    t.setText("Error on loading music test");
                }

                leftPane.add(t, 0, 6, 4, 1);
            }
        });

        SaveMIDIButtom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tf1.getText().isEmpty()) {
                    if (t.getText().isEmpty() == false) {
                        leftPane.getChildren().remove(t);
                    }
                    t.setText("Please enter a music string");
                    // root.addRow(5, t);
                    leftPane.add(t, 0, 6, 4, 1);
                    return;
                } else {
                    leftPane.getChildren().remove(t);

                    control.executeSequence(tf1.getText(), false);
                    if (control.saveSequenceMIDI()){
                        t.setText("Music saved succesfully");
                    }else{
                        t.setText("Error on saving music");
                    }

                    leftPane.add(t, 0, 6, 4, 1);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}