package controllers;

import entities.Pret;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddPretWindow {

    @FXML
    TextField d;

    @FXML
    TextField count;

    @FXML
    TextField length;

    @FXML
    TextArea addInfo;

    private boolean edited = false;
    private OrderAddWindow parentWindow;
    private int selectedIndex;

    private Pret createPret(){
        try {
            int diam = Integer.parseInt(d.getText());
            int cnt = Integer.parseInt(count.getText());
            float len = Float.parseFloat(length.getText());
            String adInf;
            if (addInfo.getText() != null) {
                adInf = addInfo.getText();
            } else {
                adInf = "";
            }
            Pret pret = new Pret();
            pret.setD(diam);
            pret.setCount(cnt);
            pret.setLength(len);
            pret.setAddInfo(adInf);

            return pret;

        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
            System.out.println("at gotowe in AddPretWindow");

            return null;
        }
    }



    public void gotowe(ActionEvent event){
        Pret pret = createPret();
        if (pret != null) {
            if (edited){
                parentWindow.deleteItem(selectedIndex);
            }
            parentWindow.addPretToList(pret);
            Stage appStage;
            appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.close();
        }
    }

    public void setPretToEdit(Pret pret, OrderAddWindow parentWindow, int selectedIndex){
        d.setText(String.valueOf(pret.getD()));
        count.setText(String.valueOf(pret.getCount()));
        length.setText(String.valueOf(pret.getLength()));
        addInfo.setText(pret.getAddInfo());
        this.selectedIndex = selectedIndex;
        edited = true;
        setParentWindow(parentWindow);
    }

    protected void setParentWindow(OrderAddWindow parentWindow){
        this.parentWindow = parentWindow;
    }
}
