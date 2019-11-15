package controllers;

import dao.OrderDAO;
import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class OrderAddWindow {

    @FXML
    TextField klientField;
    @FXML
    TextField nrTele;
    @FXML
    TextField terminDost;
    @FXML
    TextField adres;
    @FXML
    TextField nip;
    @FXML
    TextArea addInfo;
    @FXML
    ListView<TableListable> orderTable;
    @FXML
    Label orderNumLabel;


    private List<Steel> steelList;
    private ObservableList<TableListable> itemsForTable;
    private List<Bar> barList;
    private String orderNum;
    private int orderId;
    private OrderList parentWindow;


    @FXML
    public void initialize() {
        OrderNumGenerator idGenerator = new OrderNumGenerator();
        orderNum = idGenerator.generate();
        steelList = new ArrayList<>();
        itemsForTable = FXCollections.observableArrayList();
        barList = new ArrayList<>();
        orderNumLabel.setText("Nr zamówienia " + orderNum);
        orderTable.setItems(itemsForTable);
    }


    public Order createOrder(){
        Order order = new Order();
        order.setId(orderId);
        order.setOrderNum(orderNum);
        order.setCustomer(klientField.getText());
        order.setPhonNum(nrTele.getText());
        order.setDeliveryTime(terminDost.getText());
        order.setAdres(adres.getText());
        order.setNip(nip.getText());
        order.setAddInfo(addInfo.getText());
        order.setSteelList(steelList);
        order.setBarList(barList);
        return order;
    }

    public void gotowe(MouseEvent event) {
        Order order = createOrder();
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.saveOrUpdateOrder(order);
        parentWindow.addOrder(order);
        parentWindow.mainView.refresh();
        Stage appStage;
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.close();

    }

    public void OpenAddSteelWindow() throws IOException {
        URL url = new File("src/main/java/fxmls/addSteelWindow.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        AddSteelWindow addSteelWindow = loader.getController();
        addSteelWindow.setParentWindow(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openAddPretWindow() throws IOException {
        URL url = new File("src/main/java/fxmls/addPretWindow.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        AddPretWindow addPretWindow = loader.getController();
        addPretWindow.setParentWindow(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void addSteelToList(Steel steel) {
        steelList.add(steel);
        itemsForTable.add(steel);

    }

    public void addPretToList(Bar bar){
        barList.add(bar);
        itemsForTable.add(bar);
    }

    public void setOrderToEdit(Order order){
        orderId = order.getId();
        orderNum = order.getOrderNum();
        klientField.setText(order.getCustomer());
        nrTele.setText(order.getPhonNum());
        terminDost.setText(order.getDeliveryTime());
        adres.setText(order.getAdres());
        nip.setText(order.getNip());
        addInfo.setText(order.getAddInfo());
        orderNumLabel.setText(String.valueOf(order.getOrderNum()));
        steelList = order.getSteelList();
        barList = order.getBarList();

        itemsForTable.addAll(order.getSteelList());
        itemsForTable.addAll(order.getBarList());
    }


    public void deleteItem(){
        int selectedIndex = orderTable.getSelectionModel().getSelectedIndex();
        deleteItem(selectedIndex);
    }

    public void editItems() throws IOException{
        int selectedIndex = orderTable.getSelectionModel().getSelectedIndex();
        String path = null;
        if(selectedIndex >= 0)
            if (orderTable.getItems().get(selectedIndex).getClass() == Steel.class){

                Steel steel = (Steel) orderTable.getItems().get(selectedIndex);
                URL url = new File("src/main/java/fxmls/addSteelWindow.fxml").toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                AddSteelWindow addSteelWindow = loader.getController();
                addSteelWindow.setStalToEdit(steel, this, selectedIndex);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

            } else if (orderTable.getItems().get(selectedIndex).getClass() == Bar.class){

                Bar bar = (Bar) orderTable.getItems().get(selectedIndex);
                URL url = new File("src/main/java/fxmls/addPretWindow.fxml").toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                AddPretWindow addPretWindow = loader.getController();
                addPretWindow.setPretToEdit(bar,this, selectedIndex);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }

        }

    public void deleteItem(int selectedIndex){
        if (selectedIndex >= 0){
            if (itemsForTable.get(selectedIndex).getClass() == Steel.class){
                Steel itemToDelete = (Steel) itemsForTable.get(selectedIndex);
                steelList.remove(itemToDelete);

            }
            else if (itemsForTable.get(selectedIndex).getClass() == Bar.class){
                Bar itemToDelete = (Bar) itemsForTable.get(selectedIndex);
                barList.remove(itemToDelete);
            }
            itemsForTable.remove(selectedIndex);
            orderTable.refresh();
        }
    }

    public void setParentWindow(OrderList parentWindow){
        this.parentWindow = parentWindow;
    }
}
