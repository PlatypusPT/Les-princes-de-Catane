package Views;

import Controllers.Control_Game;
import Models.ModelMenu;
import Models.Partie;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yhaffner on 08/12/16
 */
public class Game_View {
    public static int IMG_SMALL_SIZE = 64;
    public static int IMG_MEDIUM_SIZE = 100;
    public static int IMG_HUGE_SIZE = 400;


    private Partie model;
    private Stage stage;
    private Menu_View menu_view;

    private VBox gameSection;
    private GridPane firstPlayerPlayedCardsLayout;
    private GridPane piocheLayout;
    private GridPane secondPlayerPlayedCardsLayout;
    private GridPane secondPlayerDeckLayout;
    private GridPane firstPlayerDeckLayout;

    private HashMap<Point,ImageView> firstPlayerPlayedCards;
    private HashMap<Point,ImageView> firstPlayerDeck;
    private HashMap<Point,ImageView> piocheCards;
    private HashMap<Point,ImageView> secondPlayerPlayedCards;
    private HashMap<Point,ImageView> secondPlayerDeck;
    private Image empty_card;

    public Game_View(Partie model, Stage stage) {
        this.model = model;
        this.stage = stage;
        initAttributs();
    }

    public void initAttributs() {
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add(new File(ModelMenu.ASSETS_PATH +"/css/game_view.css").toURI().toString());

        gameSection = new VBox();
        gameSection.setId("plateau-layout");

        firstPlayerPlayedCardsLayout = new GridPane();
        firstPlayerPlayedCardsLayout.setId("first-player-layout");
        firstPlayerDeckLayout = new GridPane();
        firstPlayerDeckLayout.setId("first-player-deck");
        piocheLayout = new GridPane();
        piocheLayout.setId("pioche-layout");
        secondPlayerPlayedCardsLayout = new GridPane();
        secondPlayerPlayedCardsLayout.setId("second-player-layout");
        secondPlayerDeckLayout = new GridPane();
        secondPlayerDeckLayout.setId("second-player-deck");

        firstPlayerPlayedCards = new HashMap<>();
        firstPlayerDeck = new HashMap<>();
        piocheCards = new HashMap<>();
        secondPlayerPlayedCards = new HashMap<>();
        secondPlayerDeck = new HashMap<>();

        empty_card = new Image(
                new File(ModelMenu.ASSETS_PATH+"/img/cards/no_card.png").toURI().toString(),
                IMG_SMALL_SIZE,IMG_SMALL_SIZE,
                true,true
        );

        actualizeCards();
    }

    public void actualizeCards() {
        firstPlayerPlayedCards.clear();
        firstPlayerDeck.clear();
        piocheCards.clear();
        secondPlayerPlayedCards.clear();
        secondPlayerDeck.clear();

        for(int i=0;i<model.getPrincipauteJoueur(1).size();i++)
            if(model.getPrincipauteJoueur(1).get(i)!=null) firstPlayerPlayedCards.put(
                    new Point(
                            i%(model.getPrincipauteJoueur(1).size()/3),
                            i/(model.getPrincipauteJoueur(1).size()/3)
                    ),
                    new ImageView(new Image(
                            new File(model.getPrincipauteJoueur(1).get(i).getImage()).toURI().toString(),
                            IMG_SMALL_SIZE,IMG_SMALL_SIZE,
                            true,true
                    ))
            );
            else firstPlayerPlayedCards.put(
                    new Point(
                            i%(model.getPrincipauteJoueur(1).size()/3),
                            i/(model.getPrincipauteJoueur(1).size()/3)
                    ),
                    new ImageView(empty_card)
            );
        for(int i=0;i<model.getMainJoueur(1).size();i++)
            firstPlayerDeck.put(
                    new Point(i,0),
                    new ImageView(new Image(
                            new File(model.getMainJoueur(1).get(i).getImage()).toURI().toString(),
                            IMG_MEDIUM_SIZE,IMG_MEDIUM_SIZE,
                            true,true
                    ))
            );
        for(int i=0;i<model.getPrincipauteJoueur(2).size();i++)
            if(model.getPrincipauteJoueur(2).get(i)!=null) secondPlayerPlayedCards.put(
                    new Point(
                            i%(model.getPrincipauteJoueur(2).size()/3),
                            i/(model.getPrincipauteJoueur(2).size()/3)
                    ),
                    new ImageView(new Image(
                            new File(model.getPrincipauteJoueur(2).get(i).getImage()).toURI().toString(),
                            IMG_SMALL_SIZE,IMG_SMALL_SIZE,
                            true,true
                    ))
            );
            else secondPlayerPlayedCards.put(
                    new Point(
                            i%(model.getPrincipauteJoueur(2).size()/3),
                            i/(model.getPrincipauteJoueur(2).size()/3)
                    ),
                    new ImageView(empty_card)
            );
        for(int i=0;i<model.getMainJoueur(2).size();i++)
            secondPlayerDeck.put(
                    new Point(i,0),
                    new ImageView(new Image(
                            new File(model.getMainJoueur(2).get(i).getImage()).toURI().toString(),
                            IMG_MEDIUM_SIZE,IMG_MEDIUM_SIZE,
                            true,true
                    ))
            );
        for(int i=0;i<6;i++)
            piocheCards.put(
                    new Point(i,0),
                    new ImageView(new Image(
                            new File(ModelMenu.ASSETS_PATH+"/img/cards/card_dos_"+(i+1)+".png").toURI().toString(),
                            IMG_SMALL_SIZE,IMG_SMALL_SIZE,
                            true,true
                    ))
            );
        for(int i=6;i<10;i++)
            piocheCards.put(
                    new Point(i,0),
                    new ImageView(new Image(
                            new File(ModelMenu.ASSETS_PATH+"/img/cards/card_dos_6.png").toURI().toString(),
                            IMG_SMALL_SIZE,IMG_SMALL_SIZE,
                            true,true
                    ))
            );


        stage.getScene().getRoot().setVisible(false);
        firstPlayerPlayedCardsLayout.getChildren().clear();
        for(Map.Entry<Point, ImageView> e : firstPlayerPlayedCards.entrySet())
            {firstPlayerPlayedCardsLayout.add(e.getValue(),e.getKey().x,e.getKey().y);e.getValue().getStyleClass().add("selectable");}
        for(Map.Entry<Point, ImageView> e : firstPlayerDeck.entrySet())
            {firstPlayerDeckLayout.add(e.getValue(),e.getKey().x,e.getKey().y);e.getValue().getStyleClass().add("selectable");}
        for(Map.Entry<Point, ImageView> e : piocheCards.entrySet())
            {piocheLayout.add(e.getValue(),e.getKey().x,e.getKey().y);e.getValue().getStyleClass().add("selectable");}
        for(Map.Entry<Point, ImageView> e : secondPlayerPlayedCards.entrySet())
            {secondPlayerPlayedCardsLayout.add(e.getValue(),e.getKey().x,e.getKey().y);e.getValue().getStyleClass().add("selectable");}
        for(Map.Entry<Point, ImageView> e : secondPlayerDeck.entrySet())
            {secondPlayerDeckLayout.add(e.getValue(),e.getKey().x,e.getKey().y);e.getValue().getStyleClass().add("selectable");}
        stage.getScene().getRoot().setVisible(true);
    }

    public void setFirstPlayerView(){
        stage.getScene().getRoot().setVisible(false);

        ((BorderPane) stage.getScene().getRoot()).getChildren().clear();
        gameSection.getChildren().clear();

        // Game content
        gameSection.getChildren().add(secondPlayerPlayedCardsLayout);
        gameSection.getChildren().add(piocheLayout);
        gameSection.getChildren().add(firstPlayerPlayedCardsLayout);
        ((BorderPane) stage.getScene().getRoot()).setCenter(gameSection);
        ((BorderPane) stage.getScene().getRoot()).setBottom(firstPlayerDeckLayout);

        stage.getScene().getRoot().setVisible(true);
    }

    public void setSecondPlayerView(){
        stage.getScene().getRoot().setVisible(false);

        ((BorderPane) stage.getScene().getRoot()).getChildren().clear();
        gameSection.getChildren().clear();

        // Game content
        gameSection.getChildren().add(firstPlayerPlayedCardsLayout);
        gameSection.getChildren().add(piocheLayout);
        gameSection.getChildren().add(secondPlayerPlayedCardsLayout);
        gameSection.getChildren().add(secondPlayerDeckLayout);
        ((BorderPane) stage.getScene().getRoot()).setCenter(gameSection);

        stage.getScene().getRoot().setVisible(true);
    }

    public void setController(EventHandler<MouseEvent> eh){
        for(Map.Entry<Point, ImageView> e : firstPlayerPlayedCards.entrySet())
            e.getValue().setOnMouseClicked(eh);
        for(Map.Entry<Point, ImageView> e : firstPlayerDeck.entrySet())
            e.getValue().setOnMouseClicked(eh);
        for(Map.Entry<Point, ImageView> e : piocheCards.entrySet())
            e.getValue().setOnMouseClicked(eh);
        for(Map.Entry<Point, ImageView> e : secondPlayerPlayedCards.entrySet())
            e.getValue().setOnMouseClicked(eh);
        for(Map.Entry<Point, ImageView> e : secondPlayerDeck.entrySet())
            e.getValue().setOnMouseClicked(eh);
    }
}
