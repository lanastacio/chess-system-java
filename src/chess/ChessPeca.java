package chess;

import boardgame.Peca;
import boardgame.Tabuleiro;

public class ChessPeca extends Peca {

	private Color color;

	public ChessPeca(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

}
