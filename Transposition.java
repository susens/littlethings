
public class Transposition {

	
	private static void transposition(String message, int key) {
		char[] stringtoarray = message.toCharArray();	
		//laufe die eine Schleife durch bis der Key erreicht ist. 
		for(int j=0; j<key; j++)
		{
			//Attention besonderheit:
			//nehme i=j um den zweiten Zähler immer sauber hoch zu zählen... 
			//so das er die richtigen Werte erhält. 
			//zähle bis zum key hoch. 
			for(int i=j; i<stringtoarray.length; i=i+key)
			{	
				System.out.print(stringtoarray[i]+" ");
				
			}
		}
	}
	

	public static void main(String args[])
	{
		String message="Common sense is not so common.";
		int key=8;	
		transposition(message,key);
	
	}
}
