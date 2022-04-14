/* When ever you work with JDBC you have to follow 7 steps
 * 1. Import the package --> java.sql
 * 2. Load and Register the driver --> com.microsoft.sqlserver.jdbc
 * 3. Create a connection
 * 4. Create a statement
 * 5. Execute the query
 * 6. Process the results
 * 7. Close connection
 */

import java.sql.*; //This is step 1
public class DataAccessObject extends GlobalMembers {
	Date date;
	private String fName;
	private String sName;
	private String homeAddress;
	private String dOB = "2000-11-14";
	private String email;
	private String password;
	String url = "jdbc:sqlserver://localhost:1433;databaseName=OpenLearningLibrary;instance=SQLSERVER;encrypt=true;trustServerCertificate=true";
	String uname = "sa";
	String dbPassword = "Tkuch";
	String query;
	Connection connection;
	Statement statement;
	ResultSet rs;
	String feedback;
	CallableStatement collableStatement;
	
	public DataAccessObject(
			String fName,
			String sName,
			String homeAddress,
			String dOB,
			String email,
			String password
			){
		System.out.println("In constructor");
		this.fName = fName;
		this.sName = sName;
		this.homeAddress = homeAddress;
		this.dOB = dOB;
		this.email = email;
		this.password = password;
		this.registration();
	}

	public void registration() {
		query = "{call registerNewMember (?,?,?,?,?,?,?,?)}";
		System.out.println("Some hope atleast");
		System.out.println(date);
		try 
		{
			connect();
			collableStatement = connection.prepareCall(query);
			collableStatement.setString(1, fName);
			collableStatement.setString(2, sName);
			collableStatement.setString(3, dOB);
			collableStatement.setString(4, homeAddress);
			collableStatement.setString(5, "Paid");
			collableStatement.setString(6, email);
			collableStatement.setString(7, password);
			collableStatement.registerOutParameter(8, Types.INTEGER);
			collableStatement.execute();
			System.out.println("saka chii chanetsa");
			
			if (collableStatement.getInt(8) == 0) feedback = "User already registered";
			else feedback = (collableStatement.getInt(8) == 1) ? "Member registration succesfull!" : "Registration failed!";
			
			cls();
			System.out.println(feedback + " : with " + collableStatement.getInt(8) + " rows affected");
			sleep(2000);
			collableStatement.close();
			connection.close();
			
		} 
		
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void verifyCredentials(String email, String password) {
		
		try {
			connect();
			
		} catch (Exception e) {
		}
	}
	
	private final void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		connection = DriverManager.getConnection(url,uname,dbPassword);
	}
}
