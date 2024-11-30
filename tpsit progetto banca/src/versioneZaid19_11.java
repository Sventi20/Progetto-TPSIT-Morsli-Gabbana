import java.util.Scanner;

public class versioneZaid19_11 {


	/*
	 * su inserisci scelta dobbiamo passare n che è il numero di opzioni
	 * quindi a seconda del menu c'è un numero (ad esempio qua il 6) diverso
	 */

	public static int inserisciScelta(int max) {
		
		int scelta = -1;
		
		scelta = leggiIntero(scelta);
		while (scelta < 0 || scelta > max) {
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


	

	public static void menuPrincipale() {
		
		System.out.print("\n\n\n");
		
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
	
	
	
	
	public static int switchDurata(boolean esci, int sceltaDurata) {
	
		int mesi=0;
		
			 switch (sceltaDurata) {
			 
				case 0: {				
					//return esci = true;
				}
				
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
		//return esci=false; 
	   return mesi;
	}	
	
	public static void switchTipo(boolean esci, int sceltaTipo) {
		
			 switch (sceltaTipo) {
			 
				case 0: {				
					//return esci = true;
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
			 }	
		//return esci=false; 
	   
	}	
	
	
	public static boolean depositoSoldi(double deposito,double portafoglio) {

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
	
	public static boolean prelievoSoldi(double prelievo,double contoBancario) {
		
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
	
	
	
	// margine guadagno, margine fallimento, soldi buttati dentro
	//public static double eseguiInvestimento(int durataInvestimento, String tipoInvestimento) {}
	
	
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
	
		boolean esci = false;
		
		int mesiTrascorsi = 0;
		double contoBancario = 0;
		double portafoglio = 0;
		
		do {
			
			menuPrincipale();	
			
			int scelta = inserisciScelta(6);
		
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
								
							 
							  if (depositoSoldi(deposito,portafoglio)) {
							    contoBancario += deposito;
							    portafoglio -= deposito;
							    System.out.println("Deposito avvenuto con successo");
							    inputValido = true; //ottimiza i true e ifalse, potremmo meterne di meno
							  } 
							  else {									
								    System.out.println("");
								    System.out.println("Deposito fallito - RIPROVA");
								    inputValido = false;//ottimiza i true e ifalse, potremmo meterne di meno
								}
						}while(!inputValido);   
						    
						} else {
							
						    System.out.println("Non hai abbastanza soldi nel portafoglio!");
						    System.out.println("Deposito fallito");
						    
						}
						
						break;
					}
					
					case 4: {
						
						System.out.println("\n\n");
						
						if (contoBancario > 0) {
						
						    double prelievo =0; 
						    
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
									
								 
								  if (prelievoSoldi(prelievo,portafoglio)) {
									  portafoglio += prelievo;
									  contoBancario -= prelievo;
									    System.out.println("Prelievo avvenuto con successo");
								    inputValido = true; //ottimiza i true e ifalse, potremmo meterne di meno
								  } 
								  else {									
									    System.out.println("");
									    System.out.println("Prelievo fallito - RIPROVA");
									    inputValido = false;//ottimiza i true e ifalse, potremmo meterne di meno
									}
							}while(!inputValido);   
							    
							} else {
								
								 System.out.println("Non hai abbastanza soldi nel conto!");
								 System.out.println("Prelievo fallito");							    
							}						    
						
						break;
					}
					
					case 5: {
						
						System.out.println("\n\n");
						
						menuDurataInvestimento();						
						int sceltaDurata;				
						sceltaDurata = inserisciScelta(3);						 
						switchDurata(esci,sceltaDurata);
													
						menuTipoInvestimento();
						int sceltaTipo;											
						sceltaTipo = inserisciScelta(3);
						switchTipo(esci,sceltaTipo);

						break;
					}

					case 6: {
						
						portafoglio += 100;
						mesiTrascorsi++;
						
						break;
					}
					
				}
				
		} while (!esci);
		
	}

}
