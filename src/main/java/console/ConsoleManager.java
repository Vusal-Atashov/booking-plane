package console;

import controller.BookingController;
import controller.BookingControllerImpl;
import controller.FlightController;
import controller.FlightControllerImpl;
import dto.CriteriaDto;
import dto.FlightDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleManager {
    private final FlightController flightController = new FlightControllerImpl();
    private final BookingController bookingController = new BookingControllerImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        bookingController.getAllFromFile();
        flightController.getAllFromFile();
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    flightController.saveFlight(BookingAndFlight.createFlight());
                    break;
                case 2:
                    System.out.println(flightController.getNext24HoursFlights(BookingAndFlight.getOrigin()));
                    break;
                case 3:
                    CriteriaDto criteria = BookingAndFlight.getFlightInfo();
                    System.out.println(flightController.getFlightsByCriteria(criteria));
                    System.out.print("Enter id of the flight you want to book or 0 to exit: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    if (id != 0) {
                        bookingController.saveBooking(BookingAndFlight.createBooking(id, criteria.getSeats()));
                    }
                    break;
                case 4:
                    System.out.print("Enter booking id : ");
                    bookingController.cancelBooking(Integer.parseInt(scanner.nextLine()));
                    break;
                case 5:
                    System.out.print("Enter name and surname : ");
                    System.out.println(bookingController.getAllBookingsByPassenger(scanner.nextLine()));
                    break;
                case 6:
                    exit = true;
                    bookingController.saveAllToFile();
                    flightController.saveAllToFile();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Create Flight");
        System.out.println("2. Online Board");
        System.out.println("3. Search and Book a Flight");
        System.out.println("4. Cancel Booking");
        System.out.println("5. My Flights");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }
}