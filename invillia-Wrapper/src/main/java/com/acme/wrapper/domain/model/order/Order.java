package com.acme.wrapper.domain.model.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jose.diegues
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order implements Serializable {

    private Long id;

    private String address;

    private Date dateConfirmation;

    private Long orderStatus;

    private Long storeId;

    private Long paymentId;

    private List<OrderItem> items;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateConfirmation() {
        return dateConfirmation;
    }

    public void setDateConfirmation(Date dateConfirmation) {
        this.dateConfirmation = dateConfirmation;
    }

    public Long getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Long orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.address);
        hash = 97 * hash + Objects.hashCode(this.dateConfirmation);
        hash = 97 * hash + Objects.hashCode(this.orderStatus);
        hash = 97 * hash + Objects.hashCode(this.storeId);
        hash = 97 * hash + Objects.hashCode(this.paymentId);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dateConfirmation, other.dateConfirmation)) {
            return false;
        }
        if (!Objects.equals(this.orderStatus, other.orderStatus)) {
            return false;
        }
        if (!Objects.equals(this.storeId, other.storeId)) {
            return false;
        }
        if (!Objects.equals(this.paymentId, other.paymentId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", address=" + address + ", dateConfirmation=" + dateConfirmation + ", orderStatus=" + orderStatus + ", storeId=" + storeId + ", paymentId=" + paymentId + '}';
    }

}
