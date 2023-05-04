package com.example.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    public TableView productTableView;
    public Button deleteButton, addButton, saveButton;
    public TextField idTextField, sectionTextField, itemTextField, avTextField, startTextField, finishTextField,
            orgTextField, statusTextField;
    public TextArea textArea;
    public TextField keywordsTextField;
    public Button orderButton;
    public CheckBox checkBox;
    public Label label1;
    public Button helpButton;
    public AnchorPane pane;
    public ImageView imageView;
    TableColumn col0, col1, col2, col3, col4, col5, col6, col7;

    TaskDAO inDAO;
    private Stage stage;
    private Scene scene;

    private ObservableList<ProductsData> fxlist;

    public void createTable() {

            col0 = new TableColumn<>("Номер");
            col0.setMinWidth(40);
            col0.setCellValueFactory(new PropertyValueFactory<>("id"));

            StringConverter<Integer> d = new IntegerStringConverter();
            col0.setCellFactory(new Callback<TableColumn<ProductsData, Integer>, TableCell<ProductsData, Integer>>() {
                @Override
                public TableCell<ProductsData, Integer> call(TableColumn<ProductsData, Integer> param) {
                    return new TextFieldTableCell<>(d);
                }
            });

            col0.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProductsData, Integer>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<ProductsData, Integer> t) {
                    ((ProductsData) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setId(t.getNewValue());
                }
            });

            col1 = new TableColumn<>("Раздел");
            col1.setPrefWidth(150);
            col1.setCellValueFactory(new PropertyValueFactory<>("section"));

            col1.setCellFactory(TextFieldTableCell.forTableColumn());

            col1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProductsData, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ProductsData, String> t) {
                ((ProductsData) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setSection(t.getNewValue());
            }
            });

            col2 = new TableColumn<>("Наименование товара");
            col2.setPrefWidth(230);
            col2.setCellValueFactory(new PropertyValueFactory<>("item"));

            col2.setCellFactory(TextFieldTableCell.forTableColumn());

            col2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProductsData, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ProductsData, String> t) {
                ((ProductsData) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setItem(t.getNewValue());
            }
            });

            col3 = new TableColumn<>("В наличии");
            col3.setMinWidth(40);
            col3.setCellValueFactory(new PropertyValueFactory<>("av"));

            StringConverter<Integer> d1 = new IntegerStringConverter();
            col3.setCellFactory(new Callback<TableColumn<ProductsData, Integer>, TableCell<ProductsData, Integer>>() {
            @Override
            public TableCell<ProductsData, Integer> call(TableColumn<ProductsData, Integer> param) {
                return new TextFieldTableCell<>(d1);
            }
            });

            col3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProductsData, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ProductsData, Integer> t) {
                ((ProductsData) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setAv(t.getNewValue());
            }
            });

            col4 = new TableColumn<>("Цена");
            col4.setMinWidth(40);
            col4.setCellValueFactory(new PropertyValueFactory<>("startp"));

            col4.setCellFactory(TextFieldTableCell.forTableColumn());

            col4.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProductsData, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ProductsData, String> t) {
                ((ProductsData) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setStartp(t.getNewValue());
            }
            });

            col5 = new TableColumn<>("Конец");
            col5.setMinWidth(40);
            col5.setCellValueFactory(new PropertyValueFactory<>("finish"));

            col5.setCellFactory(TextFieldTableCell.forTableColumn());

            col5.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProductsData, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ProductsData, String> t) {
                ((ProductsData) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setFinish(t.getNewValue());
            }
            });

            col6 = new TableColumn<>("Организатор");
            col6.setMinWidth(40);
            col6.setCellValueFactory(new PropertyValueFactory<>("org"));

            col6.setCellFactory(TextFieldTableCell.forTableColumn());

            col6.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProductsData, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ProductsData, String> t) {
                ((ProductsData) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setOrg(t.getNewValue());
            }
            });

            col7 = new TableColumn<>("Статус");
            col7.setMinWidth(40);
            col7.setCellValueFactory(new PropertyValueFactory<>("statusp"));

            col7.setCellFactory(TextFieldTableCell.forTableColumn());

            col7.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProductsData, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ProductsData, String> t) {
                ((ProductsData) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setStatusp(t.getNewValue());
            }
            });

            productTableView.setItems(fxlist);
            productTableView.getColumns().addAll(col0, col1, col2, col3, col4, col5, col6, col7);// добавление столбцов
            productTableView.setItems(fxlist);
            productTableView.setEditable(true);

            FilteredList<ProductsData> filteredData = new FilteredList<>(fxlist, b -> true);
            keywordsTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(productSearchModel -> {

                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (productSearchModel.getSection().toLowerCase().indexOf(searchKeyword) != -1) {
                    return true;
                } else if (productSearchModel.getItem().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (productSearchModel.getOrg().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;
                });
            });

            SortedList<ProductsData> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(productTableView.comparatorProperty());

            productTableView.setItems(sortedData);
    }

    public void setSaveButton() {
        ProductsData selectedTask = (ProductsData) productTableView.getSelectionModel().getSelectedItem();

        inDAO.updateTask(selectedTask);
        fxlist = FXCollections.observableList(inDAO.getAllTasks());
        productTableView.setItems(fxlist);

        textArea.setText("Данные изменены");
    }
    public void setHelpButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("helper.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setToOrderButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Предупреждение!");
        alert.setHeaderText("Вы действительно хотите оставить заявку на этот товар?");

        ButtonType yes = new ButtonType("Да");
        ButtonType no = new ButtonType("Нет");
        // Remove default ButtonTypes
        alert.getButtonTypes().clear();

        alert.getButtonTypes().addAll(yes, no);

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == yes) {

            Alert nalert = new Alert(Alert.AlertType.INFORMATION);

            nalert.setTitle("Статус заявки");
            nalert.setHeaderText(null);
            nalert.setContentText("Заявка принята, ожидайте обратной связи от организатора!");

            nalert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      inDAO = new Service();
      fxlist = FXCollections.observableList(inDAO.getAllTasks());
      createTable();
        idTextField.setVisible(false);
        sectionTextField.setVisible(false);
        itemTextField.setVisible(false);
        avTextField.setVisible(false);
        startTextField.setVisible(false);
        finishTextField.setVisible(false);
        orgTextField.setVisible(false);
        statusTextField.setVisible(false);
        addButton.setVisible(false);
        saveButton.setVisible(false);
        deleteButton.setVisible(false);
        orderButton.setVisible(true);
        textArea.setVisible(false);
        label1.setVisible(false);

        Image image  = new Image(getClass().getResourceAsStream("image.png"));
        imageView.setImage(image);
    }
    public void changeVisible(ActionEvent event) {

        if (checkBox.isSelected()){
            idTextField.setVisible(true);
            sectionTextField.setVisible(true);
            itemTextField.setVisible(true);
            avTextField.setVisible(true);
            startTextField.setVisible(true);
            finishTextField.setVisible(true);
            orgTextField.setVisible(true);
            statusTextField.setVisible(true);
            addButton.setVisible(true);
            saveButton.setVisible(true);
            deleteButton.setVisible(true);
            orderButton.setVisible(false);
            textArea.setVisible(true);
            label1.setVisible(true);
        }
        else {
            idTextField.setVisible(false);
            sectionTextField.setVisible(false);
            itemTextField.setVisible(false);
            avTextField.setVisible(false);
            startTextField.setVisible(false);
            finishTextField.setVisible(false);
            orgTextField.setVisible(false);
            statusTextField.setVisible(false);
            addButton.setVisible(false);
            saveButton.setVisible(false);
            deleteButton.setVisible(false);
            orderButton.setVisible(true);
            textArea.setVisible(false);
            label1.setVisible(false);
        }
    }
    public void setDeleteButton() {

        ProductsData selectedTask = (ProductsData) productTableView.getSelectionModel().getSelectedItem();

        if (selectedTask != null) {
            inDAO.deleteTask(selectedTask.getId());
            fxlist = FXCollections.observableList(inDAO.getAllTasks());
            productTableView.setItems(fxlist);
        }
        textArea.setText("Данные удалены");
    }

    public void setAddButton() {
        // Создаем новый объект задачи на основе введенных пользователем данных в форму
        ProductsData newTask = new ProductsData(
                Integer.parseInt(idTextField.getText()),
                sectionTextField.getText(),
                itemTextField.getText(),
                Integer.parseInt(avTextField.getText()),
                startTextField.getText(),
                finishTextField.getText(),
                orgTextField.getText(),
                statusTextField.getText()
        );

        // Вызываем метод DAO для добавления задачи в базу данных
        inDAO.addTask(newTask);

        // Обновляем список задач в таблице
        fxlist = FXCollections.observableList(inDAO.getAllTasks());
        productTableView.setItems(fxlist);

        // Очищаем поля формы
        idTextField.clear();
        sectionTextField.clear();
        itemTextField.clear();
        avTextField.clear();
        startTextField.clear();
        finishTextField.clear();
        orgTextField.clear();
        statusTextField.clear();

        textArea.setText("Добавлены новые данные");
    }
}