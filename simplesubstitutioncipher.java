import java.util.Scanner;

public class simplesubstitutioncipher {
	
	private static char[] SYMBOLS= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
			'p','q','r','s','t','u','v','w','x','y','z',' ','-'};
	
	//private static char[] myKey= {'v','j','z','b','g','n','f','e','p','l','i','t','m','x','d',
	//		'w','k','q','u','c','r','y','a','h','s','o',' ','-'};

	private static char[] myKey = {'L','F','W','O','A','Y','U','I','S','V','K','M','N','X','P','B','D','C','R','J','T','Q','E','G','H','Z',' ','-'};
	
	//Fuer die Verschluesselung
	private static int buchstabezuzahl(char buchstabe)
	{
		
		for(int i=0; i<SYMBOLS.length;i++)
		{
			if(SYMBOLS[i]==buchstabe)
			{
				return i;
			}
		}
		//System.exit(0);
		return 0;
	}

	private static char zahlzubuchstabe(int neueZahl)
	{
		return myKey[neueZahl];
	}

	private static void verschluessel(String zu_verschluesselnder_text)
	{
		//String zu_verschluesselnder_text="Attack at dawn";
		
		for(int i=0; i<zu_verschluesselnder_text.length();i++)
		{
			char c = zu_verschluesselnder_text.charAt(i);
			int temp=buchstabezuzahl(c);
			char endgueltig=zahlzubuchstabe(temp);
			System.out.print(endgueltig+"");
		}	
	}

	//=======================================================//
	//Fuer die Entschluesselung
	private static int buchstabezuzahl_ent(char buchstabe)
	{
		
		for(int i=0; i<myKey.length;i++)
		{
			if(myKey[i]==buchstabe)
			{
				return i;
			}
		}
		//System.exit(0);
		return 0;
	}

	private static char zahlzubuchstabe_ent(int neueZahl)
	{
		return SYMBOLS[neueZahl];
	}

	private static void entschluessel(String verschluesselt)
	{
		//String verschluesselt="LJJLWK LJ OLEX";
		for(int i=0; i<verschluesselt.length();i++)
		{
			char c = verschluesselt.charAt(i);
			int temp = buchstabezuzahl_ent(c);
			char endgueltig=zahlzubuchstabe_ent(temp);
			System.out.print(endgueltig+"");
		}		
	}
	
	public static void main(String[] args) {
		
		System.out.println("Wollen Sie ver(1)- oder entschluesseln(2)");
		Scanner s 		= new Scanner(System.in);
		int     zahl	= s.nextInt();
		
		/*
		String zu_verschluesselnder_text="if a man is offered a fact which goes against his" + 
				" instincts, he will scrutinize it closely, and unless the evidence" + 
				" is overwhelming, he will refuse to believe it. if, on the other" + 
				" hand, he is offered something which affords a reason for acting" + 
				" in accordance to his instincts, he will accept it even on the" + 
				" slightest evidence. the origin of myths is explained in this way." + 
				" -bertrand russell'";
		
		*/
		
		if(zahl==1)
		{
			System.out.println("Geben Sie Ihren zu verschluesselnden Text ein:");
			Scanner st 		= new Scanner(System.in);
			String  daten	= st.nextLine();
				
			verschluessel(daten);
		}
		if(zahl==2)
		{
			System.out.println("Geben Sie Ihren zu entschluesselnden Text ein:");
			Scanner str 		= new Scanner(System.in);
			String  daten_ent	= str.nextLine();
			
			entschluessel(daten_ent);
		}
	}
}
