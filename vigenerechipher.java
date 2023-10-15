public class vigenerechipher {

	public static char[] SYMBOLS= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	
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
	
	private static String zahlzubuchstabe(int zahl)
	{
		char buchstabe;
		String helper="";
		for(int i=0; i<SYMBOLS.length;i++)
		{			
			if(zahl==i)
			{
				buchstabe=(char)(SYMBOLS[i]);
				helper=String.valueOf(buchstabe);
				return helper;
			}
		}
		
		return helper;
	}
	
	public static void main(String[] args) {
		
		String verschluesselter_text="common sense is not so common";
		String chiffre = "pizza";
		String chiffre2= "pizza";
		
		while(verschluesselter_text.length()>chiffre.length())
		{
			chiffre=chiffre+chiffre2;
		}
		
		String chiffre_verkuerzt=chiffre.substring(0,verschluesselter_text.length());
		
		for(int i=0; i<verschluesselter_text.length(); i++)
		{
			char buchstabe = verschluesselter_text.charAt(i);
			
			if(buchstabe==' ')
			{
				System.out.print(" ");
			}else {
				int zahl_text = buchstabezuzahl(buchstabe);
			
				char buchstabe2 = chiffre_verkuerzt.charAt(i);
				int zahl_chiffre2=buchstabezuzahl(buchstabe2);
			
				int verschluesselte_zahl=zahl_text+zahl_chiffre2;
				if(verschluesselte_zahl>SYMBOLS.length)
				{
					verschluesselte_zahl=verschluesselte_zahl-SYMBOLS.length;
				}
			
				String verschluesselt=zahlzubuchstabe(verschluesselte_zahl);
				System.out.print(verschluesselt);
			}
		}
		
	}
}
