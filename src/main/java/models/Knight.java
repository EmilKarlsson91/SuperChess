package models;

import models.ImageSource;
import models.Team;
import models.TileState;

/**
 * Model for the piece knight.
 * 
 * @author Emil Karlsson
 *
 */

public class Knight extends Piece {

	private TileState[][] tileState;
	public Knight(Team team) {
		super(team);
		if (team == Team.TEAM_WHITE) {
			this.setImgSource(ImageSource.KNIGHT_WHITE.getImgSource());

		} else {
			this.setImgSource(ImageSource.KNIGHT_BLACK.getImgSource());
		}
	}

	@Override
	public TileState[][] movementPattern(Tile[][] tiles, int x, int y) {
		tileState = new TileState[8][8];	
		addPossibleTiles(tiles, x, y);
		
		return tileState;
	}
	
	private void addPossibleTile(Tile[][] tiles, int x, int y) {
		if(x >= 0 && x < 8 && y >= 0 && y < 8) {
			if(tiles[x][y].isOccupied()) {
				if(tiles[x][y].getPiece().getTeam() == this.team) {
					if(checkingForKing) {
						tileState[x][y] = TileState.POSSIBLE;
					}
					else {
						tileState[x][y] = TileState.NOT_POSSIBLE;
					}				}
				else {
					tileState[x][y] = TileState.POSSIBLE;
				}
			}
			else {
				tileState[x][y] = TileState.POSSIBLE;
			}
		}
	}
	private void addPossibleTiles(Tile[][] tiles, int x, int y) {
		addPossibleTile(tiles, x-1, y+2);
		addPossibleTile(tiles, x+1, y+2);

		addPossibleTile(tiles, x-1, y-2);
		addPossibleTile(tiles, x+1, y-2);
		
		addPossibleTile(tiles, x+2, y-1);
		addPossibleTile(tiles, x+2, y+1);
		
		addPossibleTile(tiles, x-2, y-1);
		addPossibleTile(tiles, x-2, y+1);
	}
}
