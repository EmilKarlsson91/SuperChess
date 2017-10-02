package models;

public class BlackPieceFactory implements ITeamFactory {

	private static BlackPieceFactory blackPieceFactory = null;

	public static ITeamFactory getInstance() {
		if(blackPieceFactory == null) {
			blackPieceFactory = new BlackPieceFactory();
		}
		return blackPieceFactory;		
	}
	
	@Override
	public Piece createKing() {
		return new King(Team.TEAM_BLACK);
	}

	@Override
	public Piece createQueen() {
		return new Queen(Team.TEAM_BLACK);
	}

	@Override
	public Piece createBishop() {
		return new Bishop(Team.TEAM_BLACK);
	}

	@Override
	public Piece createKnight() {
		return new Knight(Team.TEAM_BLACK);
	}

	@Override
	public Piece createRook() {
		return new Rook(Team.TEAM_BLACK);
	}

	@Override
	public Piece createPawn() {
		return new Pawn(Team.TEAM_BLACK);
	}
}
