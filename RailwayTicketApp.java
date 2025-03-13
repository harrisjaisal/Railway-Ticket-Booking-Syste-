package samplepack;

import java.util.*;

class Passenger{
	String name;
	int age;
	String gender;
	String birthPreference;
	
	public Passenger(String name, int age, String gender, String birthPreference) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.birthPreference = birthPreference;
	}
	
	public Passenger() {
		
	}
}

 

public class RailwayTicketApp {
	private int totalBerthAvailable = 3;
	private int lowerBerthAvailable = 1;
	private int middleBerthAvailable = 1;
	private int upperBerthAvailable = 1;
	private int RACAvailable = 1;
	private int WLAvailable = 1;
	private ArrayList<Passenger> bookedTickets = new ArrayList<>();
	private ArrayList<Passenger> racTickets = new ArrayList<>();
	private ArrayList<Passenger> wlTickets = new ArrayList<>();
	
	public void bookTicket() {
		Scanner sc = new Scanner(System.in);
		
		if(WLAvailable == 0) {
			System.out.println("No tickets Available!!");
			return;
		}
		
		System.out.println("Enter passenger name: ");
		String name = sc.nextLine().toUpperCase();
		System.out.println("Enter passenger age: ");
		int age = sc.nextInt();
		System.out.println("Enter your gender(male/female): ");
		String gender = sc.nextLine().toUpperCase();
		sc.nextLine();
		System.out.println("Enter berth Preference(Lower/Upper/Middle): ");
		String berthPreference = sc.nextLine().toUpperCase();
		
		if(berthPreference.equals("LOWER")) {
			if(lowerBerthAvailable>0) {
				System.out.println("Ticket booked successfully");
				lowerBerthAvailable--;
			}
			else {
				System.out.println("L0wer berth not available...Available berths");
				suggestAvailableBerths(name,age,gender);
				return;
			}
			
		}
		if(berthPreference.equals("UPPER")) {
			if(upperBerthAvailable>0) {
				System.out.println("Ticket booked successfully");
				upperBerthAvailable--;
			}
			else {
				System.out.println("UPPer berth not available...Available berths");
				suggestAvailableBerths(name,age,gender);
				return;
			}
			
		}
		if(berthPreference.equals("MIDDLE")) {
			if(middleBerthAvailable>0) {
				System.out.println("Ticket booked successfully");
				middleBerthAvailable--;
			}
			else {
				System.out.println("Middle berth not available...Available berths");
				suggestAvailableBerths(name,age,gender);
				return;
			}
			
		}
		
		if(age>5) {
			Passenger passenger = new Passenger(name,age,gender,berthPreference);
			if(totalBerthAvailable >0) {
				bookedTickets.add(passenger);
				totalBerthAvailable--;
			}
			else if(RACAvailable >0) {
				racTickets.add(passenger);
				RACAvailable--;
				System.out.println("Ticket booked successfully(RAC)");
			}
			else{
				wlTickets.add(passenger);
				WLAvailable--;
				System.out.println("Ticket is in waiting list");
			}
		}
		
	}
	public void suggestAvailableBerths(String name, int age , String gender) {
		if(lowerBerthAvailable>0) {
			System.out.println("1.Lower available");
		}
		if(upperBerthAvailable>0) {
			System.out.println("2.Upper available");
		}
		if(middleBerthAvailable>0) {
			System.out.println("3.Middle available");
		}
		else if(lowerBerthAvailable==0 && upperBerthAvailable ==0 && middleBerthAvailable == 0 && RACAvailable>0) {
			System.out.println("4.Book on RAC");
		}else {
			System.out.println("Do you want to book on WL");
		}
		
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("Ticket booked successfully");
			lowerBerthAvailable--;
			bookedTickets.add(new Passenger(name,age,gender,"LOWER"));
			break;
		case 2:
			System.out.println("Ticket booked successfully");
			upperBerthAvailable--;
			bookedTickets.add(new Passenger(name,age,gender,"UPPER"));
			break;
		case 3:
			System.out.println("Ticket booked successfully");
			middleBerthAvailable--;
			bookedTickets.add(new Passenger(name,age,gender,"MIDDLE"));
			break;
		case 4:
			System.out.println("Ticket booked successfully");
			RACAvailable--;
			racTickets.add(new Passenger(name,age,gender,"side lower"));
			break;
		case 5:
			System.out.println("Ticket booked successfully");
			WLAvailable--;
			wlTickets.add(new Passenger(name,age,gender,"NA"));
		}
	}
	
	public void cancelTicket() {
	    Scanner sc = new Scanner(System.in);
	    
	    if (bookedTickets.isEmpty() && racTickets.isEmpty() && wlTickets.isEmpty()) {
	        System.out.println("No tickets to cancel.");
	        return;
	    }

	    // Print all booked tickets
	    System.out.println("Choose the type of ticket you want to cancel:");
	    System.out.println("1. Booked Tickets");
	    System.out.println("2. RAC Tickets");
	    System.out.println("3. WL Tickets");
	    
	    int typeChoice = sc.nextInt();
	    sc.nextLine(); // Consume the newline

	    int ticketIndex = -1;
	    
	    switch (typeChoice) {
	        case 1:
	            if (bookedTickets.isEmpty()) {
	                System.out.println("No booked tickets available.");
	                return;
	            }
	            // Show booked tickets
	            System.out.println("Booked Tickets:");
	            for (int i = 0; i < bookedTickets.size(); i++) {
	                Passenger ticket = bookedTickets.get(i);
	                System.out.println((i + 1) + ". Name: " + ticket.name + " Age: " + ticket.age + " Gender: " + ticket.gender + " Berth Preference: " + ticket.birthPreference);
	            }
	            System.out.println("Enter the ticket number to cancel: ");
	            ticketIndex = sc.nextInt() - 1;
	            if (ticketIndex >= 0 && ticketIndex < bookedTickets.size()) {
	                Passenger canceledTicket = bookedTickets.remove(ticketIndex);
	                System.out.println("Ticket canceled: " + canceledTicket.name + " (" + canceledTicket.birthPreference + ")");
	                // Free the berth
	                if (canceledTicket.birthPreference.equals("LOWER")) {
	                    lowerBerthAvailable++;
	                } else if (canceledTicket.birthPreference.equals("UPPER")) {
	                    upperBerthAvailable++;
	                } else if (canceledTicket.birthPreference.equals("MIDDLE")) {
	                    middleBerthAvailable++;
	                }
	                totalBerthAvailable++;
	            } else {
	                System.out.println("Invalid ticket number.");
	            }
	            break;
	        case 2:
	            if (racTickets.isEmpty()) {
	                System.out.println("No RAC tickets available.");
	                return;
	            }
	            // Show RAC tickets
	            System.out.println("RAC Tickets:");
	            for (int i = 0; i < racTickets.size(); i++) {
	                Passenger ticket = racTickets.get(i);
	                System.out.println((i + 1) + ". Name: " + ticket.name + " Age: " + ticket.age + " Gender: " + ticket.gender + " Berth Preference: " + ticket.birthPreference);
	            }
	            System.out.println("Enter the ticket number to cancel: ");
	            ticketIndex = sc.nextInt() - 1;
	            if (ticketIndex >= 0 && ticketIndex < racTickets.size()) {
	                Passenger canceledTicket = racTickets.remove(ticketIndex);
	                System.out.println("Ticket canceled: " + canceledTicket.name);
	                // Free the RAC slot
	                RACAvailable++;
	            } else {
	                System.out.println("Invalid ticket number.");
	            }
	            break;
	        case 3:
	            if (wlTickets.isEmpty()) {
	                System.out.println("No WL tickets available.");
	                return;
	            }
	            // Show WL tickets
	            System.out.println("WL Tickets:");
	            for (int i = 0; i < wlTickets.size(); i++) {
	                Passenger ticket = wlTickets.get(i);
	                System.out.println((i + 1) + ". Name: " + ticket.name + " Age: " + ticket.age + " Gender: " + ticket.gender + " Berth Preference: " + ticket.birthPreference);
	            }
	            System.out.println("Enter the ticket number to cancel: ");
	            ticketIndex = sc.nextInt() - 1;
	            if (ticketIndex >= 0 && ticketIndex < wlTickets.size()) {
	                Passenger canceledTicket = wlTickets.remove(ticketIndex);
	                System.out.println("Ticket canceled: " + canceledTicket.name);
	                // Free the WL slot
	                WLAvailable++;
	            } else {
	                System.out.println("Invalid ticket number.");
	            }
	            break;
	        default:
	            System.out.println("Invalid choice. Please enter a number between 1 and 3.");
	            break;
	    }
	}

		
		public void printBookedTicket() {
			if (bookedTickets.isEmpty()) {
				System.out.println("No booked tickets available.");
				System.out.println("---");
				} 
			else {
				System.out.println("Booked Tickets:");
				System.out.println("-----");
				for (int i = 0; i < bookedTickets.size(); i++) {
				Passenger ticket = bookedTickets.get(i);
				System.out.println((i + 1) + ". Namę: " + ticket.name +  "Age:" + ticket.age + "Gender" + ticket.gender + "Berth Preference:" + ticket.birthPreference); 
				}
				System.out.println("-------");
				System.out.println("RAC Tickets:");
				System.out.println("-----");
				for (int i = 0; i < racTickets.size(); i++) {
				Passenger ticket = racTickets.get(i);
				System.out.println((i + 1) + ". Namę: " + ticket.name +  "Age:" + ticket.age + "Gender" + ticket.gender + "Berth Preference:" + ticket.birthPreference); 
				}
				System.out.println("-------");
				System.out.println("WL Tickets:");
				System.out.println("-----");
				for (int i = 0; i < wlTickets.size(); i++) {
					Passenger ticket = wlTickets.get(i);
					System.out.println((i + 1) + ". Namę: " + ticket.name +  "Age:" + ticket.age + "Gender" + ticket.gender + "Berth Preference:" + ticket.birthPreference); 
				}
				System.out.println("-------");
				
				System.out.println("Total booked tickets:" + bookedTickets.size());
				System.out.println("Total rac tickets:" + racTickets.size());
				System.out.println("Total wl tickets:" + wlTickets.size());
			}}
		
		public void	printAvailableTicket(){
			System.out.println("Available berths:"+ totalBerthAvailable);
			System.out.println("Available RAC:"+ RACAvailable);
			System.out.println("Available WL:"+ WLAvailable);
				
			}
		


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RailwayTicketApp rs = new RailwayTicketApp();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("Railway Ticket Reservaton System");
			System.out.println("1.Book Ticket");
			System.out.println("2.Cancel Ticket");
			System.out.println("3.Print Booked Tickets");
			System.out.println("4.Print Available Tickets");
			System.out.println("5.Exit");
			
			System.out.println("Enter your choice:(1-5):"); 
						int choice = sc.nextInt();
			
			System.out.println("---------------------------");
			
			switch(choice) {
			case 1 :
				rs.bookTicket();
				break;
			case 2 :
				rs.cancelTicket();
				break;
			case 3 :
				rs.printBookedTicket();
				break;
			case 4 :
				rs.printAvailableTicket();
				break;
			case 5 :
				System.out.println("Exiting the Application, Thank you!!");
				System.exit(0);
			default:
				System.out.println("Enter a number between 1 yo 5");
			}
		}

	}

}
