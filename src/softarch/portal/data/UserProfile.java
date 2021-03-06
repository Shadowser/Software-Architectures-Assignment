package softarch.portal.data;

import java.util.Date;

/**
 * This is an abstract superclass for all user profiles.
 * @author Niels Joncheere
 */
public abstract class UserProfile extends Data {
	protected	String	username;
	protected	String	password;
	protected	String	firstName;
	protected	String	lastName;
	protected	String	emailAddress;
	protected	Date	lastLogin;

	/**
	 * Returns an SQL string that allows the system to add the account
	 * to a relational database.
	 */
	public abstract String asSql();

	/**
	 * When a user has logged in successfully, he will be
	 * redirected to this page.
	 */
	public abstract String getDefaultPage();

	/**
	 * Returns an SQL UPDATE string that allows the system to update
	 * the account in a relational database.
	 */
	public abstract String asSqlUpdate();
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Date getLastLogin() {
		return lastLogin;
	}
	
	public String getEmail()
	{
		return emailAddress;
	}

	public UserProfile setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
		return this;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
}
