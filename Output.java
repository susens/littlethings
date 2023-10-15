import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Output {
		public void Datenausgabe(StringBuffer returncommandos) {
			// TODO Auto-generated method stub
			String datenausgabe = returncommandos.toString();
			
			if (! datenausgabe.isEmpty())
			{
				if ( datenausgabe.contains( "result: 0 Success" ) )
				{
					JOptionPane.showMessageDialog(null, "The System run without Errors", 
							"result: 0 Success", JOptionPane.PLAIN_MESSAGE);
					System.out.println("The System run without Errors");
					System.out.println("result: 0 Success");
				}else
				{
					JOptionPane.showMessageDialog(null, "The System run with Errors", 
							"check commandoline ", JOptionPane.OK_OPTION);
					System.out.println("The System run with a failure, output on commandoline");
					System.out.println(datenausgabe);
				}
			}else
			{
				JOptionPane.showMessageDialog(null, "It Cam a empty string back ldap server is running?", "check commandoline ", JOptionPane.OK_OPTION);
				System.out.println("It came a empty string back, the ldapserver is running?");
			}
		}
	}
