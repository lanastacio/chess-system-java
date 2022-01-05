package chess.pecas;

import boardgame.Position;
import boardgame.Tabuleiro;
import chess.ChessMatch;
import chess.ChessPeca;
import chess.Color;

public class Rei extends ChessPeca {

	private ChessMatch chessMatch;

	public Rei(Tabuleiro tabuleiro, Color color, ChessMatch chessMatch) {
		super(tabuleiro, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPeca p = (ChessPeca) getTabuleiro().peca(position);
		return p == null || p.getColor() != getColor();
	}

	private boolean testRookCastling(Position position) {
		ChessPeca p = (ChessPeca) getTabuleiro().peca(position);
		return p != null && p instanceof Torre && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Position p = new Position(0, 0);

		// above
		p.setValues(position.getLinha() - 1, position.getColuna());
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// below
		p.setValues(position.getLinha() + 1, position.getColuna());
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// left
		p.setValues(position.getLinha(), position.getColuna() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// right
		p.setValues(position.getLinha(), position.getColuna() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// nw
		p.setValues(position.getLinha() - 1, position.getColuna() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// ne
		p.setValues(position.getLinha() - 1, position.getColuna() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// sw
		p.setValues(position.getLinha() + 1, position.getColuna() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// se
		p.setValues(position.getLinha() + 1, position.getColuna() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// #specialmove castling
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// #specialmove castling kingside rook
			Position posT1 = new Position(position.getLinha(), position.getColuna() + 3);
			if (testRookCastling(posT1)) {
				Position p1 = new Position(position.getLinha(), position.getColuna() + 1);
				Position p2 = new Position(position.getLinha(), position.getColuna() + 2);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					mat[position.getLinha()][position.getColuna() + 2] = true;
				}
			}
			// #specialmove castling queenside rook
			Position posT2 = new Position(position.getLinha(), position.getColuna() - 4);
			if (testRookCastling(posT2)) {
				Position p1 = new Position(position.getLinha(), position.getColuna() - 1);
				Position p2 = new Position(position.getLinha(), position.getColuna() - 2);
				Position p3 = new Position(position.getLinha(), position.getColuna() - 3);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null
						&& getTabuleiro().peca(p3) == null) {
					mat[position.getLinha()][position.getColuna() - 2] = true;
				}
			}
		}
		return mat;
	}
}
