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
		signIn(sc);
		try {
			switch(Integer.valueOf(responce)) {
			case(1):{
				signIn(sc);
				break;
			}
			case(2):{
				register(sc);
				break;
			}
			case(3):{
				guestPage();
				break;
			}
			}
		} catch (Exception e) {
			System.out.println("Invalid Input!");
			cls(); homeDisplay(sc);
		}
	}
	
	private static void guestPage() {
		
	}

	protected void membersHomePage(){
		System.out.println(
				"\t\tSelect either of the following options\n" +
				"\t\t\t1 : Books\n" +
				"\t\t\t2 : Podcasts"
				);
		String choice = sc.nextLine();
		try {
			Integer.parseInt(choice);
			if(Integer.parseInt(choice) == 1) bookSections();
			else if (Integer.parseInt(choice) == 2) podcast();
			else throw new Exception("Choose a valid choice");
			sleep(1000);
		} catch (Exception e) {
			System.out.println("please enter a valid option");
		}
	}

	private void podcast() {
		// TODO Auto-generated method stub
		
	}

	private void bookSections() {
		cls();
		System.out.println("\t\tChoose Section of Interest");
		for(int i = 0; i < bookSections.size(); i++) {
			System.out.println(i + " : " + bookSections.pop());
		}
		String choice = sc.nextLine();
		try {
			Integer.valueOf(choice);
			switch(Integer.valueOf(choice)) {
			case(1) : { //choice 1 pertains..
				
			}
			case(2) : { //choice 1 pertains..
				
			}
			case(3) : { //choice 1 pertains..
				
			}
			case(4) : { //choice 1 pertains..
				
			}
			case(5) : { //choice 1 pertains..
				
			}
			case(6) : { //choice 1 pertains..
				
			}
			}
		} catch (Exception e) {
			System.out.println("Invalid Choice!");
			bookSections();
		}
		
	}
}