package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.LoginValidation;
import login.ProxyLoginValidation;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUI extends JFrame {

	private JPanel contentPane;
	private JLabel usernameLabel;
	private JTextField username;
	private JLabel passwordLabel;
	private JTextField password;
	private JLabel errorMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
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
	public LoginUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 190);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(10, 21, 75, 20);
		contentPane.add(usernameLabel);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 52, 75, 20);
		contentPane.add(passwordLabel);
		
		username = new JTextField();
		username.setBounds(84, 21, 136, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(84, 52, 136, 20);
		contentPane.add(password);
		
		errorMessage = new JLabel();
		errorMessage.setBounds(84, 83, 136, 20);
		contentPane.add(errorMessage);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateUser()) {
					startMainUI();
				}
			}
		});
		
		btnNewButton.setBounds(84, 114, 136, 32);
		contentPane.add(btnNewButton);

	}
	
	/**
	 * Validates the user against the validator proxy
	 * @return a boolean value for if the user is found in the login text file.
	 */
	private boolean validateUser() {
		LoginValidation validator = new ProxyLoginValidation(username.getText(), password.getText());
		if (validator.validateUser()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Starts the Main UI element
	 */
	private void startMainUI() {
		try {
			MainUI mainUI = new MainUI();
			this.dispose();
			mainUI.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
}
