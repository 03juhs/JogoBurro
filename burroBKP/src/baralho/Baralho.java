package baralho;

import java.util.ArrayList;
import java.util.Collections;

public class Baralho {

	private final ArrayList < Carta > baralho;

	public Baralho() {
		this.baralho = new ArrayList<>();
		for (int i = 0; i < 13; i++) {
			Valor valor = Valor.values()[i];

			for (int j = 0; j < 4; j++) {
				Carta carta = new Carta(Naipe.values()[j], valor);
				this.baralho.add(carta);
			}
		}

	}

	public void embaralhar() {
		Collections.shuffle(baralho);
	}



	public Carta getCarta() {
		Naipe naipe = baralho.get(0).getNaipe();
		Valor valor = baralho.get(0).getValorCarta();
		baralho.remove(0);
		return new Carta(naipe, valor);
	}

	public Boolean temCarta() {
		return !baralho.isEmpty();
	}
}