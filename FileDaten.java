import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.scene.control.Slider;
import javafx.stage.FileChooser;


public class FileDaten implements ActionListener  {

	private static JButton		text;
	private static JButton 		showFileDialogButton;
	private static JTextField	homedir;
	private static JTextField	textField;
	private static JTextField	textFieldx;
	private static JTextField	textFieldy;
	private static FileChooser fileChooser;
	private static JCheckBox 	checkboxy;
	private static JPanel       panel_textfield;
	private static JButton read; 
	private static JButton read_all; 
	private static JButton write; 
	private static JButton write_all; 
	private static JButton excecute; 
	private static JButton excecute_all;	
	private static JButton exit;
	private static JButton read_no; 
	private static JButton read_all_no; 
	private static JButton write_no; 
	private static JButton write_all_no; 
	private static JButton excecute_no; 
	private static JButton excecute_all_no;	
	private static JButton Okay;

	
	
	JFileChooser fc;
	FileDialog fd; 
	static int zaehler = 0;
		
	
	public static void aufbau_textField(){
		JFrame frame_textdaten = new JFrame("Datenanzeige");
		frame_textdaten.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame_textdaten.setBounds(30, 30, 300, 300);
		
		addComponentsToPane2(frame_textdaten.getContentPane());
		
		frame_textdaten.setSize(300, 400);
		frame_textdaten.pack();
		frame_textdaten.setVisible(true);
	}
	
	
	 private static void addComponentsToPane2(Container contentPane) {
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		panel_textfield = new JPanel();
		panel_textfield.setLayout(new GridLayout(0,1));
		panel_textfield.setPreferredSize(new Dimension(400, 300));
		
		textFieldx = new JTextField("");
		//textFieldx.setEnabled(false);
		String helper = homedir.getText();
		textFieldx.setText(helper);
		panel_textfield.add(textFieldx);

		JPanel panel_textfield2 = new JPanel();
		panel_textfield2.setLayout(new GridLayout(0,7));
		
		
		read = new JButton("Read File Owner");
		read.addActionListener(new FileDaten());
		panel_textfield2.add(read);
		
		read_all = new JButton("Read File All");
		read_all.addActionListener(new FileDaten());		
		panel_textfield2.add(read_all);
		
		write = new JButton("Write File Owner");
		write.addActionListener(new FileDaten());
		panel_textfield2.add(write);
		
		write_all = new JButton("Write File All");
		write_all.addActionListener(new FileDaten());
		panel_textfield2.add(write_all);
		
		excecute = new JButton("Excecute File Owner");
		excecute.addActionListener(new FileDaten());
		panel_textfield2.add(excecute);
		
		excecute_all = new JButton("Excecute File All");
		excecute_all.addActionListener(new FileDaten());
		panel_textfield2.add(excecute_all);
		
		exit = new JButton("Exit");
		exit.addActionListener(new FileDaten());
		panel_textfield2.add(exit);

		JPanel panel_textfield3 = new JPanel();
		panel_textfield2.setLayout(new GridLayout(0,7));
		
		
		read_no = new JButton("Read File Owner No");
		read_no.addActionListener(new FileDaten());
		panel_textfield2.add(read_no);
		
		read_all_no = new JButton("Read File All No");
		read_all_no.addActionListener(new FileDaten());		
		panel_textfield2.add(read_all_no);
		
		write_no = new JButton("Write File Owner No");
		write_no.addActionListener(new FileDaten());
		panel_textfield2.add(write_no);
		
		write_all_no = new JButton("Write File All No");
		write_all_no.addActionListener(new FileDaten());
		panel_textfield2.add(write_all_no);
		
		excecute_no = new JButton("Excecute File Owner No");
		excecute_no.addActionListener(new FileDaten());
		panel_textfield2.add(excecute_no);
		
		excecute_all_no = new JButton("Excecute File All No");
		excecute_all_no.addActionListener(new FileDaten());
		panel_textfield2.add(excecute_all_no);
		
		Okay = new JButton("Okay");
		Okay.addActionListener(new FileDaten());
		panel_textfield2.add(Okay);
		
		contentPane.add(panel_textfield);	
		contentPane.add(panel_textfield2);
		contentPane.add(panel_textfield3);
	}

	public static void listDir(File dir) {
		
		 File[] files = dir.listFiles();
		 if (files != null) {
			 for (int i = 0; i < files.length; i++) {
				 if (files[i].isDirectory()) {
					 listDir(files[i]);
				 }
				 else {
					 zaehler++;
					 System.out.print(files[i].getAbsolutePath());
					 System.out.print("\n");
					 
					 textFieldy = new JTextField("");
						//textFieldx.setEnabled(false);
					 textFieldy.setText(files[i].getAbsolutePath()+"\n");
					 files[i].setExecutable(true, true);
					 textFieldy.setSize(new Dimension(300,800));
					 panel_textfield.add(textFieldy);
					 
				}			
			}	 
		}
	}
	
	public static void daten_change(File dir, String gesetzt) {
		File[] files = dir.listFiles();
		if (files != null) {
			 for (int i = 0; i < files.length; i++) {
				 if (files[i].isDirectory()) {
					 daten_change(files[i], gesetzt);
				 }
				 else {
					 
					 if(gesetzt=="read")
					 {
						 boolean reado = files[i].setReadable(true);
					 }
					
					 if(gesetzt=="read_all")
					 {
						 //System.out.println(gesetzt);
						 boolean reada = files[i].setReadable(true, true);
					 }

					 if(gesetzt=="write")
					 {
						 //System.out.println(gesetzt);
						 boolean writeo = files[i].setWritable(true);
					 }
					
					 if(gesetzt=="write_all")
					 {
						 //System.out.println(gesetzt);
						 boolean writea = files[i].setWritable(true, true);
					 }
					 
					 if(gesetzt=="excecute")
					 {
						 //System.out.println(gesetzt);
						 boolean executeo = files[i].setExecutable(true);
					 }
					
					 if(gesetzt=="excecute_all")
					 {
						 //System.out.println(gesetzt);
						 boolean executea = files[i].setExecutable(true, true);
					 }

					 if(gesetzt=="read_no")
					 {
						 boolean reado = files[i].setReadable(false);
					 }
					
					 if(gesetzt=="read_all_no")
					 {
						 //System.out.println(gesetzt);
						 boolean reada = files[i].setReadable(false, false);
					 }

					 if(gesetzt=="write_no")
					 {
						 //System.out.println(gesetzt);
						 boolean writeo = files[i].setWritable(false);
					 }
					
					 if(gesetzt=="write_all_no")
					 {
						 //System.out.println(gesetzt);
						 boolean writea = files[i].setWritable(false, false);
					 }
					 
					 if(gesetzt=="excecute_no")
					 {
						 //System.out.println(gesetzt);
						 boolean executeo = files[i].setExecutable(false);
					 }
					
					 if(gesetzt=="excecute_all_no")
					 {
						 //System.out.println(gesetzt);
						 boolean executea = files[i].setExecutable(false, false);
					 }

					 
					 
				 }
			 }
		}
	}

	
	 private static void addComponentsToPane(Container contentPane) {
		 	contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
			JPanel  panel = new JPanel();
			panel.setLayout(new GridLayout(0,2));
			
			showFileDialogButton = new JButton("Open Directory");
			showFileDialogButton.addActionListener(new FileDaten());
			panel.add(showFileDialogButton);

			homedir = new JTextField();		
			homedir.setText("/home/strzempek/Programmierung/contentstore");
			panel.add(homedir);
			
			JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(0,1));
			
			text = new JButton();
			text.setText("Verzeichnis nach bin durchsuchen");
			text.addActionListener(new FileDaten());		
			panel2.add(text);

			JPanel panel3 = new JPanel();
			panel3.setLayout(new GridLayout(0,2));
			
			contentPane.add(panel);	
			contentPane.add(panel2);
	 }
	 	 
	 private static void createAndShowGUI() {
			JFrame frame = new JFrame("Dateneingabe");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setBounds(30, 30, 300, 300);
	        //Set up the content pane.
	        
			addComponentsToPane(frame.getContentPane());
			
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
		}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//File f = new File("/home/strzempek/Programmierung/contentstore");
		//listDir(f);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
	     });	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == text)
		{
			String textfield = homedir.getText();
			File f = new File(textfield);
			aufbau_textField();
			listDir(f);
			System.out.println(textfield);
			
		}
		
		if(e.getSource() == showFileDialogButton)
		{
		      JFileChooser chooser = new JFileChooser();
		      chooser.setCurrentDirectory(new java.io.File("."));
		      chooser.setDialogTitle("Select Dir");
		      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		      chooser.setAcceptAllFileFilterUsed(false);

		      if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		        System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
		        File textfield = chooser.getCurrentDirectory();
		        aufbau_textField();
		        //System.out.println(textfield.toString());
		        homedir.setText("");
		        homedir.setText(textfield.toString());
		        //listDir(textfield);
		      
		      } else {
		        System.out.println("No Selection ");
		      }
		 }
		
		 if(e.getSource() == exit)
		 {
			 System.exit(0);
		 }
	
		 if(e.getSource() == read)
		 {
			 	String textfield = textFieldx.getText();
			 	String read="read";
				File daten_uebertragung = new File(textfield);
				daten_change(daten_uebertragung,read);
		 }
		
		 if(e.getSource() == read_all)
		 {
			 	String textfield = textFieldx.getText();
			 	String read_all="read_all";
				File daten_uebertragung = new File(textfield);
				daten_change(daten_uebertragung,read_all);
		 }

		 if(e.getSource() == write)
		 {
			 	String textfield = textFieldx.getText();
			 	String write="write";
				File daten_uebertragung = new File(textfield);
				daten_change(daten_uebertragung,write);

		 }
		
		 if(e.getSource() == write_all)
		 {
			 	String textfield = textFieldx.getText();
			 	String write_all="write_all";
				File daten_uebertragung = new File(textfield);
				daten_change(daten_uebertragung,write_all);
		 }

		 if(e.getSource() == excecute)
		 {
			 	String textfield = textFieldx.getText();
			 	String excecute="excecute";
				File daten_uebertragung = new File(textfield);
				daten_change(daten_uebertragung,excecute);

		 }
		
		 if(e.getSource() == excecute_all)
		 {
			 	String textfield = textFieldx.getText();
			 	String excecute_all="excecute_all";
				File daten_uebertragung = new File(textfield);
				daten_change(daten_uebertragung,excecute_all);
		 }
		 
		 if(e.getSource() == read_no)
		 {
			 	String textfield = textFieldx.getText();
			 	String read="read_no";
				File daten_uebertragung = new File(textfield);
				daten_change(daten_uebertragung,read);
		 }
		
		 if(e.getSource() == read_all_no)
		 {
			 	String textfield = textFieldx.getText();
			 	String read_all="read_all_no";
				File daten_uebertragung = new File(textfield);
				daten_change(daten_uebertragung,read_all);
		 }

		 if(e.getSource() == write_no)
		 {
			 	String textfield = textFieldx.getText();
			 	String write="write_no";
				File daten_uebertragung = new File(textfield);
				daten_change(daten_uebertragung,write);

		 }
		
		 if(e.getSource() == write_all_no)
		 {
			 	String textfield = textFieldx.getText();
			 	String write_all="write_all_no";
				File daten_uebertragung = new File(textfield);
				daten_change(daten_uebertragung,write_all);
		 }

		 if(e.getSource() == excecute_no)
		 {
			 	String textfield = textFieldx.getText();
			 	String excecute="excecute_no";
				File daten_uebertragung = new File(textfield);
				daten_change(daten_uebertragung,excecute);

		 }
		
		 if(e.getSource() == excecute_all_no)
		 {
			 	String textfield = textFieldx.getText();
			 	String excecute_all="excecute_all_no";
				File daten_uebertragung = new File(textfield);
				daten_change(daten_uebertragung,excecute_all);
		 }
	}
}

