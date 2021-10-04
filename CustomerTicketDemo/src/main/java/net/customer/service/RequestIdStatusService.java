package net.customer.service;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.customer.model.IdStatusTable;
import net.customer.repository.dao.Dao;
import net.customer.repository.dao.RequestIdStatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Api(value = "ticket payment service")
@Slf4j
@Service("requestIdStatusService")
public class RequestIdStatusService {
    @Autowired
    IdStatusTable idStatusTable;
    @Autowired
    Dao requestIdStatusDao;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public IdStatusTable getRequestIdStatus(long id) {
        IdStatusTable idStatusTable = (IdStatusTable) requestIdStatusDao.getById(id);

        log.info(" ==== ticket payment request is returned " + idStatusTable.getRequestId() + " ==== ");
        return idStatusTable;
    }

    @Transactional
    public void saveRequestIdStatus(long id) {
        createDefaultIdStatusTable(id);
        requestIdStatusDao.save(idStatusTable);;

        log.info(" ==== " + idStatusTable.getRequestId() + " is saved ==== ");
    }

    @Transactional
    public void updateStatus(long id, HttpStatus httpStatus) {
        idStatusTable.setRequestId(id);
        idStatusTable.setExecutionStatus(httpStatus.getReasonPhrase());
        idStatusTable.setRequestStatusCode(String.valueOf(httpStatus.value()));

        requestIdStatusDao.update(idStatusTable);

        log.info(" ==== " + id + " is updated ==== ");
    }

    public IdStatusTable createDefaultIdStatusTable(long id) {
        IdStatusTable idStatusTable = new IdStatusTable();
        idStatusTable.setRequestId(id);
        idStatusTable.setRequestStatusCode(String.valueOf(HttpStatus.TEMPORARY_REDIRECT));
        idStatusTable.setExecutionStatus(HttpStatus.TEMPORARY_REDIRECT.name());

        return idStatusTable;
    }

}
