package chess;

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
	
	private void placeNewPiece(char coluna, int linha, ChessPeca peca) {
		tabuleiro.lugarPeca(peca, new ChessPosition(coluna, linha).toPosition());
	}
	
	private void initialSetup() {
		placeNewPiece('b', 6, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('e', 8, new Rei(tabuleiro, Color.BLACK));
		placeNewPiece('e', 1, new Rei(tabuleiro, Color.WHITE));
	}
}
