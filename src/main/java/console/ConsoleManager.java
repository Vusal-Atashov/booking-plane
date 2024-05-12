package console;

import controller.BookingController;
import controller.BookingControllerImpl;
import controller.FlightController;
import controller.FlightControllerImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConsoleManager {
    private final FlightController flightController = new FlightControllerImpl();
    private final BookingController bookingController = new BookingControllerImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        flightController.getAllFromFile();
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    flightController.saveFlight(BookingAndFlight.createFlight());
                    break;
                case 2:
                    flightController.getNext24HoursFlights(scanner.next()).forEach(flightDto -> System.out.println(flightDto.toString()));
//                    System.out.println(flightController.getLast24HoursFlights(scanner.next()));;
                    break;
                case 3:
                    getFlightInfo();
                    break;
                case 4:
                    System.out.print("Enter Flight id : ");
                    flightController.getFlightById(Integer.parseInt(scanner.nextLine()));
                    bookingController.saveBooking(BookingAndFlight.createBooking());
                    break;
                case 5:
                    System.out.print("Enter booking id : ");
                    bookingController.cancelBooking(Integer.parseInt(scanner.nextLine()));
                    break;
                case 6:
                    System.out.print("Enter name and surname : ");
                    bookingController.getAllBookingsByPassenger(scanner.nextLine());
                    break;
                case 7:
                    exit = true;
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
        System.out.println("3. Show Flight Info");
        System.out.println("4. Search and Book a Flight");
        System.out.println("5. Cancel Booking");
        System.out.println("6. My Flights");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private void getFlightInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.print("Enter the destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter the date (yyyy-MM-dd HH:mm): ");
        LocalDateTime time = LocalDateTime.parse(scanner.nextLine(), formatter);
        System.out.print("Enter the number of seats: ");
        int seats = scanner.nextInt();
        scanner.nextLine();
        System.out.println(flightController.getFlightsByCriteria(destination, time, seats));
    }
}