import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Time;

public class Main extends Application {

    public void start(Stage primaryStage) {
        primaryStage.setHeight(450);
        primaryStage.setWidth(800);
        primaryStage.setTitle("Dessins!");
        primaryStage.setScene(scene());
        primaryStage.show();
    }

    private Scene scene() {
        Text jour = new Text(157, 325, "Jour");
        Font f = new Font("Default", 45);
        jour.setFont(f);

        Text nuit = new Text(557, 325, "Nuit");
        Font ff = new Font("Default", 45);
        nuit.setFill(Color.WHITE);
        nuit.setFont(ff);

        Rectangle gauche = new Rectangle(400, 450);
        gauche.setFill(Color.LIGHTGREY);
        Rectangle droite = new Rectangle(400, 0, 400, 450);
        droite.setFill(Color.BLACK);

        Group maison = maison();
        maison.setTranslateX(150);maison.setTranslateY(150);
        Group maisonLune = maison();
        maisonLune.setTranslateX(550);maisonLune.setTranslateY(150);
        Group soleil = soleil();
        soleil.setTranslateX(290);soleil.setTranslateY(50);
        Group lune = lune();
        lune.setTranslateX(690);lune.setTranslateY(50);
        Group etoile = etoile();
        Group oiseau1 = new Group(oiseau());
        oiseau1.setTranslateX(10);oiseau1.setTranslateY(75);
        Group oiseau2 = new Group(oiseau());
        oiseau2.setTranslateX(120);oiseau2.setTranslateY(20);

        DropShadow ds = new DropShadow(10, -10, 10, Color.DARKGRAY);
        maison.setEffect(ds);

        RotateTransition COGGERS = new RotateTransition(Duration.seconds(4), soleil);
        COGGERS.setByAngle(360);
        COGGERS.setCycleCount(Timeline.INDEFINITE);
        COGGERS.setInterpolator(Interpolator.LINEAR);
        COGGERS.play();

        Group root = new Group(gauche, droite, maison, maisonLune, soleil, etoile, lune, jour, nuit, oiseau1, oiseau2);
        return new Scene(root);
    }

    private Group maison() {
        Rectangle body = new Rectangle(5, 45, 90, 50);
        body.setFill(Color.WHITE);
        Polygon roof = new Polygon(50, 0, 100, 45, 0, 45);
        roof.setFill(Color.DARKRED);
        Rectangle door = new Rectangle(22.5, 67.5, 20, 27.5);
        door.setFill(Color.SADDLEBROWN);
        Circle doorknob = new Circle(15+22.5, 13.75+67.5, 4);
        doorknob.setFill(Color.YELLOWGREEN);
        Rectangle window = new Rectangle(55, 52.5, 30, 20);
        window.setFill(Color.AQUA);
        window.setStrokeWidth(1.5);
        window.setStroke(Color.DARKGREY);
        Line windowline = new Line(70, 52.5, 70, 72.5);
        windowline.setStrokeWidth(1.5);
        windowline.setStroke(Color.DARKGREY);
        Line windowline2 = new Line(55, 62.5, 85, 62.5);
        windowline2.setStrokeWidth(1.5);
        windowline2.setStroke(Color.DARKGREY);

        return new Group(roof, body, door, doorknob, window, windowline, windowline2);
    }

    private Group soleil() {
        Circle sun = new Circle(30, 30, 30);
        sun.setFill(Color.YELLOW);

        FillTransition magma = new FillTransition(Duration.seconds(2), sun);
        magma.setToValue(Color.ORANGE);
        magma.setCycleCount(Timeline.INDEFINITE);
        magma.setAutoReverse(true);
        magma.play();

        Circle eye1 = new Circle(20, 22.5, 2.5);
        Circle eye2 = new Circle(40, 22.5, 2.5);
        Arc mouth = new Arc(30, 37.5, 12, 5, 180, 180);
        mouth.setType(ArcType.OPEN);
        mouth.setStrokeWidth(2.5);
        mouth.setFill(Color.TRANSPARENT);
        mouth.setStroke(Color.BLACK);

        Line big1 = new Line(30, -10, 30, -45);
        Line big2 = new Line(70, 30, 105, 30);
        Line big3 = new Line(30, 70, 30, 105);
        Line big4 = new Line(-10, 30, -45, 30);
        Line small1 = new Line(45+14.14, 15-14.14, 45+14.14+18, 15-14.14-18); // :) :) :) :)
        Line small2 = new Line(45+14.14, 45+14.14, 45+14.14+18, 45+14.14+18);
        Line small3 = new Line(15-14.14, 45+14.14, 15-14.14-18, 45+14.14+18);
        Line small4 = new Line(15-14.14, 15-14.14, 15-14.14-18, 15-14.14-18);
        big1.setStroke(Color.YELLOW);big2.setStroke(Color.YELLOW);big3.setStroke(Color.YELLOW);big4.setStroke(Color.YELLOW);
        small1.setStroke(Color.YELLOW);small2.setStroke(Color.YELLOW);small3.setStroke(Color.YELLOW);small4.setStroke(Color.YELLOW);

        return new Group(sun, big1, big2, big3, big4, small1, small2, small3, small4, eye1, eye2, mouth);
    }

    private Group lune() {
        Circle lune = new Circle(30, 30, 30);
        Stop[] stops = new Stop[]{
                new Stop(0, Color.WHITE),
                new Stop(1, Color.BLACK)
        };
        LinearGradient lg = new LinearGradient(
                0, 0,
                1, 1,
                true,
                CycleMethod.NO_CYCLE,
                stops);
        lune.setFill(lg);

        Circle eye1 = new Circle(20, 22.5, 2.5);
        Circle eye2 = new Circle(40, 22.5, 2.5);
        Arc mouth = new Arc(30, 37.5, 12, 5, 180, 180);
        mouth.setType(ArcType.OPEN);
        mouth.setStrokeWidth(2.5);
        mouth.setFill(Color.TRANSPARENT);
        mouth.setStroke(Color.BLACK);
        Line eyebrow1 = new Line(15, 10, 25, 15);
        eyebrow1.setStrokeWidth(2);
        Line eyebrow2 = new Line(45, 10, 35, 15);
        eyebrow2.setStrokeWidth(2);
        Group face = new Group(eye1, eye2, mouth, eyebrow1, eyebrow2);

        ScaleTransition st = new ScaleTransition(Duration.seconds(0.25), face);
        st.setByX(2);
        st.setByY(0.5);
        st.setCycleCount(Timeline.INDEFINITE);
        st.setAutoReverse(true);
        st.play();

        FillTransition angery = new FillTransition(Duration.seconds(0.1), eye1);
        angery.setToValue(Color.RED);
        angery.setAutoReverse(true);
        angery.setCycleCount(Timeline.INDEFINITE);
        angery.play();
        FillTransition angery1 = new FillTransition(Duration.seconds(0.1), eye2);
        angery1.setToValue(Color.RED);
        angery1.setAutoReverse(true);
        angery1.setCycleCount(Timeline.INDEFINITE);
        angery1.play();


        return new Group(lune, face);
    }

    private Group etoile() {
        Polygon etoile1 = new Polygon(20, 0,   15, 15,   0, 15,   12.5, 25,   7.5, 40,   20, 30,   32.5, 40,   27.5, 25,   40, 15,   25, 15);
        etoile1.setTranslateX(450);etoile1.setTranslateY(15);etoile1.setFill(Color.LIGHTGOLDENRODYELLOW);etoile1.setScaleX(0.75);etoile1.setScaleY(0.75);
        FillTransition ft1 = new FillTransition(Duration.seconds(2), etoile1);
        ft1.setToValue(Color.BLACK);
        ft1.setCycleCount(Timeline.INDEFINITE);
        ft1.setAutoReverse(true);
        ft1.play();

        Polygon etoile2 = new Polygon(20, 0,   15, 15,   0, 15,   12.5, 25,   7.5, 40,   20, 30,   32.5, 40,   27.5, 25,   40, 15,   25, 15);
        etoile2.setTranslateX(475);etoile2.setTranslateY(90);etoile2.setFill(Color.LIGHTGOLDENRODYELLOW);etoile2.setScaleX(0.5);etoile2.setScaleY(0.5);
        FillTransition ft2 = new FillTransition(Duration.seconds(2), etoile2);
        ft2.setDelay(Duration.seconds(0.75));
        ft2.setToValue(Color.BLACK);
        ft2.setCycleCount(Timeline.INDEFINITE);
        ft2.setAutoReverse(true);
        ft2.play();

        Polygon etoile3 = new Polygon(20, 0,   15, 15,   0, 15,   12.5, 25,   7.5, 40,   20, 30,   32.5, 40,   27.5, 25,   40, 15,   25, 15);
        etoile3.setTranslateX(535);etoile3.setTranslateY(50);etoile3.setFill(Color.LIGHTGOLDENRODYELLOW);etoile3.setScaleX(0.75);etoile3.setScaleY(0.75);
        FillTransition ft3 = new FillTransition(Duration.seconds(2), etoile3);
        ft3.setDelay(Duration.seconds(1.5));
        ft3.setToValue(Color.BLACK);
        ft3.setCycleCount(Timeline.INDEFINITE);
        ft3.setAutoReverse(true);
        ft3.play();

        Polygon etoile4 = new Polygon(20, 0,   15, 15,   0, 15,   12.5, 25,   7.5, 40,   20, 30,   32.5, 40,   27.5, 25,   40, 15,   25, 15);
        etoile4.setTranslateX(610);etoile4.setTranslateY(70);etoile4.setFill(Color.LIGHTGOLDENRODYELLOW);
        FillTransition ft4 = new FillTransition(Duration.seconds(2), etoile4);
        ft4.setDelay(Duration.seconds(1.5));
        ft4.setToValue(Color.BLACK);
        ft4.setCycleCount(Timeline.INDEFINITE);
        ft4.setAutoReverse(true);
        ft4.play();


        return new Group(etoile1, etoile2, etoile3, etoile4);
    }

    private Group oiseau() {
        QuadCurve curve1 = new QuadCurve(0, 5, 25, 0, 50, 50);
        curve1.setStrokeWidth(1);
        curve1.setStroke(Color.BLACK);
        curve1.setFill(Color.LIGHTGRAY);
        QuadCurve curve2 = new QuadCurve(100, 5, 75, 0, 50, 50);
        curve2.setStrokeWidth(1);
        curve2.setStroke(Color.BLACK);
        curve2.setFill(Color.LIGHTGRAY);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        KeyValue keyStart1 = new KeyValue(curve1.startYProperty(), 60);
        KeyValue keyEnd1 = new KeyValue(curve1.endYProperty(), 40);
        KeyValue keyStart2 = new KeyValue(curve2.startYProperty(), 60);
        KeyValue keyEnd2 = new KeyValue(curve2.endYProperty(), 40);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyStart1, keyEnd1, keyStart2, keyEnd2);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        return new Group(curve1, curve2);
    }
}
