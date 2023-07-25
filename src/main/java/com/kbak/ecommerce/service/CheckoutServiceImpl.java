package com.kbak.ecommerce.service;

import com.kbak.ecommerce.dao.CustomerRepository;
import com.kbak.ecommerce.dto.Purchase;
import com.kbak.ecommerce.dto.PurchaseResponse;

import com.kbak.ecommerce.entity.Customer;
import com.kbak.ecommerce.entity.Order;
import com.kbak.ecommerce.entity.OrderItem;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        // take order from Purchase object
        Order order = purchase.getOrder();

        // generate random tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // add orderItems to order object
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // add adresses to order
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // add order to customer
        Customer customer = purchase.getCustomer();
        customer.add(order);

        // save customer to DB using repository
        customerRepository.save(customer);

        // return tracing number as a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
