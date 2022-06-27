package bits.squad.project2.Controllers;

import bits.squad.project2.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class WelcomeController {
    @FXML
    protected void exit(){
        Platform.exit();
    }

    @FXML
    protected void gridSelector() throws IOException {
        App.setRoot("gridselector");
    }

    @FXML
    protected void highScores() throws IOException {
        App.setRoot("highscores");
    }
}