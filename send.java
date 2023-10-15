import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class send implements ActionListener{
	
	private static JTextField textField;
	private static JButton cmd;

	public void sendcommandos() {
		JFrame 		frame = new JFrame("JFrame Example");
		JPanel 		panel = new JPanel();
					panel.setLayout(new FlowLayout());
				
					textField = new JTextField();		
					textField.setText("Linux/Windows Kommando einfügen");
				  	textField.setMaximumSize(new Dimension(400,400));
				  					  	
				  	cmd	= new JButton();
					cmd.addActionListener(this);
					cmd.setText("Linux Kommando absenden");
					panel.add(textField);
					panel.add(cmd);				  	
					frame.add(panel);
					frame.setSize(300, 300);
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					frame.pack();
					
				  	// catch the enter key in the JTextField and perform an OK click on the JButton
				  	textField.addKeyListener(new KeyAdapter() {
				  		public void keyPressed(KeyEvent e) {
				  			if (e.getKeyCode()==KeyEvent.VK_ENTER) { 
				  				cmd.doClick();
				            }
				         }
				      });
				  	
				  	textField.addMouseListener(new MouseAdapter() {
				  	    @Override
				  	    public void mouseClicked(MouseEvent e) {
				  	        textField.setText("");
				  	    }
				  	});

	}
	
	private void Datenaufbereitung(String datenausgabe) {
		
		if(!datenausgabe.isEmpty())
		{
			JFrame 		frame = new JFrame("Output Linux Kommandos");
			JPanel 		panel = new JPanel();
			JTextArea	text  = new JTextArea(50,80);
						text.setEditable(false);
						text.setText(datenausgabe);
			JScrollPane jScrol= new JScrollPane(text);
		
						panel.setLayout(new BorderLayout());
						panel.add(jScrol, BorderLayout.CENTER);
						//add(panel);
					
						panel.setPreferredSize(new Dimension(600,400));
						frame.getContentPane().add(panel);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(true);
						frame.pack();	
						System.out.println(datenausgabe);
						
		}
	}
	
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == cmd){ 
			processe call_process2 = new processe();
			String commando=textField.getText();
			StringBuffer returncommandos = call_process2.processe_with_string(commando);
			String 		 datenausgabe    = returncommandos.toString();
			
			Datenaufbereitung(datenausgabe);		
		}
		
		

		
	}
}
