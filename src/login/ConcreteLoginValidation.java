package login;

/**
 * This represents a class for Validation using a concrete method
 */
public class ConcreteLoginValidation implements LoginValidation {
	
	private String username;
	private String password;
	private LoginDatabase db; 

	/*
	 * Constructs a concrete login validator. 
	 */
	public ConcreteLoginValidation(String username, String password) {
		db = new LoginDatabase();
		this.username = username;
		this.password = password;
	}

	/**
	 * method for searching of user
	 * @return a boolean value of whether a user exists
	 */
	@Override
	public boolean validateUser() {
		return db.searchForUser(username, password);
	}
}
