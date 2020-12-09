package baralho;

public class Carta {
	private Naipe naipe;
	private Valor valor;

	public Carta(Naipe naipe, Valor valor) {
		this.setNaipe(naipe);
		this.setValorCarta(valor);
	}
	public Carta() {

	}
	public Naipe getNaipe() {
		return naipe;
	}
	public void setNaipe(Naipe naipe) {
		this.naipe = naipe;
	}
	public Valor getValorCarta() {
		return valor;
	}
	public void setValorCarta(Valor valor) {
		this.valor = valor;
	}
}