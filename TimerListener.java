/**
 * @Author: Panagiotis Dimitropoulos
 * @Purpose: Creating TimeListener class
 * @Date: 25/11/19
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {
	
	Ball ball;
	Display dis;
	int x = 10;		//increment to the x coordinate
	int y = 10;		//increment to the y coordinate
	int direction = -1;		// multiplied by the increment based on the direction of travel

	public TimerListener(Ball b, Display d) { 	
		
		ball = b;
		dis = d;
	}
	public void actionPerformed(ActionEvent event) {
		
		ball.run();
		dis.repaint();
	}
}
