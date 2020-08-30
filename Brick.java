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
import java.util.ArrayList;
public class Brick{
	private int x;
	private int y;
	private Rectangle x1,x2,y1,y2;
	private Image image;
	public Brick(int w, int h, Image image)
	{
		x=w;
		y=h;
		this.image=image;

	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public Rectangle getx1()
	{
		return x1;
	}

	public Rectangle getx2()
	{
		return x2;
	}

	public Rectangle gety1()
	{
		return y1;
	}

	public Rectangle gety2()
	{
		return y2;
	}

	public void drawBrick(GraphicsContext gc)
	{
		gc.drawImage(image, x, y);
	}

	public void drawLines() //draws really small rectangles around the borders of the brick image
	{
		x1= new Rectangle(x,y, image.getWidth(), .00000001);
		x2 = new Rectangle(x,y+image.getHeight(), image.getWidth(),.0000001);
		y1 = new Rectangle(x,y, .00000001, image.getHeight());
		y2 = new Rectangle(x + image.getWidth(), y, .00000001, image.getHeight());
	}


}