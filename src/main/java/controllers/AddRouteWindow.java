package controllers;

import dao.OrderDAO;
import dao.RouteDAO;
import entities.Order;
import entities.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.stage.Stage;


public class AddRouteWindow {

    @FXML
    TextField truck;
    @FXML
    TextField driver;
    @FXML
    TextField hour;
    @FXML
    TextField day;
    @FXML
    ListView<Order> newRoute;
    @FXML
    ListView<Order> allOrderList;


    private int id;
    private ObservableList<Order> allOrders;
    private ObservableList<Order> routeOrderList;
    private Order tempOrder;
    private Routes parentWindow;

    @FXML
    public void initialize() {
        allOrders = FXCollections.observableArrayList();
        routeOrderList = FXCollections.observableArrayList();

        OrderDAO orderDAO = new OrderDAO();
        allOrders.addAll(orderDAO.getOrdersWithNoRoute());

        allOrderList.setItems(allOrders);
        newRoute.setItems(routeOrderList);

        allOrderList.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getSource() == allOrderList) {
                    int selectedIndex = allOrderList.getSelectionModel().getSelectedIndex();
                    tempOrder = allOrderList.getItems().get(selectedIndex);
                }
            }
        });

        newRoute.setOnMouseReleased( event -> {
                if (event.getSource() == newRoute && tempOrder != null) {
                    routeOrderList.add(tempOrder);
                    newRoute.refresh();
                    allOrders.remove(tempOrder);
                    allOrderList.refresh();
                    tempOrder = null;
            }
        });
    }

    private Route createRoute(){
        Route route = null;
        try {
            route = new Route();
            route.setTruck(truck.getText());
            route.setDriver(driver.getText());
            route.setDay(day.getText());
            route.setStartHr(hour.getText());
            for (Order order : routeOrderList){
                order.setRouteId(id);
            }
            route.setOrderList(routeOrderList);

        } catch (IllegalArgumentException e){
            e.printStackTrace();
            route = null;
        }
        return route;
        }


    public void gotowe(ActionEvent event){
        Route route = createRoute();
        if(route != null) {
            RouteDAO routeDAO = new RouteDAO();
            routeDAO.saveOrUpdateRoute(route);
            parentWindow.addRoute(route);
            parentWindow.routesTable.refresh();
            Stage appStage;
            appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.close();
        }

    }

    public void setRouteToEdit(Route route){
        id = route.getId();
        System.out.println(id);
        truck.setText(route.getTruck());
        driver.setText(route.getDriver());
        hour.setText(route.getStartHr());
        day.setText(route.getDay());
        routeOrderList.addAll(route.getOrderList());
    }

    public void setParentWindow(Routes parentWindow){
        this.parentWindow = parentWindow;
    }

}
