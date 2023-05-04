package com.example.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable {
    public Button buttonToMain;
    public Button sendButton;
    public TextField textField2;
    public TextField textField3;
    public TextField textField1;
    public TextArea textArea;
    private Stage stage;
    private Scene scene;
    private Parent parent;

    public void toMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("products.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        String css = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
    }
    public void setSendButton() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("Вопрос в поддержку отправлен! Ожидайте ответ в ближайшее время.");

        alert.showAndWait();

        textArea.clear();
        textField1.clear();
        textField2.clear();
        textField3.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
