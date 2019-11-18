package controllers;

import entities.Steel;
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

    private Steel createSteel(){
        try{
            Steel steel = new Steel();

            steel.setHeigh(Float.parseFloat(height.getText()));
            steel.setWidth(Float.parseFloat(width.getText()));
            steel.setThick(Float.parseFloat(thick.getText()));
            steel.setLengthCM(Float.parseFloat(length.getText()));
            steel.setCount(Integer.parseInt(count.getText()));
            steel.setTier(Integer.parseInt(tier.getText()));
            String adInf;
            if(addInfo.getText() != null) {
                adInf = addInfo.getText();
            } else {
                adInf = " ";
            }
            steel.setAddInfo(adInf);
            return steel;

        }catch (NumberFormatException e){
            return null;
        }

    }


    public void ready(ActionEvent event){
        Steel steel = createSteel();
        if (steel != null){
            if(edited){
                parentWindow.deleteItem(selectedIndex);
            }
            parentWindow.addSteelToList(steel);
            Stage appStage;
            appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.close();
        }
    }

    public void setSteelToEdit(Steel steel, OrderAddWindow parentWindow, int selectedIndex){
        height.setText(String.valueOf(steel.getHeigh()));
        width.setText(String.valueOf(steel.getWidth()));
        thick.setText(String.valueOf(steel.getThick()));
        length.setText(String.valueOf(steel.getLengthCM()));
        count.setText(String.valueOf(steel.getCount()));
        tier.setText(String.valueOf(steel.getTier()));
        addInfo.setText(steel.getAddInfo());
        edited = true;
        this.selectedIndex = selectedIndex;
        setParentWindow(parentWindow);
    }

    public void setParentWindow(OrderAddWindow parentWindow){
        this.parentWindow = parentWindow;
    }

}
