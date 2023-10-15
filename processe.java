import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class processe {

	public StringBuffer processe_with_string(String command) {
		System.out.println(command);
		StringBuffer output = new StringBuffer(); 	
		Process shell;
		try {
			shell = Runtime.getRuntime().exec(command);	
			
		   InputStreamReader in = new InputStreamReader(shell.getInputStream()); 
		   BufferedReader reader = new BufferedReader(in); 
		      
		   //BufferedReader reader = new BufferedReader(new InputStreamReader(shell.getInputStream()));
	        String zeile="";
	        
	        while ((zeile = reader.readLine()) != null) {
	        	output.append(zeile + "\n");
	        	//System.out.println(zeile);
	        } 
	        shell.destroy(); 
	        reader.close(); 
	        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		return output;
	}
}
