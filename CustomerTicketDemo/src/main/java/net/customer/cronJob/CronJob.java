package net.customer.cronJob;

import lombok.extern.slf4j.Slf4j;
import net.customer.exceptionHandler.CustomExceptionHandler;
import net.customer.repository.dao.RequestIdStatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
public class CronJob {
    @Autowired
    RequestIdStatusDao requestIdStatusDao;

    @Async
    @Scheduled(fixedRate = 60000)
    public void scheduleTaskAsync() {
        log.info("job task began");

        Long id = requestIdStatusDao.getRequestIdStatusByStatus("307");

        if (id != null) {
            HttpEntity<HttpStatus> httpEntity = new HttpEntity<>(new HttpHeaders());
            new RestTemplateBuilder()
                    .errorHandler(new CustomExceptionHandler())
                    .build()
                    .exchange("http://localhost:8080/ticket/check/" + id,
                            HttpMethod.PUT,
                            httpEntity,
                            Void.class);
            log.info("job task ended");
        }
        else {
            log.info("id = " + id);
        }
    }
}
