package chap04;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Person {

    //JavaFX的Domain Object很明显不是普通的POJO
    //它所有的property都得是它自己特别的type
    //String -> StringProperty
    //List -> ObservableList
    //但是这些Model暴露出来的方法接受的参数也都还是普通的类型
    
    private StringProperty aliasName;
    private StringProperty firstName;
    private StringProperty lastName;    
    private ObservableList<Person> employees = FXCollections.observableArrayList();

    //下面这些方法都是3个为1组
    
    //-----------------------------------------------
    
    public final void setAliasName(String value) {
        aliasNameProperty().set(value);
    }

    public final String getAliasName() {
        return aliasNameProperty().get();
    }

    public StringProperty aliasNameProperty() {
        if (aliasName == null) {
            aliasName = new SimpleStringProperty();
        }
        return aliasName;
    }
    
    //-----------------------------------------------
    
    public final void setFirstName(String value) {
        firstNameProperty().set(value);
    }

    public final String getFirstName() {
        return firstNameProperty().get();
    }

    public StringProperty firstNameProperty() {
        if (firstName == null) {
            firstName = new SimpleStringProperty();
        }
        return firstName;
    }
    
    //-----------------------------------------------    

    public final void setLastName(String value) {
        lastNameProperty().set(value);
    }

    public final String getLastName() {
        return lastNameProperty().get();
    }

    public StringProperty lastNameProperty() {
        if (lastName == null) {
            lastName = new SimpleStringProperty();
        }
        return lastName;
    }
    
    //-----------------------------------------------
    
    public ObservableList<Person> employeesProperty() {
        return employees;
    }

    public Person(String alias, String firstName, String lastName) {
        setAliasName(alias);
        setFirstName(firstName);
        setLastName(lastName);
    }
    
}