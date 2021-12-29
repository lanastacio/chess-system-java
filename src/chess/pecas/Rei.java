package chess.pecas;

import boardgame.Tabuleiro;
import chess.ChessPeca;
import chess.Color;

public class Rei extends ChessPeca {

	public Rei(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);

	}

	@Override
	public String toString() {
		return "K";
	}
}
