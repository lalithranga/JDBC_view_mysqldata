package controller;

import com.mysql.cj.protocol.Resultset;
import customer.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DashBordForm implements Initializable {


    public TextField name;
    public TextField id;
    public TextField address;
    public TableView tblview;
    public TableColumn tblName;
    public TableColumn tblId;
    public TableColumn tblAddress;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        Loadtable();


    }

    public void btnAddOnaction(ActionEvent actionEvent) {


    }

    public void btnviewOnaction(ActionEvent actionEvent) {

      Loadtable();

    }
private void Loadtable(){


    ObservableList<Customer> observableArray= FXCollections.observableArrayList();


    String SQL="Select * from customer";
    try {
        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345");

        Statement statement = connection.createStatement();
        ResultSet resulset = statement.executeQuery(SQL);
        while(resulset.next()){
            Customer customer;
            customer = new Customer(
                    resulset.getString("CustomerId"),
                    resulset.getString("Name"),
                    resulset.getString("Address")
            );
            observableArray.add(customer);

            System.out.println(resulset.getString("customerID"));
        };
        tblview.setItems(observableArray);

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}

}
