package bits.squad.project2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WelcomeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void exit(){
        Platform.exit();
    }

    @FXML
    protected void newGame(){
        App.setRoot("");
    }

    @FXML
    protected void highScores(){

    }
}