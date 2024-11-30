import java.util.Scanner;
import java.text.DecimalFormat;

public class ProgettoBancaMorsliGabbana {

	static DecimalFormat df = new DecimalFormat("0.00");

	public static int inserisciScelta(int max, int min) {

		int scelta = -1;

		scelta = leggiIntero(scelta);
		while (scelta < min || scelta > max) {
			System.out.println("Valore intero non valido!" + "\n");
			scelta = leggiIntero(scelta);
		}

		return scelta;
	}

	public static int leggiIntero(int valore) {

		Scanner input = new Scanner(System.in);
		boolean ok;

		do {
			ok = true;

			System.out.print("scelta --> ");
			String valoreS = input.nextLine();

			try {
				valore = (int) Integer.parseInt(valoreS);
			} catch (NumberFormatException e) {
				System.out.print("Input non valido!" + "\n\n");
				ok = false;
			}
		} while (!ok);

		return valore;
	}

	public static boolean metodoTryCatch(String stringa) {

		try {
			Double.parseDouble(stringa);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static void mostraData(int meseCorrente, int annoCorrente) {

		System.out.print("\n\n\n");

		String mese = trovaMese(meseCorrente);

		System.out.println("Data: " + mese + " " + annoCorrente);
	}

	public static String trovaMese(int meseCorrente) {
		meseCorrente++;
		String mese = "";

		switch (meseCorrente) {
		case 1: {
			mese = "gennaio";
			break;
		}
		case 2: {
			mese = "febbraio";
			break;
		}
		case 3: {
			mese = "marzo";
			break;
		}
		case 4: {
			mese = "aprile";
			break;
		}
		case 5: {
			mese = "maggio";
			break;
		}
		case 6: {
			mese = "giugno";
			break;
		}
		case 7: {
			mese = "luglio";
			break;
		}
		case 8: {
			mese = "agosto";
			break;
		}
		case 9: {
			mese = "settembre";
			break;
		}
		case 10: {
			mese = "ottobre";
			break;
		}
		case 11: {
			mese = "novembre";
			break;
		}
		case 12: {
			mese = "dicembre";
			break;
		}
		}

		return mese;
	}

	public static void menuPrincipale() {

		System.out.println();

		System.out.println("----------------");
		System.out.println("      MENU      ");
		System.out.println("----------------");
		System.out.println();

		System.out.println("1) depositare sul conto bancario");
		System.out.println("2) prelevare dal conto bancario");
		System.out.println("3) effettuare un investimento");

		System.out.println();
		System.out.println("4) Avanzare di 1 mese");

		System.out.println();
		System.out.println("0) Uscire");
		System.out.println();
	}

	public static void menuDurataInvestimento() {

		System.out.println();
		System.out.println("1) investimento breve durata (1 mese)");
		System.out.println("2) investimento media durata (2 mesi)");
		System.out.println("3) investimento lunga durata (4 mesi)");

		System.out.println();
	}

	public static void menuTipoInvestimento() {

		System.out.println();
		System.out.println("1) basso rischio / guadagno");
		System.out.println("2) medio rischio / guadagno");
		System.out.println("3) alto rischio  / guadagno");

		System.out.println();
	}

	public static boolean operazioneSoldiValida(double soldiUsati, double soldiTotali) {

		if (soldiUsati <= 0) {

			System.out.println("La cifra inserita deve essere maggiore di zero!");
			System.out.println();
			return false;

		} else {

			if (soldiUsati > soldiTotali) {

				System.out.println("Non puoi inserire una cifra maggiore di quella disponibile!");
				System.out.println();
				return false;
			}
		}
		return true;
	}

	public static int switchDurata(int sceltaDurata) {

		int mesi = 0;

		switch (sceltaDurata) {

		case 1: {
			mesi = 1;
			break;
		}

		case 2: {
			mesi = 2;
			break;
		}

		case 3: {
			mesi = 4;
			break;
		}
		}

		return mesi;
	}

	public static int switchTipo(int sceltaTipo) {

		int tipo = 0;

		switch (sceltaTipo) {

		case 1: {
			tipo = 1;
			break;
		}

		case 2: {
			tipo = 2;
			break;
		}

		case 3: {
			tipo = 3;
			break;
		}
		}

		return tipo;
	}

	public static int definisciMargineSuccesso(int tipo) {

		int successo = 0;

		switch (tipo) {

		case 1: {
			successo = 70;
			break;
		}

		case 2: {
			successo = 50;
			break;
		}

		case 3: {
			successo = 30;
			break;
		}

		}

		return successo;
	}

	public static int definisciPercentualeGuadagno(int tipo) {

		int guadagno = 0;

		switch (tipo) {

		case 1: {
			guadagno = 35;
			break;
		}

		case 2: {
			guadagno = 65;
			break;
		}

		case 3: {
			guadagno = 140;
			break;
		}

		}

		return guadagno;
	}

	public static double effettuaInvestimento(double cifraInvestita, int margineSuccesso, int percentualeGuadagno) {

		int nCasuale = (int) (Math.random() * 100) + 1;
		int percentualeGuadagnoCasuale = (int) (Math.random() * percentualeGuadagno) + 1;

		double guadagno = cifraInvestita * ((double) percentualeGuadagnoCasuale / 100);

		if (nCasuale < margineSuccesso) {
			return (cifraInvestita + guadagno);
		} else {
			return (cifraInvestita - guadagno);
		}
	}

	public static void mostraRisultatiInvestimento(double investimento, int indiceInvestimento, double cifraInvestita) {

		System.out.println("\n\n\n");

		System.out.println("**************************");
		System.out.println(" RISULTATI INVESTIMENTO " + indiceInvestimento);
		System.out.println("**************************");
		System.out.println();

		if (cifraInvestita < investimento) {

			System.out
					.println("Investimento con esito positivo --> +" + df.format(investimento - cifraInvestita) + " $");

		} else {

			System.out
					.println("Investimento con esito negativo --> -" + df.format(cifraInvestita - investimento) + " $");

		}

	}

	public static void mostraSituazioneEconomica(double portafoglio, double contoBancario) {

		System.out.println();
		System.out.println("Bilancio portafoglio:    " + df.format(portafoglio) + " $");
		System.out.println("Bilancio conto bancario: " + df.format(contoBancario) + " $");

		System.out.println();

	}

	public static void ordinaInvestimenti(double investimenti[], int durate[], int i) {

		for (int j = i; j < 9; j++) {
			investimenti[j] = investimenti[j + 1];
			durate[j] = durate[j + 1];
		}

		investimenti[9] = 0;
		durate[9] = 0;
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		boolean esci = false;

		int numeroControlloInputMax;
		int numeroControlloInputMin;

		int anno = 2024;
		int mesiTrascorsi = 0;
		double contoBancario = 0;
		double portafoglio = 0;
		double[] investimenti = new double[10];
		double[] cifreInvestite = new double[10];
		int[] durate = new int[10];
		int indiceInvestimento = 0;

		do {

			int meseCorrente = mesiTrascorsi % 12;
			int annoCorrente = anno + (mesiTrascorsi / 12);

			for (int i = 0; i < 10; i++) {
				if (investimenti[i] != 0 && durate[i] == 0) {
					mostraRisultatiInvestimento(investimenti[i], indiceInvestimento, cifreInvestite[i]);
					contoBancario += investimenti[i];
					cifreInvestite[i] = 0;
					ordinaInvestimenti(investimenti, durate, i);
					indiceInvestimento--;
					i--;
				}
			}

			mostraData(meseCorrente, annoCorrente);

			mostraSituazioneEconomica(portafoglio, contoBancario);

			menuPrincipale();

			numeroControlloInputMax = 4;
			numeroControlloInputMin = 0;
			int scelta = inserisciScelta(numeroControlloInputMax, numeroControlloInputMin);

			switch (scelta) {

			case 0: {

				esci = true;

				input.close();

				break;
			}

			case 1: {

				System.out.println("\n\n");

				if (portafoglio > 0) {

					double deposito = 0;
					boolean inputValido;

					do {

						do {

							System.out.print("Inserire la cifra da depositare: ");
							String depositoStringa = input.nextLine();
							inputValido = true;

							if (metodoTryCatch(depositoStringa)) {

								deposito = Double.parseDouble(depositoStringa);

							} else {

								System.out.println("Input non valido!");
								System.out.println();
								inputValido = false;

							}

						} while (!inputValido);

						if (operazioneSoldiValida(deposito, portafoglio)) {

							contoBancario += deposito;
							portafoglio -= deposito;
							System.out.println("Deposito avvenuto con successo");

						} else {

							System.out.println();
							System.out.println("Deposito fallito - RIPROVA");
							inputValido = false;

						}

					} while (!inputValido);

				} else {

					System.out.println("Non hai abbastanza soldi nel portafoglio!");
					System.out.println("Deposito fallito");

				}

				break;
			}

			case 2: {

				System.out.println("\n\n");

				if (contoBancario > 0) {

					double prelievo = 0;
					boolean inputValido;

					do {

						do {

							System.out.print("Inserire la cifra da prelevare: ");
							String prelievoStringa = input.nextLine();
							inputValido = true;

							if (metodoTryCatch(prelievoStringa)) {

								prelievo = Double.parseDouble(prelievoStringa);

							} else {

								System.out.println("Input non valido!");
								System.out.println();
								inputValido = false;

							}

						} while (!inputValido);

						if (operazioneSoldiValida(prelievo, contoBancario)) {

							portafoglio += prelievo;
							contoBancario -= prelievo;
							System.out.println("Prelievo avvenuto con successo");

						} else {

							System.out.println("");
							System.out.println("Prelievo fallito - RIPROVA");
							inputValido = false;

						}

					} while (!inputValido);

				} else {

					System.out.println("Non hai abbastanza soldi nel conto bancario!");
					System.out.println("Prelievo fallito");

				}

				break;
			}

			case 3: {

				System.out.println("\n");

				if (contoBancario > 0 && indiceInvestimento < 10) {

					menuDurataInvestimento();

					numeroControlloInputMax = 3;
					numeroControlloInputMin = 1;

					int sceltaDurata = inserisciScelta(numeroControlloInputMax, numeroControlloInputMin);
					durate[indiceInvestimento] = switchDurata(sceltaDurata);

					menuTipoInvestimento();

					int sceltaTipo = inserisciScelta(numeroControlloInputMax, numeroControlloInputMin);
					int tipo = switchTipo(sceltaTipo);

					// double cifraInvestita = 0;
					boolean inputValido;

					do {

						do {

							System.out.println();
							System.out.print("Inserire la cifra da investire: ");
							String cifraInvestitaStringa = input.nextLine();
							inputValido = true;

							if (metodoTryCatch(cifraInvestitaStringa)) {

								cifreInvestite[indiceInvestimento] = Double.parseDouble(cifraInvestitaStringa);

							} else {

								System.out.println("Input non valido!");
								System.out.println();
								inputValido = false;

							}

						} while (!inputValido);

						if (operazioneSoldiValida(cifreInvestite[indiceInvestimento], contoBancario)) {

							System.out.println("Investimento avvenuto con successo");

						} else {

							System.out.println();
							System.out.println("Investimento fallito - RIPROVA");
							inputValido = false;

						}

					} while (!inputValido);

					int margineSuccesso = definisciMargineSuccesso(tipo);
					int percentualeGuadagno = definisciPercentualeGuadagno(tipo);

					investimenti[indiceInvestimento] = effettuaInvestimento(cifreInvestite[indiceInvestimento],
							margineSuccesso, percentualeGuadagno);
					contoBancario -= cifreInvestite[indiceInvestimento];
					indiceInvestimento++;

				} else {

					if (contoBancario > 0) {

						System.out.println("Hai raggiunto il limite di 10 investimenti allo stesso tempo!");
						System.out.println("Investimento fallito");

					} else {

						System.out.println("Non hai abbastanza soldi nel conto bancario!");
						System.out.println("Investimento fallito");

					}

				}

				break;
			}

			case 4: {

				portafoglio += 100;
				mesiTrascorsi++;

				for (int i = 0; i < 10; i++) {
					if (investimenti[i] != 0) {
						durate[i]--;
					}
				}

				break;
			}

			}

		} while (!esci);

	}

}
