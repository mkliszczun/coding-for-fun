package controllers;

import entities.Bar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddBarWindow {

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

    private Bar createBar(){
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
            Bar bar = new Bar();
            bar.setD(diam);
            bar.setCount(cnt);
            bar.setLength(len);
            bar.setAddInfo(adInf);

            return bar;

        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
            return null;
        }
    }



    public void ready(ActionEvent event){
        Bar bar = createBar();
        if (bar != null) {
            if (edited){
                parentWindow.deleteItem(selectedIndex);
            }
            parentWindow.addBarToList(bar);
            Stage appStage;
            appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.close();
        }
    }

    public void setBarToEdit(Bar bar, OrderAddWindow parentWindow, int selectedIndex){
        d.setText(String.valueOf(bar.getD()));
        count.setText(String.valueOf(bar.getCount()));
        length.setText(String.valueOf(bar.getLength()));
        addInfo.setText(bar.getAddInfo());
        this.selectedIndex = selectedIndex;
        edited = true;
        setParentWindow(parentWindow);
    }

    protected void setParentWindow(OrderAddWindow parentWindow){
        this.parentWindow = parentWindow;
    }
}
