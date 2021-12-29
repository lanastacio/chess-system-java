package chess.pecas;

import boardgame.Tabuleiro;
import chess.ChessPeca;
import chess.Color;

public class Torre extends ChessPeca {

	public Torre(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);

	}

	@Override
	public String toString() {
		return "T";
	}
}
