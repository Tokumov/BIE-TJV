package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.BookingDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.BookingService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.converter.BookingDtoToBookingConverter;
import tjv.tokumshy_semestrialwork.kazakhcuisine.converter.BookingToBookingDtoConverter;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Booking;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.BookingRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/booking")

public class BookingController {
    @Autowired
    private BookingService bookingService;
    private final BookingDtoToBookingConverter dtoToEntityConverter;
    private final BookingToBookingDtoConverter entityToDtoConverter;

    @Autowired
    public BookingController(BookingService bookingService,
                             BookingDtoToBookingConverter dtoToEntityConverter,
                             BookingToBookingDtoConverter entityToDtoConverter) {
        this.bookingService = bookingService;
        this.dtoToEntityConverter = dtoToEntityConverter;
        this.entityToDtoConverter = entityToDtoConverter;
    }

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = dtoToEntityConverter.convert(bookingDto);
        Booking savedBooking = bookingService.create(booking);
        BookingDto savedBookingDto = entityToDtoConverter.convert(savedBooking);
        return new ResponseEntity<>(savedBookingDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBooking(@PathVariable Long id) {
        Optional<Booking> bookingOptional = bookingService.readById(id);
        if (!bookingOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BookingDto bookingDto = entityToDtoConverter.convert(bookingOptional.get());
        return new ResponseEntity<>(bookingDto, HttpStatus.OK);
    }

    @GetMapping
    public List<BookingDto> getAllBookings() {
        List<Booking> bookingList = StreamSupport.stream(bookingService.readAll().spliterator(), false)
                .collect(Collectors.toList());
        return bookingList.stream()
                .map(entityToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void updateBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto) {
        Optional<Booking> existingBookingOptional = bookingService.readById(id);

        Booking bookingToUpdate = dtoToEntityConverter.convert(bookingDto);
        bookingService.update(id,bookingToUpdate);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        Optional<Booking> bookingOptional = bookingService.readById(id);
        if (!bookingOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookingService.deleteById(bookingOptional.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
