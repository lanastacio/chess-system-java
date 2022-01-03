package chess;

import boardgame.Peca;
import boardgame.Position;
import boardgame.Tabuleiro;

public abstract class ChessPeca extends Peca {

	private Color color;

	public ChessPeca(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPeca p = (ChessPeca)getTabuleiro().peca(position);
		return p != null && p.getColor() != color;
	}

}
