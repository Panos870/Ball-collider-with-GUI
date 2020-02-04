/**
 * @Author: Panagiotis Dimitropoulos
 * @Purpose: Creating FrameViewer class
 * @Date: 25/11/19
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class FrameViewer extends JFrame{
	
	public static void main(String[] args)
	{
		 EventQueue.invokeLater(new Runnable()  {   
			 public void run()   {   
				 JFrame frame = new FrameViewer(); 
					frame.setTitle("Colour Ball Collision");
					frame.setVisible(true);
					frame.setSize(500,500);
					frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
					frame.setSize(new Dimension(300,300));
					frame.setLayout(new BorderLayout());
					Display display = new Display();
					
					JPanel controlPanel = new JPanel(new GridLayout(1,2));
					JPanel buttonPanel = new JPanel(new GridLayout(1,3,10,10));
					
					JSlider slider = new JSlider(1,5);
					slider.setMajorTickSpacing(1);
					slider.setPaintTicks(true);
					slider.setPaintLabels(true);

					
					frame.add(display, BorderLayout.CENTER);
					slider.addChangeListener(new SliderListener(display));
					
					JButton start = new JButton("start");
					start.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							display.addBall();
							
						}
						
					});
					
					
					JButton stop = new JButton("stop");
					stop.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							try {
								TimeUnit.SECONDS.sleep(5);					//Sleep for 5 seconds to retain the state at which display was stopped
								
								display.clear();
								display.repaint();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							}
						});
					
					JButton pause = new JButton("pause");
					pause.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							display.pause();
							
						}
						
					});
					
					
					buttonPanel.add(stop);
					buttonPanel.add(pause);
					buttonPanel.add(start);
					
					controlPanel.add(buttonPanel);
					controlPanel.add(slider);
					
					frame.add(controlPanel,BorderLayout.SOUTH);
					frame.pack();
					
				 }
			 }); 
		


	}

}
