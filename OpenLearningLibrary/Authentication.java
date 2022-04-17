
import java.util.Scanner;

public abstract class Authentication extends GlobalMembers {
	private static String fName;
	private static String sName;
	private static String homeAddress;
	private static String dOB;
	private static String email;
	private static String password;
	private static String password2;
	private static String inputValidation;
	String[][] entityDetails = {
			{"fName",""},
			{"sName",""},
			{"homeAddress",""},
			{"dOB",""},
			{"email",""},
			{"password",""},
			{"password2",""}
	};
	
	protected final static void register(Scanner sc){
		cls();
		attributeCollection(sc);
		System.out.println("got here");
		new DatabaseUtility(fName, sName, homeAddress, dOB, email, password);
		System.out.println("End to end");
	}

	protected final static void signIn(Scanner sc) {
		cls();
		System.out.print("\n\t\tEnter Email: ");
		sc.nextLine();
		email = sc.nextLine();
		System.out.print("\n\n\t\tEnter Password: ");
		password = sc.nextLine();
		new DatabaseUtility(email, password);
		sleep(2900);
	}
	
	private static void attributeCollection(Scanner sc) {
		String flag = "";
		int counter = 0;
		String message = "";
		do {
			switch(counter) {
			case(0) : break;
			case(1) : {
				System.out.println("\t\t\t\tRegister");
				System.out.println("\t\t\tEnter 'r' to restart process");
				System.out.print("\n\t\tEnter fName: ");
				flag = sc.nextLine();
				if(!(flag.equalsIgnoreCase("r")) && !(flag.equalsIgnoreCase("")) && flag.length() > 2) fName = flag;
				else if(flag.length() < 3) {
					cls();
					inputValidation = "\t\tName too short";
					System.out.println(inputValidation);
					sleep(1000);
					cls();
					counter = 0;
					break;
				}
				else {cls();counter = 0; break; }
				break;
			}
			case(2) : {
				System.out.print("\t\tEnter Surname: ");
				flag = sc.nextLine();
				if((flag.equalsIgnoreCase("r")))
				{cls();counter = 0; break; }
				else if(flag.length() < 3) {
					inputValidation = "Name too short!";
					System.out.println(inputValidation);
					counter = 1;
					break;
				}
				else if((flag.equalsIgnoreCase("")))
				{counter = 1; break; }
				sName = flag;
				break;
			}
			case(3) : {
				System.out.print("\t\tEnter Home Address: ");
				flag = sc.nextLine();
				if((flag.equalsIgnoreCase("r")))
				{cls();counter = 0; break; }
				else if((flag.equalsIgnoreCase("")))
				{counter = 2; break; }
				homeAddress = flag;
				break;
			}
			case(4) : {
				System.out.print("\t\tEnter D.O.B(YYYY/MM/DD): ");
				flag = sc.nextLine();
				if((flag.equalsIgnoreCase("r")))
				{cls();counter = 0; break; }
				else if((flag.equalsIgnoreCase("")))
				{counter = 3; break; }
				dOB = flag;
				break;
			}
			case(5) : {
				System.out.print("\t\tEnter email: ");
				flag = sc.nextLine();
				if((flag.equalsIgnoreCase("r")))
				{cls();counter = 0; break; }
				else if((flag.equalsIgnoreCase("")))
				{counter = 4; break; }
				email = flag;
				break;
			}
			case(6) : {
				System.out.print("\t\tEnter password: ");
				flag = sc.nextLine();
				if((flag.equalsIgnoreCase("r")))
				{cls();counter = 0; break; }
				else if((flag.equalsIgnoreCase("")) || flag.length() < 7)
				{
					inputValidation = "Password complexity requirements not met";
					System.out.println(inputValidation);
					counter = 5; break;
				}
				password = flag;
				break;
			}
			case(7) : {
				System.out.print("\t\tRe-Enter password: ");
				flag = sc.nextLine();
				if((flag.equalsIgnoreCase("r")))
				{cls();counter = 0; break; }
				else if((flag.equalsIgnoreCase("")))
				{counter = 6; break; }
				password2 = flag;
				boolean matched = matchTest(password,password2);
				message = (matched == true) ? "Password Matched!" : "Password did not match";
				System.out.println(message);
				if(matched == false) counter = 5;
				break;
			}
			}
			counter++;
		}while(counter < 8);
		System.out.println("collection complete");
	}

	public static boolean matchTest(String password, String password2) {
		boolean flag = false;
		if(password.equals(password2)) flag = true;
		return flag;
	}

}