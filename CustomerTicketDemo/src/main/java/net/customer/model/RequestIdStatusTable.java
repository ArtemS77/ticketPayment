package net.customer.model;

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
public class RequestIdStatusTable {

    @PrePersist
    private void preInit(){
        executionStatus = HttpStatus.ACCEPTED.getReasonPhrase();
        requestStatusCode = (short) HttpStatus.ACCEPTED.value();
    }

    @OneToOne(mappedBy = "requestIdStatusTable", cascade = CascadeType.ALL)
    private TicketPaymentRequestTable ticketPaymentRequestTable;

    @JoinColumn(name = "ticket_payment_request_table", referencedColumnName = "request_id")
    @ApiModelProperty(value = "request id")
    @Id
    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "execution_status", nullable=false)
    private String executionStatus;

    @Column(name = "request_status_code", nullable=false)
    private Short requestStatusCode;

}
