package bits.squad.project2.Controllers;

import bits.squad.project2.App;
import bits.squad.project2.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class HighScoresController implements Initializable {

    @FXML private TableView<Player> playersTable;
    @FXML private TableColumn<Player,String> nicknameColumn;
    @FXML private TableColumn<Player,String> timeColumn;
    @FXML private TableColumn<Player, Integer> gridSizeColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nicknameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("nickname"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("time"));
        gridSizeColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("gridSize"));

        ObservableList<Player> players = getPlayers();
        playersTable.setItems(players);
    }

    public ObservableList getPlayers(){
        ObservableList<Player> players = FXCollections.observableArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/bits/squad/project2/data/highscores.txt"));
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                String[] values = text.trim().split("\t");
                players.add(new Player(values[0], values[1], Integer.parseInt(values[2])));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return players;
    }
}
