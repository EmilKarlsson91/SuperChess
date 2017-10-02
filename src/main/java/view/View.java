package view;

import java.util.ArrayList;
import java.util.List;

import controllers.Controller;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import models.TilePressedEvent;
import models.TilePressedListener;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

@SuppressWarnings("restriction")
public class View extends Application {

	private static View view = null;
	
	public static View getInstance() {
		if(view == null) {
			view = new View();
		}
		return view;
	}
	
	private BorderPane pane;
	private Scene scene;
	private GameBoardGrid gameBoardGrid = GameBoardGrid.getInstance();

	public void startView() {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		pane = new BorderPane();
		pane.setMinSize(450, 450);
		pane.setPrefSize(700, 700);
		pane.setMaxSize(700, 700);
		pane.setStyle("-fx-background-color: #343640 ;");
		pane.getChildren().add(gameBoardGrid);

		scene = new Scene(pane, 800, 800);
		// scene.getStylesheets().add("monster.css");
		primaryStage.setScene(scene);
		primaryStage.show();

		/*
		 * pane.setAlignment(Pos.CENTER); pane.setHgap(10); pane.setVgap(10);
		 */
		// pane.setPadding(new Insets(25, 25, 25, 25));		
	}

	public void draw() {
	    Task<Void> task = new Task<Void>() {
	        @Override protected Void call() throws Exception {
	        	gameBoardGrid.draw();
	            return null;
	        }
	    };
		
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
	}
}
