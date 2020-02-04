/**
 * @Author: Panagiotis Dimitropoulos
 * @Purpose: Creating Ball class
 * @Date: 25/11/19
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.lang.Math;

public class Ball implements Runnable{
	
	
	int xinc = 10;						//increment to the x coordinate
	int yinc = 10;						//increment to the y coordinate
	int fps = 200;
	Random rand = new Random();
	int disHeight;
	int disWidth;
	Ball ball;
	boolean Running = true;
	Display dis;
	private Ellipse2D.Double oval;
	private double x;
	private double y; 
	private double width; 
	private double height;  
	private Color colour;
	private double radius;
	
	boolean paused = false;
	
	public Ball(Display display) {
		this.dis = display;
		disWidth = display.getWidth();
		disHeight = display.getHeight();
		x = rand.nextInt(disWidth);
		y = rand.nextInt(disHeight);
		width = 10;
		height = 10;
		radius = width/2;
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);
		colour = new Color(r,g,b);
	
	}
	
	public void setFPS(int fps) {
		this.fps = fps;
	}
	
	public void draw(Graphics2D g2) {
		oval = new Ellipse2D.Double(x, y, width, height);
		g2.setColor(colour);
		g2.fill(oval);
		
	}

	
	public void addXY(int x, int y) {
		
		this.setX(this.getX() + x);
		this.setY(this.getY() + y);
		
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX(){
		
		return (int)this.x;
		
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY(){
		
		return (int)this.y;
		
	}
	
	public boolean intersects(Ball ball) {
		double distance = Math.sqrt(Math.pow(((double)this.getX()- (double)ball.getX()),2) + Math.pow(((double)this.getY()- (double)ball.getY()),2));
		if(distance < (radius * 2))
			return true;
		return false;
	}
	
	public synchronized void pause() {
		if(paused) {
			resume();
		}
		else
			paused = true;
	}
	
	public void resume() {

		synchronized(this/*pauseLock*/) {

			paused = false;	
			notifyAll();
		}	
		

	}
	
	public synchronized void check() {
		if (paused) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}
	}
	public void run() {
	
		while(Running) {

			synchronized(this){
				if(paused) {
					try {
						wait();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			
				if(this.getX() >= disWidth || this.getX() <= 0) {			//change direction of travel in the x vector
					xinc = -xinc;
					
				}
					
	
				if(this.getY() >= disHeight || this.getY() <= 0) {	//change direction of travel in y vector
					yinc = -yinc;
					
				}
				
			
				
				this.addXY(xinc, yinc);
				
				dis.repaint();
				
				try {
					Thread.sleep(fps);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
		}
			

	}

}


