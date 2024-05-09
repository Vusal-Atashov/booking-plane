package console;

import dto.BookingDTO;
import dto.FlightDTO;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookingAndFlight {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<BookingDTO> bookings = new ArrayList<>();
    public static ArrayList<FlightDTO> flights = new ArrayList<>();


    public static List<BookingDTO> createBooking() {
        BookingDTO bookingDTO = new BookingDTO();
        System.out.println("Enter flightID :");
        bookingDTO.setFlightId(scanner.nextInt());
        System.out.println("Enter name :");
        String a=scanner.next();
        System.out.println("Enter Surname :");
        String b=scanner.next();
        bookingDTO.setPassengerNames(Arrays.asList(a, b));
        bookings.add(bookingDTO);
        return bookings;
    }

    public static List<FlightDTO> createFlight() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        FlightDTO flightDTO = new FlightDTO();
        System.out.println("Enter flight origin: ");
        flightDTO.setOrigin(scanner.nextLine());
        System.out.println("Enter flight destination: ");
        flightDTO.setDestination(scanner.nextLine());
        System.out.println("Enter flight time in format (yyyy-MM-dd HH:mm): ");
        flightDTO.setDepartureTime(LocalDateTime.parse(scanner.nextLine(), formatter));
        System.out.println("Enter total number of seats: ");
        flightDTO.setNumOfSeats(scanner.nextInt());
        flights.add(flightDTO);
        return flights;
    }
}
