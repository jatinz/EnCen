package org;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.NavigationEntry;
import com.teamdev.jxbrowser.chromium.events.FailLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.FrameLoadEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.events.LoadEvent;
import com.teamdev.jxbrowser.chromium.events.NetError;
import com.teamdev.jxbrowser.chromium.events.ProvisionalLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.StartLoadingEvent;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class Home {

	private static JFrame frame = null;
	private static Browser browser = null;
	private static BrowserView view = null;
	private static JLabel labelDisplay;
	
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
		
		 
        browser.loadURL("http://www.google.com");
        labelDisplay = new JLabel("Loading Webpage ...");
        
        //Add to the Browser view-------------------
        
        view.add(labelDisplay,BorderLayout.NORTH);
        view.add(exitButton,BorderLayout.SOUTH);
        
        //-------------------------------------------
        
        int entryCount = browser.getNavigationEntryCount();
        int index = browser.getCurrentNavigationEntryIndex();
		NavigationEntry navigationEntry = browser.getNavigationEntryAtIndex(index);
        System.out.println("URL = " + navigationEntry.getURL());
        System.out.println("Original URL = " + navigationEntry.getOriginalURL());
        System.out.println("Title = " + index);
        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onStartLoadingFrame(StartLoadingEvent event) {
                if (event.isMainFrame()) {
                    System.out.println("Main frame has started loading");
                    view.remove(labelDisplay);
                }
            }

            @Override
            public void onProvisionalLoadingFrame(ProvisionalLoadingEvent event) {
                if (event.isMainFrame()) {
                    System.out.println("Provisional load was committed for a frame");
                    view.remove(labelDisplay);
                }
            }

            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                if (event.isMainFrame()) {
                    System.out.println("Main frame has finished loading");
                    view.remove(labelDisplay);
                }
            }

            @Override
            public void onFailLoadingFrame(FailLoadingEvent event) {
                NetError errorCode = event.getErrorCode();
                if (event.isMainFrame()) {
                    System.out.println("Main frame has failed loading: " + errorCode);
                    labelDisplay = new JLabel("Error-FailLoadingEvent : Encountered Error. Check Logs.");
                    //labelLoading.setBounds(100, 199, 200, 50);
                    view.add(labelDisplay,BorderLayout.NORTH);
                }
            }

            @Override
            public void onDocumentLoadedInFrame(FrameLoadEvent event) {
                System.out.println("Frame document is loaded.");
                view.remove(labelDisplay);
            }

            @Override
            public void onDocumentLoadedInMainFrame(LoadEvent event) {
                System.out.println("Main frame document is loaded.");
                view.remove(labelDisplay);
            }
        });



       
        frame.add(view,BorderLayout.CENTER);
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
