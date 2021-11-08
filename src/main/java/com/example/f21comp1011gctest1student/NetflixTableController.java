package com.example.f21comp1011gctest1student;

import Utilities.DBUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private ArrayList<NetflixShow> netflixShowArrayList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        netflixShowArrayList = DBUtility.getShowDetails("All", "All ratings");

        showIdCol.setCellValueFactory(new PropertyValueFactory<>("showId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        directorCol.setCellValueFactory(new PropertyValueFactory<>("director"));
        castCol.setCellValueFactory(new PropertyValueFactory<>("cast"));

        tableView.getItems().addAll(DBUtility.getShowDetails("All", "All ratings"));
        tableView.getItems().addAll(netflixShowArrayList);

        selectRatingComboBox.getItems().add("All ratings");
        selectRatingComboBox.getItems().addAll(getRatingFromTheTable());

        movieCheckBox.setSelected(true);
        tvCheckBox.setSelected(true);

        updateLabel();

    }

    private TreeSet<String> getRatingFromTheTable()
    {
        TreeSet<String> ratings = new TreeSet<>();

        for(NetflixShow shows : tableView.getItems())
            ratings.add(shows.getRating());

        return ratings;
    }




    @FXML
    void applyFilter(ActionEvent event)  {
        tableView.getItems().clear();


        String ratingSelected =  selectRatingComboBox.getSelectionModel().getSelectedItem();

        if (ratingSelected == null)
            ratingSelected = "All ratings";


        /*String type = "All";
        if (movieCheckBox.isSelected() && tvCheckBox.isSelected())
            type = "Movie";
        //for the check box

        else if (!movieCheckBox.isSelected() && tvCheckBox.isSelected())
            type = "TV Show";

        else if (!movieCheckBox.isSelected() && !tvCheckBox.isSelected())
            type = "none";

        tableView.getItems().addAll(DBUtility.getShowDetails(type, ratingSelected));*/


            //create the arraylist for the netflix shows;
        ArrayList<NetflixShow> list = new ArrayList<>();
        list.addAll(netflixShowArrayList);

        //create the loop
        for (NetflixShow  shows : netflixShowArrayList)
        {
            if (movieCheckBox.isSelected() && !tvCheckBox.isSelected() && !shows.getType().equals("Movie"))
                list.remove(shows);

            if (!movieCheckBox.isSelected() && tvCheckBox.isSelected() && !shows.getType().equals("TV Show"))
                list.remove(shows);

            if (!movieCheckBox.isSelected() && !tvCheckBox.isSelected())
                list.remove(shows);

            if (!ratingSelected.equals("All ratings") && !shows.getRating().equals(ratingSelected))
                list.remove(shows);


        }
        tableView.getItems().addAll(list);
        updateLabel();

    }
    private void updateLabel(){
        numOfShowsLabel.setText("Number of movies/shows: " +"  "+ tableView.getItems().size());
    }
}
