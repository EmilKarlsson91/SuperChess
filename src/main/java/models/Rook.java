package models;

import models.ImageSource;
import models.Team;
import models.TileState;

/**
 * Model for the piece rook.
 * 
 * @author Emil Karlsson
 *
 */

public class Rook extends Piece {

	private TileState[][] tileState;

	private boolean haveMoved = false;

	public Rook(Team team) {
		super(team);
		if (team == Team.TEAM_WHITE) {
			this.setImgSource(ImageSource.ROOK_WHITE.getImgSource());

		} else {
			this.setImgSource(ImageSource.ROOK_BLACK.getImgSource());
		}
	}

	@Override
	public TileState[][] movementPattern(Tile[][] tiles, int x, int y) {
		tileState = new TileState[8][8];
		calculateLinearMovement(tiles, x, y);
		return tileState;
	}

	private void calculateLinearMovement(Tile[][] tiles, int x, int y) {
		for (int i = x - 1; i >= 0; i--) {
			if (tiles[i][y].isOccupied()) {
				if (tiles[i][y].getPiece().getTeam() == this.team) {
					tileState[i][y] = TileState.NOT_POSSIBLE;
					if (checkingForKing) {
						tileState[i][y] = TileState.POSSIBLE;
					} else {
						tileState[i][y] = TileState.NOT_POSSIBLE;
					}
					i = 0;
				} else {
					tileState[i][y] = TileState.POSSIBLE;
					i = 0;
				}
			} else {
				tileState[i][y] = TileState.POSSIBLE;
			}
		}
		for (int i = x + 1; i < 8; i++) {
			if (tiles[i][y].isOccupied()) {
				if (tiles[i][y].getPiece().getTeam() == this.team) {
					if (checkingForKing) {
						tileState[i][y] = TileState.POSSIBLE;
					} else {
						tileState[i][y] = TileState.NOT_POSSIBLE;
					}
					i = 8;
				} else {
					tileState[i][y] = TileState.POSSIBLE;
					i = 8;
				}
			} else {
				tileState[i][y] = TileState.POSSIBLE;
			}
		}
		for (int i = y - 1; i >= 0; i--) {
			if (tiles[x][i].isOccupied()) {
				if (tiles[x][i].getPiece().getTeam() == this.team) {
					if (checkingForKing) {
						tileState[x][i] = TileState.POSSIBLE;
					} else {
						tileState[x][i] = TileState.NOT_POSSIBLE;
					}
					i = 0;
				} else {
					tileState[x][i] = TileState.POSSIBLE;
					i = 0;
				}
			} else {
				tileState[x][i] = TileState.POSSIBLE;
			}
		}
		for (int i = y + 1; i < 8; i++) {
			if (tiles[x][i].isOccupied()) {
				if (tiles[x][i].getPiece().getTeam() == this.team) {
					if (checkingForKing) {
						tileState[x][i] = TileState.POSSIBLE;
					} else {
						tileState[x][i] = TileState.NOT_POSSIBLE;
					}
					i = 8;
				} else {
					tileState[x][i] = TileState.POSSIBLE;
					i = 8;
				}
			} else {
				tileState[x][i] = TileState.POSSIBLE;
			}
		}
	}

	/**
	 * Method that register that the rook have moved at least once.
	 */
	public void move() {
		this.haveMoved = true;
	}

	public boolean getIfMoved() {
		return this.haveMoved;
	}
}
