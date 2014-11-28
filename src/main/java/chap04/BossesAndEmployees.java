package chap04;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BossesAndEmployees extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();                         //1) 第1层 - BorderPane做root - 你所有的东西都放到BorderPane里面                     
        Scene scene = new Scene(root, 500, 250, Color.WHITE);       //2) 第2层 - BorderPane放在scene里面                                         
        primaryStage.setScene(scene);                               //3) 第3层 - scene放在传入的primaryStage里面                                    
        primaryStage.setTitle("Bosses and Employees: Chapter 4 Working with Tables");
//从这往上，全都忽略----------------------------------------------------------------------------------------                  
        
        // create a grid pane
        GridPane gridpane = new GridPane(); 
        gridpane.setPadding(new Insets(5));     //外面有一个5公分的框框
        gridpane.setHgap(10);                   //水平cell间隔10公分
        gridpane.setVgap(10);                   //垂直cell间隔10公分
        root.setCenter(gridpane);
        
        
        // candidates label
        createLabel("Boss", gridpane, 0, 0);
        
        // List of leaders
        ObservableList<Person> observableList = getPeople();                           //ObservableList和ListView绝对是绝配
        ListView<Person> listView = new ListView<>(observableList);
        listView.setPrefWidth(150);
        listView.setMaxWidth(Double.MAX_VALUE);
        listView.setPrefHeight(150);
        
        // display first and last name with tooltip using alias
        listView.setCellFactory(param -> { //param是ListView, 这个方法必须要传出一个ListCell
                    final Label label = new Label();
                    final Tooltip tooltip = new Tooltip();
                    final ListCell<Person> listCell = new ListCell<Person>() {
                        @Override public void updateItem(Person item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                label.setText(item.getAliasName());
                                setText(item.getFirstName() + " " + item.getLastName());
                                tooltip.setText(item.getAliasName());
                                setTooltip(tooltip);
                            }
                        }
                    };
                    return listCell;
                }
        );
        gridpane.add(listView, 0, 1);
        
        createLabel("Employees", gridpane, 2, 0);
        
        TableView<Person> tableView = new TableView<>();
        tableView.setPrefWidth(300);
        ObservableList<Person> observableListTeamMember = FXCollections.observableArrayList();
        tableView.setItems(observableListTeamMember);
        
        TableColumn<Person, String> aliasColumn = createTableColumn("Alias", "aliasName", tableView.getPrefWidth() / 3);  
        TableColumn<Person, String> firstNameColumn = createTableColumn("First Name", "firstName", tableView.getPrefWidth() / 3);  
        TableColumn<Person, String> lastNameColumn = createTableColumn("Last Name", "lastName", tableView.getPrefWidth() / 3);  
        tableView.getColumns().setAll(aliasColumn, firstNameColumn, lastNameColumn);
        gridpane.add(tableView, 2, 1);
        
        // selection listening
        listView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Person> observable, Person oldValue, Person newValue) -> {
            if (observable != null && observable.getValue() != null) {
                observableListTeamMember.clear();
                observableListTeamMember.addAll(observable.getValue().employeesProperty());
                //或者
                //observableListTeamMember.addAll(newValue.employeesProperty());
            }
        });
        
        primaryStage.show();
    }

    private ObservableList<Person> getPeople() {
        ObservableList<Person> people = FXCollections.<Person> observableArrayList();
        Person docX = new Person("Professor X", "Charles", "Xavier");
        docX.employeesProperty().add(new Person("Wolverine", "James", "Howlett"));
        docX.employeesProperty().add(new Person("Cyclops", "Scott", "Summers"));
        docX.employeesProperty().add(new Person("Storm", "Ororo", "Munroe"));
        Person magneto = new Person("Magneto", "Max", "Eisenhardt");
        magneto.employeesProperty().add(new Person("Juggernaut", "Cain", "Marko"));
        magneto.employeesProperty().add(new Person("Mystique", "Raven", "Darkhlme"));
        magneto.employeesProperty().add(new Person("Sabretooth", "Victor", "Creed"));
        Person biker = new Person("Mountain Biker", "Jonathan", "Gennick");
        biker.employeesProperty().add(new Person("Josh", "Joshua", "Juneau"));
        biker.employeesProperty().add(new Person("Freddy", "Freddy", "Guime"));
        biker.employeesProperty().add(new Person("Mark", "Mark", "Beaty"));
        biker.employeesProperty().add(new Person("John", "John", "O'Conner"));
        biker.employeesProperty().add(new Person("D-Man", "Carl", "Dea"));
        people.add(docX);
        people.add(magneto);
        people.add(biker);
        return people;
    }

    
    private void createLabel(String name, GridPane gridPane, int gridPaneX, int gridPaneY) {
        Label label = new Label(name);
        GridPane.setHalignment(label, HPos.CENTER);
        gridPane.add(label, gridPaneX, gridPaneY);
    }
    
    private TableColumn<Person,String> createTableColumn(String columnName, String domainObjectPropertyName, double width) {
        TableColumn<Person, String> column = new TableColumn<>(columnName);
        column.setCellValueFactory(new PropertyValueFactory<Person, String>(domainObjectPropertyName));
        column.setPrefWidth(width);
        column.setEditable(true);
        return column;
    }
}
