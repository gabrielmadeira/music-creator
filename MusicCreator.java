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

public class MusicCreator extends Application {
    Controller control = new Controller();

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
        primaryStage.setTitle("Music Creator");
        Label name = new Label("Music string");
        TextArea tf1 = new TextArea();
        tf1.setPrefRowCount(5);
        Button Submit = new Button("Submit");

        root.addRow(1, text);
        root.setVgap(10);
        root.addRow(2, name, tf1);
        root.setVgap(10);
        root.addRow(4, Submit);
        root.setVgap(10);

        primaryStage.setScene(scene);
        primaryStage.show();

        // Button Events
        Text t = new Text();
        Submit.setOnAction(new EventHandler<ActionEvent>() {
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