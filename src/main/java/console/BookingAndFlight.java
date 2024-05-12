package console;

import dto.BookingDto;
import dto.CriteriaDto;
import dto.FlightDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingAndFlight {
    public static Scanner scanner = new Scanner(System.in);

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
        scanner.nextLine();
        return flightDTO;
    }

    public static String getOrigin() {
        System.out.println("Enter flight origin: ");
        return scanner.nextLine();
    }

    public static CriteriaDto getFlightInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.print("Enter the destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter the date (yyyy-MM-dd HH:mm): ");
        LocalDateTime time = LocalDateTime.parse(scanner.nextLine(), formatter);
        System.out.print("Enter the number of seats: ");
        int seats = Integer.parseInt(scanner.nextLine());
        return new CriteriaDto(destination, time, seats);
    }

    public static BookingDto createBooking(int id, int numberOfSeats) {
        BookingDto bookingDTO = new BookingDto();
        bookingDTO.setFlightId(id);
        List<String> names = new ArrayList<>();
        for (int i = 0; i < numberOfSeats; i++) {
            System.out.println("Enter name and surname :");
            String a = scanner.nextLine();
            names.add(a);
        }
        bookingDTO.setPassengerNames(names);
        return bookingDTO;
    }
}
