package models;

public class WhitePieceFactory implements ITeamFactory {

	private static WhitePieceFactory whitePieceFactory = null;

	public static ITeamFactory getInstance() {
		if(whitePieceFactory == null) {
			whitePieceFactory = new WhitePieceFactory();
		}
		return whitePieceFactory;		
	}
	
	@Override
	public Piece createKing() {
		return new King(Team.TEAM_WHITE);
	}

	@Override
	public Piece createQueen() {
		return new Queen(Team.TEAM_WHITE);
	}

	@Override
	public Piece createBishop() {
		return new Bishop(Team.TEAM_WHITE);
	}

	@Override
	public Piece createKnight() {
		return new Knight(Team.TEAM_WHITE);
	}

	@Override
	public Piece createRook() {
		return new Rook(Team.TEAM_WHITE);
	}

	@Override
	public Piece createPawn() {
		return new Pawn(Team.TEAM_WHITE);
	}
}
