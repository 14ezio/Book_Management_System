import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JPasswordField;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	Connection conn = null;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		conn = sqliteConnection.dbConnector();
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblUsername.setBounds(67, 72, 76, 17);
		contentPane.add(lblUsername);
		
		JLabel lblPasssword = new JLabel("Passsword");
		lblPasssword.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblPasssword.setBounds(67, 131, 76, 17);
		contentPane.add(lblPasssword);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(171, 67, 110, 29);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					if(passwordField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please enter the data..!!");
						
					}
					else
					{
						String query = "insert into login (username,password) values (?,?)";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setString(1,textFieldUsername.getText());
						pst.setString(2,passwordField.getText());
						
						pst.execute();
						
						JOptionPane.showMessageDialog(null,"done...!!!");
						
						pst.close();
						
					}
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				
			}
		});
		btnSignup.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnSignup.setBounds(85, 195, 89, 23);
		contentPane.add(btnSignup);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.exit(getDefaultCloseOperation());
				Login log = new Login();
				log.main(null);
				contentPane.setVisible(false);
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.setBounds(192, 196, 89, 23);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(171, 130, 110, 29);
		contentPane.add(passwordField);
	}
}
