/**
 * @Author: Panagiotis Dimitropoulos
 * @Purpose: Creating Display class
 * @Date: 25/11/19
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class Display extends JPanel{
	
	int countLimit = 20;

	//Creating ball,timer and timer listener arrays to allow the display of 10
	ArrayList<Ball> ball = new ArrayList<Ball>();                          
	
	//keeps track of the number of balls in the display
	int ballCount = 0;									
	Random rand = new Random();
	final int DISPLAY_HEIGHT = 400;
	final int DISPLAY_WIDTH = 400;
	
	public Display() {
		this.setPreferredSize(new Dimension(DISPLAY_WIDTH,DISPLAY_HEIGHT));
	}
		//Add balls into the display
		public void addBall() {
		
		//Creating random starting points for the balls and random colours
		rand.nextInt(DISPLAY_WIDTH);
		rand.nextInt(DISPLAY_HEIGHT);
			
		ball.add(new Ball(this /*, lock*/));
		
		//Starting ball thread
		new Thread(ball.get(ball.size()-1)).start();          	
	}
	
	public void collisions() {
		Ball BallA = null;
		Ball BallB = null;
		boolean crush = false;
		int b= 0 ,a = 0;
		for( a = 0; a< ball.size() ; a++) {
			if(crush) {
				break;
			}
			for(b = 0; b < ball.size(); b++) {
				if(!crush)
				if(ball.get(a) != ball.get(b))
					if(ball.get(a).intersects(ball.get(b))) {
						BallA = ball.get(a);
						BallB = ball.get(b);
						crush = true;
						break;
					}
			}
		}
		if(crush) {
			ball.remove(BallB);
			ball.remove(BallA);
		}
	}
	public void setFPS(int fps) {
		for( Ball ball : ball) {
			ball.setFPS(fps);
		}
	}
	public void pause() {
		for(Ball ball : ball)
			ball.pause();
	}
	public void clear() {
		ball.removeAll(ball);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		
		collisions();
		for(int i = 0; i < ball.size(); i++) {
		ball.get(i).draw(g2);	
		}
}
}
