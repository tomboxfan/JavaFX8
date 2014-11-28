package chap04;

import java.util.Random;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BackgroundProcesses extends Application {
    static Task copyWorker;
    final int numFiles = 30;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 330, 120, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BackgroundProcesses: Chapter 4 Background Processes");
//从这往上，全都忽略----------------------------------------------------------------------------------------
        
        BorderPane borderPane = new BorderPane();
        borderPane.layoutXProperty().bind(scene.widthProperty().subtract(borderPane.widthProperty()).divide(2));
        root.getChildren().add(borderPane);
        final Label label = new Label("Files Transfer:");
        final ProgressBar progressBar = new ProgressBar(0);
        final ProgressIndicator progressIndicator = new ProgressIndicator(0);
        final HBox hbox = new HBox();
        hbox.setSpacing(5);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(label, progressBar, progressIndicator);
        borderPane.setTop(hbox);
        final Button startButton = new Button("Start");
        final Button cancelButton = new Button("Cancel");
        final TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefSize(200, 70);
        final HBox hb2 = new HBox();
        hb2.setSpacing(5);
        hb2.setAlignment(Pos.CENTER);
        hb2.getChildren().addAll(startButton, cancelButton, textArea);
        borderPane.setBottom(hb2);
        // wire up start button
        startButton.setOnAction((ActionEvent event) -> {
            startButton.setDisable(true);
            progressBar.setProgress(0);
            progressIndicator.setProgress(0);
            textArea.setText("");
            cancelButton.setDisable(false);
            copyWorker = createWorker(numFiles);
            // wire up progress bar
                progressBar.progressProperty().unbind();
                progressBar.progressProperty().bind(copyWorker.progressProperty());
                progressIndicator.progressProperty().unbind();
                progressIndicator.progressProperty().bind(copyWorker.progressProperty());
                // append to text area box
                copyWorker.messageProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    textArea.appendText(newValue + "\n");
                });
                new Thread(copyWorker).start();
            });
        // cancel button will kill worker and reset.
        cancelButton.setOnAction((ActionEvent event) -> {
            startButton.setDisable(false);
            cancelButton.setDisable(true);
            copyWorker.cancel(true);
            // reset
                progressBar.progressProperty().unbind();
                progressBar.setProgress(0);
                progressIndicator.progressProperty().unbind();
                progressIndicator.setProgress(0);
                textArea.appendText("File transfer was cancelled.");
            });
        primaryStage.show();
    }

    private Task createWorker(final int numFiles) {
        return new Task() {
            @Override protected Object call() throws Exception {
                for (int i = 0; i < numFiles; i++) {
                    long elapsedTime = System.currentTimeMillis();
                    copyFile("some file", "some dest file");
                    elapsedTime = System.currentTimeMillis() - elapsedTime;
                    String status = elapsedTime + " milliseconds";
                    // queue up status
                    updateMessage(status);
                    updateProgress(i + 1, numFiles); // (progress, max)
                }
                return true;
            }
        };
    }

    private void copyFile(String src, String dest) throws InterruptedException {
        // simulate a long time
        Random rnd = new Random(System.currentTimeMillis());
        long millis = rnd.nextInt(1000);
        Thread.sleep(millis);
    }
}
