package net.customer.restControllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.customer.service.DateComparingService;
import net.customer.service.TicketPaymentRequestService;
import net.customer.model.TicketPaymentRequestTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(value = "post and get for tickets payment")
@Slf4j
@RestController
@RequestMapping("/ticket")
public class TicketIdRestController {
    @Autowired
    TicketPaymentRequestService ticketPaymentRequestService;
    @Autowired
    DateComparingService dateComparingService;


    @ApiOperation(value = "get request info for concrete id", response = TicketPaymentRequestTable.class)
    @RequestMapping(value = "/get-data/{requestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TicketPaymentRequestTable> getTicketTable(@PathVariable Long requestId) {

          TicketPaymentRequestTable ticketPaymentRequestTable = ticketPaymentRequestService.getTicketPaymentRequest(requestId);

          if (ticketPaymentRequestTable == null) {
              HttpHeaders httpHeaders =  new HttpHeaders();
              httpHeaders.set("Message header", "There is no such client");
              return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
          }

        return new ResponseEntity<>(ticketPaymentRequestTable, HttpStatus.OK);
    }


    @ApiOperation(value = "get list of request info  for concrete client id and status", response = TicketPaymentRequestTable.class)
    @RequestMapping(value = "/get-past-data/{clientId}/{executionStatus}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<TicketPaymentRequestTable>> getPastData(@PathVariable Long clientId, @PathVariable String executionStatus) {

        if (clientId == null) {
            HttpHeaders httpHeaders =  new HttpHeaders();
            httpHeaders.set("Message header", "There is no clientId");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if (executionStatus == null) {
            HttpHeaders httpHeaders =  new HttpHeaders();
            httpHeaders.set("Message header", "There is no executionStatus");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        List<TicketPaymentRequestTable> pastList = dateComparingService.findPastRequests(clientId, executionStatus);

        return new ResponseEntity<>(pastList, HttpStatus.OK);
    }


    @ApiOperation(value = "update ticket for concrete id", response = TicketPaymentRequestTable.class)
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TicketPaymentRequestTable> putTicketTable(@RequestBody TicketPaymentRequestTable ticketPaymentRequestTable) {

        if (ticketPaymentRequestTable == null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Message header", " TicketPaymentRequestTable is not valid");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if (ticketPaymentRequestTable.getRouteNumber() == null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Message header", "TicketPaymentRequestTable does not have route number");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if (ticketPaymentRequestTable.getClientId() == null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Message header", "TicketPaymentRequestTable does not have client id");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if (ticketPaymentRequestTable.getDate() == null) {
            HttpHeaders httpHeaders =  new HttpHeaders();
            httpHeaders.set("Message header", "TicketPaymentRequestTable does not have date");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        ticketPaymentRequestService.updateTicketPaymentRequest(ticketPaymentRequestTable);

        return new ResponseEntity<>(ticketPaymentRequestTable, HttpStatus.OK);
    }


    @ApiOperation(value = "post ticket object and return request id", response = Long.class)
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Long> postTicketTable(@RequestBody TicketPaymentRequestTable ticketPaymentRequestTable) {

        if (ticketPaymentRequestTable == null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Message header", "TicketPaymentRequestTable is not valid");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if (ticketPaymentRequestTable.getRouteNumber() == null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Message header", "TicketPaymentRequestTable does not have route number");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if (ticketPaymentRequestTable.getClientId() == null) {
            HttpHeaders httpHeaders = new HttpHeaders();
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
}
