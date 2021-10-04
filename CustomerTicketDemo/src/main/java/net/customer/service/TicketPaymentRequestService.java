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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Api(value = "ticket payment service")
@Slf4j
@Service("ticketPaymentRequestService")
public class TicketPaymentRequestService {

    @Autowired
    @Qualifier("ticketPaymentRequestDao")
    Dao dao;
    @Autowired
    RequestIdStatusService requestIdStatusService;
    @Autowired
    IdGenerator idGenerator;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public TicketPaymentRequestTable getTicketPaymentRequest(long id) {
        TicketPaymentRequestTable ticketPaymentRequestTable = dao.getById(id);

        log.info(" ==== ticket payment request is returned " + ticketPaymentRequestTable.getRequestId() + " ==== ");
        return ticketPaymentRequestTable;
    }

    @Transactional
    public void saveTicketPaymentRequest(TicketPaymentRequestTable ticketPaymentRequestTable) {
        ticketPaymentRequestTable.setRequestId(idGenerator.generateUniqueId().get());
        IdStatusTable idStatusTable = requestIdStatusService.createDefaultIdStatusTable(ticketPaymentRequestTable.getRequestId());

        ticketPaymentRequestTable.setStatusTable(idStatusTable);
        dao.save(ticketPaymentRequestTable);

        log.info(" ==== " + ticketPaymentRequestTable.getRouteNumber() + " is saved ==== ");
    }
    @Transactional
    public void updateTicketPaymentRequest(TicketPaymentRequestTable ticketPaymentRequestTable) {
        ticketPaymentRequestTable.setRequestId(idGenerator.generateUniqueId().get());
        dao.update(ticketPaymentRequestTable);

        log.info(" ==== " + ticketPaymentRequestTable.getRouteNumber() + " is updated ==== ");
    }
}
