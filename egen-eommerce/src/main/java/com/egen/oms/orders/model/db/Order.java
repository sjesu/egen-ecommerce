package com.egen.oms.orders.model.db;

import com.egen.oms.orders.model.Address;
import com.egen.oms.orders.model.OrderDetails;
import com.egen.oms.orders.model.PaymentDetails;
import com.egen.oms.orders.utils.AddressConverter;
import com.egen.oms.orders.utils.OrderJsonConverter;
import com.egen.oms.orders.utils.PaymentJsonConverter;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(name = "id_seq", sequenceName = "id_seq",allocationSize = 1,initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @Column
    Long id;

    @Column(name = "order_num")
    String orderNum;

    @Convert(converter = OrderJsonConverter.class)
    @Column(name = "order_json")
    OrderDetails orderJson;

    @Column(name = "order_status")
    String orderStatus;

    @Convert(converter = PaymentJsonConverter.class)
    @Column(name = "payment_json")
    List<PaymentDetails> paymentJson;

    @Column(name = "order_date")
    Date orderDate;

    @Convert(converter = AddressConverter.class)
    @Column(name = "shipping_address_json")
    Address shippingAddress;

    @Convert(converter = AddressConverter.class)
    @Column(name = "billing_address_json")
    Address billingAddress;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    Date modifiedDate;
}
