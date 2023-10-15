import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class slapadd implements ActionListener{
	
	processe call_process = new processe();
	private static JButton 			OKButton;
	private static JButton			ExitButton;
	private static File  			selectedFile;
	private static JTextField		cntext;
	private static JTextField		dc1text;
	private static JTextField		dc2text;
	private static JTextField		hostname;
	private static JTextField		portname;
	private static JPasswordField	Passwordtext;
	private static JFrame			frame;
	private static JFileChooser		jfc;
	
	private void daten_abfragen()
	{
		//ldapadd -x -D cn=Administrator,dc=example,dc=com -w secret -f orgunit
		//JFrame 		frame = new JFrame("ldapadd -x -D cn=Administrator,dc=example,dc=com -w secret -f orgunit");
	
		jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("LDapFile open");
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
						frame = new JFrame("ldapadd -x -D cn=Administrator,dc=example,dc=com.....");
			JPanel 		panel = new JPanel();
						panel.setSize(400, 600);
						panel.setLayout(new GridLayout(0,2));
						
			JLabel		cn	   	= new JLabel();
						cn.setText("cn zum Beispiel Administrator ");			
						panel.add(cn);
						
						cntext	= new JTextField();
						cntext.setText("Administrator");
						panel.add(cntext);
						
			JLabel		dc1		= new JLabel();
						dc1.setText("dc=example");
						panel.add(dc1);
						
						dc1text	= new JTextField();
						dc1text.setText("example");
						panel.add(dc1text);
			
			JLabel		dc2		= new JLabel();
						dc2.setText("dc=com");
						panel.add(dc2);
						
						dc2text	= new JTextField();
						dc2text.setText("com");
						panel.add(dc2text);
						
			JLabel		host	= new JLabel();
						host.setText("host=localhost");
						panel.add(host);
						
						hostname = new JTextField();
						hostname.setText("localhost");
						panel.add(hostname);
						
			JLabel		port	 = new JLabel();
						port.setText("Port=389");
						panel.add(port);
			
						portname = new JTextField();
						portname.setText("389");
						panel.add(portname);
				
			JLabel		Password		= new JLabel();
						Password.setText("Password");
						panel.add(Password);
						
						Passwordtext	= new JPasswordField();
						Passwordtext.setText("secret");
						panel.add(Passwordtext);
						
						ExitButton = new JButton();
						ExitButton.setText("Cancel");
						ExitButton.addActionListener(this);
						panel.add(ExitButton);
						
						OKButton = new JButton();
						OKButton.setText("Okay send command");
						OKButton.addActionListener(this);			
						panel.add(OKButton);
						
						frame.add(panel);
						frame.setLocationRelativeTo(null);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(true);
						frame.pack();		
		}
	}
	
	public void ldapaddition()
	{
		daten_abfragen();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == OKButton)
		{
			String cn  		= cntext.getText();
			String dc1 		= dc1text.getText();
			String dc2 		= dc2text.getText();
			String host 	= hostname.getText();
			String port		= portname.getText();
			String Pw  		= Passwordtext.getText();
			
			selectedFile 	= jfc.getSelectedFile();
			String file 	= selectedFile.getAbsolutePath();
			
			buttons2 dir 	= new buttons2();
			String   ldapdir=dir.zu_holender_text();
			
			String 			ldapadd			= ldapdir+"ldapadd -x -D cn="+cn+",dc="+dc1+",dc="+dc2+" -w "+Pw+" -f "+file
										+" -h "+host+" -p "+port;
			StringBuffer 	returncommandos = call_process.processe_with_string(ldapadd);
			//String 			daten		    = returncommandos.toString();
			//System.out.println(ldapadd);
			Output2 Output = new Output2();
					Output.Datenausgabe(returncommandos);
		}
		
		if(e.getSource() == ExitButton)
		{
			frame.setVisible(false);
		}	
	}
}
