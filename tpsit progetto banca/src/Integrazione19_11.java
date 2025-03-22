import java.util.Scanner;

public class Integrazione19_11 {
	
	

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



	public static void mostraData(int mesiTrascorsi, int anno) {
		
		System.out.print("\n\n\n");
		
		String mese = trovaMese(mesiTrascorsi);
		
		System.out.println("Data: " + mese + " " + anno);
	}
	
	public static String trovaMese(int mesiTrascorsi) {
		mesiTrascorsi++;
		String mese = "";
		
		switch (mesiTrascorsi) {
			case 1:  {mese = "gennaio";   break;}
			case 2:  {mese = "febbraio";  break;}
			case 3:  {mese = "marzo";     break;}
			case 4:  {mese = "aprile";    break;}
			case 5:  {mese = "maggio";    break;}
			case 6:  {mese = "giugno";    break;}
			case 7:  {mese = "luglio";    break;}
			case 8:  {mese = "agosto";    break;}
			case 9:  {mese = "settembre"; break;}
			case 10: {mese = "ottobre";   break;}
			case 11: {mese = "novembre";  break;}
			case 12: {mese = "dicembre";  break;}
		}
		
		return mese;
	}
	
	
	
	public static void menuPrincipale() {
		
		System.out.println();
		
		System.out.println("----------------");
		System.out.println("      MENU      ");
		System.out.println("----------------");
		System.out.println();
		
		System.out.println("1) visualizzare bilancio conto bancario");
		System.out.println("2) visualizzare bilancio portafoglio");
		System.out.println("3) depositare sul conto bancario");
		System.out.println("4) prelevare dal conto bancario");
		System.out.println("5) effettuare un investimento");
		
		System.out.println();
		System.out.println("6) Avanzare di 1 mese");
		
		System.out.println();
		System.out.println("0) Uscire");
		System.out.println();
	}
	
	public static void menuDurataInvestimento() {
		
		System.out.println();
		System.out.println("1) investimento breve durata");
		System.out.println("2) investimento media durata");
		System.out.println("3) investimento lunga durata");
		
		System.out.println();
	}
	
	public static void menuTipoInvestimento() {
		
		System.out.println();
		System.out.println("1) basso rischio / guadagno");
		System.out.println("2) medio rischio / guadagno");
		System.out.println("3) alto rischio  / guadagno");
		
		System.out.println();
	}
	
	
	
	public static boolean depositoSoldiValido(double deposito,double portafoglio) {

        if (deposito <= 0) {
        	
            System.out.println("Il deposito deve essere maggiore di zero!");
            System.out.println();
            return false;
            
        } else {
        	
        	if (deposito > portafoglio) {
        		
                System.out.println("Non puoi depositare più soldi di quanti ne hai nel portafoglio!");
                System.out.println();
                return false;
                
            }
        }

        return true;
	}
	
	public static boolean prelievoSoldiValido(double prelievo,double contoBancario) {
		
	if (prelievo <= 0) {
        	
            System.out.println("Il prelievo deve essere maggiore di zero!");
            System.out.println();
            return false;
            
        } else {
        	
        	if (prelievo > contoBancario) {
        		
                System.out.println("Non puoi prelevare più soldi di quanti ne hai nel conto!");
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
					mesi=1;			
					break;
				}
				
				case 2: {
					mesi=2;
					break;
				}
				
				case 3: {
					mesi=4;
					break;
				}
			 }	
			 
	   return mesi;
	}
	
	public static int switchTipo(int sceltaTipo) {
		
		int tipo = 0;
		
		switch (sceltaTipo) {
		 
			case 1: {
						
				break;
			}
			
			case 2: {
				
				break;
			}
			
			case 3: {
				
				break;
			}
		}	 
  
		return tipo;
}
	
	
	
	// margine guadagno, margine fallimento, soldi buttati dentro
//	public static double eseguiInvestimento(int durataInvestimento, String tipoInvestimento) {
//		
//	}
	
	
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
	
		boolean esci = false;
		
		int numeroControlloInputMax;
		int numeroControlloInputMin;
		
		int anno = 2024;
		int mesiTrascorsi = 0;
		double contoBancario = 0;
		double portafoglio = 0;
		
		do {
			
			mostraData(mesiTrascorsi, anno);
			
			menuPrincipale();	
			
			numeroControlloInputMax = 6;
			numeroControlloInputMin = 0;
			int scelta = inserisciScelta(numeroControlloInputMax, numeroControlloInputMin);
		
				switch (scelta) {
				
					case 0: {
						
						esci = true;
						
						break;
					}
					
					case 1: {
								
						System.out.println("\n\n");
						System.out.println("Bilancio conto bancario: " + contoBancario + " $");
						
						break;
					}
					
					case 2: {
						
						System.out.println("\n\n");
						System.out.println("Bilancio portafoglio: " + portafoglio + " $");
						
						break;
					}
					
					case 3: {
						
						System.out.println("\n\n");
						
						if (portafoglio > 0) {
							
							double deposito = 0;
						    boolean inputValido;	
							
							do {
								
								do {
									
									System.out.print("Inserire la cifra da depositare: ");
								    String depositoStringa = input.nextLine();
								    inputValido = true;
								    
								    try {
								        deposito = Double.parseDouble(depositoStringa);
								    } catch (NumberFormatException e) {
								        System.out.println("Input non valido!");
								        System.out.println();
								        inputValido = false;
								    }
		
								} while (!inputValido);
									
								 
								if (depositoSoldiValido(deposito,portafoglio)) {
									
									contoBancario += deposito;
								    portafoglio -= deposito;
								    System.out.println("Deposito avvenuto con successo");
								    inputValido = true; //ottimiza i true e ifalse, potremmo meterne di meno
								    
								} else {		
									
									System.out.println();
									System.out.println("Deposito fallito - RIPROVA");
									inputValido = false;//ottimiza i true e ifalse, potremmo meterne di meno
									
								}
								
							} while (!inputValido);   
						    
						} else {
							
						    System.out.println("Non hai abbastanza soldi nel portafoglio!");
						    System.out.println("Deposito fallito");
						    
						}
						
						break;
					}
					
					case 4: {
						
						System.out.println("\n\n");
						
						if (contoBancario > 0) {
						
						    double prelievo = 0; 
						    boolean inputValido;	
							
							do {
																
								do {
									
								    System.out.print("Inserire la cifra da prelevare: ");
							        String prelievoStringa = input.nextLine();
							        inputValido = true;
							        
							        try {
							            prelievo = Double.parseDouble(prelievoStringa);
							        } catch (NumberFormatException e) {
							            System.out.println("Input non valido!");
							            System.out.println();
							            inputValido = false;
							        }
		
								} while (!inputValido);
									
								 
								if (prelievoSoldiValido(prelievo,portafoglio)) {
									
									portafoglio += prelievo;
									contoBancario -= prelievo;
									System.out.println("Prelievo avvenuto con successo");
								    inputValido = true; //ottimiza i true e ifalse, potremmo meterne di meno
								    
								} else {	
									  
									System.out.println("");
								    System.out.println("Prelievo fallito - RIPROVA");
								    inputValido = false;//ottimiza i true e ifalse, potremmo meterne di meno
								    
								}
								
							} while (!inputValido);   
							    
						} else {
							
							 System.out.println("Non hai abbastanza soldi nel conto!");
							 System.out.println("Prelievo fallito");
							 
						}						    
						
						break;
					}
					
					case 5: {
						
						System.out.println("\n\n");
						
						menuDurataInvestimento();
						
						numeroControlloInputMax = 3;
						numeroControlloInputMin = 1;
						int sceltaDurata = inserisciScelta(numeroControlloInputMax, numeroControlloInputMin);
						int durata = switchDurata(sceltaDurata);
							
						menuTipoInvestimento();	
						
//						numeroControlloInputMax = 3;
//						numeroControlloInputMin = 1;
						int sceltaTipo = inserisciScelta(numeroControlloInputMax, numeroControlloInputMin);
						int tipo = switchTipo(sceltaTipo);
						
						break;
					}

					case 6: {
						
						portafoglio += 100;
						mesiTrascorsi++;
						
						if (mesiTrascorsi == 11) {
							anno++;
							mesiTrascorsi = 0;
						}
						
						break;
					}
					
				}
				
		} while (!esci);
		
	}


}

