package models;

import models.Team;
import models.TileState;
import models.ImageSource;;

/**
 * Model for the piece bishop.
 * 
 * @author Emil Karlsson
 *
 */

public class Bishop extends Piece {

	private TileState[][] tileState;
	public Bishop(Team team) {
		super(team);
		if(team == Team.TEAM_WHITE) {
			this.setImgSource(ImageSource.BISHOP_WHITE.getImgSource());

		}
		else {
			this.setImgSource(ImageSource.BISHOP_BLACK.getImgSource());
		}
	}

	@Override
	public TileState[][] movementPattern(Tile[][] tiles, int x, int y) {
		tileState = new TileState[8][8];
		calculateDiagonalMovement(tiles, x, y);
		return tileState;		
	}
	private void calculateDiagonalMovement(Tile[][] tiles, int x, int y) {
		int tempX = x -1;
		int tempY = y -1;
		while (tempX >= 0 && tempY >= 0) {
			if (tiles[tempX][tempY].isOccupied()) {
				if (tiles[tempX][tempY].getPiece().getTeam() == this.team) {
					if(checkingForKing) {
						tileState[tempX][tempY] = TileState.POSSIBLE;
					}
					else {
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
		
		
		tempX = x +1;
		tempY = y -1;
		while (tempX < 8 && tempY >= 0) {
			if (tiles[tempX][tempY].isOccupied()) {
				if (tiles[tempX][tempY].getPiece().getTeam() == this.team) {
					if(checkingForKing) {
						tileState[tempX][tempY] = TileState.POSSIBLE;
					}
					else {
						tileState[tempX][tempY] = TileState.NOT_POSSIBLE;
					}					tempX = 8;
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
		
		tempX = x -1;
		tempY = y +1;
		while (tempX >= 0 && tempY < 8) {
			if (tiles[tempX][tempY].isOccupied()) {
				if (tiles[tempX][tempY].getPiece().getTeam() == this.team) {
					if(checkingForKing) {
						tileState[tempX][tempY] = TileState.POSSIBLE;
					}
					else {
						tileState[tempX][tempY] = TileState.NOT_POSSIBLE;
					}					tempX = 0;
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
		
		tempX = x +1;
		tempY = y +1;
		while (tempX < 8 && tempY < 8) {
			if (tiles[tempX][tempY].isOccupied()) {
				if (tiles[tempX][tempY].getPiece().getTeam() == this.team) {
					if(checkingForKing) {
						tileState[tempX][tempY] = TileState.POSSIBLE;
					}
					else {
						tileState[tempX][tempY] = TileState.NOT_POSSIBLE;
					}					tempX = 8;
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
