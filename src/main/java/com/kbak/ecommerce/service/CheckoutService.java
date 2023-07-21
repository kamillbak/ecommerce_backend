package com.kbak.ecommerce.service;

import com.kbak.ecommerce.dto.Purchase;
import com.kbak.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
