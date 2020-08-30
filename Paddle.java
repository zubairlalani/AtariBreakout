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
public class Paddle{
	private static int x;
	private static int y;
	private static Rectangle r1,r2,r3,r4,r5,r6,r7,r8;
	private static Image image;

	public Paddle(int w, int h, Image image)
	{
		x=w;
		y=h;
		this.image=image;
	}

	public static void add(int a)
	{
		//System.out.println("IM IN add");
		x+=a;
	}

	public static void subtract(int s)
	{
		//System.out.println("IM IN subtract");
		x-=s;
	}

	public static int getX()
	{
		return x;
	}

	public static int getY()
	{
		return y;
	}
	public static void setX(int a)
	{
		x=a;
	}
	public static void setY(int a)
	{
		y=a;
	}
	public void drawPaddle(GraphicsContext gc)
	{
		gc.drawImage(image, x, y);
	}

	public static void drawRects()
	{
		r1 = new Rectangle (x, y, image.getWidth()/8, image.getHeight());
		r2 = new Rectangle (x + (image.getWidth()/8), 370, image.getWidth()/8, image.getHeight());
		r3 = new Rectangle (x + 2*(image.getWidth()/8), 370, image.getWidth()/8, image.getHeight());
		r4 = new Rectangle (x + 3*(image.getWidth()/8), 370, image.getWidth()/8, image.getHeight());
		r5 = new Rectangle (x + 4*(image.getWidth()/8), 370, image.getWidth()/8, image.getHeight());
		r6 = new Rectangle (x + 5*(image.getWidth()/8), 370, image.getWidth()/8, image.getHeight());
		r7 = new Rectangle (x + 6*(image.getWidth()/8), 370, image.getWidth()/8, image.getHeight());
		r8 = new Rectangle (x + 7*(image.getWidth()/8), 370, image.getWidth()/8, image.getHeight());

	}

	public static Rectangle getR1()
	{
		return r1;
	}
	public static Rectangle getR2()
	{
		return r2;
	}
	public static Rectangle getR3()
	{
		return r3;
	}
	public static Rectangle getR4()
	{
		return r4;
	}
	public static Rectangle getR5()
	{
		return r5;
	}
	public static Rectangle getR6()
	{
		return r6;
	}
	public static Rectangle getR7()
	{
		return r7;
	}
	public static Rectangle getR8()
	{
		return r8;
	}



}