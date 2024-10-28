package com.sec.leetcodesystem.controller;


import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.sec.leetcodesystem.entities.Customer;
import com.sec.leetcodesystem.entities.PaymentInfo;
import com.sec.leetcodesystem.entities.PaymentInfo.PaymentStatus;
import com.sec.leetcodesystem.exceptions.ResourceNotFoundException;
import com.sec.leetcodesystem.repository.CustomerRepository;
import com.sec.leetcodesystem.repository.PaymentInfoRepository;
import com.sec.leetcodesystem.service.PayPalService;
import com.sec.leetcodesystem.service.PaymentService;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping("/lcms/payment")
public class PaymentController {

    @Autowired
    private PayPalService payPalService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerRepository customerRepository;
    
	@Autowired
    private PaymentInfoRepository paymentInfoRepository;
	
	

    //private static final String SUCCESS_URL = "http://localhost:8080/lcms/payment/";
    //private static final String CANCEL_URL = "http://localhost:8080/api/payment/cancel";

    @GetMapping("/{userId}/create")
    public  ResponseEntity<?>  createPayment(@RequestParam String planType, @PathVariable int userId) {
        try {
            // Fetch the plan rate dynamically
            double amount = paymentService.getPlanRate(planType);

            Payment payment = payPalService.createPayment(
                    amount,         // Total amount based on plan
                    "USD",          // Currency
                    "paypal",       // Payment method
                    "sale",         // Intent
                    planType + " Subscription",  // Description
                    "https://localhost:8443/lcms/payment/"+userId+"/cancel",     // Cancel URL
                    "https://localhost:8443/lcms/payment/"+userId+"/success"     // Success URL
            );
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                	  return ResponseEntity.ok(link.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error during payment creation: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body("Unable to create payment");
    }

    @GetMapping("/{userId}/success")
    public ResponseEntity<?> successPayment(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, @RequestParam String planType, @PathVariable int userId) {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
              
            	Customer customer = customerRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

            	  // Save payment information and enable subscription
                paymentService.savePaymentInfo(payment, planType, customer);
                String redirectUrl = "http://localhost:3000/";
                return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUrl)).build();
            }
            else {
                return ResponseEntity.unprocessableEntity().body("Payment execution failed. State: " + payment.getState());
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error during payment execution: " + e.getMessage());
        }
 
    }

    @GetMapping("/{userId}/cancel")
    public ResponseEntity<?> cancelPayment( @PathVariable long userId ) {
        try {
            PaymentInfo paymentInfo = new PaymentInfo();
           paymentInfo.setPaymentId(UUID.randomUUID().toString());
           paymentInfo.setPayerId("EVYULUZWTXRC2");
           paymentInfo.setCurrency("USD");
            if (paymentInfo != null) {
                paymentInfo.setPaymentStatus(PaymentStatus.CANCELLED.getStr());
                
            }
            String redirectUrl = "http://localhost:3000/";
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUrl)).build();
      
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while cancelling the payment");
        }
    }
    
    
    /**
     * API to check if a customer has an active subscription.
     * @param customerId the ID of the customer to check subscription for
     * @return subscription status and details if active
     */
    @GetMapping("/{customerId}/status")
    public ResponseEntity<String> checkSubscriptionStatus(@PathVariable int customerId) {
        try {
            String status = paymentService.checkSubscription(customerId);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

