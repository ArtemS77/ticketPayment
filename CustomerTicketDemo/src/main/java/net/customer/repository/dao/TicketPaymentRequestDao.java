package net.customer.repository.dao;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.customer.model.TicketPaymentRequestTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@Setter
@Repository
public class TicketPaymentRequestDao implements Dao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public <TicketPaymentRequestTable>void save(TicketPaymentRequestTable ticketPaymentRequestTable) {
        Session session = this.sessionFactory.getCurrentSession();

        session.save(ticketPaymentRequestTable);
    }

    @Override
    public TicketPaymentRequestTable getById(Long requestId) {
        Session session = this.sessionFactory.getCurrentSession();

        TicketPaymentRequestTable ticketPaymentRequestTable = session.get(TicketPaymentRequestTable.class, requestId);

        return ticketPaymentRequestTable;
    }

    @Override
    public <TicketPaymentRequestTable> void update(TicketPaymentRequestTable ticketPaymentRequestTable) {
        Session session = this.sessionFactory.getCurrentSession();

        session.update(ticketPaymentRequestTable);
    }

    public List<TicketPaymentRequestTable> findPastPaymentRequestByClientId(Long clientId) {
        Session session = this.sessionFactory.getCurrentSession();


        String findPastPaymentRequestByClientId = "FROM TicketPaymentRequestTable " +
                "WHERE request_date <= DATE(NOW()) AND client_id = " + clientId;

        Query query = session.createQuery(findPastPaymentRequestByClientId);

        List<TicketPaymentRequestTable> pastPaymentList = null;

        try {
            pastPaymentList = query.getResultList();
        } catch (NoResultException noResultException) {
            log.info("No list found for " + clientId);
        }

        return pastPaymentList;
    }
}
