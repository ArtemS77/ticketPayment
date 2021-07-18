package net.customer.restControllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.customer.exceptionHandler.CustomExceptionHandler;
import net.customer.service.RequestIdStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Api(value = "checks the ticket execution status by id")
@Slf4j
@RestController
@RequestMapping("/ticket/check")
public class StatusChekingRestController {
    @Autowired
    private RequestIdStatusService requestIdStatusService;

    @ApiOperation(value = "checking the request status by request id", response = HttpStatus.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<HttpStatus> checkStatus(@PathVariable("id") Long requestId) {

        if (requestId == null) {
            log.info("request Id is null");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Message header", "request Id is null");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        ResponseEntity<HttpStatus> responseEntity =
                new RestTemplateBuilder()
                        .errorHandler(new CustomExceptionHandler())
                        .build()
                        .getForEntity("http://localhost:8080/status",  HttpStatus.class);

        requestIdStatusService.updateStatus(requestId, responseEntity.getBody());

        return new ResponseEntity<>(responseEntity.getBody(), responseEntity.getBody());
    }
}
