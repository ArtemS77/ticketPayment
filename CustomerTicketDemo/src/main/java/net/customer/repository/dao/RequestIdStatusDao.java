package net.customer.repository.dao;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.customer.model.IdStatusTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Slf4j
@Repository
@Setter
public class RequestIdStatusDao implements Dao{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public IdStatusTable getById(Long requestId) {
        Session session = this.sessionFactory.getCurrentSession();

        IdStatusTable ticketPaymentRequestTable = session.get(IdStatusTable.class, requestId);

        return ticketPaymentRequestTable;
    }

    @Override
    public <IdStatusTable> void save(IdStatusTable idStatusTable) {
        Session session = this.sessionFactory.getCurrentSession();

        session.save(idStatusTable);
    }

    @Override
    public <IdStatusTable> void update(IdStatusTable idStatusTable) {

        Session session = sessionFactory.getCurrentSession();

        session.update(idStatusTable);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Long getRequestIdStatusByStatus(String statusCode) {
        String getIdByStatus = "SELECT requestId FROM IdStatusTable WHERE requestStatusCode = '" + statusCode + "'";
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(getIdByStatus).setMaxResults(1);
        Long requestId = null;

        try {
            requestId = (Long) query.getSingleResult();
        } catch (NoResultException noResultException) {
            log.info("No entity found for " + statusCode);
        }
        return requestId;
    }
}
