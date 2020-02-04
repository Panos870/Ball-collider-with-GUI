/**
 * @Author: Panagiotis Dimitropoulos
 * @Purpose: Creating SliderListener class
 * @Date: 25/11/19
 */

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener {
	
	Display display;
	public SliderListener(Display display) {
	this.display = display;
	}
	int fps;
	
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			int fps = (int)source.getValue();
			this.fps = fps;
			display.setFPS(getState());
		}  
	}

	public int getState() {
		if(fps == 1)	
			return 200;
		else if(fps == 2)
			return 100;
		else if(fps == 3)
			return 50;
		else if(fps == 4)
			return 25;
		else 
			return 10;
		
	}
}