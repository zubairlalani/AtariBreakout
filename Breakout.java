import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.shape.Rectangle;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import java.awt.geom.*;
import java.lang.Math.*;
import java.util.ArrayList;

//don't forget to add audio


public class Breakout extends Application implements EventHandler<InputEvent>
{
	GraphicsContext gc;
	Image background;
	Image darksouls;
	Image victory;
	Paddle paddle;
	Ball ball;
	//int ballX = 380;
	//int ballY = 350;
	//int direction = 0;
	int score=0;
	boolean aimstart = false;
	boolean end=false;
	boolean right, left, hit, anstart;
	//boolean speedUp=true; //speeding up the ball
	AudioClip bounceSound; //audio for bouncing
	AudioClip death; //for audio when you die


	AnimateObjects animate;
	Canvas canvas;
	ArrayList<Brick> lvl1 = new ArrayList<Brick>();
	boolean start=false;
	Scene scene;

	public static void main(String[] args)
	{
		launch();
	}

	public void start(Stage stage)
	{
		//create arraylist of brick objects
		for(int x = 0;x<800;x+=(new Image("theRealBrick.png")).getWidth())
		{
			for(int y = 50;y<150;y+=(new Image("theRealBrick.png")).getHeight())
			{
				lvl1.add( new Brick( x , y, new Image ( "theRealBrick.png" ) ) );
			}
		}

		//setting up the game
		stage.setTitle("Breakout");
		Group root = new Group();
		canvas = new Canvas(800, 400);
		root.getChildren().add(canvas);

		scene= new Scene(root);
		stage.setScene(scene);

		stage.show();

		//setting up sound variables
		URL bounceSoundURL = getClass().getResource("bounce.wav");
		bounceSound = new AudioClip(bounceSoundURL.toString());
		URL deathURL = getClass().getResource("YOUDIED.wav");
		death = new AudioClip(deathURL.toString());

		gc=canvas.getGraphicsContext2D();

		//Background and ending image
		background= new Image("background.jpeg");
		darksouls= new Image("DarkSoulsEnd.jpg");
		victory=new Image("Victory.jpg");

		//create ball and paddle objects
		ball=new Ball(380, 300, -4, -4, new Image("theRealBall.png"));
		paddle=new Paddle(325, 370, new Image("paddle2.png"));

		animate = new AnimateObjects();
		animate.start();
		start=true;
		stage.show();


		scene.addEventHandler(KeyEvent.KEY_RELEASED, this);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, this);
	}

	public void handle(final InputEvent event) //input from user
	{
		if(((KeyEvent)event).getCode() == KeyCode.SPACE) //start game
		{
			aimstart=true;

			animate.start();
			ball.trueStart();
		}

		if(event.getEventType() == KeyEvent.KEY_PRESSED)
		{
			if(((KeyEvent)event).getCode() == KeyCode.LEFT)
				left=true;
			if(((KeyEvent)event).getCode() == KeyCode.RIGHT)
				right=true;
		}

		if(event.getEventType()== KeyEvent.KEY_RELEASED )
		{
			if(((KeyEvent)event).getCode() == KeyCode.LEFT)
				left=false;
			if(((KeyEvent)event).getCode() == KeyCode.RIGHT)
				right=false;
		}

		if(((KeyEvent)event).getCode()== KeyCode.ENTER) //restart button
		{
			animate.restart();
			death.stop();
		}



	}

	public class AnimateObjects extends AnimationTimer
	{
		public void handle(long now)
		{
			if(end==false)
			{
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

				//draw paddle and ball
				gc.drawImage(background, 0, 0);
				drawScoreAndTitle();
				ball.drawBall(gc);
				paddle.drawPaddle(gc);

				if(aimstart)
				{
					if(right && paddle.getX()<682)
					{
						paddle.add(7);
						System.out.println(paddle.getX());
					}
					else
						paddle.add(0);

					if(left && paddle.getX()>3)
					{
						paddle.subtract(7);
						System.out.println(paddle.getX());
					}
					else
						paddle.add(0);
				}

				else //for aiming where the ball goes in the beginning of the game
				{
					System.out.println("Hello");
					ball.drawAim(gc);
					if(right)
						ball.addAimAngle(2);
					if(left)
						ball.subAimAngle(2);
					ball.drawAim(gc);
				}

				//draw bricks and border around brick
				for(int x =0; x<lvl1.size();x++)
				{
					lvl1.get(x).drawBrick(gc);
					lvl1.get(x).drawLines();
				}

				//move ball
				if(aimstart==true)
				{
					ball.addX(ball.getIncrementX());
					ball.addY(ball.getIncrementY());
				}

				//rectangle around paddle and ball
				ball.drawRect();
				paddle.drawRects();

				bounce();

				if(lvl1.size()==0)
				{
					gc.drawImage(victory, 0, 0);
					gc.setFill( Color.RED);
					gc.setStroke( Color.BLACK );
					gc.setLineWidth(1);
					Font font = Font.font( "Arial", FontWeight.NORMAL, 25 );
					gc.setFont( font );
					gc.fillText( "(Press ENTER to restart)" , 230, 350);
					gc.strokeText( "(Press ENTER to restart)" , 230, 350);
					stop();
				}
			}
			else //when you die displays image
			{
				death.play();
				gc.drawImage(darksouls, 0, 0);
				gc.setFill( Color.RED);
				gc.setStroke( Color.BLACK );
				gc.setLineWidth(1);
				Font font = Font.font( "Arial", FontWeight.NORMAL, 25 );
				gc.setFont( font );
				gc.fillText( "(Press ENTER to restart)" , 230, 350);
				gc.strokeText( "(Press ENTER to restart)" , 230, 350);

				stop();

			}
		}

		//bounce ball off of walls, paddle, and bricks
		public void bounce()
		{
			if(ball.getRect().intersects(paddle.getR1().getLayoutBounds()))
			{
				ball.setAngle(1);
				bounceSound.play();
			}
			else if(ball.getRect().intersects(paddle.getR2().getLayoutBounds()))
			{
				ball.setAngle(2);
				bounceSound.play();
			}
			else if(ball.getRect().intersects(paddle.getR3().getLayoutBounds()))
			{
				ball.setAngle(3);
				bounceSound.play();
			}
			else if(ball.getRect().intersects(paddle.getR4().getLayoutBounds()))
			{
				ball.setAngle(4);
				bounceSound.play();
			}
			else if(ball.getRect().intersects(paddle.getR5().getLayoutBounds()))
			{
				ball.setAngle(5);
				bounceSound.play();
			}
			else if(ball.getRect().intersects(paddle.getR6().getLayoutBounds()))
			{
				ball.setAngle(6);
				bounceSound.play();
			}
			else if(ball.getRect().intersects(paddle.getR7().getLayoutBounds()))
			{
				ball.setAngle(7);
				bounceSound.play();
			}
			else if(ball.getRect().intersects(paddle.getR8().getLayoutBounds()))
			{
				ball.setAngle(8);
				bounceSound.play();

			}


			// if it hits the walls
			else if(ball.getY()<0)
			{
				ball.setIncrementY( ball.getIncrementY()*-1 );
			}
			else if(ball.getX()>785 ||ball.getX()<0)
			{
				ball.setIncrementX( ball.getIncrementX()*-1 );
			}

			else if(ball.getY()>400) //if lose display game over
			{
				end=true;
			}

			//if it hits the bricks
			else
			{
				for(int i =0;i<lvl1.size();i++)
				{

					if(lvl1.get(i).gety1().intersects(ball.getRect().getLayoutBounds())|| lvl1.get(i).gety2().intersects(ball.getRect().getLayoutBounds()))
					{
						if(!(hit))
							ball.setIncrementX( ball.getIncrementX()*-1 );
						hit = true;
						lvl1.remove(i);
						score+=100;
					}
					else if(lvl1.get(i).getx1().intersects(ball.getRect().getLayoutBounds())|| lvl1.get(i).getx2().intersects(ball.getRect().getLayoutBounds()))
					{
						//System.out.println("HIT TOP/Bot");
						if(!(hit))
							ball.setIncrementY( ball.getIncrementY()*-1 );
						hit =true;
						//System.out.println("INCREMENT Y: "+ball.getIncrementY());
						lvl1.remove(i);
						score+=100;

						/*
						if(ball.getY()<100)
						{
							ball.setIncrementX(-5);
							ball.setIncrementY(ball.getIncrementX()-1);
							speedUp=false;
						}
						*/

					}
				}
				hit = false;
			}
		}

		public void restart()
		{
			ball.setX(380);
			ball.setY(350);
			ball.setIncrementX(-3);
			ball.setIncrementY(-3);
			paddle.setX(325);
			for(int i = lvl1.size()-1;i>=0;i--)
				lvl1.remove(i);
			for(int x = 0;x<800;x+=(new Image("theRealBrick.png")).getWidth())
			{
				for(int y = 50;y<150;y+=(new Image("theRealBrick.png")).getHeight())
				{
					lvl1.add( new Brick( x , y, new Image ( "theRealBrick.png" ) ) );
				}
			}
			score=0;
			start=true;
			end=false;
			aimstart=false;
			ball.falseStart();
			start();
		}


		public void drawScoreAndTitle()
		{
			gc.setFill( Color.RED);
			gc.setStroke( Color.BLACK );
			gc.setLineWidth(1);
			Font font = Font.font( "Arial", FontWeight.NORMAL, 32 );
			gc.setFont( font );
			gc.fillText( "Score: "+score, 600, 40 );
			gc.strokeText( "Score: "+score, 600, 40);

			gc.fillText("BREAKOUT!", 300, 40);
			gc.strokeText("BREAKOUT!", 300, 40);
		}



	}


}