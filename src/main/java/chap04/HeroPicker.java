package chap04;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//Hero Picker application demonstrates observable lists and list views.
public class HeroPicker extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }    
    
    @Override public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();                      //1) 第1层 - BorderPane做root - 你所有的东西都放到BorderPane里面                      
        Scene scene = new Scene(root, 400, 250, Color.WHITE);    //2) 第2层 - BorderPane放在scene里面                                     
        primaryStage.setScene(scene);                            //3) 第3层 - scene放在传入的primaryStage里面        
        primaryStage.setTitle("Hero Picker: Chapter 4 Creating and Working with ObservableLists");
//从这往上，全都忽略----------------------------------------------------------------------------------------
        
        
        // create a grid pane
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        
        ColumnConstraints column1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);  //最小，最合适都是150宽，当然如果你UI变化，那么我可以无限宽
        column1.setHgrow(Priority.ALWAYS);                                              //高度我无限高
        
        ColumnConstraints column2 = new ColumnConstraints(50);                          //无论UI怎么大小变化，第2列必须一直宽50
        
        ColumnConstraints column3 = new ColumnConstraints(150, 150, Double.MAX_VALUE);  //最小，最合适都是150宽，当然如果你UI变化，那么我可以无限宽
        column3.setHgrow(Priority.ALWAYS);                                              //高度我无限高
        
        gridpane.getColumnConstraints().addAll(column1, column2, column3);
        
        // Candidates label
        Label candidatesLbl = new Label("Candidates");              //gridPane上 (0,0)坐标加一个label         
        GridPane.setHalignment(candidatesLbl, HPos.CENTER);
        gridpane.add(candidatesLbl, 0, 0);
                                                                    //明显(1,0)是空着的
        // Heroes label
        Label heroesLbl = new Label("Heroes");                      //gridPane上 (2,0)坐标加一个label
        GridPane.setHalignment(heroesLbl, HPos.CENTER);
        gridpane.add(heroesLbl, 2, 0);

        
        
        //------------这段代码是这个程序的重点！！！！！-------------------------------------
        //heros和candidates是两个ObservableList
        //直接使用ObservableList创建的ListVIew可以实时监测list里面的变化，reflect到UI上
        
        // Candidates
        final ObservableList<String> candidates = FXCollections.observableArrayList("Superman", "Spiderman", "Wolverine", "Police", "Fire Rescue", "Soldiers", "Dad & Mom", "Doctor", "Politician", "Pastor", "Teacher");
        final ListView<String> candidatesListView = new ListView<>(candidates);
        gridpane.add(candidatesListView, 0, 1);                                         //gridPane上 (0,1)坐标加candidatesListView 
        
        // heros
        final ObservableList<String> heroes = FXCollections.observableArrayList();
        final ListView<String> heroListView = new ListView<>(heroes);                   
        gridpane.add(heroListView, 2, 1);                                               //gridPane上 (2,1)坐标加heroListView
        
        // select heroes
        Button sendRightButton = new Button(" > ");
        sendRightButton.setOnAction(event -> {
            String potential = candidatesListView.getSelectionModel().getSelectedItem();
            if (potential != null) {
                candidatesListView.getSelectionModel().clearSelection();                //如果选中的potential不为空，那么首先清掉选择
                candidates.remove(potential);                                           //然后把potential从candidates list里面拿掉
                heroes.add(potential);                                                  //放到heros list里面
            }
        });
        // deselect heroes
        Button sendLeftButton = new Button(" < ");
        sendLeftButton.setOnAction(event -> {
            String notHero = heroListView.getSelectionModel().getSelectedItem();
            if (notHero != null) {
                heroListView.getSelectionModel().clearSelection();
                heroes.remove(notHero);
                candidates.add(notHero);
            }
        });
        //-------------------------------------------------------------------------
        
        
        VBox vbox = new VBox(5);                                                        //gridPane(1,1)坐标上加一个VBox
        vbox.getChildren().addAll(sendRightButton, sendLeftButton);                     //VBox里面放的是两个按钮
        gridpane.add(vbox, 1, 1);
        
        root.setCenter(gridpane);
        GridPane.setVgrow(root, Priority.ALWAYS);
        
        
//从这往下，全都忽略----------------------------------------------------------------------------------------             
        primaryStage.show();
    }
}
