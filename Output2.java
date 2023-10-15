import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Output2 {
		public void Datenausgabe(StringBuffer returncommandos) {
			// TODO Auto-generated method stub
			String datenausgabe = returncommandos.toString();
			if (! datenausgabe.isEmpty())
			{
				System.out.println(datenausgabe);
				JOptionPane.showMessageDialog(null, datenausgabe, 
						"Returns", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				System.out.println("It came a empty string back, sure that the ldapserver is running?");
			}
		}
	}
