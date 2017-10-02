package models;

import models.TileColor;
import models.Team;
import models.TileState;
/**
 * Model for the game board
 * 
 * @author Emil Karlsson
 *
 */
public class GameBoard {

	private static GameBoard gameBoard = null;

	private ITeamFactory whitePieceFactory = WhitePieceFactory.getInstance();
	private ITeamFactory blackPieceFactory = BlackPieceFactory.getInstance();
	
	public static GameBoard getInstance() {
		if (gameBoard == null) {
			gameBoard = new GameBoard();
		}
		return gameBoard;
	}

	private Tile[][] tileList;

	public GameBoard() {
		tileList = new Tile[8][8];
		generateTiles();
		placePieces();
	}

	/**
	 * Method that generates tiles with different colors.
	 */
	public void generateTiles() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 != 0) {
					if (j % 2 == 0) {
						tileList[i][j] = (addTile(TileColor.BLACK, i, j));
					} else {
						tileList[i][j] = (addTile(TileColor.WHITE, i, j));
					}
				} else {
					if (j % 2 != 0) {
						tileList[i][j] = (addTile(TileColor.BLACK, i, j));
					} else {
						tileList[i][j] = (addTile(TileColor.WHITE, i, j));
					}
				}
			}
		}
	}

	private void placePieces() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (j == 0) {
					placeSpecialPieces(i, j, Team.TEAM_BLACK);
				} else if (j == 1) {
					tileList[i][j].setPiece(blackPieceFactory.createPawn());
				}
				if (j == 6) {
					tileList[i][j].setPiece(whitePieceFactory.createPawn());
				} else if (j == 7) {
					placeSpecialPieces(i, j, Team.TEAM_WHITE);
				}
			}
		}
	}

	private void placeSpecialPieces(int i, int j, Team team) {

		ITeamFactory tempFactory;
		if(team == Team.TEAM_WHITE) {
			tempFactory = whitePieceFactory;
		}
		else {
			tempFactory = blackPieceFactory;
		}
		if (i == 0 || i == 7) {
			tileList[i][j].setPiece(tempFactory.createRook());
		} else if (i == 1 || i == 6) {
			tileList[i][j].setPiece(tempFactory.createKnight());
		} else if (i == 2 || i == 5) {
			tileList[i][j].setPiece(tempFactory.createBishop());
		} else if (i == 3) {
			tileList[i][j].setPiece(tempFactory.createQueen());
		} else if (i == 4) {
			tileList[i][j].setPiece(tempFactory.createKing());
		}
	}

	/**
	 * Method that checks if a certain tile is click able
	 * 
	 * @param tile
	 */
	public void checkGrid(Tile tile) {
		TileState[][] tileState = tile.getPiece().movementPattern(tileList, tile.getIndex()[0], tile.getIndex()[1]);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tileState[i][j] == TileState.POSSIBLE) {
					this.tileList[i][j].movePossible();
				} else if (tileState[i][j] == TileState.NOT_POSSIBLE) {
					this.tileList[i][j].moveNotPossible();
				}
			}
		}
	}

	/**
	 * Method that resets all tiles.
	 */
	public void resetTiles() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.tileList[i][j].setNormalColor();
			}
		}
	}

	private Tile addTile(TileColor color, int i, int j) {
		return new Tile(color, i, j);
	}

	/**
	 * Method that returns a two dimensional array of all tiles.
	 * 
	 * @return tileList
	 */
	public Tile[][] getTiles() {
		return tileList;
	}
}
