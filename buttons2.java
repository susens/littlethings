import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class buttons2 implements ActionListener {
	private static JButton LdapSearchLocalhost;
	private static JButton LdapSearchxhost;
	private static JButton LdapSearchverschluesselt;
	private static JButton Slapcat;
	private static JButton Slapadd;
	private static JButton Slapdelete;
	private static JButton Slapmodifiy;
	private static JButton View;
	private static JButton Commandos;
	private static JTextField ldapdir;	

	static processe call_process = new processe();
	static Output 	 output_daten = new Output();
			
	/*sendet ein LDAPSearch fÃ¼r den localen Host an und wartet auf das Ergebnis*/
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == LdapSearchLocalhost){ 
			String 			command 		= "ldapsearch -x -h localhost";
			StringBuffer 	returncommandos = call_process.processe_with_string(command);
							output_daten.Datenausgabe(returncommandos);
							
		}	
		
		/*fragt den Host ab und sendet dann das Ergebnis */
		if(e.getSource() ==LdapSearchxhost) {
			//localerHost();
			newMainFrame 	main		= new newMainFrame();
							main.localerHost();
		}
		
		if(e.getSource() == LdapSearchverschluesselt){
			verschluesselt verschluesselt = new verschluesselt();
							verschluesselt.ldapssearch();
		}
		
		
		//rufe klasse ldapsearch auf und von dort aus gehe weiter..... 
		if(e.getSource() == Slapcat)
		{
			slapcat ldapsearch = new slapcat();
					ldapsearch.ldapsearch();	
		}
		
		if(e.getSource() == Slapadd)
		{
			slapadd ldapadd = new slapadd();
					ldapadd.ldapaddition();
		}
		
		if(e.getSource() == Slapdelete)
		{
			slapdelete  ldapdeletex = new slapdelete();
						ldapdeletex.ldapdelete();
			
			/*			
			slapdelete_final ldapdelete = new slapdelete_final();
							 ldapdelete.ldapdelete();
			*/		
		}
		
		if(e.getSource() == Slapmodifiy)
		{
			//ldapmodify -x -D cn=Administrator,dc=example,dc=com -W -f tux.ldif
		}
		
		if(e.getSource() == View)
		{
			ldapview view = new ldapview();
					  view.view();
		}
		
		/*sendet beliebige Kommandos auf der Kommandozeile dafuer wird ein neuer
		 * Frame geÃ¶ffnet der darauf wartet das kommandos gesendet werden */
		if(e.getSource() == Commandos){ 
			send kommandos = new send();
			kommandos.sendcommandos();
		}	
	}
	


	private static void addComponentsToPane(Container contentPane) {
		// TODO Auto-generated method stub
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		LdapSearchLocalhost = new JButton("LdapSearchLocalhost");
		LdapSearchLocalhost.setMaximumSize(new Dimension(400,400));
		LdapSearchLocalhost.addActionListener(new buttons2());
		contentPane.add(LdapSearchLocalhost);
		
		LdapSearchxhost = new JButton("LdapSearch beliebiger Host");
		LdapSearchxhost.setMaximumSize(new Dimension(400,400));
		LdapSearchxhost.addActionListener(new buttons2());
		contentPane.add(LdapSearchxhost);
		
		
		LdapSearchverschluesselt = new JButton("LdapS beliebiger Host");
		LdapSearchverschluesselt.setMaximumSize(new Dimension(400,400));
		LdapSearchverschluesselt.addActionListener(new buttons2());
		contentPane.add(LdapSearchverschluesselt);
	    
	    
		Slapcat = new JButton("Ldapsearch");
		Slapcat.setMaximumSize(new Dimension(400,400));
		Slapcat.addActionListener(new buttons2());
		contentPane.add(Slapcat);
		
		Slapadd = new JButton("Ldapadd");
		Slapadd.setMaximumSize(new Dimension(400,400));
		Slapadd.addActionListener(new buttons2());
		contentPane.add(Slapadd);
	    
		Slapmodifiy = new JButton("LdapModify");
		Slapmodifiy.setMaximumSize(new Dimension(400,400));
		Slapmodifiy.addActionListener(new buttons2());
		contentPane.add(Slapmodifiy);
	
		Slapdelete = new JButton("LdapDelete");
		Slapdelete.setMaximumSize(new Dimension(400,400));
		Slapdelete.addActionListener(new buttons2());
		contentPane.add(Slapdelete);

		View = new JButton("LdapView");
		View.setMaximumSize(new Dimension(400,400));
		View.addActionListener(new buttons2());
		contentPane.add(View);

		
		Commandos = new JButton("Commandos Unix/Windows");
		Commandos.setMaximumSize(new Dimension(400,400));
		Commandos.addActionListener(new buttons2());
		contentPane.add(Commandos);
		
		String os = "os.name";
		Properties prop = System.getProperties( );
		String Betriebssyste=prop.getProperty(os);
		
		
		if(Betriebssyste.contentEquals("Linux"))
		{
			String command1="which ldapsearch";
			StringBuffer whichldap = call_process.processe_with_string(command1);
			String whichldaptostring=whichldap.toString();
			String Pfad = whichldaptostring.substring(0, whichldaptostring.length()-11);
			ldapdir = new JTextField();		
			ldapdir.setText(Pfad);
			contentPane.add(ldapdir);
		}
		if(Betriebssyste.contains("Windows"))
		{	
			ldapdir = new JTextField();		
			ldapdir.setText("C:\\OpenLDAP\\ClientTools\\");
			contentPane.add(ldapdir);
		}

		
			
	}
	
	//Ist dafür das der String überall eingeholt werden kann
	public static String zu_holender_text()
	{
		String Textfield=ldapdir.getText();
		return Textfield;
		
	}
	
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("LDap Commandos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
