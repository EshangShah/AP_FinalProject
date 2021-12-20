package com.example.snakesandladder;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;


public class DiceRoll extends Application {

    public int[][] cirPos =new int[10][10];
    public int[][] ladderPos = new int[6][3];

    public static final int tileSize=40;
    public static final int width=10;
    public static final int height=10;
    public int random;
    public Label randDice;

    private Group tileGroup = new Group();
    public Circle player1;
    public Circle player2;


    public int playerPosition1=1;
    public int playerPosition2=1;


    public boolean player1Turn = true;
    public boolean player2Turn = true;

    public static int player1posX=20;
    public static int player1posY=380;

    public static int player2posX=20;
    public static int player2posY=380;

    public static int posCircle1=1;
    public static int posCircle2=1;

    public boolean gameStart=false;

    public Button gameInitialize;

    private Parent createContent(){
        Pane root = new Pane();
        //made it dynamic by using width*tileSize and height*tileSize instead of directly using numbers.
        //added 80 as we need space to add buttons for dice and other operations
        root.setPrefSize(width*tileSize,(height*tileSize)+200);
        root.getChildren().addAll(tileGroup);

        for(int i =0; i<height; i++){
            for (int j=0; j<width; j++){
                Tile tile=new Tile(tileSize,tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);

                tileGroup.getChildren().add(tile);
                //X-coordinate
                //cirPos[i][j]=j*(tileSize-40);

            }
        }
//        //To calculate value of X coordinate
//        for(int j=0; j<height; j++){
//            for (int k=0; k<width; k++){
//                System.out.println(cirPos[j][k]+" ");
//            }
//            System.out.println();
//        }

        player1 = new Circle(10);
        player1.setId("P1");
        player1.setFill(Color.AQUA);
        player1.getStyleClass().add("style.css");
        player1.setTranslateX(player1posX);
        player1.setTranslateY(player1posY);

        player2 = new Circle(10);
        player2.setId("P2");
        player2.setFill(Color.WHITE);
        player2.getStyleClass().add("style.css");
        player2.setTranslateX(player2posX);
        player2.setTranslateY(player2posY);

        Button button2Player = new Button("P2");
        button2Player.setTranslateX(350);
        button2Player.setTranslateY(410);


        //lamda expression used here
        button2Player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStart){
                    if (player2Turn){
                        getDiceValue();
                        randDice.setText(String.valueOf(random));
                        move2Player();
                        translatePlayer(player2posX,player2posY,player2);

                        //ensure single turn each time
                        player1Turn=true;
                        player2Turn=false;
                        playerPosition2+=random;

                    }
                }
            }
        });

        Button button1Player = new Button("P1");
        button1Player.setTranslateX(40);
        button1Player.setTranslateY(410);
        button1Player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStart){
                    if (player1Turn){
                        getDiceValue();
                        randDice.setText(String.valueOf(random));
                        move1Player();
                        translatePlayer(player1posX,player1posY,player1);

                        //ensure single turn each time
                        player2Turn=true;
                        player1Turn=false;
                        playerPosition1+=random;
//                        if (player1posX==80 && player1posY==760){
//                            translatePlayer();
//
//                        }
                    }
                }

            }
        });
        Image imgs = new Image("d.jpg");
        ImageView view = new ImageView(imgs);
        Button dice = new Button("Dice");
        dice.setGraphic(view);
        dice.setTranslateX(185);
        dice.setTranslateY(440);
        view.setFitHeight(50);
        view.setPreserveRatio(true);
        dice.setPrefSize(5, 5);

        gameInitialize=new Button("Play Now");
        gameInitialize.setTranslateX(185);
        gameInitialize.setTranslateY(410);
        gameInitialize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameInitialize.setText("Game in Progress");
                player1posX=20;
                player1posY=380;

                player2posX=20;
                player2posY=380;

                player1.setTranslateX(player1posX);
                player1.setTranslateY(player1posY);

                player2.setTranslateX(player2posX);
                player2.setTranslateY(player2posY);
                gameStart=true;

            }
        });
        randDice=new Label("0");
        randDice.setTranslateX(150);
        randDice.setTranslateY(410);

        Image img = new Image("scene.jpeg");
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);
        bgImage.setFitHeight(400);
        bgImage.setFitWidth(400);








        tileGroup.getChildren().addAll(bgImage,player1,player2,button2Player,button1Player,gameInitialize,randDice,dice);
        return root;


    }
    private void getDiceValue(){

        //casting random to integer and randomizing the value of dice.
        random=(int) (Math.random()*6+1);
    }

    private void translatePlayer(int x, int y, Circle b){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),b);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }

    private void move1Player(){
        for (int i=0; i<random; i++){
            if (posCircle1%2==1){
                player1posX+=40;

            }
            if (posCircle1%2==0){
                player1posX-=40;
            }
            if (player1posX>380){
                player1posY-=40;
                player1posX-=40;
                posCircle1++;
            }
            if (player1posX<20){
                player1posY-=40;
                player1posX+=40;
                posCircle1++;
            }
            if (player1posX<15 || player1posY<15){
                player1posY=player1posX=20;
                gameStart=false;
                randDice.setText("Player 1 Wins");
                gameInitialize.setText("Play Again");
            }
        }
    }
    private void move2Player(){
        for (int i=0; i<random; i++){
            if (posCircle2%2==1){
                player2posX+=40;

            }
            if (posCircle2%2==0){
                player2posX-=40;
            }
            if (player2posX>380){
                player2posY-=40;
                player2posX-=40;
                posCircle2++;
            }
            if (player2posX<20){
                player2posY-=40;
                player2posX+=40;
                posCircle2++;
            }
            if (player2posX<15 || player2posY<15){
                player2posY=player2posX=20;
                gameStart=false;
                randDice.setText("Player 2 Wins");
                gameInitialize.setText("Play Again");
            }
        }
    }



    @Override
    public void start(Stage stage) throws IOException {


        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}