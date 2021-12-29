package chess;

import boardgame.Position;
import boardgame.Tabuleiro;
import chess.pecas.Rei;
import chess.pecas.Torre;

public class ChessMatch {

	private Tabuleiro tabuleiro;

	public ChessMatch() {
		tabuleiro = new Tabuleiro(8, 8);
		initialSetup();
	}

	public ChessPeca[][]getPecas(){
		ChessPeca[][] mat = new ChessPeca[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for(int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (ChessPeca) tabuleiro.peca(i,j);
			}
		}
		return mat;
	}
	
	private void initialSetup() {
		tabuleiro.lugarPeca(new Torre(tabuleiro, Color.WHITE), new Position(2, 1));
		tabuleiro.lugarPeca(new Rei(tabuleiro, Color.BLACK), new Position(0, 4));
		tabuleiro.lugarPeca(new Rei(tabuleiro, Color.WHITE), new Position(7, 4));
	}
}
