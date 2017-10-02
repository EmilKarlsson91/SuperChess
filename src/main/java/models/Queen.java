package models;

import models.ImageSource;
import models.Team;
import models.TileState;

/**
 * Model for the piece queen.
 * 
 * @author Emil Karlsson
 *
 */

public class Queen extends Piece {

	private TileState[][] tileState;

	public Queen(Team team) {
		super(team);
		if (team == Team.TEAM_WHITE) {
			this.setImgSource(ImageSource.QUEEN_WHITE.getImgSource());

		} else {
			this.setImgSource(ImageSource.QUEEN_BLACK.getImgSource());
		}
	}

	@Override
	public TileState[][] movementPattern(Tile[][] tiles, int x, int y) {
		tileState = new TileState[8][8];
		calculateLinearMovement(tiles, x, y);
		calculateDiagonalMovement(tiles, x, y);
		return tileState;
	}

	private void calculateLinearMovement(Tile[][] tiles, int x, int y) {
		for (int i = x - 1; i >= 0; i--) {
			if (tiles[i][y].isOccupied()) {
				if (tiles[i][y].getPiece().getTeam() == this.team) {
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

	private void calculateDiagonalMovement(Tile[][] tiles, int x, int y) {
		int tempX = x - 1;
		int tempY = y - 1;
		while (tempX >= 0 && tempY >= 0) {
			if (tiles[tempX][tempY].isOccupied()) {
				if (tiles[tempX][tempY].getPiece().getTeam() == this.team) {
					if (checkingForKing) {
						tileState[tempX][tempY] = TileState.POSSIBLE;
					} else {
						tileState[tempX][tempY] = TileState.NOT_POSSIBLE;
					}
					tempX = 0;
					tempY = 0;
				} else {
					tileState[tempX][tempY] = TileState.POSSIBLE;
					tempX = 0;
					tempY = 0;
				}
			} else {
				tileState[tempX][tempY] = TileState.POSSIBLE;
			}
			tempX--;
			tempY--;
		}

		tempX = x + 1;
		tempY = y - 1;
		while (tempX < 8 && tempY >= 0) {
			if (tiles[tempX][tempY].isOccupied()) {
				if (tiles[tempX][tempY].getPiece().getTeam() == this.team) {
					if (checkingForKing) {
						tileState[tempX][tempY] = TileState.POSSIBLE;
					} else {
						tileState[tempX][tempY] = TileState.NOT_POSSIBLE;
					}
					tempX = 8;
					tempY = 0;
				} else {
					tileState[tempX][tempY] = TileState.POSSIBLE;
					tempX = 8;
					tempY = 0;
				}
			} else {
				tileState[tempX][tempY] = TileState.POSSIBLE;
			}
			tempX++;
			tempY--;
		}

		tempX = x - 1;
		tempY = y + 1;
		while (tempX >= 0 && tempY < 8) {
			if (tiles[tempX][tempY].isOccupied()) {
				if (tiles[tempX][tempY].getPiece().getTeam() == this.team) {
					if (checkingForKing) {
						tileState[tempX][tempY] = TileState.POSSIBLE;
					} else {
						tileState[tempX][tempY] = TileState.NOT_POSSIBLE;
					}
					tempX = 0;
					tempY = 8;
				} else {
					tileState[tempX][tempY] = TileState.POSSIBLE;
					tempX = 0;
					tempY = 8;
				}
			} else {
				tileState[tempX][tempY] = TileState.POSSIBLE;
			}
			tempX--;
			tempY++;
		}

		tempX = x + 1;
		tempY = y + 1;
		while (tempX < 8 && tempY < 8) {
			if (tiles[tempX][tempY].isOccupied()) {
				if (tiles[tempX][tempY].getPiece().getTeam() == this.team) {
					if (checkingForKing) {
						tileState[tempX][tempY] = TileState.POSSIBLE;
					} else {
						tileState[tempX][tempY] = TileState.NOT_POSSIBLE;
					}
					tempX = 8;
					tempY = 8;
				} else {
					tileState[tempX][tempY] = TileState.POSSIBLE;
					tempX = 8;
					tempY = 8;
				}
			} else {
				tileState[tempX][tempY] = TileState.POSSIBLE;
			}
			tempX++;
			tempY++;
		}
	}
}
