package com.example.f21comp1011gctest1student;

import Utilities.DBUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class NetflixTableController implements Initializable {

    @FXML
    private TableView<NetflixShow> tableView;

    @FXML
    private TableColumn<NetflixShow, String> showIdCol;

    @FXML
    private TableColumn<NetflixShow, String> typeCol;

    @FXML
    private TableColumn<NetflixShow, String> titleCol;

    @FXML
    private TableColumn<NetflixShow, String> ratingCol;

    @FXML
    private TableColumn<NetflixShow, String> directorCol;

    @FXML
    private TableColumn<NetflixShow, String> castCol;

    @FXML
    private CheckBox movieCheckBox;

    @FXML
    private CheckBox tvCheckBox;

    @FXML
    private ComboBox<String> selectRatingComboBox;

    @FXML
    private Label numOfShowsLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showIdCol.setCellValueFactory(new PropertyValueFactory<>("showId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        directorCol.setCellValueFactory(new PropertyValueFactory<>("director"));
        castCol.setCellValueFactory(new PropertyValueFactory<>("cast"));

        tableView.getItems().addAll(DBUtility.getShowDetails("All", "All ratings"));

        selectRatingComboBox.getItems().add("All ratings");
        selectRatingComboBox.getItems().addAll(getRatingFromTable());
        updateLabel();

        movieCheckBox.setSelected(true);
        tvCheckBox.setSelected(true);

    }

    private TreeSet<String> getRatingFromTable(){
        TreeSet<String> ratings = new TreeSet<>();

        for(NetflixShow show : tableView.getItems())
            ratings.add(show.getRating());

        return ratings;
    }




    @FXML
    void applyFilter(ActionEvent event)  {


    }
    private void updateLabel(){
        numOfShowsLabel.setText("Number of movies/shows: " +"  "+ tableView.getItems().size());
    }
}
