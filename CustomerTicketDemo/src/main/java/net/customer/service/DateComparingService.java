package net.customer.service;

import lombok.extern.slf4j.Slf4j;
import net.customer.model.TicketPaymentRequestTable;
import net.customer.repository.dao.TicketPaymentRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class DateComparingService {
    @Autowired
    private TicketPaymentRequestDao ticketPaymentRequestDao;

    @Transactional
    public List<TicketPaymentRequestTable> findPastRequests(Long clientId, String status) {

        List<TicketPaymentRequestTable> pastRequestsStatusList = StreamSupport
                .stream(ticketPaymentRequestDao.findPastPaymentRequestByClientId(clientId).spliterator(), false)
                .sorted((TicketPaymentRequestTable table1, TicketPaymentRequestTable table2)
                        -> table2.getDate().compareTo(table1.getDate()))
                .filter(TicketPaymentRequestTable
                        -> TicketPaymentRequestTable.getStatusTable().getExecutionStatus().equals(status))
                .collect(Collectors.toList());

        log.info("future requests returned, size = " + pastRequestsStatusList.size());

        return pastRequestsStatusList;
    }
}
