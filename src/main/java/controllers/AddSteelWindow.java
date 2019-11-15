package controllers;

import entities.Stal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddSteelWindow {
    @FXML
    TextField height;
    @FXML
    TextField width;
    @FXML
    TextField thick;
    @FXML
    TextField length;
    @FXML
    TextField count;
    @FXML
    TextField tier;
    @FXML
    TextArea addInfo;

    private OrderAddWindow parentWindow;

    private boolean edited = false;
    private int selectedIndex;

    private Stal createStal(){
        try{
            Stal stal = new Stal();

            stal.setHeigh(Float.parseFloat(height.getText()));
            stal.setWidth(Float.parseFloat(width.getText()));
            stal.setThick(Float.parseFloat(thick.getText()));
            stal.setLengthCM(Float.parseFloat(length.getText()));
            stal.setCount(Integer.parseInt(count.getText()));
            stal.setTier(Integer.parseInt(tier.getText()));
            String adInf;
            if(addInfo.getText() != null) {
                adInf = addInfo.getText();
            } else {
                adInf = " ";
            }
            stal.setAddInfo(adInf);
            return stal;

        }catch (NumberFormatException e){
            return null;
        }

    }


    public void gotowe(ActionEvent event){
        Stal stal = createStal();
        if (stal != null){
            if(edited){
                parentWindow.deleteItem(selectedIndex);
            }
            parentWindow.addSteelToList(stal);
            Stage appStage;
            appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.close();
        }
    }

    public void setStalToEdit(Stal stal, OrderAddWindow parentWindow, int selectedIndex){
        height.setText(String.valueOf(stal.getHeigh()));
        width.setText(String.valueOf(stal.getWidth()));
        thick.setText(String.valueOf(stal.getThick()));
        length.setText(String.valueOf(stal.getLengthCM()));
        count.setText(String.valueOf(stal.getCount()));
        tier.setText(String.valueOf(stal.getTier()));
        addInfo.setText(stal.getAddInfo());
        edited = true;
        this.selectedIndex = selectedIndex;
        setParentWindow(parentWindow);
    }

    public void setParentWindow(OrderAddWindow parentWindow){
        this.parentWindow = parentWindow;
    }

}
