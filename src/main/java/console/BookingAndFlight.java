package console;

import dto.BookingDto;
import dto.FlightDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class BookingAndFlight {
    public static Scanner scanner = new Scanner(System.in);




    public static BookingDto createBooking() {
        BookingDto bookingDTO = new BookingDto();
        System.out.println("Enter flightID :");
        bookingDTO.setFlightId(scanner.nextInt());
        System.out.println("Enter name and surname :");
        String a=scanner.next();
        bookingDTO.setPassengerNames(List.of(a));
        return bookingDTO;
    }

    public static FlightDto createFlight() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        FlightDto flightDTO = new FlightDto();
        System.out.println("Enter flight origin: ");
        flightDTO.setOrigin(scanner.nextLine());
        System.out.println("Enter flight destination: ");
        flightDTO.setDestination(scanner.nextLine());
        System.out.println("Enter flight time in format (yyyy-MM-dd HH:mm): ");
        flightDTO.setDepartureTime(LocalDateTime.parse(scanner.nextLine(), formatter));
        System.out.println("Enter total number of seats: ");
        flightDTO.setNumOfSeats(scanner.nextInt());
        return flightDTO;
    }
}
