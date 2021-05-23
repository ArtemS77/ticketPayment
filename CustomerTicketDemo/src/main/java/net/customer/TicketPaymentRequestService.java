package net.customer;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.customer.model.TicketPaymentRequestTable;
import net.customer.repository.dao.TicketPaymentRequestDao;
import net.customer.ticketPaymentService.util.idRandomGenerator.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Api(value = "ticket payment service")
@Slf4j
@Service("ticketPaymentRequestService")
public class TicketPaymentRequestService {

    @Autowired
    TicketPaymentRequestDao ticketPaymentRequestDao;
    @Autowired
    IdGenerator idGenerator;

    public TicketPaymentRequestTable getTicketPaymentRequest(long id) {
        TicketPaymentRequestTable ticketPaymentRequestTable = ticketPaymentRequestDao.getTicketPaymentRequest(id);

        log.info(" ticket payment request is returned " + ticketPaymentRequestTable.getRequestId());

        return ticketPaymentRequestTable;
    }

    public void saveTicketPaymentRequest(TicketPaymentRequestTable ticketPaymentRequestTable) {
        ticketPaymentRequestTable.setRequestId(idGenerator.generateUniqueId().get());
        ticketPaymentRequestDao.saveTicketPaymentRequest(ticketPaymentRequestTable);

        log.info(ticketPaymentRequestTable.getRouteNumber() + " is saved ");
    }
}
