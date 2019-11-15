package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainMenu {

    static {
//        creates sessionFactory at the beginning, to avoid loading while opening orderList, or Routes
        HibernateUtil.getSessionFactory();
    }


    @FXML
    public void initialize(){}

    public void swapScene(MouseEvent event, String path) throws IOException {
        Stage appStage;
        Parent root;
        URL url = new File(path).toURI().toURL();

        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(url);
//        root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();

    }

    public void routesClicked(MouseEvent event) throws IOException {
        swapScene(event, "src/main/java/fxmls/routes.fxml");
    }

    public void orderClicked(MouseEvent event) throws IOException {
        swapScene(event, "src/main/java/fxmls/orderList.fxml");
    }

    public void home(MouseEvent event) throws IOException {
        swapScene(event, "src/main/java/fxmls/mainMenu.fxml");
    }

    public void history(MouseEvent event) throws IOException {
        swapScene(event, "src/main/java/fxmls/history.fxml");
    }
}
