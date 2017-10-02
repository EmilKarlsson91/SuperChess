package controllers;

import models.Team;
import models.BlackPieceFactory;
import models.GameBoard;
import models.ITeamFactory;
import models.King;
import models.Pawn;
import models.Queen;
import models.Rook;
import models.Tile;
import models.TilePressedEvent;
import models.TilePressedListener;
import models.WhitePieceFactory;
import view.View;

/**
 * 
 * @author Emil Karlsson
 *
 *Controller class that makes all method calls between models and view, it also handles all logic between models.
 *
 */

public class Controller implements TilePressedListener {

	private ITeamFactory whitePieceFactory = WhitePieceFactory.getInstance();
	private ITeamFactory blackPieceFactory = BlackPieceFactory.getInstance();
	
	private static Controller controller = null;
	private Tile prevTile = null;
	private Tile enPassantTile = null;
	private Team currentTeam;
	private static GameBoard gameBoard;
	private static View view;
	
	public Controller() {
		currentTeam = Team.TEAM_WHITE;
	}

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}
	
	public static void main(String[] args) {
		Controller.getInstance();
		gameBoard = GameBoard.getInstance();
		view = View.getInstance();
		view.startView();
	}

	@Override
	public void tilePressed(TilePressedEvent event) {
		if (gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].isOccupied()) {
			if (gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].getPiece()
					.getTeam() == currentTeam) {
				gameBoard.resetTiles();
				gameBoard.checkGrid(gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]]);
				gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].isPressed();
				prevTile = gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]];
			} else if (gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].getPiece()
					.getTeam() != currentTeam) {
				if (prevTile != null && prevTile.isOccupied()) {
					if (gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].isMovePossible()) {
						gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].remvoePiece();
						gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]]
								.setPiece(prevTile.getPiece());
						checkIfKing(event);
						checkIfRook(event);
						checkIfPawn(event);
						restorePrevTile();
						checkIfPromotionMove(event);
						changeTeam();
					}
				}
			}
		} else {
			if (prevTile != null) {
				if (gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].isMovePossible()) {
					gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]]
							.setPiece(prevTile.getPiece());
					checkIfKing(event);
					checkIfRook(event);
					checkIfPawn(event);
					restorePrevTile();
					checkIfPromotionMove(event);
					changeTeam();
				}
			}
		}
		view.draw();
	}

	/**
	 * 
	 */
	private void changeTeam() {
		King tempKing = (King) whitePieceFactory.createKing();
		if (currentTeam == Team.TEAM_WHITE) {
			currentTeam = Team.TEAM_BLACK;
		} else {
			currentTeam = Team.TEAM_WHITE;
		}
		tempKing.changeKing();
	}

	private void restorePrevTile() {
		gameBoard.resetTiles();
		prevTile.remvoePiece();
		prevTile = null;
	}

	private void checkIfPawn(TilePressedEvent event) {
		Pawn pawn;
		if(prevTile.getPiece().getTeam() == Team.TEAM_WHITE) {
			pawn = (Pawn) whitePieceFactory.createPawn();
		}
		else {
			pawn = (Pawn) blackPieceFactory.createPawn();
		}
		if (prevTile.getPiece() instanceof Pawn) {
			if (prevTile.getIndex()[1] - event.getPressedTile()[1] == 2) {
				pawn = (Pawn) gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].getPiece();
				pawn.setEnPassantMovement(event.getPressedTile()[0], event.getPressedTile()[1] + 1);
				enPassantTile = gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]];
			} else if (prevTile.getIndex()[1] - event.getPressedTile()[1] == -2) {
				pawn = (Pawn) gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].getPiece();
				pawn.setEnPassantMovement(event.getPressedTile()[0], event.getPressedTile()[1] - 1);
				enPassantTile = gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]];
			} else if (enPassantTile != null) {
				isEnPassantMove(event, pawn);
			}
		} else {
			pawn.removeEnPassantMovement();
			enPassantTile = null;
		}
	}
	
	private void checkIfPromotionMove(TilePressedEvent event) {
		if (gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].getPiece() instanceof Pawn) {
			if(event.getPressedTile()[1] == 0) {
				gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].remvoePiece();
				gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].setPiece(whitePieceFactory.createQueen());
			}
			else if(event.getPressedTile()[1] == 7) {
				gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].remvoePiece();
				gameBoard.getTiles()[event.getPressedTile()[0]][event.getPressedTile()[1]].setPiece(whitePieceFactory.createQueen());
			}
		}
	}

	private void isEnPassantMove(TilePressedEvent event, Pawn pawn) {
		if (event.getPressedTile()[0] == pawn.getEnpassantMovement()[0]
				&& event.getPressedTile()[1] == pawn.getEnpassantMovement()[1]) {
			enPassantTile.remvoePiece();
		}
	}

	private void isCastlingMove(TilePressedEvent event) {	
		if (prevTile.getIndex()[0] - event.getPressedTile()[0] == 2) {
			gameBoard.getTiles()[prevTile.getIndex()[0] - 1][event.getPressedTile()[1]].setPiece(gameBoard.getTiles()[00][event.getPressedTile()[1]].getPiece());
			gameBoard.getTiles()[0][event.getPressedTile()[1]].remvoePiece();

		} else if (prevTile.getIndex()[0] - event.getPressedTile()[0] == -2) {
			gameBoard.getTiles()[prevTile.getIndex()[0] + 1][event.getPressedTile()[1]].setPiece(gameBoard.getTiles()[7][event.getPressedTile()[1]].getPiece());
			gameBoard.getTiles()[7][event.getPressedTile()[1]].remvoePiece();
		}
	}

	private void checkIfKing(TilePressedEvent event) {
		if (prevTile.getPiece() instanceof King) {
			King king = (King) prevTile.getPiece();
			isCastlingMove(event);
			king.move();
		}
	}

	private void checkIfRook(TilePressedEvent event) {
		if (prevTile.getPiece() instanceof Rook) {
			Rook rook = (Rook) prevTile.getPiece();
			rook.move();
		}
	}
}
