package com.bella.controller;


import com.bella.EmailManager;
import com.bella.controller.services.LoginService;
import com.bella.model.EmailAccount;
import com.bella.view.ViewFactory;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.bella.controller.EmailLoginResult.SUCCES;

public class LoginWindowController extends BaseController  {

    @FXML
    private ImageView foto;

    @FXML
    private ImageView fotouser;

    @FXML
    private ImageView fotopas;

    @FXML
    private JFXTextField emailAddressField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private Label errorLabel;

    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    void loginButtonAction() {
        System.out.println("loginButton apelat");
        if(fieldsAreValid()){
            EmailAccount emailAccount = new EmailAccount(emailAddressField.getText(), passwordField.getText());
            LoginService loginService = new LoginService(emailAccount, emailManager);
            loginService.start();
            loginService.setOnSucceeded(event ->{

                EmailLoginResult emailLoginResult= loginService.getValue();

                switch (emailLoginResult){
                    case SUCCES:
                        System.out.println("login succesfull " + emailAccount);
                        if(viewFactory.isMainViewInitialized()){
                            viewFactory.showMainWindow();
                        }

                        Stage stage = (Stage) errorLabel.getScene().getWindow(); // pt disparitia ferestrei de logIn
                        viewFactory.closeStage(stage);
                        return;
                }

            });

        }

    }

    private boolean fieldsAreValid() {
        if(emailAddressField.getText().isEmpty()){
            errorLabel.setText("Completeaza emailul");
            return false;
        }
        if(passwordField.getText().isEmpty()){
            errorLabel.setText("Completeaza parola");
            return false;
        }
        return true;

    }


}
