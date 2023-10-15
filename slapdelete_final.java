import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class slapdelete_final implements ActionListener {

	processe call_process = new processe();
	DefaultMutableTreeNode child,subchild	=	null;
	
	static private JButton			OKButton;
	static private JTextField		ldapsearch;
	static private JTextField 		ldapsearch2;
	static private JButton			delete;
	private static JTextField		cntext;
	private static JPasswordField	Passwordtext;
	
	public void ldapdelete() {
		abfragen();		
	}

	private void abfragen() {
		JFrame 		frame = new JFrame("ldapdelete");
		JPanel 		panel = new JPanel();
					panel.setLayout(new GridLayout(0,2));
			
					
		JLabel		cn	   	= new JLabel();
					cn.setText("cn zum Beispiel Administrator ");			
					panel.add(cn);
					
					cntext	= new JTextField();
					cntext.setText("Administrator");
					panel.add(cntext);
					
		JLabel		text	   = new JLabel();
					text.setText("dc zum Beispiel example");
		
					ldapsearch = new JTextField();		
					ldapsearch.setText("example");
					ldapsearch.setMaximumSize(new Dimension(400,400));

		JLabel		text2	   = new JLabel();
					text2.setText("dc zum Beispiel com");
		
					ldapsearch2 = new JTextField();		
					ldapsearch2.setText("com");
					ldapsearch2.setMaximumSize(new Dimension(400,400));

		JLabel		Password		= new JLabel();
					Password.setText("Password");
					
					Passwordtext	= new JPasswordField();
					Passwordtext.setText("secret");
							
				  	
		JButton		ExitButton = new JButton();
					ExitButton.setText("Cancel");
					OKButton = new JButton();
					OKButton.setText("Okay select entry");
					OKButton.addActionListener(this);
					
					panel.add(text);
					panel.add(ldapsearch);		
					panel.add(text2);
					panel.add(ldapsearch2);		
					panel.add(Password);
					panel.add(Passwordtext);	
					panel.add(ExitButton);
					panel.add(OKButton);
					frame.add(panel);
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					frame.pack();		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == OKButton)
		{
			String 			dc1				= ldapsearch.getText();
			String 			dc2				= ldapsearch2.getText();
			String 			command 		= "ldapsearch -x -S '' -b dc="+dc1+",dc="+dc2;
			StringBuffer 	returncommandos = call_process.processe_with_string(command);
			String 			daten		    = returncommandos.toString();
			
			String[] parts = daten.split("\n");
			StringBuffer output = new StringBuffer(); 	
			
			for(int i=0; i<parts.length;i++)
			{
				if(parts[i].contains("dn:"))
				{	
					output.append(parts[i] + "\n");
				}
			}			
			ldaptree();
			//System.out.println(output.toString());
		}
		
		if(e.getSource() == delete)
		{		
		}
		
	}

	private void ldaptree() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	
		JPanel 		panel 	= new JPanel();
					panel.setLayout(new FlowLayout());
					delete	= new JButton("Delete");
					//delete.addActionListener(this);
				
					//DefaultMutableTreeNode child, subchild;				
					DefaultMutableTreeNode root 	=	null;
					DefaultMutableTreeNode child	=	null;
			
					String first_node	=	ldapsearch.getText();
					String second_node	=	ldapsearch2.getText();
	
					root = new DefaultMutableTreeNode(first_node+"."+second_node);
	
					String 			command 		= "ldapsearch -x -b dc="+first_node+",dc="+second_node;
					StringBuffer 	returncommandos = call_process.processe_with_string(command);
					String 			daten		    = returncommandos.toString();
					
					String[] parts = daten.split("\n");
					StringBuffer output = new StringBuffer(); 	
					
					int z=parts.length;
					for(int i=0; i<parts.length;i++)
					{
						//System.out.println(parts[i]);
						if(parts[i].contains("result: 32 No such object"))
						{
							int result=	JOptionPane.showOptionDialog(null, "Connection with failure, close program","Connection with failure",
  									JOptionPane.OK_OPTION,
  									JOptionPane.WARNING_MESSAGE, null, 
  									new String[]{"Okay"}, "Okay");
							System.exit(0);
						}
						
						if(parts[i].contains("dn:"))
						{	
							String helper = parts[i];
							String dn_root="dc="+first_node+",dc="+second_node;
							if (helper.endsWith(dn_root)) {
									String substringlaenge=("dc="+first_node+",dc="+second_node);
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
	  			
	  			
	  			tree2.getSelectionModel().addTreeSelectionListener
	  			(
	  					new TreeSelectionListener()
	  					{
	  						private void delete(String pathstringtodelete) {
	  							// TODO Auto-generated method stub
	  							String first_node	=	ldapsearch.getText();
	  							String second_node	=	ldapsearch2.getText();
	  							String admin		=	cntext.getText();
	  							String password		=	Passwordtext.getText();
	  							int laenge = first_node.length()+2+second_node.length()+2;
			
	  							String substring1=pathstringtodelete.substring(laenge,pathstringtodelete.length()-1);
	  							String bereinigt = substring1.trim();
	  							String end_bereinigt = bereinigt.replace( "dn: ", "" );
	  							
	  							String[] wechsel_datas = end_bereinigt.split(",");
	  							int wechsel_data_laenge= wechsel_datas.length;
	  							String 	stringdelete;
	  							
	  							if(wechsel_data_laenge==2)
	  							{
		  							System.out.println("ldapdelete -x -D cn="+admin+ ",dc="+first_node+",dc="+second_node+ " "
		  		  									+ "-w "+password+wechsel_datas[1]+","+wechsel_datas[0]+",dc="+first_node+",dc="+second_node); 
		  							stringdelete = "ldapdelete -x -D cn="+admin+ ",dc="+first_node+",dc="+second_node+ " "
		  									+ "-w "+password+wechsel_datas[1]+","+wechsel_datas[0]+",dc="+first_node+",dc="+second_node;
		  							
	  							}
	  							else {
	  								//ldapdelete -x -D cn=Administrator,dc=example,dc=com -W cn=Tux Linux,ou=devel,dc=example,dc=com
	  								System.out.println("ldapdelete -x -D cn="+admin+ ",dc="+first_node+",dc="+second_node+ " "
	  										+ "-w "+password+" "+end_bereinigt+",dc="+first_node+",dc="+second_node); 
	  							
	  								stringdelete = "ldapdelete -x -D cn="+admin+ ",dc="+first_node+",dc="+second_node+ " "
	  									+ "-w "+password+" "+end_bereinigt+",dc="+first_node+",dc="+second_node;
	  							}
	  							
	  							int result=	JOptionPane.showOptionDialog(null, stringdelete,"Wirklich löschen",
	  									JOptionPane.YES_NO_CANCEL_OPTION,
	  									JOptionPane.WARNING_MESSAGE, null, 
	  									new String[]{"Delete", "Cancel"}, "Cancel");
			
	  							if (result == JOptionPane.YES_OPTION) {
	  								call_process.processe_with_string(stringdelete);
	  							} else if (result == JOptionPane.NO_OPTION) {
	  							
	  						}
	  					}
		
	  					@Override public void valueChanged( TreeSelectionEvent e )
	  					{
	  						TreePath path = e.getNewLeadSelectionPath();
	  						String pathstringtodelete = path.toString();
	  						//System.out.println(pathstringtodelete);
	  						delete(pathstringtodelete);
	  					}
	  				} 
	  			);
	}

	private StringBuffer string_vergleiche(String vergleich_string, String mit_was) {
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
