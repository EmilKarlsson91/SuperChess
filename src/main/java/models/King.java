package models;

import models.ImageSource;
import models.Team;
import models.TileState;

/**
 * Model for the piece king.
 * 
 * @author Emil Karlsson
 *
 */

public class King extends Piece {

	private TileState[][] tileState;

	private static Team currentKing = Team.TEAM_WHITE;

	private boolean haveMoved = false;

	public King(Team team) {
		super(team);
		if (team == Team.TEAM_WHITE) {
			this.setImgSource(ImageSource.KING_WHITE.getImgSource());

		} else {
			this.setImgSource(ImageSource.KING_BLACK.getImgSource());
		}
	}

	@Override
	public TileState[][] movementPattern(Tile[][] tiles, int x, int y) {
		tileState = new TileState[8][8];
		addPossibleTiles(tiles, x, y);

		return tileState;
	}

	private void addPossibleTile(Tile[][] tiles, int x, int y) {
		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
			if (tiles[x][y].isOccupied()) {
				if (tiles[x][y].getPiece().getTeam() == this.team) {
					tileState[x][y] = TileState.NOT_POSSIBLE;
				} else {

					if (team == currentKing) {
						if (checkIfTileIsAttacked(tiles, x, y)) {
							tileState[x][y] = TileState.POSSIBLE;
						} else {
								tileState[x][y] = TileState.NOT_POSSIBLE;
						}
					} else {
						tileState[x][y] = TileState.POSSIBLE;
					}
				}
			} else {
				if (team == currentKing) {
					if (checkIfTileIsAttacked(tiles, x, y)) {
						tileState[x][y] = TileState.POSSIBLE;
					} else {
						if (checkingForKing) {
							tileState[x][y] = TileState.POSSIBLE;
						} else {
							tileState[x][y] = TileState.NOT_POSSIBLE;
						}
					}
				} else {
					tileState[x][y] = TileState.POSSIBLE;
				}
			}
		}
	}

	private void addPossibleTiles(Tile[][] tiles, int x, int y) {
		addPossibleTile(tiles, x - 1, y - 1);
		addPossibleTile(tiles, x - 1, y);
		addPossibleTile(tiles, x - 1, y + 1);
		addPossibleTile(tiles, x + 1, y - 1);
		addPossibleTile(tiles, x + 1, y);
		addPossibleTile(tiles, x + 1, y + 1);
		addPossibleTile(tiles, x, y - 1);
		addPossibleTile(tiles, x, y + 1);
		if (team == currentKing) {
			checkIfCastlingAvailable(tiles, x, y);
		}
	}

	private boolean checkIfTileIsAttacked(Tile[][] tiles, int x, int y) {
		boolean state = true;
		TileState[][] tempTileState;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tiles[i][j].isOccupied()) {
					if (tiles[i][j].getPiece().getTeam() != team) {
						tiles[i][j].getPiece().setCheckingForKing(true);
						tempTileState = tiles[i][j].getPiece().movementPattern(tiles, i, j);
						tiles[i][j].getPiece().setCheckingForKing(false);
						if (tempTileState[x][y] == TileState.POSSIBLE) {
							state = false;
						}
					}
				}
			}
		}
		return state;
	}

	/**
	 * Method that checks if castling is available for the current king.
	 * 
	 * @param tiles
	 * @param x
	 * @param y
	 */
	public void checkIfCastlingAvailable(Tile[][] tiles, int x, int y) {
		if (!haveMoved) {
			if (team == Team.TEAM_WHITE) {
				if (tiles[0][7].getPiece() instanceof Rook) {
					Rook rook = (Rook) tiles[0][7].getPiece();
					if (!rook.getIfMoved()) {
						boolean castling = true;
						for (int i = x - 1; i > 0; i--) {
							if (tiles[i][y].isOccupied()) {
								castling = false;
								i = 0;
							}
						}
						if (castling) {
							if (checkIfTileIsAttacked(tiles, x - 1, y)) {
								if (checkIfTileIsAttacked(tiles, x - 2, y)) {
									tileState[x - 2][y] = TileState.POSSIBLE;
								}
							}
						}
					}
				}
				if (tiles[7][7].getPiece() instanceof Rook) {
					Rook rook = (Rook) tiles[0][7].getPiece();
					if (!rook.getIfMoved()) {
						boolean castling = true;
						for (int i = x + 1; i < 7; i++) {
							if (tiles[i][y].isOccupied()) {
								castling = false;
								i = 7;
							}
						}
						if (castling) {
							if (checkIfTileIsAttacked(tiles, x + 1, y)) {
								if (checkIfTileIsAttacked(tiles, x + 2, y)) {
									tileState[x + 2][y] = TileState.POSSIBLE;
								}
							}
						}
					}
				}
			} else if (team == Team.TEAM_BLACK) {
				if (tiles[0][0].getPiece() instanceof Rook) {
					Rook rook = (Rook) tiles[0][0].getPiece();
					if (!rook.getIfMoved()) {
						boolean castling = true;
						for (int i = x - 1; i > 0; i--) {
							if (tiles[i][y].isOccupied()) {
								castling = false;
								i = 0;
							}
						}
						if (castling) {
							if (checkIfTileIsAttacked(tiles, x - 1, y)) {
								if (checkIfTileIsAttacked(tiles, x - 2, y)) {
									tileState[x - 2][y] = TileState.POSSIBLE;
								}
							}
						}
					}
				}
				if (tiles[7][0].getPiece() instanceof Rook) {
					Rook rook = (Rook) tiles[7][0].getPiece();
					if (!rook.getIfMoved()) {
						boolean castling = true;
						for (int i = x + 1; i < 7; i++) {
							if (tiles[i][y].isOccupied()) {
								castling = false;
								i = 7;
							}
						}
						if (castling) {
							if (checkIfTileIsAttacked(tiles, x + 1, y)) {
								if (checkIfTileIsAttacked(tiles, x + 2, y)) {
									tileState[x + 2][y] = TileState.POSSIBLE;
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Method that decides if the king making its turn is black or white.
	 */
	public void changeKing() {
		if (currentKing == Team.TEAM_WHITE) {
			currentKing = Team.TEAM_BLACK;
		} else if (currentKing == Team.TEAM_BLACK) {
			currentKing = Team.TEAM_WHITE;
		}
	}

	/**
	 * Method that register that the king have moved at least once.
	 */
	public void move() {
		this.haveMoved = true;
	}

	public boolean getIfMoved() {
		return this.haveMoved;
	}
}
