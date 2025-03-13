import java.util.Scanner;
import java.text.DecimalFormat;

public class ProgettoBanca {
	static DecimalFormat df = new DecimalFormat("0.00");
	static Scanner input = new Scanner(System.in);

	public static int inserisciScelta(int min, int max) {
		int scelta;
		do {
			scelta = leggiIntero("Scelta --> ");
			if (scelta < min || scelta > max) {
				System.out.println("Valore non valido! Riprova.\n");
			}
		} while (scelta < min || scelta > max);
		return scelta;
	}

	public static int leggiIntero(String messaggio) {
		int valore;
		while (true) {
			System.out.print(messaggio);
			String inputUtente = input.nextLine();
			try {
				valore = Integer.parseInt(inputUtente);
				return valore;
			} catch (NumberFormatException e) {
				System.out.println("Input non valido! Riprova.\n");
			}
		}
	}

	public static boolean isNumeroValido(String stringa) {
		try {
			Double.parseDouble(stringa);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void mostraData(int meseCorrente, int annoCorrente) {
		String[] mesi = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
		System.out.println("\nData: " + mesi[meseCorrente] + " " + annoCorrente);
	}

	public static void mostraMenu() {
		System.out.println("\n------ MENU ------");
		System.out.println("1) Deposita");
		System.out.println("2) Preleva");
		System.out.println("3) Effettua investimento");
		System.out.println("4) Avanza di 1 mese");
		System.out.println("0) Esci\n");
	}

	public static boolean verificaTransazione(double importo, double saldo) {
		if (importo <= 0) {
			System.out.println("L'importo deve essere maggiore di zero!\n");
			return false;
		}
		if (importo > saldo) {
			System.out.println("Fondi insufficienti!\n");
			return false;
		}
		return true;
	}

	public static void gestisciTransazione(boolean deposito, double[] portafoglio, double[] contoBancario) {
		System.out.println(deposito ? "\nInserisci importo da depositare:" : "\nInserisci importo da prelevare:");
		double importo;
		while (true) {
			String inputImporto = input.nextLine();
			if (isNumeroValido(inputImporto)) {
				importo = Double.parseDouble(inputImporto);
				if (verificaTransazione(importo, deposito ? portafoglio[0] : contoBancario[0])) {
					if (deposito) {
						contoBancario[0] += importo;
						portafoglio[0] -= importo;
					} else {
						contoBancario[0] -= importo;
						portafoglio[0] += importo;
					}
					System.out.println((deposito ? "Deposito" : "Prelievo") + " avvenuto con successo!\n");
					return;
				}
			} else {
				System.out.println("Input non valido. Riprova!\n");
			}
		}
	}

	public static void main(String[] args) {
		boolean esci = false;
		int mesiTrascorsi = 0, anno = 2024;
		double[] contoBancario = {0};
		double[] portafoglio = {0};

		while (!esci) {
			int meseCorrente = mesiTrascorsi % 12;
			int annoCorrente = anno + (mesiTrascorsi / 12);

			mostraData(meseCorrente, annoCorrente);
			System.out.println("Portafoglio: " + df.format(portafoglio[0]) + " $");
			System.out.println("Conto bancario: " + df.format(contoBancario[0]) + " $");

			mostraMenu();
			int scelta = inserisciScelta(0, 4);

			switch (scelta) {
				case 0 -> esci = true;
				case 1 -> gestisciTransazione(true, portafoglio, contoBancario);
				case 2 -> gestisciTransazione(false, portafoglio, contoBancario);
				case 4 -> {
					mesiTrascorsi++;
					portafoglio[0] += 100;
				}
			}
		}
		input.close();
	}
}
