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
import java.util.Stack;
public class DatabaseUtility extends BaseContoller{
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
	String userFeedback;
	CallableStatement callableStatement;
	PreparedStatement ps;
	
	
	public DatabaseUtility() {}
	public DatabaseUtility(String email, String password) 
	{
		this.verifyCredentials(email, password);
	}
	public DatabaseUtility(
			String fName,
			String sName,
			String homeAddress,
			String dOB,
			String email,
			String password
			){
		this.fName = fName;
		this.sName = sName;
		this.homeAddress = homeAddress;
		this.dOB = dOB;
		this.email = email;
		this.password = password;
		this.registration();
	}

	public void registration() {
		try 
		{
			connect();
			query = "{call registerNewMember (?,?,?,?,?,?,?,?)}";
			callableStatement = connection.prepareCall(query);
			callableStatement.setString(1, fName.toLowerCase());
			callableStatement.setString(2, sName.toLowerCase());
			callableStatement.setString(3, dOB);
			callableStatement.setString(4, homeAddress.toLowerCase());
			callableStatement.setString(5, "Paid.toLowerCase()");
			callableStatement.setString(6, email.toLowerCase());
			callableStatement.setString(7, password);
			callableStatement.registerOutParameter(8, Types.INTEGER);
			callableStatement.execute();
			System.out.println("saka chii chanetsa");
			if (callableStatement.getInt(8) == 0) userFeedback = "User already registered";
			else userFeedback = (callableStatement.getInt(8) == 1) ? "Member registration succesfull!" : "Registration failed!";
			cls();
			System.out.println(userFeedback + " : with " + callableStatement.getInt(8) + " rows affected");
			sleep(2000);
			callableStatement.close();
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
	
	public void verifyCredentials(String signInEmail, String password) {
		
		try {
			connect();
			callableStatement = connection.prepareCall("{call credentialsValidation (?,?,?)}");
			callableStatement.setString(1, signInEmail);
			callableStatement.setString(2, password.trim());
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.executeQuery();
			System.out.println(callableStatement.getInt(3));
			cls();
			userFeedback = (callableStatement.getInt(3) == 1) ? "Validation Successful!" : "Email Address or Password Invalid!";
			System.out.println(userFeedback);
			sleep(2000);	
			if(callableStatement.getInt(3) == 1) 
			{ getBookSections(); membersHomePage();}
			else signIn(sc);
			callableStatement.close();
			connection.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void getBookSections() {
		try {
			connect();
			ps = connection.prepareStatement("Select sectionName from section");
			boolean hasResultSet = ps.execute();
			if(hasResultSet) {
				rs = ps.getResultSet();
				while(rs.next()) {
					System.out.println(rs.getString(1));
					bookSections.add(rs.getString(1));
				}
			}
			ps.close();
			connection.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private final void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		connection = DriverManager.getConnection(url,uname,dbPassword);
	}
}
