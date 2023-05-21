package hangman;

import java.awt.*;
import java.io.*;
import java.util.*;
import com.opencsv.CSVReader;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("unused")
public class game {

	public JFrame frame;
	private String word,Inserita,giaInserite="",allWord1;
	@SuppressWarnings("unused")
	private CSVReader reader;
	private ArrayList<JLabel> a=new ArrayList<JLabel>();
	private ArrayList<String> records;
	private JTextField lettera;
	public JLabel score1;
	private JLabel hangy;
	private boolean giaTentato=true,giaAggiunto=false;
	private JButton punteggio1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					game window = new game();
					window.frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public game(){
		initialize();
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("static-access")
	private void initialize() {
	
			
		
		this.setWord();
		
		frame = new JFrame("HangMan - The Game");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(90, 110, 270, 45);
		frame.getContentPane().add(panel);
		frame.setResizable(false);
		panel.setBackground(new Color(0).WHITE);
		frame.setBackground(new Color(0).white);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("srcImg\\homeIcon.jpg"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		hangy = new JLabel();
		hangy.setHorizontalAlignment(SwingConstants.CENTER);
		hangy.setBackground(new Color(255, 255, 255));
		hangy.setIcon( new ImageIcon("srcImg\\1.png"));
		hangy.setBounds(170, 3, 100, 100);
		frame.getContentPane().add(hangy);
		this.addTheWord(panel);
		
		JButton inserisci = new JButton();
		inserisci.setIcon( new ImageIcon("srcImg\\send.png"));
		inserisci.setBounds(381, 225, 35, 35);
		frame.getContentPane().add(inserisci);
		
		lettera = new JTextField();
		lettera.setBounds(330, 203, 96, 19);
		frame.getContentPane().add(lettera);
		lettera.setColumns(10);
		
		JLabel piuCaratteri = new JLabel("hai aggiunto più di un carattere");
		piuCaratteri.setFont(new Font("Tahoma", Font.BOLD, 10));
		piuCaratteri.setBounds(202, 180, 225, 13);
		piuCaratteri.setVisible(false);
		piuCaratteri.setForeground(Color.RED);
		frame.getContentPane().add(piuCaratteri);
		
		JLabel lettereSbagliate = new JLabel("Lettere Sbagliate:");
		lettereSbagliate.setFont(new Font("Tahoma", Font.BOLD, 10));
		lettereSbagliate.setBounds(10, 204, 161, 16);
		lettereSbagliate.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lettereSbagliate);
		
		JLabel lettereSbagliate1 = new JLabel("");
		lettereSbagliate1.setBackground(new Color(242, 236, 159));
		lettereSbagliate1.setBounds(59, 225, 301, 16);
		lettereSbagliate1.setForeground(Color.RED);
		frame.getContentPane().add(lettereSbagliate1);
		
		
		
		JLabel score = new JLabel("Score:");
		score.setFont(new Font("Rockwell", Font.BOLD, 10));
		score.setHorizontalAlignment(SwingConstants.RIGHT);
		score.setBounds(356, 10, 36, 13);
		frame.getContentPane().add(score);
		
		score1 = new JLabel("6");
		score1.setFont(new Font("Tahoma", Font.BOLD, 10));
		score1.setHorizontalAlignment(SwingConstants.CENTER);
		score1.setForeground(Color.MAGENTA);
		score1.setBounds(381, 10, 45, 13);
		frame.getContentPane().add(score1);
		
		JLabel sbagliato = new JLabel("");
		sbagliato.setForeground(Color.RED);
		sbagliato.setBounds(248, 206, 10, 10);
		sbagliato.setBackground(Color.RED);
		sbagliato.setVisible(true);
		frame.getContentPane().add(sbagliato);
		
		JLabel corretto = new JLabel("");
		corretto.setForeground(Color.BLUE);
		corretto.setBounds(290, 206, 10, 10);
		corretto.setVisible(true);
		corretto.setBackground(Color.BLUE);
		frame.getContentPane().add(corretto);
		
		JButton restart = new JButton();
		restart.setIcon( new ImageIcon("srcImg\\reload.png"));
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				home a= new home();
				frame.setVisible(false);
				a.frame.setVisible(true);
				
			}
		});
		restart.setBounds(10, 225, 35, 35);
		frame.getContentPane().add(restart);
		
		JButton allWord = new JButton("");
		allWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(giaTentato) {
				allWord1= JOptionPane.showInputDialog(frame, "send all word:");
				giaTentato=!giaTentato;
				if(allWord1.toLowerCase().compareTo(word)==0&&allWord1.length()==word.length()) {
					if(!giaAggiunto) {
					score1.setText("0");
					Soundplay("srcSound//win.wav");
					JOptionPane.showMessageDialog(frame,"you won", "HangMan - The Game",JOptionPane.PLAIN_MESSAGE );
					changePunteggio(Integer.toString(Integer.parseInt(readPunteggio())+1));
					giaAggiunto=true;
					for(int i=0;i<word.length();i++) {
							a.get(i).setText(""+word.toLowerCase().charAt(i)+"");
						}
					
					}}
				else {
					score1.setText("1");
					JOptionPane.showMessageDialog(frame,"you have only one possibility", "HangMan - The Game",JOptionPane.PLAIN_MESSAGE );
					changeStatusBody();
				}}
			}
		});
		allWord.setBounds(10, 6, 30, 30);
		allWord.setIcon( new ImageIcon("srcImg\\indovinaAll.png"));
		frame.getContentPane().add(allWord);
		
		punteggio1 = new JButton(this.readPunteggio());
		punteggio1.setForeground(Color.GREEN);
		punteggio1.setFont(new Font("Gadugi", Font.BOLD, 10));
		punteggio1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePunteggio("0");
			}
		});
		punteggio1.setBounds(42, 20, 50, 50);
		frame.getContentPane().add(punteggio1);
		
		JLabel punteggio = new JLabel("Punteggio:");
		punteggio.setFont(new Font("Tahoma", Font.BOLD, 10));
		punteggio.setBounds(42, 7, 62, 13);
		frame.getContentPane().add(punteggio);
		
		
		inserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lettera.getText().length()>1||giaInserite.contains(lettera.getText().toString().toLowerCase())||Integer.parseInt(score1.getText().toString())<=0) {
					if(giaInserite.contains(lettera.getText().toLowerCase())){
					piuCaratteri.setText("hai già inserito questo carattere eo è nullo");
					}else if(lettera.getText().length()>1){
					piuCaratteri.setText("hai aggiunto più di un carattere");	
					}else if(Integer.parseInt(score1.getText().toString())<=0) {
					piuCaratteri.setText("hai già completoto la sessione");
					}
					
					piuCaratteri.setVisible(true);
					lettera.setText("".toLowerCase());
				}else {
					giaInserite+=lettera.getText().toLowerCase();
					Inserita=lettera.getText().toLowerCase();
					if(word.contains(Inserita.toLowerCase().toLowerCase())) {
						
						sbagliato.setVisible(false);
						corretto.setVisible(true);
						
						for(int i=0;i<word.length();i++) {
							if(word.charAt(i)==	Inserita.toLowerCase().charAt(0)) {
								a.get(i).setText(Inserita.toLowerCase());
							}
						}
						int s=0;
						for(int i=0;i<word.length();i++) {
							if(!a.get(i).getText().toString().contains("_".toLowerCase())) {
								s++;
							}
						}
						if(s>=word.length()&&!giaAggiunto) {
							score1.setText("0");
							Soundplay("srcSound//win.wav");
							changePunteggio(Integer.toString(Integer.parseInt(readPunteggio())+1));
							JOptionPane.showMessageDialog(frame,"you won", "HangMan - The Game",JOptionPane.PLAIN_MESSAGE );
							giaAggiunto=true;
						}
					}else{
						if(!lettereSbagliate1.getText().contains(Inserita.toLowerCase())){
							score1.setText(Integer.toString(Integer.parseInt(score1.getText())-1));
							lettereSbagliate1.setText(lettereSbagliate1.getText()+Inserita.toLowerCase());
						}
						sbagliato.setVisible(true);
						corretto.setVisible(false);
						changeStatusBody();
					}
					
				}
			}
		});
		lettera.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				piuCaratteri.setVisible(false);
				lettera.setText("".toLowerCase());
				sbagliato.setVisible(false);
				corretto.setVisible(false);
			}
		});
	}

public void addTheWord(JPanel p ) {
	for(int i=0; i<this.word.length();i++) {
		a.add(new JLabel("_".toLowerCase()));
		a.get(i).setBounds(new Rectangle(20,30));
	}
	for(int i=0; i<a.size();i++) {
		p.add(a.get(i));
	}
}
public void setWord1() throws FileNotFoundException, IOException{
	this.records = new ArrayList<String>();
try (CSVReader csvReader = new CSVReader(new FileReader("srcTxt\\parole_italiane.csv"));) {
    String[] values = null;
    while ((values = csvReader.readNext()) != null) {
        records.addAll(Arrays.asList(values));
    }
    }
}

public void setWord() {
try {
	this.setWord1();
} catch (FileNotFoundException e) {
	
	e.printStackTrace();
} catch (IOException e) {
	
	e.printStackTrace();
}
this.word=this.records.get(new Random().nextInt(this.records.size()-1)).toLowerCase();
System.out.println(this.word);
}
public void changeStatusBody(){
	switch(this.score1.getText()) {
	case "5":{
		hangy.setIcon( new ImageIcon("srcImg\\2.png"));
		Soundplay("srcSound//manPain.wav");
		break;
		
	}
	case "4":{
		hangy.setIcon( new ImageIcon("srcImg\\3.png"));
		Soundplay("srcSound//manPain2.wav");
		break;
	}
	case "3":{
		hangy.setIcon( new ImageIcon("srcImg\\4.png"));
		Soundplay("srcSound//manPain.wav");
		break;
	}
	case "2":{
		hangy.setIcon( new ImageIcon("srcImg\\5.png"));
		Soundplay("srcSound//manPain2.wav");
		break;
	}
	case "1":{
		hangy.setIcon( new ImageIcon("srcImg\\6.png"));
		Soundplay("srcSound//manPain.wav");
		break;
	}
	case "0":{
		hangy.setIcon( new ImageIcon("srcImg\\7.png"));
		Soundplay("srcSound//manPain2.wav");
		for(int i=0;i<word.length();i++) {
			a.get(i).setText(""+word.charAt(i)+"");
			}
		JOptionPane.showMessageDialog(frame,"you lost", "HangMan - The Game",JOptionPane.PLAIN_MESSAGE );
		break;
	}
	default:{
		hangy.setIcon( new ImageIcon("srcImg\\1.png"));
		break;
	}
	}
}
public void Soundplay(String file)
{
	 AePlayWave aw = new AePlayWave(file);
	 aw.start();
}
public void changePunteggio(String a) {
	try {
	      FileWriter myWriter = new FileWriter("srcTxt\\punteggio.txt");
	      myWriter.write(a);
	      myWriter.close();
	      System.out.println("Punteggio successfully wrote to the file.");
	    } catch (IOException e) {
	      System.out.println("An error occurred to add punteggio.");
	      e.printStackTrace();
	    }
	this.punteggio1.setText(readPunteggio());
}
public String readPunteggio() { 
	String data="";
	try {
	      File myObj = new File("srcTxt\\punteggio.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        data = myReader.nextLine();
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred to read punteggio.");
	      data="-1";
	      e.printStackTrace();
	    }
	return data; 
}
}
