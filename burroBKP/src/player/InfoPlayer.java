package player;

public class InfoPlayer extends MaoPlayer {

	private String nome;

	public InfoPlayer(String nome, int identificador) {
		this.nome = nome;
		super.setId(identificador);
	}

	@Override
	public String getNome() {
		return nome;
	}

}