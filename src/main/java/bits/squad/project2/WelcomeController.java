package bits.squad.project2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private Label welcomeText;

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