package org;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.Rectangle2D;
import java.net.URL;

import com.sun.glass.ui.Screen;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
 
public class Home extends Application {
 
	HomeContent homeContent;
	private Button button = new Button("Exit");
	
   @Override
   public void start(final Stage stage) throws Exception{
       
       homeContent = new HomeContent(stage);
       final WebView browser = new WebView();
       
       
       final WebEngine webEngine = browser.getEngine();
       browser.setPrefHeight(1500);
       webEngine.setJavaScriptEnabled(true);
 
       //webEngine.loadContent("https://www.google.com");
       //webEngine.load("http://www.google.com");
       //webEngine.loadContent("home.html");
       button.setOnAction(new EventHandler<ActionEvent>() {
 
           @Override
           public void handle(ActionEvent event) {
               // Call a JavaScript function of the current page
        	   stage.setFullScreen(false);
           }
       });
 
      /* VBox root = new VBox();
       root.setPadding(new Insets(0));
       root.setSpacing(0);
       root.getChildren().addAll(homeContent,button);*/
      
       Scene scene = new Scene(homeContent,1000,1000);
       stage.setTitle("Browser");
       stage.setScene(scene);
       stage.setWidth(450);
       stage.setHeight(300);
       stage.setFullScreen(true);
       stage.show();
       
   }
 
   public static void main(String[] args) {
	   try{
       launch(args);
	   }catch(Exception e){
		   System.out.println("(#)EnCen Exception : -"+e.getMessage());
	   }
   }
 
}

class HomeContent extends Region{

	WebView view = new WebView();
	WebEngine engine = view.getEngine();
	Button button = new Button("Exit");
	
	public HomeContent(Stage stage){
		int primaryScreenHeight = Screen.getMainScreen().getVisibleHeight();
		int primaryScreenWidth = Screen.getMainScreen().getVisibleWidth();
		button.setOnAction(new EventHandler<ActionEvent>() {
			 
	           @Override
	           public void handle(ActionEvent event) {
	               // Call a JavaScript function of the current page
	        	   stage.setFullScreen(false);
	           }
	       });
		view.setPrefHeight(primaryScreenHeight-10);
		view.setPrefWidth(primaryScreenWidth);
		URL url = getClass().getResource("home.html");
		engine.load(url.toExternalForm());
		getChildren().addAll(view,button);
		
	}
}




