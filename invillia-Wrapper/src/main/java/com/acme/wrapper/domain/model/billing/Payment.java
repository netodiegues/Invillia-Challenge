package com.acme.wrapper.domain.model.billing;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author jose.diegues
 */
public class Payment implements Serializable {

    private Long id;

    private Long paymentStatus;

    private String numberCard;

    private Date paymentDate;

    private Long storeId;

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Long paymentStatus) {
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
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.paymentStatus);
        hash = 97 * hash + Objects.hashCode(this.numberCard);
        hash = 97 * hash + Objects.hashCode(this.paymentDate);
        hash = 97 * hash + Objects.hashCode(this.storeId);
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
        if (!Objects.equals(this.paymentStatus, other.paymentStatus)) {
            return false;
        }
        if (!Objects.equals(this.paymentDate, other.paymentDate)) {
            return false;
        }
        if (!Objects.equals(this.storeId, other.storeId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", paymentStatus=" + paymentStatus + ", numberCard=" + numberCard + ", paymentDate=" + paymentDate + ", storeId=" + storeId + '}';
    }

}
