package chap04;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Person {

    //JavaFX��Domain Object�����Բ�����ͨ��POJO
    //�����е�property���������Լ��ر��type
    //String -> StringProperty
    //List -> ObservableList
    //������ЩModel��¶�����ķ������ܵĲ���Ҳ��������ͨ������
    
    private StringProperty aliasName;
    private StringProperty firstName;
    private StringProperty lastName;    
    private ObservableList<Person> employees = FXCollections.observableArrayList();

    //������Щ��������3��Ϊ1��
    
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