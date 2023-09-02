package bits.squad.project2;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Point3D;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class CardView extends ImageView {
    private static final double scale = 0.95;
    private static DropShadow shadowHover = new DropShadow(5, 4, 4, Color.BLACK); 
    private static DropShadow shadowDown = new DropShadow(2, 2, 2, Color.BLACK);
    private int imageId;
    private static Image backside = null;
    private boolean isOpened;
    private SequentialTransition open;
    private SequentialTransition close;

    public CardView(int imageId) {
        super(backside);
        this.imageId = imageId;
        Image image = new Image(App.class.getResource("img/card-" + imageId + ".png").toExternalForm(), 140, 140, true, true);
        isOpened = false;
        //Animations
        setRotationAxis(new Point3D(200, 0, 0));
        setScaleX(scale);
        setScaleY(scale);
        setEffect(shadowDown);
        //PickUp animation
        setOnMouseEntered(m -> {
            setEffect(shadowHover);
            setScaleX(scale * 1.01);
            setScaleY(scale * 1.01);
        });
        //PutDown animation
        setOnMouseExited(m -> {
            setEffect(shadowDown);
            setScaleX(scale);
            setScaleY(scale);
        });
        //Click
        RotateTransition rot1 = new RotateTransition(Duration.millis(300), this);
        rot1.setByAngle(90);
        rot1.setOnFinished(e -> setImage(image));
        RotateTransition rot2 = new RotateTransition(Duration.millis(300), this);
        rot2.setByAngle(-90);

        RotateTransition rot3 = new RotateTransition(Duration.millis(300), this);
        rot3.setByAngle(90);
        rot3.setOnFinished(e -> setImage(backside));
        RotateTransition rot4 = new RotateTransition(Duration.millis(300), this);
        rot4.setByAngle(-90);
        open = new SequentialTransition(rot1, rot2, new PauseTransition(Duration.millis(300)));
        close = new SequentialTransition(rot3, rot4);
        setOnMouseClicked(m -> {
            if (App.getGame().isStarted() && open.getStatus() != Animation.Status.RUNNING && close.getStatus() != Animation.Status.RUNNING
                    && (App.getGame().getFirstCard() == null || App.getGame().getSecondCard() == null)) {
                if (isOpened) {
                    close();
                    if (this == App.getGame().getFirstCard())
                        App.getGame().setFirstCard(null);
                    else
                        App.getGame().setSecondCard(null);
                } else {
                    open();
                    App.getGame().getMutex().release();
                    if (App.getGame().getFirstCard() == null)
                        App.getGame().setFirstCard(this);
                    else {
                        App.getGame().setSecondCard(this);
                    }
                }
            }
        });
    }

    public int getImageId() {
        return imageId;
    }

    public static void setBackSide(Image image) {
        backside = image;
    }

    public void open() {
        open.play();
        isOpened = true;
    }

    public void close() {
        close.play();
        isOpened = false;
    }
}
