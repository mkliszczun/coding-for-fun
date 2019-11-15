package controllers;


import dao.OrderDAO;
import entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class OrderList {

    @FXML
    TableView<Order> mainView;
    @FXML
    TableColumn<Order, String> orderNum;
    @FXML
    TableColumn<Order, String> price;
    @FXML
    TableColumn<Order, String> customer;
    @FXML
    TableColumn<Order, String> adress;
    @FXML
    TableColumn<Order, String> phoneNum;
    @FXML
    TableColumn<Order, String> delTime;

    private ObservableList<Order> allOrderList;


    @FXML
    public void initialize(){
        allOrderList = FXCollections.observableArrayList();
        mainView.setItems(allOrderList);
        populateAllOrderList();
        orderNum.setCellValueFactory(new PropertyValueFactory<>("orderNum"));
        customer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        adress.setCellValueFactory(new PropertyValueFactory<>("adres"));
        phoneNum.setCellValueFactory(new PropertyValueFactory<>("phonNum"));
        delTime.setCellValueFactory(new PropertyValueFactory<>("deliveryTime"));
    }

    private void populateAllOrderList(){
        OrderDAO orderDAO = new OrderDAO();
        List<Order> ordList = orderDAO.getAllOrders();
        if(ordList != null) {
            allOrderList.addAll(ordList);
        } else {
            System.out.println("shit, null List");
        }
        }



    public void swapScene(MouseEvent event, String path) throws IOException{
        Stage appStage;
        Parent root;
        URL url = new File(path).toURI().toURL();

        appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
}

    public void home(MouseEvent event) throws IOException {
        swapScene(event, "src/main/java/fxmls/sample.fxml");
    }

    public void openOrderAddWindow() throws IOException{
        URL url = new File("src/main/java/fxmls/orderAddWindow.fxml").toURI().toURL();
        Stage appStage = new Stage();
        Parent root;

        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();

        OrderAddWindow orderAddWindow = loader.getController();
        orderAddWindow.setParentWindow(this);

        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    public void deleteRow(){
        int selectedIndex = mainView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            Order order = mainView.getItems().get(selectedIndex);
            mainView.getItems().remove(selectedIndex);
            allOrderList.remove(order);
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.deleteOrder(order);
        }

    }

    public void editOrder() throws IOException{
        int selectedIndex = mainView.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            int id = mainView.getItems().get(selectedIndex).getId();
            Order order = mainView.getItems().get(selectedIndex);

            URL url = new File("src/main/java/fxmls/orderAddWindow.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            OrderAddWindow orderAddWindow = loader.getController();
            orderAddWindow.setOrderToEdit(order);
            orderAddWindow.setParentWindow(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        }
    }

    public void addOrder(Order order){
        allOrderList.add(order);
    }

}
