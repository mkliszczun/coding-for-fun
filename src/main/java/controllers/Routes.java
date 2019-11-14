package controllers;

import dao.RouteDAO;
import entities.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Routes {

    @FXML
    TableView<Route> routesTable;
    @FXML
    TableColumn<Route, String> day;
    @FXML
    TableColumn<Route, String> truck;
    @FXML
    TableColumn<Route, String> driver;
    @FXML
    TableColumn<Route, String> destination;
    @FXML
    TableColumn<Route, String> orderCount;

    private ObservableList<Route> allRoutesList;

    @FXML
    public void initialize(){
        RouteDAO routeDAO = new RouteDAO();
        allRoutesList = FXCollections.observableArrayList(routeDAO.getAllRoutes());
        routesTable.setItems(allRoutesList);
        day.setCellValueFactory(new PropertyValueFactory<>("day"));
        truck.setCellValueFactory(new PropertyValueFactory<>("truck"));
        driver.setCellValueFactory(new PropertyValueFactory<>("driver"));
//        orderCount.setCellValueFactory(new PropertyValueFactory<>("orderCount"));
    }

    public void deleteRoute(ActionEvent event){
        int selectedIndex = routesTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            RouteDAO routeDAO = new RouteDAO();
        routeDAO.deleteRoute(routesTable.getItems().get(selectedIndex));
            allRoutesList.remove(selectedIndex);
            routesTable.refresh();
        }
    }

    public void setRouteToEdit()throws IOException{
        int selectedIndex = routesTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            int routeId = routesTable.getItems().get(selectedIndex).getId();
            RouteDAO routeDAO = new RouteDAO();
            Route route = routeDAO.getRouteWithOrders(routeId);

            URL url = new File("src/main/java/fxmls/addRouteWindow.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            AddRouteWindow addRouteWindow= loader.getController();
            addRouteWindow.setRouteToEdit(route);
            addRouteWindow.setParentWindow(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }
    }


    public void home(MouseEvent event) throws IOException {
        Stage appStage;
        Parent root;
        URL url = new File("src/main/java/fxmls/sample.fxml").toURI().toURL();

        appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    public void openAddRouteWindow(MouseEvent event) throws IOException{
        Stage appStage = new Stage();
        Parent root;
        URL url = new File("src/main/java/fxmls/addRouteWindow.fxml").toURI().toURL();


        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();

        AddRouteWindow addRouteWindow = loader.getController();
        addRouteWindow.setParentWindow(this);

        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    public void addRoute(Route route){
        allRoutesList.add(route);
    }

}