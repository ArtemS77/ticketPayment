package net.customer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import javax.persistence.*;

@Setter
@Getter
@Component
@Entity
@Table(name = "id_status_table")
public class IdStatusTable {

    @PrePersist
    private void initStatus(){
        executionStatus = HttpStatus.TEMPORARY_REDIRECT.getReasonPhrase();
        requestStatusCode = String.valueOf(HttpStatus.TEMPORARY_REDIRECT.value());
    }

    @JoinColumn(name = "request_id", table = "ticket_payment_request_table", referencedColumnName = "request_id")
    @OneToOne
    @JsonBackReference
    TicketPaymentRequestTable ticketPaymentRequestTable;

    @ApiModelProperty(value = "request id")
    @Id
    @Column(name = "request_id", updatable=true)
    private Long requestId;

    @Column(name = "execution_status", nullable=false)
    private String executionStatus;

    @Column(name = "request_status_code", nullable=false)
    private String requestStatusCode;

}
