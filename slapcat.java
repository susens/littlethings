import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class slapcat extends JFrame implements ActionListener{
	
	processe call_process = new processe();
	private static	JTextArea		text;
	private static	JButton			ExitButton;
	private static	JButton			OKButton;
	private static	JTextField		ldapsearch;
	private static	JTextField		ldapsearch2;
	private static	JTextField		ldapport;
	private static	JTextField		ldaphost;
	private static 	JFrame 			frame;
	private static  JPanel		 	panel;
	
	private void initComponents() {
			JFrame	frame = new JFrame("Output LdapSearch -x -b dc=example, dc=com");
			JPanel	panel = new JPanel();
					text  = new JTextArea(50,80);
					text.setEditable(false);
		JScrollPane jScrol= new JScrollPane(text);
		
					panel.setLayout(new BorderLayout());
					panel.add(jScrol, BorderLayout.CENTER);
					add(panel);
					
					panel.setPreferredSize(new Dimension(600,400));
					frame.getContentPane().add(panel);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					frame.pack();	
	}
	
	private void abfragen() {
		//ldapsearch -x -b dc=example,dc=com
					frame = new JFrame("ldapsearch -x -b dc=example,dc=com");
					panel = new JPanel();
					panel.setLayout(new GridLayout(0,2));
					
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

		JLabel		port		= new JLabel();
					port.setText("Port zum Beispiel 389");
		
					ldapport	= new JTextField();
					ldapport.setText("389");
					ldapport.setMaximumSize(new Dimension(400,400));
				  	
		JLabel		host		= new JLabel();
					host.setText("Host zum Beispiel localhost");
					
					ldaphost	= new JTextField();
					ldaphost.setText("localhost");
					ldaphost.setMaximumSize(new Dimension(400,400));
					
					ExitButton = new JButton();
					ExitButton.setText("Cancel");
					ExitButton.addActionListener(this);
					OKButton = new JButton();
					OKButton.setText("Okay send command");
					OKButton.addActionListener(this);
					
					panel.add(text);
					panel.add(ldapsearch);		
					panel.add(text2);
					panel.add(ldapsearch2);		
					panel.add(host);
					panel.add(ldaphost);
					panel.add(port);
					panel.add(ldapport);
					panel.add(ExitButton);
					panel.add(OKButton);
					frame.add(panel);
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					frame.pack();		
	}
	
	public void ldapsearch()
	{
		abfragen();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == OKButton)
		{
			//ldapsearch -x -b dc=example,dc=com -h localhost -p 389
			buttons2 dir = new buttons2();
			String 			ldapdir			= dir.zu_holender_text();
			String 			host 			= ldapsearch.getText();
			String 			dc1				= ldapsearch.getText();
			String 			dc2				= ldapsearch2.getText();
			String			port			= ldapport.getText();
			String			hostdaten		= ldaphost.getText();
			String 			command 		= ldapdir+"ldapsearch -x -b dc="+dc1+",dc="+dc2+" -h "+hostdaten+" -p "+port;
			
			//System.out.println(command);
			StringBuffer 	returncommandos = call_process.processe_with_string(command);
			String 			daten		    = returncommandos.toString();
			initComponents();
			text.setText(daten);
		}
		if(e.getSource() == ExitButton)
		{
			frame.setVisible(false);
		}
	}
}
