/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.io.File;
import static java.lang.Math.abs;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 *
 * @author kimo only
 */
public class FXMLDocumentController implements Initializable {

    
    @FXML
    private Label label;
    @FXML
    private Circle cir;
    @FXML
    private Circle food;
    @FXML
    private Arc right;
    @FXML
    private Arc down;
    @FXML
    private Arc left;
    @FXML
    private Arc up;
    @FXML
    private Circle eat;
    @FXML
    private Pane contain;
    @FXML
    private Arc life1;
    @FXML
    private Arc life2;
    @FXML
    private Arc life3;
    @FXML
    private Circle eatlife1;
    @FXML
    private Circle eatlife2;
    @FXML
    private Circle eatlife3;
    @FXML
    private Circle evil;
    @FXML
    private Text text1;
    @FXML
    private Pane start;
    @FXML
    Text score;
    @FXML
    private Circle evil2;
    @FXML
    private Circle evil4;
    @FXML
    private Rectangle block;
    @FXML
    private Rectangle block2;
    int scoree = 0;
    blocks block_1 = new blocks(0, 0, 0, 0, 0, 0);
    ArrayList arr = new ArrayList();
    blocks block_2 = new blocks(0, 0, 0, 0, 0, 0);
        blocks block_4 = new blocks(0, 0, 0, 0, 0, 0);
    blocks block_3 = new blocks(0, 0, 0, 0, 0, 0);
int music=0;
    ArrayList arr2 = new ArrayList();
    ArrayList imagex = new ArrayList();
    ArrayList imagey = new ArrayList();
    ArrayList dir = new ArrayList();
        ArrayList faces = new ArrayList();
boolean music2=false;
    int b;
    boolean lost = false;
    boolean newgame = true;
    int imageX = 0;
    int imageY = 0;
    Timeline timeline;
    int num;
    boolean v = false;
    double speed = 2;
    int turn = 1;
    int num2 = 0;
    int head = 1;
    int timer = 0;
    int show;
    int level = 1;
    int centerx;
    int centery;
    int b2 = 1;
    int b3 = 1;
    int b4 = 1;
    int maxlevel = 4;
    int computerCount;
    int x2 = 37;
    int lose = 1;
    boolean begin = true;
    int lifes = 4;
    @FXML
    private Circle evil5;
    KeyCode keyCode;
    @FXML
    private Circle upeye;
    @FXML
    private Circle lefteye;
    @FXML
    private Circle downeye;
    @FXML
    private Circle righteye;
    @FXML
    private ImageView face1;
    @FXML
    private ImageView face2;
    @FXML
    private ImageView face3;
    @FXML
    private ImageView face4;
    @FXML
    private Rectangle block4;
    @FXML
    private Rectangle block3;
    String bip = "music\\game.mp3";
Media hit2 = new Media(new File(bip).toURI().toString());
final MediaPlayer mediaPlayer2 = new MediaPlayer(hit2);
    @FXML
    private Rectangle border1;
    @FXML
    private Rectangle border2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cir.setFocusTraversable(true);
        cir.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                        rotate();
                if (!lost) {
                    evils();
                    level();
                    lifes();
                    keyCode = key.getCode();
                    key();
                    if (turn == 1 && !begin) {
                        turn = 2;
                        newgame = false;
                        begin();
                        lose = 1;
                        blocks();
                        evils();
                        random();
                        time();

                    }
                }
            }

            public void time() {

                timeline = new Timeline(new KeyFrame(Duration.seconds(0.05),
                        new EventHandler() {
                            @Override
                            public void handle(Event t) {
                                if (lost) {
                                    timeline.pause();
                                }
                                updateImageView(num);
                                for (int i = 1; i <= level; i++) {
                                    updateImageView2(i);
                                }
                                if (lose == 0) {
                                    timeline.pause();
                                }
                                
                                timer++;
                                if (timer > 6) {
                                    timer = 0;
                                }
                                if (timer < 3) {
                                    eat.setVisible(true);
                                    if (lifes > 0) {
                                        eatlife1.setVisible(true);
                                    }
                                    if (lifes > 1) {
                                        eatlife2.setVisible(true);
                                    }
                                    if (lifes > 2) {
                                        eatlife3.setVisible(true);
                                    }
                                    color(1);

                                } else {
                                    eat.setVisible(false);
                                    eatlife1.setVisible(false);
                                    eatlife2.setVisible(false);
                                    eatlife3.setVisible(false);
                                    color(2);
                                }
                            }
                        }));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
            }
        });
        cir.setFocusTraversable(true);
    }

    public void food() {
        double e = cir.getLayoutX() + cir.getCenterX();
        double f = food.getLayoutX() + food.getCenterX();
        double y1 = cir.getLayoutY() + cir.getCenterY();
        double y2 = food.getLayoutY() + food.getCenterY();
        if (abs(e - f) < 25 && abs(y1 - y2) < 25) {
            sound(1);
            scoree++;
            random();
        }
    }

    public void eat() {
        for (int i = 0; i < level; i++) {
            {
                double e = cir.getLayoutX() + cir.getCenterX();
                Circle n = (Circle) arr2.get(i);
                double f = n.getLayoutX() + n.getCenterX();
                double y1 = cir.getLayoutY() + cir.getCenterY();
                double y2 = n.getLayoutY() + n.getCenterY();
                if (abs(e - f) < 40 && abs(y1 - y2) < 40) {
                    lose();
                }
            }

        }
    }
int h=0;
    public void random() {
         int R = 0;
         if(h==0){blocks();h++;}
         int R2=0;
            Random r = new Random();
            int Low = 0;
            int High = 400;
            R = r.nextInt(High - Low) + Low;
            R2 = r.nextInt(High - Low) + Low;
        if (!avoidfood(R+food.getLayoutX(), R2+food.getLayoutY())){
            food.setCenterX(R);
                        food.setCenterY(R2); 
        }
        else random();
        
         
    }

    public void rotate() {
        if (head == 3) {
            right.setVisible(false);
            down.setVisible(true);
            up.setVisible(false);
            left.setVisible(false);
            eat.setVisible(false);
righteye.setVisible(false);
            downeye.setVisible(true);
            upeye.setVisible(false);
            lefteye.setVisible(false);
        }
        if (head == 1) {
            right.setVisible(true);
            down.setVisible(false);
            up.setVisible(false);
            left.setVisible(false);
            eat.setVisible(false);
righteye.setVisible(true);
            downeye.setVisible(false);
            upeye.setVisible(false);
            lefteye.setVisible(false);
        }
        if (head == 4) {
            up.setVisible(true);
            down.setVisible(false);
            right.setVisible(false);
            left.setVisible(false);
            eat.setVisible(false);
            upeye.setVisible(true);
            downeye.setVisible(false);
            righteye.setVisible(false);
            lefteye.setVisible(false);
        }
        if (head == 2) {
            up.setVisible(false);
            down.setVisible(false);
            right.setVisible(false);
            left.setVisible(true);
            upeye.setVisible(false);
            downeye.setVisible(false);
            righteye.setVisible(false);
            lefteye.setVisible(true);
            eat.setVisible(false);
        }

    }

    public int computer(int number) {

        {double f;double  y2;
            if(number%2==1)
            { f= cir.getLayoutX() + cir.getCenterX();
             y2= cir.getLayoutY() + cir.getCenterY();}
            else{
             f = food.getLayoutX() + food.getCenterX();
             y2 = food.getLayoutY() + food.getCenterY();
            }
            double e = 0;
            double y1 = 0;
            Circle n2 = (Circle) arr2.get(number - 1);
            e = n2.getLayoutX() + n2.getCenterX() * 1;
            y1 = n2.getLayoutY() + n2.getCenterY() * 1;

            int n = 10;
            int ard = (int) ((int) f - e);
            int tool = (int) ((int) y1 - y2);
            if (!avoid((int) dir.get(number - 1), e, y1)||!avoidwall((int) dir.get(number - 1), e, y1)
                    ||!avoidevils((int) dir.get(number - 1), e, y1)) {
                Random r = new Random();
                dir.set(number - 1, r.nextInt((4 - 1) + 1) + 1);

            }
            x2++;
            if (ard > 30 && tool > 30 && x2 > 50-maxlevel) {
                double xu = Math.random();
                if (xu > 0.5) {
                    dir.set(number - 1, 4);
                } else {
                    dir.set(number - 1, 1);
                }

            } else if (ard < 30 && tool > 30 & x2 > 50-maxlevel) {
                double xu = Math.random();
                if (xu > 0.5) {
                    dir.set(number - 1, 4);
                } else {
                    dir.set(number - 1, 2);
                }

            } else if (ard > 30 && tool < 30 && x2 > 50-maxlevel) {
                double xu = Math.random();
                if (xu > 0.5) {
                    dir.set(number - 1, 3);
                } else {
                    dir.set(number - 1, 1);
                }

            } else if (ard < 30 && tool < 30 && x2 > 50-maxlevel) {
                double xu = Math.random();
                if (xu > 0.5) {
                    dir.set(number - 1, 3);
                } else {
                    dir.set(number - 1, 2);
                }

            }
            if (x2 > 50) {
                x2 = 10;
            }
        }
        return (int) dir.get(number - 1);

    }

    public void lose() {

        contain.setStyle("-fx-background-color: #" + "FF0000");
        cir.setFill(javafx.scene.paint.Color.RED);
        lose = 0;
                    sound(2);
for(int i=0;i<arr2.size();i++)
{Circle v=(Circle) arr2.get(i);
                ImageView n= (ImageView)faces.get(i);

v.setCenterX(26.0);
v.setCenterY(467.0);
imagex.set(i, 0);
imagey.set(i, 0);
v.setLayoutX(26.0);
v.setLayoutY(467.0);
n.setX(26.0);
n.setY(467.0);


}
lifes--;
musiclost();
        resset();
    }

    public void resset() {

        cir.setLayoutX(26);
        right.setLayoutX(26);
        down.setLayoutX(26);
        up.setLayoutX(26);
        left.setLayoutX(26);
        eat.setLayoutX(26);
        imageX = 3;
        imageY = 0;
        cir.setCenterX(imageX);
        right.setCenterX(imageX);
        down.setCenterX(imageX);
        up.setCenterX(imageX);
        left.setCenterX(imageX);
        eat.setCenterX(imageX);
        cir.setLayoutY(26);
        right.setLayoutY(26);
        down.setLayoutY(26);
        up.setLayoutY(26);
        left.setLayoutY(26);
        eat.setLayoutY(26);
        imageY = 3;
        cir.setCenterY(imageY);
        right.setCenterY(imageY);
        down.setCenterY(imageY);
        up.setCenterY(imageY);
        left.setCenterY(imageY);
        eat.setCenterY(imageY);
        turn = 1;
        contain.setStyle("-fx-background-color: #" + "000000");
        cir.setFill(javafx.scene.paint.Color.BLACK);
        text1.setText(lifes + " life last!! ");
        if (lifes < 0) {
            text1.setText("you lost");
            lost = true;
        }
        text1.setVisible(true);
        text1.setTextAlignment(TextAlignment.CENTER);
        begin = true;
    }

    public void level() {
        for (int i = 0; i < maxlevel; i++) {
            if (i + 1 <= level) {
                Circle n = (Circle) arr2.get(i);
                                ImageView v= (ImageView)faces.get(i);

                n.setVisible(true);
                                v.setVisible(true);

            } else {
                Circle n = (Circle) arr2.get(i);
                                                ImageView v= (ImageView)faces.get(i);

                n.setVisible(false);
                                                v.setVisible(false);

            }

        }
    }

    public boolean avoid(int c, double e, double y1) {
        for (int i = 0; i < arr.size(); i++) {
            blocks k = (blocks) arr.get(i);
            if (c == 1 && ((!k.left(e, y1) && !k.downlimit(e, y1)))) {
                return false;
            }
            if (c == 2 && ((!k.right(e, y1) && !k.downlimit(e, y1)))) {
                return false;
            }
            if (c == 3 && ((!k.top(e, y1) && !k.leftlimit(e, y1)))) {
                return false;
            }
            if (c == 4 && ((!k.down(e, y1) && !k.leftlimit(e, y1)))) {
                return false;
            }
           

        }

        return true;
    }

    public void blocks() {
        block_1.set(block.getLayoutX()-block.getStrokeWidth(), block.getLayoutX() + block.getWidth()+block.getStrokeWidth(), block.getLayoutY()-block.getStrokeWidth(), block.getLayoutY() + block.getHeight()+block.getStrokeWidth(), block.getWidth(), block.getHeight());
                block_2.set(block2.getLayoutX()-block2.getStrokeWidth(), block2.getLayoutX() + block2.getWidth()+block2.getStrokeWidth(), block2.getLayoutY()-block2.getStrokeWidth(), block2.getLayoutY() + block2.getHeight()+block2.getStrokeWidth(), block2.getWidth(), block2.getHeight());
        block_3.set(block3.getLayoutX()-block3.getStrokeWidth(), block3.getLayoutX() + block3.getWidth()+block3.getStrokeWidth(), block3.getLayoutY()-block3.getStrokeWidth(), block3.getLayoutY() + block3.getHeight()+block3.getStrokeWidth(), block3.getWidth(), block3.getHeight());
        block_4.set(block4.getLayoutX()-block4.getStrokeWidth(), block4.getLayoutX() + block4.getWidth()+block4.getStrokeWidth(), block4.getLayoutY()-block4.getStrokeWidth(), block4.getLayoutY() + block4.getHeight()+block4.getStrokeWidth(), block4.getWidth(), block4.getHeight());
        arr.add(block_1);
        arr.add(block_2);
arr.add(block_3);
        arr.add(block_4);
    }
     public void color(int col) {
if(col==1){block.setStroke(Paint.valueOf("#e6194b"));    
block2.setStroke(Paint.valueOf("#e6194b"));    
block3.setStroke(Paint.valueOf("#e6194b"));    
block4.setStroke(Paint.valueOf("#e6194b"));
border1.setStroke(Paint.valueOf("#e6194b"));    
border2.setStroke(Paint.valueOf("#e6194b"));    
}
else{block.setStroke(Paint.valueOf("#0082c8"));    
block2.setStroke(Paint.valueOf("#0082c8"));    
block3.setStroke(Paint.valueOf("#0082c8"));    
block4.setStroke(Paint.valueOf("#0082c8"));   
border1.setStroke(Paint.valueOf("#0082c8"));    
border2.setStroke(Paint.valueOf("#0082c8"));    
}
     }

    public void evils() {

        arr2.add(evil);
        arr2.add(evil2);

        imagex.add(0);
        imagey.add(0);
        imagex.add(0);
        imagey.add(0);
        arr2.add(evil4);
        imagex.add(0);
        imagey.add(0);
        arr2.add(evil5);
        imagex.add(0);
        dir.add(1);
        dir.add(1);
        dir.add(1);
        dir.add(1);
        imagey.add(0);
        faces.add(face1);
                faces.add(face2);
        faces.add(face3);
                faces.add(face4);

    }

    public void replay() {
        turn = 1;
        contain.setStyle("-fx-background-color: #" + "000000");
        cir.setFill(javafx.scene.paint.Color.BLACK);
        text1.setText("LEVEL " + level);
        if (lifes < 0) {
            text1.setText("you lost");
            lost = true;
        }
        text1.setVisible(true);
        text1.setTextAlignment(TextAlignment.CENTER);
    }

    public void updateImageView(int num) {
        if(scoree>=(5*level)) {
            musiclevel();
            level ++;
            level();
            lose = 0;
            replay();
        }
        if (avoidfood(food.getCenterX(), food.getCenterY())){
            random();
        }
        double e = cir.getLayoutX() + cir.getCenterX();
        double y1 = cir.getLayoutY() + cir.getCenterY();
        if ((num == 1 && cir.getCenterX() < 476 - 24) && avoid(1, e, y1)) {
            imageX = imageX + 5;
            cir.setCenterX(imageX);
            right.setCenterX(imageX);
            down.setCenterX(imageX);
            up.setCenterX(imageX);
            left.setCenterX(imageX);
            eat.setCenterX(imageX);
                        lefteye.setCenterX(imageX);
                        righteye.setCenterX(imageX);
                        upeye.setCenterX(imageX);
                        downeye.setCenterX(imageX);

        }
        if ((num == 2 && cir.getCenterX() > 0) && avoid(2, e, y1)) {
            imageX = imageX - 5;
            cir.setCenterX(imageX);
            right.setCenterX(imageX);
            down.setCenterX(imageX);
            up.setCenterX(imageX);
            left.setCenterX(imageX);
            eat.setCenterX(imageX);
lefteye.setCenterX(imageX);
                        righteye.setCenterX(imageX);
                        upeye.setCenterX(imageX);
                        downeye.setCenterX(imageX);
        }
        if ((num == 3 && cir.getCenterY() < 476 - 24) && avoid(3, e, y1)) {
            imageY = imageY + 5;
            cir.setCenterY(imageY);
            right.setCenterY(imageY);
            down.setCenterY(imageY);
            up.setCenterY(imageY);
            left.setCenterY(imageY);
            eat.setCenterY(imageY);
lefteye.setCenterY(imageY);
                        righteye.setCenterY(imageY);
                        upeye.setCenterY(imageY);
                        downeye.setCenterY(imageY);
        }
        if ((num == 4 && cir.getCenterY() > 0) && avoid(4, e, y1)) {
            imageY = imageY - 5;
            cir.setCenterY(imageY);
            right.setCenterY(imageY);
            down.setCenterY(imageY);
            up.setCenterY(imageY);
            left.setCenterY(imageY);
            eat.setCenterY(imageY);
            lefteye.setCenterY(imageY);
                        righteye.setCenterY(imageY);
                        upeye.setCenterY(imageY);
                        downeye.setCenterY(imageY);
        }

        if (e < 26 + 2 || e > 500 - 26 - 2 || y1 < 26 + 2 || y1 > 500 - 26 - 2) {
            lose();
        } else {
            contain.setStyle("-fx-background-color: #" + "000000");
            cir.setFill(javafx.scene.paint.Color.BLACK);
        }
        food();
        rotate();
        eat();
        score.setText(Integer.toString(scoree));
    }

    public void updateImageView2(int lev) {

        {
            {
                Circle n = (Circle) arr2.get(lev - 1);
                ImageView v= (ImageView)faces.get(lev-1);
                double e = n.getLayoutX() + n.getCenterX();
                double y1 = n.getLayoutY() + n.getCenterY();
                int num = computer(lev);
                if (num == 1 && n.getCenterX() < 476 - 24 && avoid(1, e, y1)) {
                    imagex.set(lev - 1, (int) imagex.get(lev - 1) + 5);
                    n.setCenterX((int) imagex.get(lev - 1));
                                        v.setX((int) imagex.get(lev - 1));
                }
                if (num == 2 && n.getCenterX() > 0 && avoid(2, e, y1)) {
                    imagex.set(lev - 1, (int) imagex.get(lev - 1) - 5);

                    n.setCenterX((int) imagex.get(lev - 1));
                                        v.setX((int) imagex.get(lev - 1));

                }

                if (num == 3 && y1 < 476 && avoid(3, e, y1)) {
                    imagey.set(lev - 1, (int) imagey.get(lev - 1) + 5);
                    n.setCenterY((int) imagey.get(lev - 1));
                                        v.setY((int) imagey.get(lev - 1));
                }
                if (num == 4 && y1 > 24 && avoid(4, e, y1)) {
                    imagey.set(lev - 1, (int) imagey.get(lev - 1) - 5);
                                        v.setY((int) imagey.get(lev - 1));
                    n.setCenterY((int) imagey.get(lev - 1));
                }
            }

        }
    }

    public void lifes() {
        if (lifes > 0) {
            life3.setVisible(true);
        } else {
            life3.setVisible(false);
        }
        if (lifes > 1) {
            life2.setVisible(true);
        } else {
            life2.setVisible(false);
        }
        if (lifes > 2) {
            life1.setVisible(true);
        } else {
            life1.setVisible(false);
        }
    }

    public void begin() {

        imageY = imageY + 4;
        cir.setCenterY(imageY);
        right.setCenterY(imageY);
        down.setCenterY(imageY);
        up.setCenterY(imageY);
        left.setCenterY(imageY);
        righteye.setCenterY(imageY);
        downeye.setCenterY(imageY);
        upeye.setCenterY(imageY);
        lefteye.setCenterY(imageY);
        eat.setCenterY(imageY);
        imageX = imageX + 4;
        cir.setCenterX(imageX);
        right.setCenterX(imageX);
        down.setCenterX(imageX);
        up.setCenterX(imageX);
        left.setCenterX(imageX);
        righteye.setCenterX(imageX);
        downeye.setCenterX(imageX);
        upeye.setCenterX(imageX);
        lefteye.setCenterX(imageX);
        eat.setCenterX(imageX);
        level();
                                                                music();

    }

    public void key() {

        if (keyCode.equals(KeyCode.RIGHT)) {
            begin = false;
            text1.setVisible(false);
            head = 1;
            num = 1;
        } else if (keyCode.equals(KeyCode.LEFT) && !begin) {

            head = 2;
            num = 2;
        } else if (keyCode.equals(KeyCode.DOWN)) {
            begin = false;
            head = 3;
            num = 3;
        } else if (keyCode.equals(KeyCode.UP) && !begin) {

            begin = false;
            text1.setVisible(false);
            head = 4;
            num = 4;
        }
        if (keyCode == KeyCode.ENTER) {
            random();
                }
    }
    public boolean avoidfood(double x, double y) {
        for (int i = 0; i < arr.size(); i++) {
            blocks k = (blocks) arr.get(i);
            if ((x>k.x1-16&&x<k.x2+16)&&(y>k.y1-16&&y<k.y2+16)) {
              return true;

        }}
        return false;
    }
    public boolean avoidwall(int c, double e, double y1) {
  if (e < 26 + 2 || e > 500 - 26 - 2 || y1 < 26 + 2 || y1 > 500 - 26 - 2) {
return false;        }
        return true;
    }
    public boolean avoidevils(int c, double e, double y1) {
        return true;
 
    }
   public void music() { 
       {
           mediaPlayer2.pause();
mediaPlayer2.setOnEndOfMedia(new Runnable() {
       public void run() {
         mediaPlayer2.seek(Duration.ZERO);
       }
   });
mediaPlayer2.play();}
   
   }
    public void musiclevel() { 
       {
          mediaPlayer2.pause();
           String bip2 = "music\\level.mp3";
Media hit3 = new Media(new File(bip2).toURI().toString());
final MediaPlayer mediaPlayer3 = new MediaPlayer(hit3);
mediaPlayer3.play();}
   
   }
    public void sound(int num) { 
       if(num==1) {String bip = "music\\eat.wav";
Media hit = new Media(new File(bip).toURI().toString());
MediaPlayer mediaPlayer = new MediaPlayer(hit);
mediaPlayer.play();}
       if(num==2) {String bip = "music\\lost.wav";
Media hit = new Media(new File(bip).toURI().toString());
MediaPlayer mediaPlayer = new MediaPlayer(hit);
mediaPlayer.play();}
    }
    public void musiclost() { 
       if(lifes>-1){
           mediaPlayer2.pause();
           String bip2 = "music\\lose.mp3";
Media hit3 = new Media(new File(bip2).toURI().toString());
final MediaPlayer mediaPlayer3 = new MediaPlayer(hit3);
mediaPlayer3.setOnEndOfMedia(new Runnable() {
       public void run() {
music();       }
   });
mediaPlayer3.play();}
       else {
           mediaPlayer2.pause();
           String bip2 = "music\\game over.mp3";
Media hit3 = new Media(new File(bip2).toURI().toString());
final MediaPlayer mediaPlayer3 = new MediaPlayer(hit3);
mediaPlayer3.play();}
   
   }
}
