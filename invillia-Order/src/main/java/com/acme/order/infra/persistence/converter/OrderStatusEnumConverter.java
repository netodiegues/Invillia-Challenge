package com.acme.order.infra.persistence.converter;

import com.acme.order.domain.model.OrderStatusEnum;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author jose.diegues
 */
@Converter(autoApply = true)
public class OrderStatusEnumConverter implements AttributeConverter<OrderStatusEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(OrderStatusEnum orderStatusEnum) {
        return orderStatusEnum.getId();
    }

    @Override
    public OrderStatusEnum convertToEntityAttribute(Long id) {
        if (id != null) {
            return OrderStatusEnum.getOrderStatusEnumById(id);
        } else {
            return null;
        }
    }

}
