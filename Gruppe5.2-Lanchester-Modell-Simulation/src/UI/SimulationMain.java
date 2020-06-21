package UI;

import java.util.Timer;

import javax.swing.*;

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
		frame = new JFrame("Simulation Lanchester Modell");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add a JPanel as the new drawing surface
		JPanel Menu = new JPanel();
		JLabel gLabel = new JLabel("G:");
		JTextField g = new JTextField(1);
		Menu.add(gLabel);
		Menu.add(g);
		
		JLabel hLabel = new JLabel("H:");
		JTextField h = new JTextField(1);
		Menu.add(hLabel);
		Menu.add(h);
		
		JLabel sLabel = new JLabel("s:");
		JTextField s = new JTextField(1);
		Menu.add(sLabel);
		Menu.add(s);
		
		JLabel rLabel = new JLabel("r:");
		JTextField r = new JTextField(1);
		Menu.add(rLabel);
		Menu.add(r);
		
		JButton button = new JButton("Starte Simulation");
		Menu.add(button);
		
		frame.add(Menu);
		JPanel panel = new WindowContent(thread);
		frame.add(panel);
		frame.pack(); //adjusts size of the JFrame to fit the size of it's components
		frame.setVisible(true);
	}
}