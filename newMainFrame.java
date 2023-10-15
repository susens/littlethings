import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class newMainFrame implements ActionListener{

		private static JButton cmd;
		private static JButton ldapcmd;
		private static JButton ExitButton;
		private static JButton CancelButton;
	
		private static JPanel 	panel;
		private static JFrame 	frame;
		
		private static JTextField textField;
		private static JTextField ldapsearch;
		private static JTextField ldapport;
		private static JTextField port;
		
		private static JTextField ldapdn1;
		private static JTextField ldapdn2;
		buttons2 test = new buttons2();
		
		public void localerHost()
		{
			
			String host		= "localhost";
			String command 	= "ldapsearch -x -h"+host;
						frame = new JFrame("ldapsearch -x -h"+host+"-b example.com");
						panel = new JPanel();
						panel.setLayout(new GridLayout(0,2));
								        
						ldapsearch = new JTextField();		
						ldapsearch.setText("localhost");
						ldapsearch.setMaximumSize(new Dimension(400,400));
					  	
						ldapport = new JTextField();		
						ldapport.setText("389");
						ldapport.setMaximumSize(new Dimension(400,400));
						
						ldapdn1 = new JTextField();		
						ldapdn1.setText("example");
						ldapdn1.setMaximumSize(new Dimension(400,400));
					  	
						ldapdn2 = new JTextField();		
						ldapdn2.setText("com");
						ldapdn2.setMaximumSize(new Dimension(400,400));
						
					  	ldapcmd	= new JButton();
					  	ldapcmd.addActionListener(this);
					  	ldapcmd.setText("ldapsearch -x -h "+ldapsearch.getText()+" -p "+ldapport.getText()
					  			+" -b dc="+ldapdn1.getText()+",dc="+ldapdn2.getText());

					  	port	= new JTextField();
					  	port.setText(": Portnummer");
					  	port.setEditable(false);
					  	port.setBackground(Color.cyan);

					  
					  	
					  	
					  	ExitButton = new JButton();
						ExitButton.setText("Exit");
						ExitButton.addActionListener(this);;
						CancelButton = new JButton();
						CancelButton.setText("Cancel");
						CancelButton.addActionListener(this);
						panel.add(ldapsearch);
						panel.add(ldapcmd);		
						panel.add(ldapport);
						panel.add(port);
						panel.add(ldapdn1);
						panel.add(ldapdn2);
						panel.add(ExitButton);
						panel.add(CancelButton);
						frame.add(panel);
						frame.setLocationRelativeTo(null);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(true);
						frame.pack();		
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			processe call_process = new processe();
			if(e.getSource() == ldapcmd)
			{
				String ldapdir=test.zu_holender_text();
				String 			host 			= ldapsearch.getText();
				String 			port			= ldapport.getText();
				String 			command 		= ldapdir+"ldapsearch -x -h "+host+" -p "+port
													+" -b dc="+ldapdn1.getText()+",dc="+ldapdn2.getText();
				System.out.println(command);
				StringBuffer	returncommandos = call_process.processe_with_string(command);
				Output 			l1 				= new Output();
								l1.Datenausgabe(returncommandos);
			}
			
			if(e.getSource() == ExitButton)
			{
				panel.setVisible(false);
				frame.setVisible(false);
			}
			if(e.getSource() == CancelButton)
			{
			}
		}
	}

