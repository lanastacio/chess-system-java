package chess;

import boardgame.Peca;
import boardgame.Position;
import boardgame.Tabuleiro;

public abstract class ChessPeca extends Peca {

	private Color color;
	private int moveCount;

	public ChessPeca(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPeca p = (ChessPeca)getTabuleiro().peca(position);
		return p != null && p.getColor() != color;
	}

}
