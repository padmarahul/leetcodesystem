package com.sec.leetcodesystem.service.impl;




import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.sec.leetcodesystem.entities.Customer;
import com.sec.leetcodesystem.entities.PaymentInfo;
import com.sec.leetcodesystem.entities.SubscriptionPlan;
import com.sec.leetcodesystem.entities.PaymentInfo.PaymentStatus;
import com.sec.leetcodesystem.entities.Subscription;
import com.sec.leetcodesystem.repository.CustomerRepository;
import com.sec.leetcodesystem.repository.PaymentInfoRepository;
import com.sec.leetcodesystem.repository.SubscriptionPlanRepository;
import com.sec.leetcodesystem.repository.SubscriptionRepository;
import com.sec.leetcodesystem.service.PayPalService;
import com.sec.leetcodesystem.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
public class PaymentServiceImpl implements PaymentService  {

    @Autowired
    private PaymentInfoRepository paymentRepository;

   

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

   @Autowired
   private SubscriptionRepository subscriptionRepository;
   

   @Autowired
   private CustomerRepository customerRepository;


    /**
     * Get the rate of the selected subscription plan.
     * 
     * @param planType The type of the plan ("monthly" or "yearly")
     * @return The rate for the selected plan
     */
    public double getPlanRate(String planType) {
        SubscriptionPlan plan = subscriptionPlanRepository.findByPlanType(planType);
        if (plan != null) {
            return plan.getRate();
        } else {
            throw new IllegalArgumentException("Invalid subscription plan type.");
        }
    }
    
    /**
     * Save payment information after successful PayPal payment
     */
    public void savePaymentInfo(Payment payment, String planType, Customer customer) {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPaymentId(payment.getId());
        paymentInfo.setPayerId(payment.getPayer().getPayerInfo().getPayerId());
        paymentInfo.setPaymentStatus(PaymentStatus.SUCCESS.getStr());

        // Extract amount and currency from payment details
        Transaction transaction = payment.getTransactions().get(0);
        paymentInfo.setAmount(Double.parseDouble(transaction.getAmount().getTotal()));
        paymentInfo.setCurrency(transaction.getAmount().getCurrency());
        // Save payment info to the database
        paymentRepository.save(paymentInfo);

        // Enable subscription for the customer
        enableSubscription(customer, planType);
    }

    /**
     * Enable subscription for the customer based on the plan type (monthly/yearly).
     */
    private void enableSubscription(Customer customer, String planType) {
        Subscription subscription = new Subscription();
        subscription.setCustomer(customer);
        subscription.setActive(true);
        subscription.setStartDate(LocalDateTime.now());

        // Set subscription end date based on plan type
        if (planType.equalsIgnoreCase("monthly")) {
            subscription.setEndDate(LocalDateTime.now().plusMonths(1));
        } else if (planType.equalsIgnoreCase("yearly")) {
            subscription.setEndDate(LocalDateTime.now().plusYears(1));
        }

        // Save the subscription
        subscriptionRepository.save(subscription);
    }

 
    public String checkSubscription(int customerId) throws Exception {
        // Fetch the subscription for the given customer from the repository
        Optional<Subscription> optionalSubscription = subscriptionRepository.findByCustomer_CustomerId(customerId);

        if (!optionalSubscription.isPresent()) {
            return "No subscription found for this customer.";
        }

        Subscription subscription = optionalSubscription.get();

        // Check both isActive and endDate to determine if the subscription is active
        if (subscription.isActive() && subscription.getEndDate().isAfter(LocalDateTime.now())) {
            return "Subscription is active. Start Date: " + subscription.getStartDate() +
                   ", End Date: " + subscription.getEndDate();
        } else {
            return "No active subscription found.";
        }
    }
    


	
}
