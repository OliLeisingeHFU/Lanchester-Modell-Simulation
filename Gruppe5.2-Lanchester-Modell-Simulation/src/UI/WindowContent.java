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
	
	public long g;
	public long h;
	public double s;
	public double r;
	
	
	public double y_axis_Stretch;
	
	public double x_axis_Stretch;
	
	public WindowContent(ApplicationTime thread) {
		this.t = thread;
	}
	public WindowContent(ApplicationTime thread, long g_value, long h_value, double s_value, double r_value) {
		this.t = thread;
		this.g = g_value;
		this.h = h_value;
		this.s = s_value;
		this.r = r_value;
	}
	
	//set this panel's preferred size for auto-sizing the container JFrame
	public Dimension getPreferredSize() {
		return new Dimension(_UI_Constants.Window_Width, _UI_Constants.Window_Height);
	}
	
	//drawing operations should be done in this method
	@Override protected void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		
		// Dimensionen Berechnung
		long start_g_x = 0;
		long start_h_x = 0;
		long start_g_y = 0;
		long start_h_y = 0;
		
		if(g >= h) {
			y_axis_Stretch = g / _UI_Constants.Window_Height;
			start_g_y = 0;
			start_h_y = Math.round((g - h) * y_axis_Stretch);
			
		}else {
			y_axis_Stretch = h / _UI_Constants.Window_Height;
			start_h_y = 0;
			start_g_y = Math.round((h - g) * y_axis_Stretch);
		}
		
		//y_axis_Stretch = () / _UI_Constants.Window_Width;
		
		time = t.GetTimeInSeconds();
		 
	}
}

