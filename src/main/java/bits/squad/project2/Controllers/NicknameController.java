package bits.squad.project2.Controllers;

import bits.squad.project2.App;
import bits.squad.project2.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;

public class NicknameController {
    @FXML
    TextField nicknameField;
    @FXML
    private void submit(){
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/bits/squad/project2/data/highscores.txt", true);
            fileWriter.append("\n").append(nicknameField.getText()).append("\t").append(App.getGame().getTimerLabel().getText()).append("\t")
                    .append(String.valueOf(Game.getGridHeight()*Game.getGridWidth()));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Platform.exit();
    }
}
