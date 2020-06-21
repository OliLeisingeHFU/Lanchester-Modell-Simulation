package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import utils.ApplicationTime;

public class WindowContent extends JPanel {
	
	//panel has a single time tracking thread associated with it
	private ApplicationTime t;
	private double time;
	
	public WindowContent(ApplicationTime thread) {
		this.t = thread;
	}
	
	//set this panel's preferred size for auto-sizing the container JFrame
	public Dimension getPreferredSize() {
		return new Dimension(_UI_Constants.Window_Width - 200, _UI_Constants.Window_Height);
	}
	
	//drawing operations should be done in this method
	@Override protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		time = t.GetTimeInSeconds();
		 
	}
}

