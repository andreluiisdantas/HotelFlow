package br.com.HotelFlow.controller;

import br.com.HotelFlow.model.BookingModel;
import br.com.HotelFlow.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    // GET ALL
    @GetMapping
    public List<BookingModel> getAllBookings() {
        return bookingRepository.findAll();
    }


}
