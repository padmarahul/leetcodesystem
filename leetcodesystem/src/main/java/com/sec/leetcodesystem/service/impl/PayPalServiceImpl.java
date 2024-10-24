package com.sec.leetcodesystem.service.impl;




import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.sec.leetcodesystem.service.PayPalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class PayPalServiceImpl implements PayPalService {
	
	@Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    
    @Value("${paypal.mode}")
    private String mode;
   

    /**
     * Create a payment for the subscription.
     * 
     * @param total       Total amount to be charged
     * @param currency    Currency to be used for payment (e.g., "USD")
     * @param method      Payment method (e.g., "paypal")
     * @param intent      Payment intent (e.g., "sale")
     * @param description Description of the payment (e.g., "Monthly Subscription")
     * @param cancelUrl   URL to redirect in case of cancellation
     * @param successUrl  URL to redirect in case of success
     * @return Payment object with payment details
     * @throws PayPalRESTException if there is an error in creating the payment
     */
    public Payment createPayment(Double total, String currency, String method,
                                 String intent, String description, String cancelUrl, String successUrl) 
                                 throws PayPalRESTException {

    	Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(Arrays.asList(transaction));

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        APIContext context = new APIContext(clientId, clientSecret, mode);
        return payment.create(context);
    }

    /**
     * Execute the PayPal payment after the user approves it.
     * 
     * @param paymentId ID of the PayPal payment to execute
     * @param payerId   ID of the payer
     * @return Payment object with the payment execution details
     * @throws PayPalRESTException if there is an error during execution
     */
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        APIContext context = new APIContext(clientId, clientSecret, mode);
        return payment.execute(context, paymentExecution);
    }
}
