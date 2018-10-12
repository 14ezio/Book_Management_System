import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	private JTextField textField_user;
	private JPasswordField passwordField;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		conn = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 503, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblUsername.setBounds(210, 60, 83, 45);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblPassword.setBounds(210, 127, 83, 31);
		frame.getContentPane().add(lblPassword);
		
		textField_user = new JTextField();
		textField_user.setBounds(303, 67, 144, 32);
		frame.getContentPane().add(textField_user);
		textField_user.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(304, 122, 143, 31);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		Image img = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
				
					String query = "select *from login where username=? AND password=? ";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1,textField_user.getText());
					pst.setString(2, passwordField.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next())
					{
						count = count + 1;
					}
					if(count == 1)
					{
						JOptionPane.showMessageDialog(null, "Username and Password is correct");
						frame.dispose();                            							//it will delete the login frame
						booksinfo binfo = new booksinfo();
						binfo.setVisible(true);
					}
					else if (count > 1)
					{
						JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Username and Password is not correct Try Again...!!!");
					}
					
					rs.close();
					pst.close();
					
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
					
				}
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLogin.setBounds(210, 205, 114, 45);
		frame.getContentPane().add(btnLogin);
		
		JLabel label = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/log.png")).getImage();
		label.setIcon(new ImageIcon(img1));
		label.setBounds(31, 67, 149, 150);
		frame.getContentPane().add(label);
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Signup sp = new Signup();
				sp.setVisible(true);
			}
		});
		btnSignup.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSignup.setBounds(352, 205, 95, 45);
		frame.getContentPane().add(btnSignup);
	}
}
