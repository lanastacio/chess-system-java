package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPeca;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			UI.printBoard(chessMatch.getPecas());
			System.out.println();
			System.out.print("Origem:");
			ChessPosition source = UI.readChessPosition(sc);
			
			System.out.println();
			System.out.print("Destino: ");
			ChessPosition target = UI.readChessPosition(sc);
			
			ChessPeca capturedPiece = chessMatch.performChessMove(source, target);
		}

	}

}
