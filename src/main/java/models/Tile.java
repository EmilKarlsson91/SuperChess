package models;

import models.TileColor;

/**
 * Model for tiles.
 * 
 * @author Emil Karlsson
 *
 */

public class Tile {

	private int index[];
	private TileColor color;
	private Piece piece = null;
	
	public Tile(TileColor color, int x, int y) {
		this.color = color;
		index = new int[2];
		index[0] = x;
		index[1] = y;
	}
	
	public TileColor getColor() {
		return color;
	}
	public int[] getIndex() {
		return index;
	}
	
	/**
	 * Method that changes the color to yellow if pressed.
	 */
	public void isPressed() {
		if(color == TileColor.BLACK) {
			color = TileColor.DARK_YELLOW;
		}
		else if(color == TileColor.WHITE) {
			color = TileColor.LIGHT_YELLOW;
		}
	}
	
	/**
	 * Method that changes the color to green if movement is possible.
	 */
	public void movePossible() {
		if(color == TileColor.BLACK) {
			color = TileColor.DARK_GREEN;
		}
		else {
			color = TileColor.LIGHT_GREEN;
		}
	}
	
	/**
	 * Method that decides if movement is possible on pressing a tile.
	 */
	public boolean isMovePossible() {
		if(color == TileColor.DARK_GREEN || color == TileColor.LIGHT_GREEN) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Method that changes the color to red if movement is not possible.
	 */
	public void moveNotPossible() {
		if(color == TileColor.BLACK) {
			color = TileColor.DARK_RED;
		}
		else {
			color = TileColor.LIGHT_RED;
		}
	}
	
	/**
	 * Method that returns the tiles color to normal.
	 */
	public void setNormalColor() {
		if(color == TileColor.DARK_YELLOW || color == TileColor.DARK_GREEN || color == TileColor.DARK_RED) {
			color = TileColor.BLACK;
		}
		else if (color == TileColor.LIGHT_YELLOW || color == TileColor.LIGHT_GREEN || color == TileColor.LIGHT_RED) {
			color = TileColor.WHITE;
		}
	}
	
	/**
	 * Method that sets a piece to the tile.
	 * 
	 * @param piece
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	public Piece getPiece() {
		return this.piece;
	}
	public void remvoePiece() {
		this.piece = null;
	}
	public boolean isOccupied() {
		if(this.piece == null) {
			return false;
		}
		else {
			return true;
		}
	}
}
