package player;

import baralho.Carta;

public abstract class MaoPlayer {
	protected Mao mao;
	public abstract String getNome();

	public MaoPlayer() {
		this.mao = new Mao();
	}
	public Carta jogarCarta(int index) {
		return mao.escolherCarta(index);
	}
	public void addCarta(Carta carta) {
		mao.addCartaMao(carta);
	}
	public int tamanhoMao() {
		return mao.sizeMao();
	}
	public void verMao() {
		mao.verMao();
	}
	public boolean maoJogadorIsEmpty() {
		return mao.maoIsEmpty();
	}

	protected void setId(int identificador) {
	}
}