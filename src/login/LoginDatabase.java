package login;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Hashtable;
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * This represents a class that holds the Login Database
 */
public class LoginDatabase {

	private File PATH = new File("./src/assets/login.txt");
	private Hashtable<String, String> login_db; 

	
	/*
	 * Constructs a login database. Parses a text file and stores each key-value pair in a dictionary
	 */
	public LoginDatabase() {
		login_db = new Hashtable<String, String>();
		try {
			Scanner reader = new Scanner(PATH);
			while (reader.hasNextLine()) {
				String key = reader.next();
				String value = reader.next();
				login_db.put(key, value);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	/**
	 * constructs a method for searching of a username and password against itself.
	 * @param username The username of the User
	 * @param password The password of the User
	 * @return a Boolean value for the existance of the user
	 */
	public boolean searchForUser(String username, String password) {
		if (login_db.containsKey(username) == true && login_db.get(username).equals(password)) {
			return true;
		}
		return false;
	}
}
