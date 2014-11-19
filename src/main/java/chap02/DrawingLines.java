package chap02;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DrawingLines extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override public void start(Stage primaryStage) {
        Group root = new Group();                                   //1) 第1层 - Group做root - 你所有的东西都放到Group里面                    
        Scene scene = new Scene(root, 300, 150, Color.GRAY);        //2) 第2层 - Group放在scene里面                                   
        primaryStage.setScene(scene);                               //3) 第3层 - scene放在传入的primaryStage里面                         
        primaryStage.setTitle("Chapter 2 Drawing Lines");
//从这往上，全都忽略----------------------------------------------------------------------------------------               

        
        
        
        
        Line redLine = new Line(10, 10, 200, 10);
        redLine.setStroke(Color.RED);
        redLine.setStrokeWidth(10);
        redLine.setStrokeLineCap(StrokeLineCap.BUTT);
        redLine.getStrokeDashArray().addAll(10d, 5d, 15d, 5d, 20d);
        redLine.setStrokeDashOffset(0);
        root.getChildren().add(redLine);

        Line whiteLine = new Line(10, 30, 200, 30);
        whiteLine.setStroke(Color.WHITE);
        whiteLine.setStrokeWidth(10);
        whiteLine.setStrokeLineCap(StrokeLineCap.ROUND);
        root.getChildren().add(whiteLine);

        Line blueLine = new Line(10, 50, 200, 50);
        blueLine.setStroke(Color.BLUE);
        blueLine.setStrokeWidth(10);
        root.getChildren().add(blueLine);

        Slider slider = new Slider(0, 100, 0);
        slider.setLayoutX(10);
        slider.setLayoutY(95);

        redLine.strokeDashOffsetProperty().bind(slider.valueProperty());
        root.getChildren().add(slider);

        Text offsetText = new Text("Stroke Dash Offset: ");
        offsetText.setX(10);
        offsetText.setY(80);
        offsetText.setStroke(Color.WHITE);

        // display stroke dash offset value
        slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number curVal, Number newVal) -> offsetText.setText("Stroke Dash Offset: " + slider.getValue()));
        root.getChildren().add(offsetText);

        
        
        
        
        
//从这往下，全都忽略----------------------------------------------------------------------------------------               
        //--标准2行结束代码,谁也跑不了------------------
        primaryStage.show();
        //-------------------------------------
    }


}