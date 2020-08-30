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
import javafx.scene.shape.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import java.lang.Math.*;
import java.awt.geom.*;
import java.awt.Graphics2D;
import javafx.scene.transform.Rotate;
import javafx.scene.image.ImageView;
import javafx.scene.SnapshotParameters;
public class Ball{

	private static double x, incrementX,incrementY, y;
	private static double xintersect;
	private static double angle, aimAngle;
	private static double speed;
	private static boolean start = false;
	private static Image image;

	private static Circle rectBall;

	public Ball(double x, double y, int incrementX, int incrementY, Image image)
	{
		this.x=x;
		this.y=y;
		this.incrementX=incrementX;
		this.incrementY=incrementY;
		this.image=image;
		angle = 20;
		aimAngle=0;

		speed = Math.sqrt(Math.pow(getIncrementX(),2) + Math.pow(getIncrementY(),2));
	}

	public static void subAimAngle(double d)
	{
		angle--;
	}
	public static void addAimAngle(double d)
	{
		angle++;
	}
	public static void setIncrementX(double d)
	{
		incrementX=d;

	}
	public static void setIncrementY(double d)
	{
		incrementY=d;

	}
	public static double getIncrementX()
	{
		return incrementX;
	}

	public static double getIncrementY()
	{
		return incrementY;
	}
	public static void addX(double a)
	{
		x+=a;
	}

	public static void addY(double a)
	{
		y+=a;
	}

	public static void subtractX(double s)
	{
		x-=s;
	}

	public static void subtractY(double s)
	{
		y-=s;
	}

	public static double getX()
	{
		return x;
	}

	public static double getY()
	{
		return y;
	}
	public static void setX(double a)
	{
		x=a;
	}
	public static void setY(double a)
	{
		y=a;
	}
	public void drawBall(GraphicsContext gc)
	{
		if(!(start))
		{
			setIncrementX(speed * Math.sin(Math.toRadians(angle)));
			setIncrementY(1 * speed * Math.cos(Math.toRadians(angle)));
		}
		gc.drawImage(image, x, y);
	}

	public static void trueStart()
	{
		start = true;
	}
	public static void falseStart()
	{
		start=false;
	}
	public static void drawRect()
	{
		rectBall = new Circle (x + image.getWidth()/2, y+image.getWidth()/2,  image.getWidth()/2 -1);
	}
	public static Circle getRect()
	{
		return rectBall;
	}

	public void setAngle(int a)
	{
		if(a ==1||a==8)
			angle = 65;
		else if(a==2||a==7)
			angle = 40;
		else if(a==3||a==6)
			angle = 25;
		else if(a==4||a==5)
			angle = angle;
		if(a<=4)
			setIncrementX(-1 * speed * Math.sin(Math.toRadians(angle)));
		else
			setIncrementX(speed * Math.sin(Math.toRadians(angle)));

			setIncrementY(-1 * speed * Math.cos(Math.toRadians(angle)));
			System.out.println(angle +" "+ xintersect);



	}



	public void drawAim(GraphicsContext gc)
	{
		//Line aim = new Line(x+image.getWidth()/2, y+image.getHeight()/2, 5*(x+((image.getWidth()/2)-(Math.sin(Math.toRadians(aimAngle))))), 5*(y+((image.getHeight()/2)+Math.cos(Math.toRadians(aimAngle)))));
		//System.out.println("Created");
		//gc.fill(
		//used online sources for this but doesnt work properly
		ImageView iv = new ImageView(new Image( "Line.jpg"));
		iv.setRotate(90-angle); //+90 because this angle is measured with respect to the horizantal, not the vertical like the other angle.
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		iv.setX(iv.getFitWidth());
		iv.setY(iv.getFitHeight());
		Image rotatedImage = iv.snapshot(params, null);
		System.out.println(rotatedImage.getWidth());
		System.out.println(rotatedImage.getHeight());
		gc.drawImage(rotatedImage, getX()+image.getWidth()/2, getY()+image.getHeight()/2);

	}


}