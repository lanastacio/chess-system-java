package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Peca;
import boardgame.Position;
import boardgame.Tabuleiro;
import chess.pecas.Rei;
import chess.pecas.Torre;

public class ChessMatch {

	private Tabuleiro tabuleiro;
	private Color currentPlayer;
	private int turn;
	private boolean check;

	private List<Peca> piecesOnTheBoard = new ArrayList<>();
	private List<Peca> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		tabuleiro = new Tabuleiro(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
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

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return tabuleiro.peca(position).possibleMoves();
	}

	public ChessPeca performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Peca capturedPiece = makeMove(source, target);
		
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Voce nao pode se colocar em check");
		}
		
		check = (testCheck(opponent(currentPlayer)))? true : false;
		
		nextTurn();
		return (ChessPeca) capturedPiece;
	}

	public void validateSourcePosition(Position position) {
		if (!tabuleiro.thereIsAPiece(position)) {
			throw new ChessException("Nao existe peca no lugar de origem");
		}
		if (currentPlayer != ((ChessPeca) tabuleiro.peca(position)).getColor()) {
			throw new ChessException("A peca escolhida pertence ao adversario");
		}
		if (!tabuleiro.peca(position).isThereAnyPossibleMove()) {
			throw new ChessException("Nao ha Movimentos Possiveis!");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!tabuleiro.peca(source).possibleMove(target)) {
			throw new ChessException("A peca escolhida nao pode ser movida para a posicao de destino");
		}
	}

	private Peca makeMove(Position source, Position target) {
		Peca p = tabuleiro.removePiece(source);
		Peca capturedPiece = tabuleiro.removePiece(target);
		tabuleiro.lugarPeca(p, target);

		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}

		return capturedPiece;
	}

	private void undoMove(Position source, Position target, Peca capturedPiece) {
		Peca p = tabuleiro.removePiece(target);
		tabuleiro.lugarPeca(p, source);

		if (capturedPiece != null) {
			tabuleiro.lugarPeca(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private ChessPeca king(Color color) {
		List<Peca> list = piecesOnTheBoard.stream().filter(x -> ((ChessPeca) x).getColor() == color)
				.collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (ChessPeca) p;
			}
		}

		throw new IllegalStateException("Nao tem " + color + " Rei no tabuleiro");
	}

	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Peca> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPeca) x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Peca p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getLinha()][kingPosition.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private void placeNewPiece(char coluna, int linha, ChessPeca peca) {
		tabuleiro.lugarPeca(peca, new ChessPosition(coluna, linha).toPosition());
		piecesOnTheBoard.add(peca);
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
