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
	private double buffertime;
	
	public long g;
	public long h;
	public double s;
	public double r;
	public double l;
	
	public double current_g;
	public double current_h;
	
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
		this.l = Calculations.lValue(g, h, r, s);
		
		current_g = g;
		current_h = h;
		initializeTeams();
		double temp;
		if(l != 0.0) {
			temp = Calculations.tZero(g, h, r, s);
		}else {
			temp = Calculations.terminationValue(g, h, r, s);
		}
		
		if (temp < 0.09) {
			buffertime = 1000;
		} else if (temp < 0.11) {
			buffertime = 200;
		} else if (temp < 0.2) {
			buffertime = 100;
		} else if (temp < 2) {
			buffertime = 10;
		} else {
			buffertime = 5;
		}
		t.start();
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
		if (Team_Red.size() - current_g > 0) {
			for (long i = 0; i < Team_Red.size() - current_g; i++ ) {
				Team_Red.remove((int) i);
			}
		}
		if (Team_Blue.size() - current_h > 0) {
			System.out.println();
			for(long j = 0; j < Team_Blue.size() - current_h; j++ ) {	
				Team_Blue.remove((int) j);
			}
		}
	}
	
	//set this panel's preferred size for auto-sizing the container JFrame
	public Dimension getPreferredSize() {
		return new Dimension(_UI_Constants.Window_Width, _UI_Constants.Window_Height);
	}
	
	//drawing operations should be done in this method
	@Override protected void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		// draw teams
		graph.setColor(Color.RED);
		for (int i = 0; i < Team_Red.size(); i++ ) {
			graph.fillOval((int)(Team_Red.get(i))[0], (int)(Team_Red.get(i))[1], dia , dia);
		}
		graph.setColor(Color.BLUE);	
		for (int j = 0; j < Team_Blue.size(); j++ ) {
			graph.fillOval((int)(Team_Blue.get(j))[0], (int)(Team_Blue.get(j))[1], dia , dia);
		}
		time = t.GetTimeInSeconds() / buffertime;
		current_g = Calculations.gCurrent(g, h, r, s, time);
		current_h = Calculations.hCurrent(g, h, r, s, time);
		System.out.println(Team_Red.size());
		System.out.println(Team_Blue.size());
		updateTeams();
		if(l != 0.0) {
			if(current_g < 1 || Team_Red.size() == 0) {
				t.stopThread();
				SimulationMain.hWon((long)current_h, Calculations.tZero(g, h, r, s));
			}else if(current_h < 1 || Team_Red.size() == 0) {
				t.stopThread();
				SimulationMain.gWon((long)current_g, Calculations.tZero(g, h, r, s));
			}
		}else {
			if(g < h && current_g < 0.5) {
				t.stopThread();
				SimulationMain.hWon(1, Calculations.terminationValue(g, h, r, s));
			}else if(h < g && current_h < 0.5) {
				t.stopThread();
				SimulationMain.gWon(1, Calculations.terminationValue(g, h, r, s));
			}else if (current_g < 0.5) {
				t.stopThread();
				SimulationMain.draw(Calculations.terminationValue(g, h, r, s));
			}
		}
	}
}

