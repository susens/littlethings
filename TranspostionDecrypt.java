

public class TranspostionDecrypt {

	
	private static void transposition_decrypt(String message, int key) {
		// TODO Auto-generated method stub
		int laenger=message.length();
	
		double 	value 	=((double) laenger/key);
				value 	= Math.ceil(value);
		int 	helper 	= (int) value;
		
		char[] stringtoarray = message.toCharArray();			
		
		int shades=helper*key;
		int folder=shades-message.length();
		int zeilentrenner=key-folder;
		int zeile=0;
		
		char[] neusort = null;
	
		for(int i=0; i<stringtoarray.length; i++)
		{	
				if(i==value)
				{
					zeile++;
					
					System.out.println("");
					
					if(zeile>=zeilentrenner)
					{
						value=value+(helper-1);
						
					}else
					{
						value=value+helper;
					}
				}
			
			System.out.print(stringtoarray[i]+" ");
			
		}
		
	}
	
	
	public static void main(String[] args)
	{
		//String message="Cenoonommstmme oo snnio. s s c";
		String message="Dmsexaecltsiht nlei ursve tesT rse";
		int key=8;
		transposition_decrypt(message, key);
	}
}
