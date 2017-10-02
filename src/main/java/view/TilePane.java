package view;

import java.util.ArrayList;
import java.util.List;

import controllers.Controller;
import javafx.scene.layout.Pane;
import models.TilePressedEvent;
import models.TilePressedListener;
import models.Tile;

@SuppressWarnings("restriction")
public class TilePane extends Pane{
	
	private Controller controller = Controller.getInstance();	
	private Tile tile;
	private Pane imgPane;
	
	public TilePane(Tile tile) {
		
		addTilePressedListener(controller);

		this.tile = tile;
		
		this.setPrefSize(100, 100);
		this.setMinSize(100, 100);
		this.setStyle("-fx-background-color: " + this.tile.getColor().getColor() + ";");

		imgPane = new Pane();
		if(tile.isOccupied()) {
			imgPane.setStyle("-fx-background-image: url('" + tile.getPiece().getImgSource() + "'); " +
			           "-fx-background-position: center center; " +
			           "-fx-background-repeat: stretch;");
			imgPane.setPrefSize(100, 100);
		}
		this.getChildren().add(imgPane);

		
		this.setOnMouseClicked(e -> {
			pressTile(tile.getIndex());
		});
	}
	
	//Draw function
	public void draw() {
		this.setStyle("-fx-background-color: " + this.tile.getColor().getColor() + ";");
			if(tile.isOccupied()) {
				imgPane.setStyle("-fx-background-image: url('" + tile.getPiece().getImgSource() + "'); " +
				           "-fx-background-position: center center; " +
				           "-fx-background-repeat: stretch;");
				imgPane.setPrefSize(100, 100);
			}
			else {
				imgPane.setStyle("-fx-background-image: null; " +
				           "-fx-background-position: center center; " +
				           "-fx-background-repeat: stretch;");
				imgPane.setPrefSize(100, 100);
			}
	}
	
	private List<TilePressedListener> tilePressedListeners = new ArrayList<>();

	public void addTilePressedListener(TilePressedListener tilePressedListener) {
		tilePressedListeners.add(tilePressedListener);
	}
	
	public void pressTile(int[] index) {
		for (TilePressedListener listener : tilePressedListeners) {
			//System.out.println("Pressing some tile");
			//System.out.println(tile.getColor().getColor());
			listener.tilePressed(new TilePressedEvent(index));
		}
	}
}
