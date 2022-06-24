package bits.squad.project2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GridSelectorController implements Initializable {

    @FXML
    private ChoiceBox<Integer> width;

    @FXML
    private ChoiceBox<Integer> height;

    @FXML
    private Label wrongSize;

    private Integer [] sizes = {1,2,3,4,5,6,7,8};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wrongSize.setVisible(false);
        for (int i = 2; i < 9; i++) {
            width.getItems().add(i);
            height.getItems().add(i);
        }
    }

    @FXML
    private void start() throws IOException {
        System.out.println(width.getValue() * height.getValue());
        if ((width.getValue() * height.getValue())%2!=0){
            wrongSize.setVisible(true);
        } else {
            Game.setGridY(height.getValue());
            Game.setGridX(width.getValue());
            App.setRoot("game");
        }
    }
}
