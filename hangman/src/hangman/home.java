package hangman;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import java.awt.event.*;


@SuppressWarnings("unused")
public class home {

	public JFrame frame;
	private game Game= new game();
			
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		  try {
	            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");

	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home window = new home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("HangMan - The Game");
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("srcImg\\homeIcon.jpg"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btn1 = new JButton("click to start game");
		btn1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.frame.setVisible(true);
				frame.setVisible(false);
			}
			
		});
		btn1.setBounds(140, 205, 150, 45);
		btn1.setBackground(Color.YELLOW);
		frame.getContentPane().add(btn1);
		
		JLabel home_img = new JLabel();
		home_img.setHorizontalAlignment(SwingConstants.CENTER);
		home_img.setBackground(new Color(255, 255, 255));
		home_img.setIcon( new ImageIcon("srcImg\\home.jpg"));
		home_img.setBounds(170, 0, 100, 200);
		frame.getContentPane().add(home_img);
		
		JLabel credit = new JLabel("by Emanuele Dolce");
		credit.setFont(new Font("Segoe UI", Font.BOLD, 10));
		credit.setBounds(332, 240, 94, 13);
		frame.getContentPane().add(credit);
	}
}
