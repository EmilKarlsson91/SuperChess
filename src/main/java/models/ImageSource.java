package models;

public enum ImageSource {
	KING_BLACK("/KING_BLACK.png"),
	KING_WHITE("/KING_WHITE.png"),
	QUEEN_BLACK("/QUEEN_BLACK.png"),
	QUEEN_WHITE("/QUEEN_WHITE.png"),
	BISHOP_BLACK("/BISHOP_BLACK.png"),
	BISHOP_WHITE("/BISHOP_WHITE.png"),
	KNIGHT_BLACK("/KNIGHT_BLACK.png"),
	KNIGHT_WHITE("/KNIGHT_WHITE.png"),
	ROOK_BLACK("/ROOK_BLACK.png"),
	ROOK_WHITE("/ROOK_WHITE.png"),
	PAWN_BLACK("/PAWN_BLACK.png"),
	PAWN_WHITE("/PAWN_WHITE.png");
		
	private final String imgSource;
	private ImageSource(String imgSource) {
		this.imgSource = imgSource;
	}
		
	public String getImgSource() {
		return this.imgSource;
	}
}

