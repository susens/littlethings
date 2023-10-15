import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class slapdelete2 {

	processe call_process = new processe();
	DefaultMutableTreeNode child,subchild	=	null;
		
	public void buidl_slapd_tree() {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	
		JPanel 		panel 	= new JPanel();
					panel.setLayout(new FlowLayout());
					
		DefaultMutableTreeNode root 	=	null;
		
		root = new DefaultMutableTreeNode("example.com");
		
					
		String 			command 		= "ldapsearch -x -b dc=example,dc=com";
		StringBuffer 	returncommandos = call_process.processe_with_string(command);
		String 			daten		    = returncommandos.toString();
		
		String[] parts = daten.split("\n");
		StringBuffer output = new StringBuffer(); 	
		
		for(int i=0; i<parts.length;i++)
		{
			if(parts[i].contains("dn:"))
			{	
				String helper = parts[i];
				String dn_root="dc=example,dc=com";
				if (helper.endsWith(dn_root)) {
						String substringlaenge=("dc=example,dc=com");
						int l = substringlaenge.length();
						helper = helper.substring(0, helper.length() - l);
                    	output.append(helper+"\n");				
                }   
			}
		}		
		
		
		String vergleichen_list2[] = output.toString().split("\n");
		for(int m=0; m<vergleichen_list2.length; m++)
		{
			//System.out.println(vergleichen_list2[m].toString());
			String letzteAusgabe = vergleichen_list2[m].toString();
			
			if(letzteAusgabe.contains("dn: cn=") && ! letzteAusgabe.toString().contains("ou"))
			{
				String letzteAusgabe2 = letzteAusgabe.substring(0, letzteAusgabe.length() - 1);
				//System.out.println(letzteAusgabe2);
				child = new DefaultMutableTreeNode(letzteAusgabe2);
				root.add(child);	
			}
		}
				
		StringBuffer tree = new StringBuffer(); 
		
		List<String> myStringList = null;
		
		String ou_list[] = output.toString().split(",");
		for(int j=0; j< ou_list.length; j++)
		{
			if(ou_list[j].contains("dn: ou="))
			{
				//System.out.println(ou_list[i]);
				//Tue den als Child anhängen......
				child = new DefaultMutableTreeNode(ou_list[j]);
				root.add(child);
			}
				
			int index1 = ou_list[j].indexOf("ou="); 
			if(index1==0)
			{
				//System.out.println(ou_list[i]);
				//Tue den als Child anhängen......
				 myStringList = Arrays.asList( ou_list[j] );
				 for (String s : myStringList)
				 {
					 if( ! tree.toString().contains(s)){
						 	tree.append(s+" ");
					 } 
				 }
			}		
		}
			
		//System.out.println(output.toString());
		//System.out.println(tree.toString());					
		StringBuffer knoten=null;
		String vergleichen_list[] = tree.toString().split(" ");
		for(int k=0; k<vergleichen_list.length; k++)
		{
			knoten= string_vergleiche(vergleichen_list[k],output.toString());
			child = new DefaultMutableTreeNode(vergleichen_list[k]);
			root.add(child);
			//System.out.println(knoten.toString());
			String ausgabe_fein[] = knoten.toString().split(" ");
			for(int l=0; l<ausgabe_fein.length;l++)
			{
				if( ! ausgabe_fein[l].equals(null) &&  ! ausgabe_fein[l].equals("") && ! ausgabe_fein[l].equals("dn:") )
				{
					subchild = new DefaultMutableTreeNode(ausgabe_fein[l].toString());
					child.add(subchild);
				}
			}
		}
		
		
		JTree tree2 = new JTree(root);
		panel.add(tree2, BorderLayout.CENTER);
		panel.add( new JScrollPane( tree2 ) );
	
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible( true );
	}

	private StringBuffer string_vergleiche(String vergleich_string, String mit_was) {
		
		//System.out.println(mit_was);
		//System.out.println(vergleich_string);
		
		String[] daten = mit_was.split("\n");
		String ruckgabe;
		StringBuffer datenreturn= new StringBuffer();
		
		for(int i=0; i<daten.length; i++)
		{
			if(daten[i].toString().contains(vergleich_string))
			{
				//System.out.println(daten[i].toString());
				String substringlaenge2=(vergleich_string);
				int la = substringlaenge2.length()+2;
				ruckgabe = daten[i].substring(0, daten[i].length()-la);
				datenreturn.append(ruckgabe+" ");
			}
		}	
		//System.out.println(datenreturn.toString());
		return datenreturn;	
	}	
}
