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
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;


public class DemoGame extends Application implements EventHandler<InputEvent>
{
	GraphicsContext gc;
	Image trooper;
	int x=0;
	AnimateObjects animate;
	Canvas canvas;
	int direction=1;;
	public static void main(String[] args)
	{
		launch();
	}

	public void start(Stage stage)
	{
		stage.setTitle("Demo");
		Group root = new Group();
		canvas = new Canvas(800, 400);
		root.getChildren().add(canvas);
		Scene scene= new Scene(root);
		stage.setScene(scene);

		stage.show();


		gc = canvas.getGraphicsContext2D();
		trooper = new Image ( "trooper.jpg" );
		gc.drawImage( trooper, 180, 100);

		animate = new AnimateObjects();
		animate.start();
		stage.show();

		scene.addEventHandler(KeyEvent.KEY_PRESSED, this);
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

	}

	public void handle(final InputEvent event){
		//if(((KeyEvent)event).getCode() == KeyCode.LEFT)               //input from user
				//x-=1;

		if(event instanceof KeyEvent){
			if(((KeyEvent)event).getCode() == KeyCode.LEFT )
				x-=1;
				System.out.println("VALUE: "+x);
		}

		if (event instanceof MouseEvent){
			System.out.println(((MouseEvent)event).getX());
			System.out.println(((MouseEvent)event).getY());

		}
	}


	public class AnimateObjects extends AnimationTimer {
		public void handle(long now) {

			//moving image left and right with boundaries
			/*
			if(x<canvas.getWidth() && direction ==1)
			{
				x+=1;
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				gc.drawImage(trooper, x, 100);
				Rectangle2D rect1= new Rectangle2D( 400, 100, 100, 100);
				gc.fillRect(400, 100, 100, 100);
				Rectangle2D rect2= new Rectangle2D( 200+x, 100, trooper.getWidth(), trooper.getHeight());
				System.out.println("VALUE: "+x);
				if(rect1.intersects(rect2))
					System.out.println("Hit");
				if(x==canvas.getWidth())
				{
					direction=0;
				}
			}
			else if(x>0 && direction==0)
			{
				x--;
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				gc.drawImage(trooper, x, 100);
				Rectangle2D rect1= new Rectangle2D( 400, 100, 100, 100);
				gc.fillRect(400, 100, 100, 100);
				System.out.println("VALUE: "+x);
				Rectangle2D rect2= new Rectangle2D( 200+x, 100, trooper.getWidth(), trooper.getHeight());
				if(rect1.intersects(rect2))
					System.out.println("Hit");
				if(x==0)
				{
					direction=1;
				}
			}
			*/

			//Hit detection and moving right
			//x+=1;
			if(x>-50)
			{
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				gc.drawImage(trooper, 180+x, 100);
				Rectangle2D rect1= new Rectangle2D( 400, 100, 100, 100);
				gc.fillRect(400, 100, 100, 100);
				Rectangle2D rect2= new Rectangle2D( 180+x, 100, trooper.getWidth(), trooper.getHeight());
				//gc.fillRect( 180+x, 100, trooper.getWidth(), trooper.getHeight());
				//System.out.println("VALUE: "+x);
				if(rect1.intersects(rect2))
				System.out.println("Hit");
			}

			else
			{
				gc.setFill( Color.YELLOW); //Fills the text in yellow
				gc.setStroke( Color.BLACK ); //Changes the outline the black
				gc.setLineWidth(1); //How big the black lines will be
				Font font = Font.font( "Arial", FontWeight.NORMAL, 48 );
				gc.setFont( font );
				gc.fillText( "Game Over", 100, 50 ); //draws the yellow part of the text
				gc.strokeText( "Game Over", 100, 50 ); //draws the outline part of the text
			}

		}


	}
}