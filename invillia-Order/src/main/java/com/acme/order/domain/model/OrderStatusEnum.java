package com.acme.order.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;

/**
 *
 * @author jose.diegues
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderStatusEnum implements Serializable{
    PENDING(1L),
    IMPLEMENTED(2L),
    ISSUED(3L),
    CANCELED(4L); 
      
    @JsonValue
    private final Long id;

    private OrderStatusEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static OrderStatusEnum getOrderStatusEnumById(Long id) {
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (orderStatusEnum.getId().equals(id)) {
                return orderStatusEnum;
            }

        }
        throw new EnumConstantNotPresentException(OrderStatusEnum.class, id.toString());

    }
}
