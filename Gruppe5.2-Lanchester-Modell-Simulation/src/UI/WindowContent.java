package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import java.util.ArrayList;

import Calculations.Calculations;
import utils.ApplicationTime;

public class WindowContent extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6820769477359842449L;
	//panel has a single time tracking thread associated with it
	private ApplicationTime t;
	private double time;
	
	public long g;
	public long h;
	public double s;
	public double r;
	
	public long current_g;
	public long current_h;
	
	public long old_g;
	public long old_h;
	
	int dia = 10;
	
	public ArrayList<long[]> Team_Red = new ArrayList<long[]>();
	public ArrayList<long[]> Team_Blue = new ArrayList<long[]>();
	
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
		
		current_g = g;
		current_h = h;
		initializeTeams();
	}
	
	public void initializeTeams() {
		for (int i = 0; i < g; i++ ) {
			long[] point = new long[2];
			point[0] = (long)(Math.random() * 500);
			point[1] = (long)(Math.random() * 500);
			Team_Red.add(point);
		}
		for(int j = 0; j < h; j++ ) {
			long[] point = new long[2];
			point[0] = (long)(Math.random() * 500 + 500);
			point[1] = (long)(Math.random() * 500);
			Team_Blue.add(point);	
		}
	}
	
	public void updateTeams() {
		for (long i = 0; i < old_g - current_g; i++ ) {
			Team_Red.remove((int) i);
		}
		for(long j = 0; j < old_h - current_h; j++ ) {	
			Team_Blue.remove((int) j);
		}
	}
	
	//set this panel's preferred size for auto-sizing the container JFrame
	public Dimension getPreferredSize() {
		return new Dimension(_UI_Constants.Window_Width, _UI_Constants.Window_Height);
	}
	
	//drawing operations should be done in this method
	@Override protected void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		

		old_g = current_g;
		old_h = current_h;
		
		current_g = (long) Calculations.gCurrent(g, h, r, s, time);
		current_h = (long) Calculations.hCurrent(g, h, r, s, time);
		
		updateTeams();
		
		// draw teams
		graph.setColor(Color.RED);
		for (int i = 0; i < current_g - 1; i++ ) {
			graph.fillOval((int)(Team_Red.get(i))[0], (int)(Team_Red.get(i))[1], dia , dia);
		}
		graph.setColor(Color.BLUE);	
		for (int j = 0; j < current_h - 1; j++ ) {
			System.out.println(current_h);
			graph.fillOval((int)(Team_Blue.get(j))[0], (int)(Team_Blue.get(j))[1], dia , dia);
		}
		time = t.GetTimeInSeconds() / 10;
		
		if(current_g < 1) {
			t.stopThread();
			SimulationMain.hWon(current_h);
			
		}else if(current_h < 1) {
			t.stopThread();
			SimulationMain.gWon(current_g);
			
		}
	}
}

