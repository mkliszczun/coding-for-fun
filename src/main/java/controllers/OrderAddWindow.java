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


    private List<Stal> stalList;
    private ObservableList<TableListable> itemsForTable;
    private List<Pret> pretList;
    private String orderNum;
    private int orderId;
    private OrderList parentWindow;


    @FXML
    public void initialize() {
        OrderIDGenerator idGenerator = new OrderIDGenerator();
        orderNum = idGenerator.generate();
        stalList = new ArrayList<>();
        itemsForTable = FXCollections.observableArrayList();
        pretList = new ArrayList<>();
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
        order.setStalList(stalList);
        order.setPretList(pretList);
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


    public void addSteelToList(Stal stal) {
        stalList.add(stal);
        itemsForTable.add(stal);

    }

    public void addPretToList(Pret pret){
        pretList.add(pret);
        itemsForTable.add(pret);
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
        stalList = order.getStalList();
        pretList = order.getPretList();

        itemsForTable.addAll(order.getStalList());
        itemsForTable.addAll(order.getPretList());
    }


    public void deleteItem(){
        int selectedIndex = orderTable.getSelectionModel().getSelectedIndex();
        deleteItem(selectedIndex);
    }

    public void editItems() throws IOException{
        int selectedIndex = orderTable.getSelectionModel().getSelectedIndex();
        String path = null;
        if(selectedIndex >= 0)
            if (orderTable.getItems().get(selectedIndex).getClass() == Stal.class){

                Stal stal = (Stal) orderTable.getItems().get(selectedIndex);
                URL url = new File("src/main/java/fxmls/addSteelWindow.fxml").toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                AddSteelWindow addSteelWindow = loader.getController();
                addSteelWindow.setStalToEdit(stal, this, selectedIndex);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

            } else if (orderTable.getItems().get(selectedIndex).getClass() == Pret.class){

                Pret pret = (Pret) orderTable.getItems().get(selectedIndex);
                URL url = new File("src/main/java/fxmls/addPretWindow.fxml").toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                AddPretWindow addPretWindow = loader.getController();
                addPretWindow.setPretToEdit(pret,this, selectedIndex);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }

        }

    public void deleteItem(int selectedIndex){
        if (selectedIndex >= 0){
            if (itemsForTable.get(selectedIndex).getClass() == Stal.class){
                Stal itemToDelete = (Stal) itemsForTable.get(selectedIndex);
                stalList.remove(itemToDelete);

            }
            else if (itemsForTable.get(selectedIndex).getClass() == Pret.class){
                Pret itemToDelete = (Pret) itemsForTable.get(selectedIndex);
                pretList.remove(itemToDelete);
            }
            itemsForTable.remove(selectedIndex);
            orderTable.refresh();
        }
    }

    public void setParentWindow(OrderList parentWindow){
        this.parentWindow = parentWindow;
    }
}
