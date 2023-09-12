package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {

    @FXML
    private Button btnNext;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField txtUserMobileNumber;

    @FXML
    private TextField txtUserPassword;


    @FXML
    void nextAction(ActionEvent event) throws Exception {
        Controler.navigator(event,"../view/UserRegistationFromView.fxml");
    }

    @FXML
    void signUpAction(ActionEvent event) throws Exception {
        if(Controler.checkContactYesOrNot(txtUserMobileNumber.getText()) && Controler.checkPassword(txtUserMobileNumber.getText(),txtUserPassword.getText())){
            Controler.navigator(event,"../view/HomePageView.fxml");
        }else{
            Controler.setAlertBox("Error","Please register as a user");
        }

    }

}