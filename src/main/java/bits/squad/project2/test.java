package bits.squad.project2;

import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.util.Duration;

public class test extends Application {
    final int rows = 4;
    final int columns = 4;
    CardView views[][] = new CardView[rows][];

    public static class CardView extends ImageView {
        static final double scale = 0.95;
        static DropShadow shadowhoover = new DropShadow(5, 4, 4, Color.rgb(50, 60, 50));
        static DropShadow shadowdown = new DropShadow(2, 2, 2, Color.rgb(50, 60, 50));
        static Image backside = null;
        public static void setbackside(Image image) { backside = image; }

        public CardView(Image image) {
            super(backside);
            setRotationAxis(new Point3D(0, 200,0));
            setScaleX(scale);
            setScaleY(scale);
            setEffect(shadowdown);
            setOnMouseEntered(m -> {
                setEffect(shadowhoover);
                setScaleX(scale*1.01);
                setScaleY(scale*1.01);
            });
            setOnMouseExited(m -> {
                setEffect(shadowdown);
                setScaleX(scale);
                setScaleY(scale);
            });
            setOnMouseClicked(m -> {
                RotateTransition r1 = new RotateTransition(Duration.millis(300), this);
                r1.setByAngle(90);
                r1.setOnFinished(e -> setImage(image));
                RotateTransition r2 = new RotateTransition(Duration.millis(300), this);
                r2.setByAngle(-90);

                RotateTransition r3 = new RotateTransition(Duration.millis(300), this);
                r3.setByAngle(90);
                r3.setOnFinished(e -> setImage(backside));
                RotateTransition r4 = new RotateTransition(Duration.millis(300), this);
                r4.setByAngle(-90);

                new SequentialTransition(r1, r2, new PauseTransition(Duration.millis(1000)), r3, r4).play();
            });
        }
    }

    @Override
    public void start(Stage gameStage) throws Exception {
        GridPane grid = new GridPane();
        grid.setBackground(new Background(new BackgroundFill(Color.rgb(140, 200, 140), new CornerRadii(0), new Insets(0))));
        grid.setHgap(5);
        grid.setVgap(5);
        Image back = new Image("C:\\PJATK\\GUI\\Project-2\\src\\main\\resources\\bits\\squad\\project2\\img\\card-back.png", 140, 200, true, true);
        Image front = new Image("C:\\PJATK\\GUI\\Project-2\\src\\main\\resources\\bits\\squad\\project2\\img\\card-1.png", 140, 200, true, true);
        CardView.setbackside(back);
        for (int r = 0; r < rows; r++) {
            views[r] = new CardView[columns];
            for (int c = 0; c < columns; c++) {
                CardView view = new CardView(front); // different front images of course...
                views[r][c] = view;
                HBox box = new HBox(5);
                box.getChildren().add(views[r][c]);
                grid.add(box, c, r);
            }
        }
//        grid.add(backOfCards, 1,1);
        Scene scene = new Scene(grid);
        gameStage.setTitle("MemoryGame");
        gameStage.setScene(scene);
        gameStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
