package org;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
 
public class Home extends Application {
 
   // A HTML Content with a javascript function.
   @Override
   public void start(final Stage stage) {
 
	   GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
       Button button = new Button("Exit");
 
       final WebView browser = new WebView();
       final WebEngine webEngine = browser.getEngine();
       browser.setPrefHeight(1500);
 
       // Enable Javascript.
       webEngine.setJavaScriptEnabled(true);
 
       //webEngine.loadContent("https://www.google.com");
       webEngine.load("http://www.google.com");
       button.setOnAction(new EventHandler<ActionEvent>() {
 
           @Override
           public void handle(ActionEvent event) {
               // Call a JavaScript function of the current page
        	   stage.setFullScreen(false);
           }
       });
 
       VBox root = new VBox();
       root.setPadding(new Insets(0));
       root.setSpacing(0);
       root.getChildren().addAll(browser,button);
       
       Scene scene = new Scene(root);
       stage.setTitle("Browser");
       stage.setScene(scene);
       stage.setWidth(450);
       stage.setHeight(300);
       stage.setFullScreen(true);
       stage.show();
       
   }
 
   public static void main(String[] args) {
       launch(args);
   }
 
}