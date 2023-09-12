package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modal.User;

public class UserRegistationFromController {
    Alert alert=new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private Button btnBack;

    @FXML
    private Button btnCreateAccount;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtMobileNumber;

    @FXML
    private TextField txtPaswordOne;

    @FXML
    private TextField txtPaswordTwo;

    @FXML
    private TextField txtUserName;

    @FXML
    void backAction(ActionEvent event) throws Exception { // back to home page action
        Controler.navigator(event,"../view/MainFormView.fxml");
    }

    @FXML
    void createAction(ActionEvent event) throws Exception {  // create new account action
        if(!txtUserName.getText().equalsIgnoreCase("") && !txtMobileNumber.getText().equalsIgnoreCase("") && !txtMail.getText().equalsIgnoreCase("")&& !txtPaswordOne.getText().equalsIgnoreCase("")&& !txtPaswordTwo.getText().equalsIgnoreCase("")){

            //Check user already registered or not
            if(!Controler.checkContactYesOrNot(txtMobileNumber.getText())){

                // Check passwords are equals or not
                if(txtPaswordOne.getText().equals(txtPaswordTwo.getText())){

                    //create new user
                    boolean bl=Controler.addUser(new User(0,txtUserName.getText(),txtMobileNumber.getText(),txtMail.getText(),txtPaswordOne.getText()));

                    if(bl){
                        // navigate home page
                        Controler.setAlertBox("Error","Added Successfully");
                        Controler.navigator(event,"../view/MainFormView.fxml");
                    }

                }else{
                    Controler.setAlertBox("Error","Passwords are not match. please enter again");
                }
            }else{
                Controler.setAlertBox("Error","This user wos already registered");
            }

        }else{
           // Alert, If all fields are not fill
            Controler.setAlertBox("Error","All fields are required");
        }
    }


}