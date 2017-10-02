package models;

public enum TileColor {
	GRAY("#454554"), 
	BLACK("#09090B"), 
	WHITE("#D5DCFF"), 
	LIGHT_YELLOW("#FFD22F"), 
	DARK_YELLOW("#AA8C1F"), 
	LIGHT_GREEN("#58FF57"), 
	DARK_GREEN("#2C802C"), 
	LIGHT_RED("#FF0C00"), 
	DARK_RED("#800600");
	private final String color;

	private TileColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return this.color;
	}
}
