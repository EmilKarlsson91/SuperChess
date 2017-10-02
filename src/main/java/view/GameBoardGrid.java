package view;

import models.GameBoard;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

@SuppressWarnings("restriction")
public class GameBoardGrid extends GridPane {
	
	private static GameBoardGrid gameBoardGrid = null;
	private static GameBoard gameBoard;
	
	private GameBoardGrid() {
		gameBoard = GameBoard.getInstance();
	}

	public static GameBoardGrid getInstance() {
		if (gameBoardGrid == null) {
			gameBoardGrid = new GameBoardGrid();
			generateTiles();
		}
		return gameBoardGrid;
	}

	/**
	 * Method that generates the visual representation of the game board
	 */
	public static void generateTiles() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				gameBoardGrid.add(new TilePane(gameBoard.getTiles()[i][j]), i, j);
			}
		}
	}	

	/**
	 * Method that 
	 */
	public void draw(){
		for(Node node : gameBoardGrid.getChildren()) {
			((TilePane) node).draw();
		}
	}
}
