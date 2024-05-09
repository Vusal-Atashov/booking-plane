package console;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.BookingController;
import controller.BookingControllerImpl;
import controller.FlightController;
import controller.FlightControllerImpl;
import dao.FlightDaoImpl;
import dao.impl.BookingFileDAO;
import services.BookingServiceImpl;
import services.FlightServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleManager {
    private final FlightController flightController;
    private final BookingController bookingController;
    private final Scanner scanner;

    public ConsoleManager() {
        this.flightController = new FlightControllerImpl(new FlightServiceImpl(new FlightDaoImpl()));
        this.bookingController = new BookingControllerImpl(new BookingServiceImpl(new BookingFileDAO(new ObjectMapper())));
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        flightController.getAllFromFile();
        // Main menu loop
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    bookingController.saveBooking(BookingAndFlight.createBooking());
                case 2:
                    flightController.saveFlight(BookingAndFlight.createFlight());
                    break;
                case 3:
                    getFlightInfo();
                    break;
                case 4:
                    System.out.print("Enter Flight id : ");
                    flightController.getFlightById(Integer.parseInt(scanner.nextLine()));
                    break;
//                case 5:
//                    System.out.print("Enter Destination :v");
//                    flightController.getAllFlightsByDestination(scanner.nextLine());
//                    break;
                case 6:
                    System.out.print("Enter booking id : ");
                    bookingController.removeBooking(Integer.parseInt(scanner.nextLine()));
                    break;
                case 7:
                    System.out.print("Enter name and surname : ");
                    bookingController.getAllBookingsByPassenger(Arrays.asList(scanner.next(), scanner.next()));
                    break;
                case 8:
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
        System.out.println("1. Create Booking");
        System.out.println("2. Create Flight");
        System.out.println("3. Online Board");
        System.out.println("4. Show Flight Info");
        System.out.println("5. Search and Book a Flight");
        System.out.println("6. Cancel Booking");
        System.out.println("6. My Flights");
        System.out.println("8. Exit");
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

