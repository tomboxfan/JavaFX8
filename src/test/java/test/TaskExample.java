package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TaskExample extends Application {  
    
    public void init(Stage primaryStage){

        MyTask progressTask = new MyTask();
        
        Region region = new Region();  
        region.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");  
        region.visibleProperty().bind(progressTask.runningProperty());  

        ProgressIndicator progressIndicator = new ProgressIndicator(0);  
        progressIndicator.setMaxSize(150, 150);  
        progressIndicator.progressProperty().bind(progressTask.progressProperty());  
        
        Label label = new Label("Loading...0%");  
        label.setFont(new Font(10));
        label.textProperty().bind(progressTask.messageProperty());  
          
        StackPane root = new StackPane();  
        root.getChildren().addAll(region, progressIndicator, label);  
        Scene scene = new Scene(root, 300, 250);  
        primaryStage.setTitle("The lesson of Task");  
        primaryStage.setScene(scene);  
          
        new Thread(progressTask).start();  
    }  
      
      
      
    @Override  
    public void start(Stage primaryStage) {  
        init(primaryStage);  
        primaryStage.show();  
    }        
  
    public static void main(String[] args) {  
        launch(args);  
    }  
}  