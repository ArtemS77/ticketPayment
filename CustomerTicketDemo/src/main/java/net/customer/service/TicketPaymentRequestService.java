package net.customer.service;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.customer.model.IdStatusTable;
import net.customer.model.TicketPaymentRequestTable;
import net.customer.repository.dao.Dao;
import net.customer.repository.dao.RequestIdStatusDao;
import net.customer.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Api(value = "ticket payment service")
@Slf4j
@Service("ticketPaymentRequestService")
public class TicketPaymentRequestService {

    @Autowired
    @Qualifier("ticketPaymentRequestDao")
    Dao dao;
    @Autowired
    IdStatusTable idStatusTable;
    @Autowired
    RequestIdStatusDao requestIdStatusDao;
    @Autowired
    IdGenerator idGenerator;

    public TicketPaymentRequestTable getTicketPaymentRequest(long id) {
        TicketPaymentRequestTable ticketPaymentRequestTable = dao.getById(id);

        log.info(" ==== ticket payment request is returned " + ticketPaymentRequestTable.getRequestId() + " ==== ");
        return ticketPaymentRequestTable;
    }

    public void saveTicketPaymentRequest(TicketPaymentRequestTable ticketPaymentRequestTable) {
        ticketPaymentRequestTable.setRequestId(idGenerator.generateUniqueId().get());
        dao.save(ticketPaymentRequestTable);

        idStatusTable.setRequestId(ticketPaymentRequestTable.getRequestId());
        requestIdStatusDao.save(idStatusTable);

        log.info(" ==== " + ticketPaymentRequestTable.getRouteNumber() + " is saved ==== ");
    }

    public void updateTicketPaymentRequest(TicketPaymentRequestTable ticketPaymentRequestTable) {
        ticketPaymentRequestTable.setRequestId(idGenerator.generateUniqueId().get());
        dao.update(ticketPaymentRequestTable);

        log.info(" ==== " + ticketPaymentRequestTable.getRouteNumber() + " is updated ==== ");
    }
}
