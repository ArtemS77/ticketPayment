package net.customer.service;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.customer.model.IdStatusTable;
import net.customer.repository.dao.Dao;
import net.customer.repository.dao.RequestIdStatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Api(value = "ticket payment service")
@Slf4j
@Service("requestIdStatusService")
public class RequestIdStatusService {
    @Autowired
    IdStatusTable idStatusTable;
    @Autowired
    Dao requestIdStatusDao;

    public IdStatusTable getRequestIdStatus(long id) {
        IdStatusTable idStatusTable = (IdStatusTable) requestIdStatusDao.getById(id);

        log.info(" ==== ticket payment request is returned " + idStatusTable.getRequestId() + " ==== ");
        return idStatusTable;
    }

    public void saveRequestIdStatus(long id, HttpStatus httpStatus) {

        idStatusTable.setRequestId(id);
        idStatusTable.setRequestStatusCode(String.valueOf(httpStatus.value()));
        idStatusTable.setExecutionStatus(httpStatus.name());

        requestIdStatusDao.save(idStatusTable);;

        log.info(" ==== " + idStatusTable.getRequestId() + " is saved ==== ");
    }

    public void updateStatus(long id, HttpStatus httpStatus) {

        idStatusTable.setRequestId(id);
        idStatusTable.setExecutionStatus(httpStatus.getReasonPhrase());
        idStatusTable.setRequestStatusCode(String.valueOf(httpStatus.value()));

        requestIdStatusDao.update(idStatusTable);

        log.info(" ==== " + id + " is updated ==== ");
    }

}
