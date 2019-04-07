package com.acme.order.domain.model;

import com.acme.order.infra.persistence.converter.OrderStatusEnumConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jose.diegues
 */
@Entity
@Table(name = "order")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "date_Confirmation")
    private Date dateConfirmation;

    @NotNull
    @Column(name = "order_Status")
    @Convert(converter = OrderStatusEnumConverter.class)
    private OrderStatusEnum orderStatus;

    @NotNull
    @Column(name = "store_Id")
    private Long storeId;

    @NotNull
    @Column(name = "payment_Id")
    private Long paymentId;

    @JsonIgnoreProperties
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
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

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
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
        int hash = 7;
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
        if (this.orderStatus != other.orderStatus) {
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
