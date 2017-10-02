package models;

import models.Team;
import models.TileState;

/**
 * Model for the abstract piece.
 * 
 * @author Emil Karlsson
 *
 */

public abstract class Piece {

	protected Team team;
	private String imgSource;
	
	protected boolean checkingForKing;

	
	public Piece(Team team) {
		this.team = team;
	}
	
	public Team getTeam() {
		return this.team;
	}
	
	public String getImgSource() {
		return this.imgSource;
	}
	
	public void setImgSource(String imgSource) {
		this.imgSource = imgSource;
	}
	
	/**
	 * Method that calculates the current piece movement pattern and possible moves.
	 * 
	 * @param tiles
	 * @param x
	 * @param y
	 * @return
	 */
	public abstract TileState[][] movementPattern(Tile[][] tiles, int x, int y);
	
	/**
	 * Method that sets if the current movement calculation is for a opponent kings movement or not.
	 * 
	 * @param checkingForKing
	 */
	public void setCheckingForKing(boolean checkingForKing) {
		this.checkingForKing = checkingForKing;
	}
}
