package com.zecchinel.bitbudget.controller;

import com.zecchinel.bitbudget.dto.PaymentMethodDto;
import com.zecchinel.bitbudget.model.PaymentMethod;
import com.zecchinel.bitbudget.repository.BaseEntityRepository;
import com.zecchinel.bitbudget.repository.PaymentMethodRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController extends BaseEntityController<PaymentMethod, PaymentMethodDto> {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodController(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    protected BaseEntityRepository<PaymentMethod> getRepository() {
        return this.paymentMethodRepository;
    }

    @Override
    protected PaymentMethodDto convertToDto(PaymentMethod entity) {
        return new PaymentMethodDto(entity.getId(), entity.getDescription());
    }
}