package UI;

import java.util.Timer;

import javax.swing.*;

import UI._UI_Constants;
import utils.ApplicationTime;
import utils.FrameUpdate;

public class SimulationMain {
	private static JFrame frame;
	private static JFrame menuframe;
	
	public static void main(String[] args) {
		ApplicationTime animThread = new ApplicationTime();
		animThread.start();
		CreateFrame(animThread);
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new FrameUpdate(frame), 100, _UI_Constants.TPF);
	}

	private static void CreateFrame(ApplicationTime thread) {
		
		//Create a new frame
		menuframe = new JFrame("Simulation Lanchester Modell");
		menuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame = new JFrame("Simulation Lanchester Modell");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Add a JPanel as the new drawing surface
		JPanel Menu = new JPanel();
		JLabel gLabel = new JLabel("G:");
		JTextField g_input = new JTextField("1",10);
		Menu.add(gLabel);
		Menu.add(g_input);
		
		JLabel hLabel = new JLabel("H:");
		JTextField h_input = new JTextField("1",10);
		Menu.add(hLabel);
		Menu.add(h_input);
		
		JLabel sLabel = new JLabel("s:");
		JTextField s_input = new JTextField("1",10);
		Menu.add(sLabel);
		Menu.add(s_input);
		
		JLabel rLabel = new JLabel("r:");
		JTextField r_input = new JTextField("1",10);
		Menu.add(rLabel);
		Menu.add(r_input);
		
		JButton button = new JButton("Starte Simulation");
		
		Menu.add(button);
		
		menuframe.add(Menu);
		menuframe.pack();
		menuframe.setVisible(true);
		
		button.addActionListener(new java.awt.event.ActionListener() {
			long g = Long.parseLong(g_input.getText());
			long h = Long.parseLong(h_input.getText());
			double s = Double.parseDouble(s_input.getText());
			double r = Double.parseDouble(r_input.getText());
			
            // Beim Dr�cken des Men�punktes wird actionPerformed aufgerufen
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	menuframe.setVisible(false);
            	JPanel panel = new WindowContent(thread, g, h, s, r);
            	frame.add(panel);
            	frame.pack();
            	frame.setVisible(true);
        		ApplicationTime animThread = new ApplicationTime();
        		CreateFrame(animThread);
        		Timer timer = new Timer();
        		timer.scheduleAtFixedRate(new FrameUpdate(frame), 100, _UI_Constants.TPF);
            }
        });
	}
}