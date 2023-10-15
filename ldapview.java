import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ldapview implements ActionListener{

	processe call_process 					= 	new processe();
	//DefaultMutableTreeNode child,subchild	=	null;
	
	static private JButton			OKButton;
	static private JTextField		ldapsearch;
	static private JTextField 		ldapsearch2;
	static private JTextField		host;
	static private JTextField		port;
	static private JButton			delete;
	private static JTextField		cntext;
	private static JPasswordField	Passwordtext;
	static DefaultMutableTreeNode root = null;
	static DefaultMutableTreeNode child	=	null;
	static DefaultMutableTreeNode subchild	= 	null;
	static DefaultMutableTreeNode subsubchild =   null;
	static JTree tree;
	private static DefaultMutableTreeNode first_node= null;
	private static DefaultMutableTreeNode second_node= null;
	private static DefaultMutableTreeNode second_node3= null;
	private static DefaultMutableTreeNode first_node2= null;
	
	
	public void view() {
		// TODO Auto-generated method stub
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == OKButton)
		{
			buttons2 		dir 			= new buttons2();
			String			ldapdir			= dir.zu_holender_text();
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
			StringBuffer output_unique = new StringBuffer();
			
			for(int i=0; i<parts.length;i++)
			{
				if(parts[i].contains("dn:"))
				{	
					output.append(parts[i] + "\n");
				}
				
			}				
						
			ldaptree(output.toString());
			ldaptree_final(output.toString());
			//System.out.println(output.toString());
		}
		
		if(e.getSource() == delete)
		{		
		}
	}

	private void ldaptree(String output) {
		// TODO Auto-generated method stub
		String[] parts = output.split("dn:");

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
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
		
	}
		
	
	private String loesche_dn(String node)
	{
		   node = node.replaceAll("dn: ", "");
		   node = node.replaceAll(",", "");

		   return node;
	}
	
	private void ldaptree_final(String output_final) {
		
		
		
		JDialog meinJDialog = new JDialog();
    	meinJDialog.setTitle("JScrollPane Beispiel");
    	meinJDialog.setSize(450,300);

    	String first_node	=	ldapsearch.getText();
		String second_node	=	ldapsearch2.getText();
		DefaultMutableTreeNode first_node3	=	null;

		root = new DefaultMutableTreeNode(first_node+"."+second_node);
		
		
		String[] 	temp 	= output_final.split("\n");
		String 	matcher		= temp[0];
		  		matcher		= matcher.replaceAll("dn: ", "");
		
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode( matcher );
		//DefaultMutableTreeNode first_node2= null;
		
		for(int i=1; i<temp.length; i++)
		{
			if(temp[i].contains(matcher) && ! temp[i].contains("cn"))
			{
				String node = temp[i].replaceAll(matcher, "");
						node=loesche_dn(node);			
				first_node2 = new DefaultMutableTreeNode( node );
				root.add( first_node2 );		
				String[] helper = temp[i].split(",");
							
				pruefe_unternodes(helper, output_final);
				
			}
			else
			{
				String[] ausweich=temp[i].split(",");
				//Geher hier rein wenn der String maximal 3 Einträge umfasst
				//bei mehr geht er woanders eh hin :-) 
				if(ausweich.length==3)
				{
					String endstring=ausweich[0];
					endstring=loesche_dn(endstring);
					first_node3 = new DefaultMutableTreeNode( endstring );
					root.add(first_node3);
				}
			}
		}
	

		//create the tree by passing in the root node
		tree = new JTree(root);
		meinJDialog.add(tree);
 
		//meinJDialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		meinJDialog.setTitle("JTree Example");        
		meinJDialog.pack();
		meinJDialog.setVisible(true);
		
}
	
	private void pruefe_unternodes(String[] helper, String daten) {
		// TODO Auto-generated method stub
	
		
		String node = helper[0].replaceAll("dn: ", "");
		String[] 	temp 	= daten.split("\n");
		
		for(int i=0; i<temp.length; i++) {
			if(temp[i].contains(node) && ! temp[i].startsWith("dn: "+node))
				{
				 String[] second_node2=temp[i].split(",");
				 String endstring=second_node2[0];
				 	endstring=loesche_dn(endstring);
				 second_node= new DefaultMutableTreeNode(endstring);
				 first_node2.add(second_node);
				}
			
			}
		
		}
		
}

