package net.customer.repository.dao;

import lombok.Setter;
import net.customer.model.RequestIdStatusTable;
import net.customer.model.TicketPaymentRequestTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Setter
@Repository
public class TicketPaymentRequestDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    RequestIdStatusTable requestIdStatusTable;


//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Transactional
    public void saveTicketPaymentRequest(TicketPaymentRequestTable ticketPaymentRequestTable) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(ticketPaymentRequestTable);
        requestIdStatusTable.setRequestId(ticketPaymentRequestTable.getRequestId());
        session.save(requestIdStatusTable);
    }

    public TicketPaymentRequestTable getTicketPaymentRequest(long requestId) {
        Session session = this.sessionFactory.getCurrentSession();
        TicketPaymentRequestTable ticketPaymentRequestTable = (TicketPaymentRequestTable) session.get(TicketPaymentRequestTable.class, requestId);

        return ticketPaymentRequestTable;
    }
}
