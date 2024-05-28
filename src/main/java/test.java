import controller.BookingController;
import controller.BookingControllerImpl;
import controller.FlightController;
import controller.FlightControllerImpl;
import dao.FlightDao;
import dao.impl.BookingFileDao;
import dao.impl.FlightDaoImpl;
import services.BookingServiceImpl;
import services.FlightServiceImpl;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        FlightDao flightDao = new FlightDaoImpl();
        FlightController flightController = new FlightControllerImpl(new FlightServiceImpl(new FlightDaoImpl()));
        BookingController bookingController = new BookingControllerImpl(new BookingServiceImpl(new BookingFileDao()));
        //flightController.saveFlight(new FlightDto(Cities.ALMATY, Cities.MOSCOW, LocalDateTime.now(), 100));
        //flightController.saveFlight(new FlightDto(Cities.AMSTERDAM, Cities.BAKU, LocalDateTime.now(), 100));
        //flightController.saveFlight(new FlightDto(Cities.BERLIN, Cities.KIEV, LocalDateTime.now(), 100));
        //flightController.saveFlight(new FlightDto(Cities.ISTANBUL, Cities.LONDON, LocalDateTime.now(), 100));*/
        // System.out.println(flightController.findByOrigin("Almaty"));
        // flightController.cancelFlight(4);
        ArrayList<String> name = new ArrayList<>();
        name.add("Aigerim");
        name.add("Aruzhan");
        // bookingController.saveBooking(new BookingDto(1, name));

        bookingController.cancelBooking(1);
        System.out.println(bookingController.getAllBookings());
        //  bookingController.cancelBooking(1);
        // flightController.cancelFlight(1);
        System.out.println(flightController.getAllFlights());


    }
}
