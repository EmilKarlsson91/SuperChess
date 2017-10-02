package models;

public interface ITeamFactory {
	Piece createKing();
	Piece createQueen();
	Piece createBishop();
	Piece createKnight();
	Piece createRook();
	Piece createPawn();
}
