package net.customer.ticketPaymentService.util.idRandomGenerator;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class IdGenerator {
    public AtomicLong generateUniqueId() {
        AtomicLong id = new AtomicLong(-1);
        do {
            id.addAndGet(UUID.randomUUID().getMostSignificantBits());
        } while (id.get() < 0);
        return id;
    }
}
