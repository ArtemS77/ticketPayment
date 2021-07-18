package net.customer.restControllers;

import io.swagger.annotations.Api;
import net.customer.util.StatusGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "returns random status")
@RestController
@RequestMapping("/status")
public class StatusRestController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<HttpStatus> getRandomStatus() {
        HttpStatus httpStatus = StatusGenerator.genetateStatus();
        return new ResponseEntity<>(httpStatus, httpStatus);
    }
}
