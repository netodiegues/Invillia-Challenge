package com.acme.billing.domain.model;

import com.acme.billing.infra.persistence.converter.PaymentStatusEnumConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jose.diegues
 */
@Entity
@Table(name = "payment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "payment_Status")
    @Convert(converter = PaymentStatusEnumConverter.class)
    private PaymentStatusEnum paymentStatus;

    @NotEmpty
    @Column(name = "number_Card")
    private String numberCard;

    @NotNull
    @Column(name = "payment_Date")
    private Date paymentDate;

    @NotNull
    @Column(name = "store_Id")
    private Long storeId;

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentStatusEnum getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusEnum paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.paymentStatus);
        hash = 41 * hash + Objects.hashCode(this.numberCard);
        hash = 41 * hash + Objects.hashCode(this.paymentDate);
        hash = 41 * hash + Objects.hashCode(this.storeId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Payment other = (Payment) obj;
        if (!Objects.equals(this.numberCard, other.numberCard)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.paymentStatus != other.paymentStatus) {
            return false;
        }
        if (!Objects.equals(this.paymentDate, other.paymentDate)) {
            return false;
        }
        return Objects.equals(this.storeId, other.storeId);
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", paymentStatus=" + paymentStatus + ", numberCard=" + numberCard + ", paymentDate=" + paymentDate + ", storeId=" + storeId + '}';
    }

}
