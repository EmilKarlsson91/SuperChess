package models;

import models.ImageSource;
import models.Team;
import models.TileState;

/**
 * Model for the piece pawn.
 * 
 * @author Emil Karlsson
 *
 */

public class Pawn extends Piece {

	private TileState[][] tileState;

	private static int[] enPassantMovement = null;

	public Pawn(Team team) {
		super(team);
		if (team == Team.TEAM_WHITE) {
			this.setImgSource(ImageSource.PAWN_WHITE.getImgSource());

		} else {
			this.setImgSource(ImageSource.PAWN_BLACK.getImgSource());
		}
		checkingForKing = false;
	}

	@Override
	public TileState[][] movementPattern(Tile[][] tiles, int x, int y) {
		tileState = new TileState[8][8];
		if (team == Team.TEAM_WHITE) {
			checkPossibleTiles(tiles, x, y - 1);
		} else {
			checkPossibleTiles(tiles, x, y + 1);
		}
		return tileState;
	}

	private void addPossibleTile(Tile[][] tiles, int standardX, int x, int y) {
		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
			if (tiles[x][y].isOccupied()) {
				if (tiles[x][y].getPiece().getTeam() == this.team) {
					if (standardX == x) {
						tileState[x][y] = TileState.NOT_POSSIBLE;
					} else if (standardX != x && checkingForKing) {
						tileState[x][y] = TileState.POSSIBLE;
					}
				} else {
					if (standardX != x) {
						tileState[x][y] = TileState.POSSIBLE;
					} else {
						tileState[x][y] = TileState.NOT_POSSIBLE;
					}
				}
			} else {
				if (standardX == x) {
					tileState[x][y] = TileState.POSSIBLE;
				} else if (checkingForKing) {
					tileState[x][y] = TileState.POSSIBLE;
				} else if (enPassantMovement != null) {
					if (enPassantMovement[0] == x && enPassantMovement[1] == y) {
						tileState[x][y] = TileState.POSSIBLE;
					}
				}
			}
		}
	}

	private void checkPossibleTiles(Tile[][] tiles, int x, int y) {
		if (!checkingForKing) {
			addPossibleTile(tiles, x, x, y);
			if (team == Team.TEAM_WHITE) {
				if (y + 1 == 6) {
					if (!tiles[x][y].isOccupied()) {
						addPossibleTile(tiles, x, x, y - 1);
					}
				}
			} else {
				if (y - 1 == 1) {
					if (!tiles[x][y].isOccupied()) {
						addPossibleTile(tiles, x, x, y + 1);
					}
				}
			}
		}
		addPossibleTile(tiles, x, x - 1, y);
		addPossibleTile(tiles, x, x + 1, y);
	}

	/**
	 * Method that registers if a move have been done that enables the possibility
	 * for the opponent to make an "en passant" move.
	 * 
	 * @param x
	 * @param y
	 */
	public void setEnPassantMovement(int x, int y) {
		enPassantMovement = new int[2];
		enPassantMovement[0] = x;
		enPassantMovement[1] = y;
	}

	/**
	 * Method that removes the possibility to make an "en passant" movement.
	 */
	public void removeEnPassantMovement() {
		enPassantMovement = null;
	}

	public int[] getEnpassantMovement() {
		return enPassantMovement;
	}
}
