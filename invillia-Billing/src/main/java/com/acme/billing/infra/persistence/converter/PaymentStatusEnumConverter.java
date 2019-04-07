package com.acme.billing.infra.persistence.converter;

import com.acme.billing.domain.model.PaymentStatusEnum;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author jose.diegues
 */
@Converter(autoApply = true)
public class PaymentStatusEnumConverter implements AttributeConverter<PaymentStatusEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(PaymentStatusEnum paymentStatusEnum) {
        return paymentStatusEnum.getId();
    }

    @Override
    public PaymentStatusEnum convertToEntityAttribute(Long id) {
        if (id != null) {
            return PaymentStatusEnum.getPaymentStatusEnumById(id);
        } else {
            return null;
        }
    }

}
