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
    static Task<Boolean> copyWorker;
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

        //--布局代码 - 布局大的borderPane------------------------------------------
        //就是一个大的BorderPane..
        BorderPane borderPane = new BorderPane();
        borderPane.layoutXProperty().bind(scene.widthProperty().subtract(borderPane.widthProperty()).divide(2));
        root.getChildren().add(borderPane);
        //-------------------------------------------------------------------

        //--布局代码 - 布局borderPane的Top部分-------------------------------------
        //Top里面放了一个HBox, 横着放着label / progressBar / progressIndicator
        final Label label = new Label("Files Transfer:");
        
        //progress监控有两个控件，ProgressBar和ProgressIndicator. 这两个控件在这个程序中统一展示---
        final ProgressBar progressBar = new ProgressBar(0);
        final ProgressIndicator progressIndicator = new ProgressIndicator(0);

        final HBox hbox = new HBox();
        hbox.setSpacing(5);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(label, progressBar, progressIndicator);
        borderPane.setTop(hbox);
        //------------------------------------------------------------------

        //--布局代码 - 布局borderPane的Bottom部分----------------------------------
        //Bottom里面放了一个HBox, 横着放着startButton / cancelButton / textArea        
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
        //------------------------------------------------------------------

        // wire up start button
        startButton.setOnAction((ActionEvent event) -> {

            startButton.setDisable(true);
            cancelButton.setDisable(false);
            textArea.setText("");

            copyWorker = createWorker(numFiles);

            progressBar.progressProperty().unbind();
            progressBar.progressProperty().bind(copyWorker.progressProperty());

            progressIndicator.progressProperty().unbind();
            progressIndicator.progressProperty().bind(copyWorker.progressProperty());

            //这行代码是重点！！ - 当copyWorker的messageProperty变化的时候，直接update textArea的内容--------------------
            copyWorker.messageProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                textArea.appendText(newValue + "\n");
            });
            
            //--------------------------------------------------------------------------------------------
            
            new Thread(copyWorker).start();
        });

        // cancel button will kill worker and reset.
        cancelButton.setOnAction((ActionEvent event) -> {

            startButton.setDisable(false);
            cancelButton.setDisable(true);

            copyWorker.cancel(true);

            progressBar.progressProperty().unbind();
            progressBar.setProgress(0);

            progressIndicator.progressProperty().unbind();
            progressIndicator.setProgress(0);

            textArea.appendText("File transfer was cancelled.");

        });

        primaryStage.show();
    }

    private Task<Boolean> createWorker(final int numFiles) {
        
        //注意：JavaFx Task就是一个Java Concurrecny的Future Task, 也就是一个Runnable. 因此可以如上面直接start -> new Thread(task).start();
        //然后它的call()方法就开始执行了. 
        //在MNJ Trading当中，也应该有一个PriceSyncTask, 每10s中执行一次，给所有的ticker那所有的price
        return new Task<Boolean>() {
            @Override protected Boolean call() throws Exception {
                for (int i = 0; i < numFiles; i++) {
                    long elapsedTime = System.currentTimeMillis();
                    copyFile("some file", "some dest file");
                    elapsedTime = System.currentTimeMillis() - elapsedTime;
                    String status = elapsedTime + " milliseconds";
                    // queue up status
                    updateMessage(status);
                    updateProgress(i + 1, numFiles); // (progress, max) --(完成了多少, 一共多少)
                    
                    //注意: task不仅仅可以update message / progress, 还可以updateValue，那么就是一个你自己定义的Busienss Object
                    //你只要注册一个listener, 如下，那么你就可以更新你想要的field了
                    //copyWorker.valueProperty().addListener(listener);
                    //updateValue(null);
                }
                return true;
            }
        };
    }

    private void copyFile(String src, String dest) throws InterruptedException {
        Random rnd = new Random(System.currentTimeMillis());
        long millis = rnd.nextInt(1000);
        Thread.sleep(millis);
    }
}

