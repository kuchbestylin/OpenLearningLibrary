import java.util.*;

public class BaseContoller extends Authentication {
	static String responce;
	
	public static void homeDisplay(Scanner sc) {
		System.out.print(
				"\t\tWelcome To Open Learning, Featuring podcasts, publications and\n"
				+ "\t\tdozens of books from a variety of different authors gathered\n"
				+ "\t\there for you to read online or offline which we hope you find\n"
				+ "\t\tenjoyable. Feedback is welcome - feel free to proceed as guest\n"
				+ "\t\tor register with us at N$550 a year.\n");
		System.out.println("\n\n\t\tPress 1: Sign In");
		System.out.println("\t\tPress 2: Register as new user.");
		System.out.println("\t\tPress 2: Continue as Guest.\n");
		System.out.print("\t\tInput: ");
		responce = sc.next();
		try {
			switch(Integer.valueOf(responce)) {
			case(1):{
				signIn(sc);
			}
			case(2):{
				register(sc);
			}
			case(3):{
				guestPage();
			}
			}
		} catch (Exception e) {
			System.out.println("Invalid Input!");
			cls(); homeDisplay(sc);
		}
	}
	
	private static void guestPage() {
		
	}

}