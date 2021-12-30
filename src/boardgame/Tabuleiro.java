package boardgame;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private Peca[][] pecas;

	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new BoardException(
					"Erro criando tabuleiro: � necess�rio que tenha pelo menos uma linha e uma coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public Peca peca(int linha, int coluna) {
		if(!positionExists(linha, coluna)) {
			throw new BoardException("Linha ou Coluna n�o existem no Tabuleiro!");
		}
		return pecas[linha][coluna];
	}

	public Peca peca(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Posi��o n�o existe no Tabuleiro!");
		}
		return pecas[position.getLinha()][position.getColuna()];
	}

	public void lugarPeca(Peca peca, Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("J� Existe uma pe�a nessa posi��o " + position);
		}
		pecas[position.getLinha()][position.getColuna()] = peca;
		peca.position = position;
	}
	
	public Peca removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posi��o n�o existe no tabuleiro");
		}
		if(peca(position) == null) {
			return null;
		}
		Peca aux = peca(position);
		aux.position = null;
		pecas[position.getLinha()][position.getColuna()] = null;
		return aux;
	}

	private boolean positionExists(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getLinha(), position.getColuna());
	}

	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Posi��o n�o existe no Tabuleiro!");
		}
		return peca(position) != null;
	}
}
