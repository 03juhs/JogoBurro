package burro;

import baralho.Baralho;
import baralho.Carta;
import player.MaoPlayer;
import player.InfoPlayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class Jogo {
	private static final Baralho baralho = new Baralho();
	private static Carta cartaMesa = null;
	private final LinkedList < MaoPlayer > listaJogadores = new LinkedList<>();
	private final ArrayList < MaoPlayer > auxListJogadores = new ArrayList<>();
	private MaoPlayer maoPlayerVencedorRodada = null;
	private Boolean fimDeJogo = false;
	private Carta maiorCarta;

	{
		maiorCarta = new Carta();
	}

	private static final Scanner s = new Scanner(System. in );



	private Boolean isJogadaValida(Carta carta) {
		if (cartaMesa == null || carta.getNaipe().equals(cartaMesa.getNaipe())) return true;
		else {
			System.out.println("Jogada Invalida!");
			return false;
		}
	}

	private void listaDeQuemJogou(MaoPlayer maoPlayer) {
		auxListJogadores.add(maoPlayer);
	}

	private Boolean isMaiorCartaDaRodada(Carta carta) {
		if (cartaMesa == null || maiorCarta.getValorCarta().getValorCarta() < carta.getValorCarta().getValorCarta()) {
			cartaMesa = carta;
			maiorCarta = carta;
			return true;
		}
		cartaMesa = carta;
		return false;
	}

	private Carta jogadaJogadorHumano(MaoPlayer maoPlayer) {
		int valor;
		maoPlayer.verMao();
		System.out.println("Digite o  numero da carta que voce deseja jogar!");
		valor = s.nextInt();
		Carta carta = maoPlayer.jogarCarta(valor);
		while (carta == null) {
			try {
				valor = s.nextInt();
				carta = maoPlayer.jogarCarta(valor);
			} catch(NumberFormatException e) {
				System.out.println("Apenas numeros");
			}
		}
		return carta;
	}

	private void rodada() {
		boolean jogadaValida = false;
		Carta carta = new Carta();
		String palavra = "";
		for (MaoPlayer maoPlayer: listaJogadores) {
			if (fimDeJogo) break;
			System.out.println("\nJogada do " + maoPlayer.getNome());
			if (cartaMesa != null) System.out.println("Carta na mesa: " + cartaMesa.getValorCarta() + " de " + cartaMesa.getNaipe());
			if (maoPlayer.maoJogadorIsEmpty()) {
				continue;
			} else if (maoPlayer instanceof InfoPlayer) {
				System.out.println("Escolha uma carta para jogar");
				if (cartaMesa == null) {
					carta = jogadaJogadorHumano(maoPlayer);
					maiorCarta = carta;
				} else {
					maoPlayer.verMao();

					while (!palavra.equals("j") && !palavra.equals("c")) {
						System.out.println("Compre uma carta [c] ou escolha uma carta de sua mao [j].");
						palavra = s.next();
					}
					if (palavra.equals("c")) {
						while (true) {
							System.out.println("Carta na mesa: " + cartaMesa.getValorCarta() + " de " + cartaMesa.getNaipe());
							maoPlayer.addCarta(baralho.getCarta());
							maoPlayer.verMao();
							System.out.println("Compre uma carta [c] ou escolha uma carta de sua mao [j].");
							palavra = s.next();
							if (palavra.equals("j")) {
								carta = jogadaJogadorHumano(maoPlayer);
								break;
							}
						}
					} else {
						carta = jogadaJogadorHumano(maoPlayer);
					}

					if (!isJogadaValida(carta)) maoPlayer.addCarta(carta);
					else jogadaValida = true;
					while (!jogadaValida) {
						if (cartaMesa != null) System.out.println("Carta na mesa: " + cartaMesa.getValorCarta() + " de " + cartaMesa.getNaipe());
						System.out.println("Compre uma carta [c] ou escolha uma carta de sua mao [j].");
						palavra = s.next();
						switch (palavra) {
							case "c":
								System.out.println("Carta na mesa: " + cartaMesa.getValorCarta() + " de " + cartaMesa.getNaipe());
								maoPlayer.addCarta(baralho.getCarta());
								maoPlayer.verMao();
								break;
							case "j":
								carta = jogadaJogadorHumano(maoPlayer);
								if (!isJogadaValida(carta)) maoPlayer.addCarta(carta);
								else jogadaValida = true;
								maoPlayer.verMao();
								break;
							default:
								break;
						}
					}
					jogadaValida = false;
					palavra = "";
				}
			}
			if (isMaiorCartaDaRodada(carta)) {
				if (maoPlayerVencedorRodada != null) listaDeQuemJogou(maoPlayerVencedorRodada);
				maoPlayerVencedorRodada = maoPlayer;
			} else listaDeQuemJogou(maoPlayer);
		}
		listaJogadores.clear();
		listaJogadores.addFirst(maoPlayerVencedorRodada);
		for (MaoPlayer jog: auxListJogadores) {
			listaJogadores.addLast(jog);
		}
		System.out.println("\n\n");
		System.out.println(listaJogadores.getFirst().
				getNome() + " venceu essa rodada!");

		cartaMesa = null;
		maoPlayerVencedorRodada = null;
		auxListJogadores.clear();
		for (MaoPlayer maoPlayer: listaJogadores) {
			if (maoPlayer.maoJogadorIsEmpty()) {
				fimDeJogo = true;
				break;
			}
		}
	}

	private void distribuirCartasIniciais() {
		for (MaoPlayer maoPlayer: listaJogadores) {
			for (int i = 0; i < 4; i++) {
				maoPlayer.addCarta(baralho.getCarta());
			}
		}
	}

	private MaoPlayer getVencedor() {
		for (MaoPlayer maoPlayer: listaJogadores) {
			if (maoPlayer.maoJogadorIsEmpty()) return maoPlayer;
		}
		return null;
	}

	public void novaPartida(int numJogadores, ArrayList < String > nomes) {
		System.out.println("Jogo de cartas Burro!! Apenas uma pessoa não sera Burro! :)");
		baralho.embaralhar();

		for (int i = 0; i < numJogadores; i++) {
			MaoPlayer maoPlayer = new InfoPlayer(nomes.get(i), i);
			listaJogadores.add(maoPlayer);
		}
		distribuirCartasIniciais();
		while (baralho.temCarta()) {
			rodada();
			if (fimDeJogo) break;
		}
		System.out.println(Objects.requireNonNull(getVencedor()).getNome() + "venceu o jogo!");
		for (MaoPlayer maoPlayer: listaJogadores) {
			if (!maoPlayer.equals(getVencedor())) {
				System.out.println(maoPlayer.getNome() + " tem " + maoPlayer.tamanhoMao() + " anos de burro!!!");
			}
		}
		s.close();
	}
}