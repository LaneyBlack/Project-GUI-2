package bits.squad.project2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    private static Game game;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("welcome"), 800,600);
        stage.setScene(scene);
        stage.setTitle("Memory the Game");
        stage.show();
        stage.setOnHidden(e -> {
            if (game!=null)
                game.getChecker().stop();
            Platform.exit();
        });
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setGame(Game game) {
        App.game = game;
    }

    public static Game getGame() {
        return game;
    }
}