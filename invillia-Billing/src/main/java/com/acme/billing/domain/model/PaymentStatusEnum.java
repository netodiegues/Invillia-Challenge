package com.acme.billing.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;

/**
 *
 * @author jose.diegues
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PaymentStatusEnum implements Serializable {
    PENDING(1L),
    IMPLEMENTED(2L),
    ISSUED(3L),
    CANCELED(4L);

    @JsonValue
    private final Long id;

    private PaymentStatusEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static PaymentStatusEnum getPaymentStatusEnumById(Long id) {
        for (PaymentStatusEnum paymentStatusEnum : PaymentStatusEnum.values()) {
            if (paymentStatusEnum.getId().equals(id)) {
                return paymentStatusEnum;
            }

        }
        throw new EnumConstantNotPresentException(PaymentStatusEnum.class, id.toString());

    }
}
