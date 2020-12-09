import burro.Jogo;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		ArrayList < String > nomes = new ArrayList<>();
		Scanner s = new Scanner(System. in );
		System.out.println("Informe a quantidade de jogadores: ");
		int qtdJogs = s.nextInt();
		while (qtdJogs < 2) {
			System.out.println("É aceito no minimo 2 jogadores!");
			qtdJogs = s.nextInt();
		}

		for (int i = 0; i < qtdJogs; i++) {
			System.out.println("Digite o nome do MaoPlayer " + (i + 1) + ":");
			nomes.add(s.next());
		}

		Jogo jogo = new Jogo();
		jogo.novaPartida(qtdJogs, nomes);
		s.close();
	}

}