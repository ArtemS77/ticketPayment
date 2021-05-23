package net.customer.ticketPaymentService.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.customer.TicketPaymentRequestService;
import net.customer.model.TicketPaymentRequestTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Api(value = "request for tickets payment")
@Slf4j
@RestController
@RequestMapping("/request")
public class TicketIdRestController {
    @Autowired
    TicketPaymentRequestService ticketPaymentRequestService;

    @ApiOperation(value = "save request object and get request id", response = Long.class)
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Long> saveRequest(@RequestBody @Valid TicketPaymentRequestTable ticketPaymentRequestTable) {

        System.out.println("gdddddddddddddddddd");

        if (ticketPaymentRequestTable == null) {
            HttpHeaders httpHeaders =  new HttpHeaders();
            httpHeaders.set("Message header", " TicketPaymentRequestTable is not valid");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if (ticketPaymentRequestTable.getRouteNumber() == null) {
            HttpHeaders httpHeaders =  new HttpHeaders();
            httpHeaders.set("Message header", "TicketPaymentRequestTable does not have route number");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if (ticketPaymentRequestTable.getClientId() == null) {
            HttpHeaders httpHeaders =  new HttpHeaders();
            httpHeaders.set("Message header", "TicketPaymentRequestTable does not have client id");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if (ticketPaymentRequestTable.getDate() == null) {
            HttpHeaders httpHeaders =  new HttpHeaders();
            httpHeaders.set("Message header", "TicketPaymentRequestTable does not have date");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        ticketPaymentRequestService.saveTicketPaymentRequest(ticketPaymentRequestTable);

        return new ResponseEntity<>(ticketPaymentRequestTable.getRequestId(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "get request list for concrete client", response = List.class)
    @RequestMapping(value = "/get-data/{requestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TicketPaymentRequestTable> getFutureRequests(@PathVariable Long clientId) {

          TicketPaymentRequestTable ticketPaymentRequestTable = ticketPaymentRequestService.getTicketPaymentRequest(clientId);

          if (ticketPaymentRequestTable == null) {
              HttpHeaders httpHeaders =  new HttpHeaders();
              httpHeaders.set("Message header", "There is no such client");
              return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
          }

        return new ResponseEntity<>(ticketPaymentRequestTable, HttpStatus.OK);
    }
}
