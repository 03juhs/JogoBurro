package player;

import baralho.Carta;
import baralho.Naipe;
import baralho.Valor;

import java.util.ArrayList;

public class Mao {
	private final ArrayList < Carta > cartas;

	public Mao() {
		this.cartas = new ArrayList<>();
	}

	protected void verMao() {
		if (!cartas.isEmpty()) {
			for (Carta carta: cartas) {
				System.out.println("[" + cartas.indexOf(carta) + "]" + carta.getValorCarta() + " de " + carta.getNaipe() + ",");
			}
		} else {
			System.out.println("Mao vazia!");
		}
	}

	protected int sizeMao() {
		return cartas.size();
	}
	protected void addCartaMao(Carta carta) {
		cartas.add(carta);
	}

	protected Carta escolherCarta(int indexCarta) {

		try {
			Naipe naipe = cartas.get(indexCarta).getNaipe();
			Valor valor = cartas.get(indexCarta).getValorCarta();
			cartas.remove(indexCarta);
			return new Carta(naipe, valor);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Valor Invalido!");
		}

		return null;
	}

	protected boolean maoIsEmpty() {
		return cartas.isEmpty();

	}
}