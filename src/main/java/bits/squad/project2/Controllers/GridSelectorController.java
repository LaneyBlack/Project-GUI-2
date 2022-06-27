package bits.squad.project2.Controllers;

import bits.squad.project2.App;
import bits.squad.project2.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GridSelectorController implements Initializable {

    @FXML private ChoiceBox<Integer> widthBox;

    @FXML private ChoiceBox<Integer> heightBox;

    @FXML private Label wrongSize;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wrongSize.setVisible(false);
        for (int i = 2; i < 6; i++) {
            widthBox.getItems().add(i);
            heightBox.getItems().add(i);
        }
    }

    @FXML
    private void startGame() throws IOException {
        if ((widthBox.getValue() * heightBox.getValue())%2!=0){
            wrongSize.setVisible(true);
            throw  new IOException("Wrong width or height!");
        } else {
            Game.setGridHeight(heightBox.getValue());
            Game.setGridWidth(widthBox.getValue());
            App.setRoot("game");
        }
    }
}
