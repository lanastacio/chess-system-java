package chess.pecas;

import boardgame.Tabuleiro;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPeca;
import chess.Color;

public class Peao extends ChessPeca {

	private ChessMatch chessMatch;

	public Peao(Tabuleiro tabuleiro, Color color, ChessMatch chessMatch) {
		super(tabuleiro, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Position p = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			p.setValues(position.getLinha() - 1, position.getColuna());
			if (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(position.getLinha() - 2, position.getColuna());
			Position p2 = new Position(position.getLinha() - 1, position.getColuna());
			if (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)
					&& getTabuleiro().positionExists(p2) && !getTabuleiro().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(position.getLinha() - 1, position.getColuna() - 1);
			if (getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(position.getLinha() - 1, position.getColuna() + 1);
			if (getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// #specialmove en passant white
			if (position.getLinha() == 3) {
				Position left = new Position(position.getLinha(), position.getColuna() - 1);
				if (getTabuleiro().positionExists(left) && isThereOpponentPiece(left)
						&& getTabuleiro().peca(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getLinha() - 1][left.getColuna()] = true;
				}
				Position right = new Position(position.getLinha(), position.getColuna() + 1);
				if (getTabuleiro().positionExists(right) && isThereOpponentPiece(right)
						&& getTabuleiro().peca(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getLinha() - 1][right.getColuna()] = true;
				}
			}
		} else {
			p.setValues(position.getLinha() + 1, position.getColuna());
			if (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(position.getLinha() + 2, position.getColuna());
			Position p2 = new Position(position.getLinha() + 1, position.getColuna());
			if (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)
					&& getTabuleiro().positionExists(p2) && !getTabuleiro().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(position.getLinha() + 1, position.getColuna() - 1);
			if (getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(position.getLinha() + 1, position.getColuna() + 1);
			if (getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// #specialmove en passant black
			if (position.getLinha() == 4) {
				Position left = new Position(position.getLinha(), position.getColuna() - 1);
				if (getTabuleiro().positionExists(left) && isThereOpponentPiece(left)
						&& getTabuleiro().peca(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getLinha() + 1][left.getColuna()] = true;
				}
				Position right = new Position(position.getLinha(), position.getColuna() + 1);
				if (getTabuleiro().positionExists(right) && isThereOpponentPiece(right)
						&& getTabuleiro().peca(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getLinha() + 1][right.getColuna()] = true;
				}
			}
		}
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}