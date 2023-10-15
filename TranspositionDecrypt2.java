
public class TranspositionDecrypt2 {

	public static String transposition_decrypt1(String message, int key) 
	{

		double 	daten 	=((double) message.length()/key);
		daten 	= Math.ceil(daten);
		int 	helper 	= (int) daten;		
	
		
		int shades=helper*key;
		int folder=shades-message.length();
		int zeilentrenner=key-folder;
		//System.out.println(zeilentrenner);
		
		StringBuilder sb = new StringBuilder();
	
		char[] stringtoarray 	= message.toCharArray();	
		int		j				= 0;
				
		for(int k=0; k<helper; k++)
		{
			int value=k;
			j=0;
			for(int i=k; i<stringtoarray.length; i++)
			{
				if(i==value)
				{
					if(j>=zeilentrenner)
					{
						//System.out.print(stringtoarray[i]);
						value=value+(helper-1);
						sb.append(stringtoarray[i]);
					}
					else
					{
						//System.out.print(stringtoarray[i]);
						value=value+helper;
						sb.append(stringtoarray[i]);
					}
					j++;
				}
			}
		}	
		
		//ist dafür da überflüssiges abzuschneiden
		//warum der String länger wird bleibt mir ein Rätsel aber ist eben so. 
		//scheinbar werden gerne noch ein paar zeichen mehr angehängt und folglich 
		//muss das wieder abgetrennt werden. 
		int laenger_string=message.length();
		int laenge        =sb.length();
		int reduce = laenge-laenger_string;
		
		String substring=sb.substring(0, sb.length() - reduce) ;
		return substring;
		//return(sb.toString());
	}
		
	public static void main(String[] args)
	{
		String message="Cenoonommstmme oo snnio. s s c";
		//String message="Dmsexaecltsiht nlei ursve tesT rse";
		//String message="D tedt xai rrtTtssd iee";
		//String message="Dertu. tar  e vessTmsDer ceesar ihxiessTsltnl cetu. tihx e vesltmsDertuesar  eiessTmsnl cees tihxievesltnl";
		
		
		for(int key=0; key<20; key++)
		{
			//transposition_decrypt(message, key);
			String entschluesselt=transposition_decrypt1(message,key);
			System.out.println(entschluesselt);
		}
	}
}
