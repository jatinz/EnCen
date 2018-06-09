package org;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class Home {

	private static JFrame frame = null;
	private static Browser browser = null;
	private static BrowserView view = null;
	
	public static void main(String[] args) {
		GraphicsDevice device = GraphicsEnvironment
		        .getLocalGraphicsEnvironment().getScreenDevices()[0];
		// TODO Auto-generated method stubimport com.teamdev.jxbrowser.chromium.swing.BrowserView;
		browser = new Browser();
		view = new BrowserView(browser);
		
		frame = createFrame();
		device.setFullScreenWindow(frame);
		Window window = new Window(frame);
		
		 JButton exitButton = new JButton("Exit");
		    exitButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            device.setFullScreenWindow(null);
		            
		        }
		    });
		//view.add(exitButton,BorderLayout.SOUTH);
        frame.add(view,BorderLayout.CENTER);
        browser.loadURL("http://www.google.com");
       

	}

	private static JFrame createFrame() {
		frame = new JFrame("Demo");
		frame.setUndecorated(true);
		frame.setSize(700, 600);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		return frame;
		
	}


}
