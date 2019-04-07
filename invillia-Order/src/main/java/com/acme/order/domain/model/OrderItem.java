package com.acme.order.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author jose.diegues
 */
@Entity
@Table(name = "orderItem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "price_unitary")
    private BigDecimal priceUnitary;

    @NotNull
    @Column(name = "Quantity")
    private BigDecimal quantity;

    @ManyToOne
    @JoinColumn(name = "order_Id")
    private Order order;

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPriceUnitary() {
        return priceUnitary;
    }

    public void setPriceUnitary(BigDecimal priceUnitary) {
        this.priceUnitary = priceUnitary;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.priceUnitary);
        hash = 79 * hash + Objects.hashCode(this.quantity);
        hash = 79 * hash + Objects.hashCode(this.order);
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
        final OrderItem other = (OrderItem) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.priceUnitary, other.priceUnitary)) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }

        return Objects.equals(this.order, other.order);

    }

    @Override
    public String toString() {
        return "OrderItem{" + "id=" + id + ", description=" + description + ", priceUnitary=" + priceUnitary + ", quantity=" + quantity + ", order=" + order + '}';
    }

}
