package com.bookshop.backend.service;

import com.bookshop.backend.dao.PaymentRepository;
import com.bookshop.backend.entity.Payment;
import com.bookshop.backend.requestmodels.PaymentInfoRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	public PaymentService(PaymentRepository paymentRepository, @Value("${stripe.key.secret}") String secretKey) {
		this.paymentRepository = paymentRepository;
		Stripe.apiKey = secretKey;
	}

	public PaymentIntent createPaymentIntent(PaymentInfoRequest paymentInfoRequest) throws StripeException {
		List<String> paymentMethodTypes = new ArrayList<>();
		paymentMethodTypes.add("card");

		Map<String, Object> params = new HashMap<>();
		params.put("amount", paymentInfoRequest.getAmount());
		params.put("currency", paymentInfoRequest.getCurrency());
		params.put("payment_method_types", paymentMethodTypes);

		return PaymentIntent.create(params);
	}

	public ResponseEntity<String> stripePayment(String userEmail) throws Exception {
		Payment payment = paymentRepository.findByUserEmail(userEmail);
		if (payment == null) {
			throw new Exception("payment is null");
		}

		payment.setAmount(00.00);
		paymentRepository.save(payment);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
