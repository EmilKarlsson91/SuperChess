package models;

/**
 * 
 * @author Emil Karlsson
 *
 */
public class TilePressedEvent {

	private int[] index;
	
	/**
	 * Event that sends the index of a tile pressed.
	 * 
	 * @param tile index
	 */
	public TilePressedEvent(int[] index) {
		this.index = index;
	}
	public int[] getPressedTile() {
		return index;
	}
}
