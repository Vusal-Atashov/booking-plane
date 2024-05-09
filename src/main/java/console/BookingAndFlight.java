package console;

import dto.BookingDTO;
import dto.FlightDTO;

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
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setDestination(scanner.next());
        flightDTO.setDate(scanner.next());
        flightDTO.setNumOfSeats(scanner.nextInt());
        flights.add(flightDTO);
        return flights;
    }
}
