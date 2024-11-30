import java.util.Scanner;

public class Main {
	
	

	public static int inserisciScelta(int n) {
		
		int scelta = -1;
		
		scelta = leggiIntero(scelta);
		while (scelta < 0 || scelta > n) {
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
		
		System.out.print("\n\n");
		System.out.println("1) investimento breve durata");
		System.out.println("2) investimento media durata");
		System.out.println("3) investimento lunga durata");
		
		System.out.println();
		System.out.println("0) Uscire" + "\n");
	}
	
	public static void menuTipoInvestimento() {
		
		System.out.print("\n\n");
		System.out.println("1) basso rischio e basso guadagno;");
		System.out.println("2) medio rischio e medio guadagno;");
		System.out.println("3) alto rischio  e alto guadagno;");
		
		System.out.println();
		System.out.println("0) Uscire" + "\n");
	}
	
	
	
	public static double depositoSoldi(double portafoglio) {
		Scanner input = new Scanner(System.in);
		double deposito = 0;
	    boolean inputValido;

		    do {
		    	System.out.print("Inserire la cifra da depositare: ");
		        String depositoStringa = input.nextLine();
		        inputValido = true;

		        try {
		            deposito = Double.parseDouble(depositoStringa);
		            //CHIEDI PROF SE VANNO BENE GLI IF DENTRO
		            if (deposito <= 0) {
		                System.out.println("Il deposito deve essere maggiore di zero!");
		                System.out.println();
		                inputValido = false;
		            } else {
		            	if (deposito > portafoglio) {
			                System.out.println("Non puoi depositare più soldi di quanti ne hai nel portafoglio!");
			                System.out.println();
			                inputValido = false;
			            }
		            }

		        } catch (NumberFormatException e) {
		            System.out.println("Input non valido!");
		            System.out.println();
		            inputValido = false;
		        }
		        
		        
		        
		   } while (!inputValido);
		    
	  return deposito;
	
	}
	
	public static double prelievoSoldi(double contoBancario) {
		Scanner input = new Scanner(System.in);
		double prelievo = 0;
	    boolean inputValido;

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
		            break;
		        }
		        if (prelievo <= 0) {
	                System.out.println("Il prelievo deve essere maggiore di zero!");
	                System.out.println();
	                inputValido = false;
	            } else {
	            	if (prelievo > contoBancario) {
		                System.out.println("Non puoi prelevare più soldi di quanti ne hai nel conto!");
		                System.out.println();
		                inputValido = false;
		            }
	            }
		        
		        
		        
		   } while (!inputValido);
		    
	  return prelievo;
	
	}
	
	
	
	// margine guadagno, margine fallimento, soldi buttati dentro
//	public static double eseguiInvestimento(int durataInvestimento, String tipoInvestimento) {
//		
//	}
	
	
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
	
		boolean ok = true;
		
		int numeroControlloInput;
		
		int anno = 2024;
		int mesiTrascorsi = 0;
		double contoBancario = 0;
		double portafoglio = 0;
		
		do {
			
			mostraData(mesiTrascorsi, anno);
			
			menuPrincipale();	
			
			numeroControlloInput = 6;
			int scelta = inserisciScelta(numeroControlloInput);
		
				switch (scelta) {
				
					case 0: {
						
						ok = false;
						
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
						
//						if (portafoglio > 0) {
//							
//						    double deposito = 0;
//						    boolean inputValido;
//
//						    do {
//						        System.out.print("Inserire la cifra da depositare: ");
//						        String depositoStringa = input.nextLine();
//						        
//						        inputValido = true;
//
//						        try {
//						            deposito = Double.parseDouble(depositoStringa);
//
//						            if (deposito <= 0) {
//						                System.out.println("Il deposito deve essere maggiore di zero!");
//						                System.out.println();
//						                inputValido = false;
//						            } else {
//						            	if (deposito > portafoglio) {
//							                System.out.println("Non puoi depositare più soldi di quanti ne hai nel portafoglio!");
//							                System.out.println();
//							                inputValido = false;
//							            }
//						            }
//						        } catch (NumberFormatException e) {
//						            System.out.println("Input non valido!");
//						            System.out.println();
//						            inputValido = false;
//						        }
//						        
//						    } while (!inputValido);
//
//						    contoBancario += deposito;
//						    portafoglio -= deposito;
//
//						    System.out.println("Deposito avvenuto con successo");
//						    
//						} else {
//							
//						    System.out.println("Non hai abbastanza soldi nel portafoglio!");
//						    System.out.println("Deposito fallito");
//						    
//						}
						
						if (portafoglio > 0) {
							
						    double deposito = depositoSoldi(portafoglio);
						    contoBancario += deposito;
						    portafoglio -= deposito;

						    System.out.println("Deposito avvenuto con successo");
						    
						} else {
							
						    System.out.println("Non hai abbastanza soldi nel portafoglio!");
						    System.out.println("Deposito fallito");
						    
						}
						
						break;
					}
					
					case 4: {
						
						System.out.println("\n\n");
						
						if (contoBancario > 0) {
							//IL PRELIEVO PUò PORTARMI IN NEGATIVO???
						    double prelievo = prelievoSoldi(portafoglio);
						    
						    portafoglio += prelievo;
						    contoBancario -= prelievo;

						    System.out.println("Prelievo avvenuto con successo");
						    
						} else {
							
						    System.out.println("Non hai abbastanza soldi nel conto!");
						    System.out.println("Prelievo fallito");
						    
						}
						
						break;
					}
					
					case 5: {
						
						System.out.println("\n\n");
						
						int sceltaDurata;
						
						menuDurataInvestimento();
						
						numeroControlloInput = 3;
						sceltaDurata = inserisciScelta(numeroControlloInput);
						
						switch (sceltaDurata) {
						
							case 0: {
								
								ok = false;
								
								break;
							}
							
							case 1: {
											
								break;
							}
							
							case 2: {
								
								break;
							}
							
							case 3: {
								
								break;
							}
						}//switchdurata	
							
						int sceltaTipo;
						menuTipoInvestimento();	
						
						numeroControlloInput = 3;
						sceltaTipo = inserisciScelta(numeroControlloInput);
						
						switch (sceltaTipo) {
						
							case 0: {
								
								ok = false;
								
								break;
							}
							
							case 1: {
											
								break;
							}
							
							case 2: {
								
								break;
							}
							
							case 3: {
								
								break;
							}	
						}//switchtipo
						
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
				
		} while (ok);
		
	}


}
