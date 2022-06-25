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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wrongSize.setVisible(false);
        for (int i = 2; i < 9; i++) {
            width.getItems().add(i);
            height.getItems().add(i);
        }
    }

    @FXML
    private void startGame() throws IOException {
        System.out.println(width.getValue() * height.getValue());
        if ((width.getValue() * height.getValue())%2!=0){
            if(wrongSize.isVisible()){
                wrongSize.setVisible(false);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                wrongSize.setVisible(true);
            }
            wrongSize.setVisible(true);
        } else {
            Game.setGridHeight(height.getValue());
            Game.setGridWidth(width.getValue());

            App.setRoot("game");
        }
    }
}
