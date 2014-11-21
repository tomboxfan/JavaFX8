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
        BorderPane root = new BorderPane();                      //1) ��1�� - BorderPane��root - �����еĶ������ŵ�BorderPane����                      
        Scene scene = new Scene(root, 400, 250, Color.WHITE);    //2) ��2�� - BorderPane����scene����                                     
        primaryStage.setScene(scene);                            //3) ��3�� - scene���ڴ����primaryStage����        
        primaryStage.setTitle("Hero Picker: Chapter 4 Creating and Working with ObservableLists");
//�������ϣ�ȫ������----------------------------------------------------------------------------------------
        
        
        // create a grid pane
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        
        ColumnConstraints column1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);  //��С������ʶ���150����Ȼ�����UI�仯����ô�ҿ������޿�
        column1.setHgrow(Priority.ALWAYS);                                              //�߶������޸�
        
        ColumnConstraints column2 = new ColumnConstraints(50);                          //����UI��ô��С�仯����2�б���һֱ��50
        
        ColumnConstraints column3 = new ColumnConstraints(150, 150, Double.MAX_VALUE);  //��С������ʶ���150����Ȼ�����UI�仯����ô�ҿ������޿�
        column3.setHgrow(Priority.ALWAYS);                                              //�߶������޸�
        
        gridpane.getColumnConstraints().addAll(column1, column2, column3);
        
        // Candidates label
        Label candidatesLbl = new Label("Candidates");              //gridPane�� (0,0)�����һ��label         
        GridPane.setHalignment(candidatesLbl, HPos.CENTER);
        gridpane.add(candidatesLbl, 0, 0);
                                                                    //����(1,0)�ǿ��ŵ�
        // Heroes label
        Label heroesLbl = new Label("Heroes");                      //gridPane�� (2,0)�����һ��label
        GridPane.setHalignment(heroesLbl, HPos.CENTER);
        gridpane.add(heroesLbl, 2, 0);

        
        
        //------------��δ��������������ص㣡��������-------------------------------------
        //heros��candidates������ObservableList
        //ֱ��ʹ��ObservableList������ListVIew����ʵʱ���list����ı仯��reflect��UI��
        
        // Candidates
        final ObservableList<String> candidates = FXCollections.observableArrayList("Superman", "Spiderman", "Wolverine", "Police", "Fire Rescue", "Soldiers", "Dad & Mom", "Doctor", "Politician", "Pastor", "Teacher");
        final ListView<String> candidatesListView = new ListView<>(candidates);
        gridpane.add(candidatesListView, 0, 1);                                         //gridPane�� (0,1)�����candidatesListView 
        
        // heros
        final ObservableList<String> heroes = FXCollections.observableArrayList();
        final ListView<String> heroListView = new ListView<>(heroes);                   
        gridpane.add(heroListView, 2, 1);                                               //gridPane�� (2,1)�����heroListView
        
        // select heroes
        Button sendRightButton = new Button(" > ");
        sendRightButton.setOnAction(event -> {
            String potential = candidatesListView.getSelectionModel().getSelectedItem();
            if (potential != null) {
                candidatesListView.getSelectionModel().clearSelection();                //���ѡ�е�potential��Ϊ�գ���ô�������ѡ��
                candidates.remove(potential);                                           //Ȼ���potential��candidates list�����õ�
                heroes.add(potential);                                                  //�ŵ�heros list����
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
        
        
        VBox vbox = new VBox(5);                                                        //gridPane(1,1)�����ϼ�һ��VBox
        vbox.getChildren().addAll(sendRightButton, sendLeftButton);                     //VBox����ŵ���������ť
        gridpane.add(vbox, 1, 1);
        
        root.setCenter(gridpane);
        GridPane.setVgrow(root, Priority.ALWAYS);
        
        
//�������£�ȫ������----------------------------------------------------------------------------------------             
        primaryStage.show();
    }
}
