import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;


public class slapdelete implements ActionListener{

	processe call_process = new processe();
	
	static private JButton			OKButton;
	static private JTextField		ldapsearch;
	static private JTextField 		ldapsearch2;
	static private JTextField		host;
	static private JTextField		port;
	static private JButton			delete;
	private static JTextField		cntext;
	private static JPasswordField	Passwordtext;
	//ldapdelete -x -D cn=Administrator,dc=example,dc=com -W cn=Tux Linux,ou=devel,dc=example,dc=com
	
	private void abfragen() {
		//ldapsearch -x -b dc=example,dc=com
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
					
		JLabel		hostname	= new JLabel();
					hostname.setText("Host");
					
					host		= new JTextField();
					host.setText("localhost");
					host.setMaximumSize(new Dimension(400,400));
					
		JLabel		portname		= new JLabel();
					portname.setText("Port: 389");
					
					port		= new JTextField();
					port.setText("389");
					port.setMaximumSize(new Dimension(400,400));
					

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
					panel.add(hostname);
					panel.add(host);
					panel.add(portname);
					panel.add(port);
					panel.add(ExitButton);
					panel.add(OKButton);
					frame.add(panel);
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					frame.pack();		
	}
	

	private void ldaptree(String output) {
		// TODO Auto-generated method stub
		String[] parts = output.split("dn:");

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
	
					String [] orgunits;
					for(int i=0; i<parts.length; i++)
					{		
						orgunits = parts[i].split(",");
						child = new DefaultMutableTreeNode(parts[i]);
						root.add(child);
					}
			
		JTree tree = new JTree(root);
	  			panel.add(tree, BorderLayout.CENTER);
	  			panel.add( new JScrollPane( tree ) );
	  		
	  			frame.getContentPane().add(panel);
	  			frame.pack();
	  			frame.setVisible( true );
	
	
	  			tree.getSelectionModel().addTreeSelectionListener
	  			(
	  					new TreeSelectionListener()
	  					{
	  						private void delete(String pathstringtodelete) {
	  							// TODO Auto-generated method stub
	  							buttons2 dir 		= new buttons2();
	  							String 	ldapdir		= dir.zu_holender_text();
	  							String first_node	=	ldapsearch.getText();
	  							String second_node	=	ldapsearch2.getText();
	  							String admin		=	cntext.getText();
	  							String password		=	Passwordtext.getText();
	  							String hostname		=	host.getText();
	  							String portname		= 	port.getText();
	  							int laenge = first_node.length()+2+second_node.length()+2;
			
	  							String substring1=pathstringtodelete.substring(laenge,pathstringtodelete.length()-2);
	  							System.out.println(ldapdir+"ldapdelete -x -D cn="+admin+ ",dc="+first_node+",dc="+second_node+ " -w "+password+substring1
	  									+" -h "+hostname+" -p "+portname); 
			
	  							String stringdelete = ldapdir+"ldapdelete -x -D cn="+admin+ ",dc="+first_node+",dc="+second_node+ " -w "
	  									+password+substring1+" -h "+hostname+" -p "+portname;
			
			
	  							int result=	JOptionPane.showOptionDialog(null, stringdelete,"Wirklich löschen",
	  									JOptionPane.YES_NO_CANCEL_OPTION,
	  									JOptionPane.WARNING_MESSAGE, null, 
	  									new String[]{"Delete", "Cancel"}, "Cancel");
			
	  							if (result == JOptionPane.YES_OPTION) {
	  								//call_process.processe_with_string(stringdelete);
	  							} else if (result == JOptionPane.NO_OPTION) {
	  						}
	  					}
		
	  					@Override public void valueChanged( TreeSelectionEvent e )
	  					{
	  						TreePath path = e.getNewLeadSelectionPath();
	  						String pathstringtodelete = path.toString();
	  						delete(pathstringtodelete);
	  					}
	  				} 
	  			);	
	}
	
	public void ldapdelete() {
		// TODO Auto-generated method stub
		abfragen();		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == OKButton)
		{
			buttons2 dir 		= new buttons2();
			String 	ldapdir		= dir.zu_holender_text();
			String 			dc1				= ldapsearch.getText();
			String 			dc2				= ldapsearch2.getText();
			String			hostname		= host.getText();
			String			portname		= port.getText();
			String 			command 		= ldapdir+"ldapsearch -x -b dc="+dc1+",dc="+dc2+" -h "+hostname+" -p "+portname;
			StringBuffer 	returncommandos = call_process.processe_with_string(command);
			String 			daten		    = returncommandos.toString();
			//System.out.println(command);
			
			String[] parts = daten.split("\n");
			StringBuffer output = new StringBuffer(); 	
			
			for(int i=0; i<parts.length;i++)
			{
				if(parts[i].contains("dn:"))
				{	
					output.append(parts[i] + "\n");
				}
			}				
			ldaptree(output.toString());
			//System.out.println(output.toString());
		}
		
		if(e.getSource() == delete)
		{		
		}
		
	}	
}
