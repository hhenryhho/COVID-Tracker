package login;

public class ProxyLoginValidation implements LoginValidation {

	private String username;
	private String password;
	private static ConcreteLoginValidation realValidator;
	
	public ProxyLoginValidation(String username, String password) {
		this.username = username; 
		this.password = password;
	}
	
	public boolean validateFields() {
		if (username.equals("") || password.equals("")) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean validateUser() {
		if (validateFields()) {
			realValidator = new ConcreteLoginValidation(username, password);
			return realValidator.validateUser();
		} 
		return false;
	}

}
