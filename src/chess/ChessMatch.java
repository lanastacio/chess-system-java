package chess;

import boardgame.Peca;
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

	public ChessPeca[][] getPecas() {
		ChessPeca[][] mat = new ChessPeca[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (ChessPeca) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}

	public ChessPeca performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Peca capturedPiece = makeMove(source, target);
		return (ChessPeca)capturedPiece;
	}
	
	public void validateSourcePosition(Position position) {
		if(!tabuleiro.thereIsAPiece(position)) {
			throw new ChessException("Não existe peça no lugar de origem");
		}
	}
	
	private Peca makeMove(Position source, Position target) {
		Peca p = tabuleiro.removePiece(source);
		Peca capturedPiece = tabuleiro.removePiece(target);
		tabuleiro.lugarPeca(p, target);
		return capturedPiece;
	}
	
	private void placeNewPiece(char coluna, int linha, ChessPeca peca) {
		tabuleiro.lugarPeca(peca, new ChessPosition(coluna, linha).toPosition());
	}

	private void initialSetup() {

		placeNewPiece('c', 1, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('c', 2, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('d', 2, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('e', 2, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('e', 1, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('d', 1, new Rei(tabuleiro, Color.WHITE));

		placeNewPiece('c', 7, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('c', 8, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('d', 7, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('e', 7, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('e', 8, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('d', 8, new Rei(tabuleiro, Color.BLACK));
	}
}
