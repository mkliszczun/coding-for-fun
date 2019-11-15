package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;


// under construction

public class History {
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
}
