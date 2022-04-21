import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import org.jfugue.player.*;

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

        GridPane root = new GridPane();
        Scene scene = new Scene(root, 500, 300);

        root.setStyle("-fx-padding: 8;" + "-fx-border-style: solid inside;" + "-fx-border-width: 4;"
                + "-fx-border-radius: 6;" + "-fx-border-color: green;");
        root.setVgap(50);
        Text text = new Text();

        text.setText("Welcome to Music Creator");
        text.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 20));

        primaryStage.setTitle("Music Creator - Crie sua música!");

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
        Button ResetOctaveButtom = new Button("RESET");
        setAddTextButtomAction(ResetOctaveButtom, tf1, ".");

        Button Dec50BPMButtom = new Button("-50");
        setAddTextButtomAction(Dec50BPMButtom, tf1, "BPM-");
        Button Dec10BPMButtom = new Button("-10");
        setAddTextButtomAction(Dec10BPMButtom, tf1, "bpm-");
        Button Inc10BPMButtom = new Button("+10");
        setAddTextButtomAction(Inc10BPMButtom, tf1, "bpm+");
        Button Inc50BPMButtom = new Button("+50");
        setAddTextButtomAction(Inc50BPMButtom, tf1, "BPM+");

        Button ResetVolumeButtom = new Button("RESET");
        setAddTextButtomAction(ResetVolumeButtom, tf1, "-");
        Button DoubleVolumeButtom = new Button("2x");
        setAddTextButtomAction(DoubleVolumeButtom, tf1, "+");

        Button DefaultInstrumentButtom = new Button("Default");
        setAddTextButtomAction(DefaultInstrumentButtom, tf1, "");
        Button PanFluteInstrumentButtom = new Button("Pan Flute");
        setAddTextButtomAction(PanFluteInstrumentButtom, tf1, "");
        Button ChurchOrganInstrumentButtom = new Button("Church Organ");
        setAddTextButtomAction(ChurchOrganInstrumentButtom, tf1, "");
        Button Piano1InstrumentButtom = new Button("Piano 1");
        setAddTextButtomAction(Piano1InstrumentButtom, tf1, "");
        Button TubularBellsInstrumentButtom = new Button("Tubular Bells");
        setAddTextButtomAction(TubularBellsInstrumentButtom, tf1, "");

        Button SilenceButtom = new Button("Silêncio"); // TODO silencio ????
        setAddTextButtomAction(SilenceButtom, tf1, "Y");
        Button ContinueButtom = new Button("Continuar");
        setAddTextButtomAction(ContinueButtom, tf1, "N");
        Button FlatButtom = new Button("Bemol");
        setAddTextButtomAction(FlatButtom, tf1, "H");
        Button SharpButtom = new Button("Sustenido");
        setAddTextButtomAction(SharpButtom, tf1, "T");
        Button RepeatButtom = new Button("Repetir");
        setAddTextButtomAction(RepeatButtom, tf1, "I");

        root.addRow(1, text);
        root.setVgap(10);
        root.addRow(1, tf1);
        root.setVgap(10);
        root.addRow(4, SaveMIDIButtom, SaveTxtButtom, LoadTxtButtom, PlayButtom, CButtom);
        root.setVgap(10);

        primaryStage.setScene(scene);
        primaryStage.show();

        Text t = new Text();
        PlayButtom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (tf1.getText().isEmpty()) {
                    if (t.getText().isEmpty() == false) {
                        root.getChildren().remove(t);
                    }
                    t.setText("Please enter a music string");
                    root.addRow(5, t);
                    return;
                } else {
                    root.getChildren().remove(t);
                    System.out.println(tf1.getText());
                    control.executeSequence(tf1.getText());
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}