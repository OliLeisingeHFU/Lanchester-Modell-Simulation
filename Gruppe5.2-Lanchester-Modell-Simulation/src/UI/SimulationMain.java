package UI;

import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import UI._UI_Constants;
import utils.ApplicationTime;
import utils.FrameUpdate;

public class SimulationMain {
	private static JFrame frame;
	
	public static void main(String[] args) {
		ApplicationTime animThread = new ApplicationTime();
		animThread.start();

		CreateFrame(animThread);
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new FrameUpdate(frame), 100, _UI_Constants.TPF);
	}

	private static void CreateFrame(ApplicationTime thread) {
		
		//Create a new frame
		frame = new JFrame("Mathematik und Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add a JPanel as the new drawing surface
		JPanel panel = new WindowContent(thread);
		frame.add(panel);
		frame.pack(); //adjusts size of the JFrame to fit the size of it's components
		frame.setVisible(true);
	}
}