package bits.squad.project2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

public class Game implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label timerLabel;
    private static int gridHeight = 0;
    private static int gridWidth = 0;
    private boolean isStarted = false;
    private CardView[] cards;
    private CardView[] chosenCards = new CardView[2];
    private Thread checker;
    private Semaphore mutex = new Semaphore(1);
    private Timeline timer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.getScene().getWindow().setHeight(900);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        CardView.setBackSide(new Image(App.class.getResource("img/card-back.png").toExternalForm(), 140, 140, true, true));
        cards = new CardView[gridWidth * gridHeight];
        int imageId = 1;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                cards[i] = new CardView(imageId);
                int index = 0;
                while (cards[index] != null)
                    index = (int) (Math.random() * cards.length);
                cards[index] = new CardView(imageId++);
            }
        }
        byte index = 0;
        for (int row = 0; row < gridWidth; row++) {
            for (int column = 0; column < gridHeight; column++) {
                HBox box = new HBox(5);
                box.getChildren().add(cards[index++]);
                grid.add(box, row, column);
            }
        }
        memoryTimer();
        App.setGame(this);
        startHandler();
    }

    public void memoryTimer() {
        for (CardView card : cards) {
            card.open();
        }
        Time time = new Time(0, 10);
        timer = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        e -> {
                            if (!isStarted) {
                                if (time.getSecond() == 1) {
                                    for (CardView card : cards) {
                                        card.close();
                                    }
                                    isStarted = true;
                                }
                                time.minusSecondPassed();
                                progressBar.setProgress(((double) time.getSecond() / 10));
                            } else {
                                time.oneSecondPassed();
                            }
                            timerLabel.setText(time.getCurrentTime());
                        })
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public void startHandler() {
        checker = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    mutex.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (isStarted && getFirstCard() != null && getSecondCard() != null) {
                    if (getFirstCard().getImageId() == getSecondCard().getImageId()) {
                        getFirstCard().setOnMouseClicked(e -> System.out.println("This card is already opened!"));
                        getSecondCard().setOnMouseClicked(e -> System.out.println("This card is already opened!"));
                        setFirstCard(null);
                        setSecondCard(null);
                        progressBar.setProgress((double)Math.round((1/(double)(gridWidth*gridHeight/2) + progressBar.getProgress())*100)/100);
                        if (progressBar.getProgress() >= 1) {
                            timer.stop();
                            try {
                                Thread.sleep(1000);
                                App.setRoot("nickname");
                            } catch (InterruptedException | IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        }
                    } else {
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        getFirstCard().close();
                        getSecondCard().close();
                        setFirstCard(null);
                        setSecondCard(null);
                    }
                }
            }
        });
        checker.start();
    }

    public static void setGridHeight(int gridHeight) {
        Game.gridHeight = gridHeight;
    }

    public static void setGridWidth(int gridWidth) {
        Game.gridWidth = gridWidth;
    }

    public static int getGridHeight() {
        return gridHeight;
    }

    public static int getGridWidth() {
        return gridWidth;
    }

    public CardView getFirstCard() {
        return chosenCards[0];
    }

    public void setFirstCard(CardView value) {
        chosenCards[0] = value;
    }

    public CardView getSecondCard() {
        return chosenCards[1];
    }

    public void setSecondCard(CardView value) {
        chosenCards[1] = value;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public Thread getChecker() {
        return checker;
    }

    public Label getTimerLabel() {
        return timerLabel;
    }
}
