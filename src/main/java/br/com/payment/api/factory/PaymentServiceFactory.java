package br.com.payment.api.factory;

import br.com.payment.api.model.entity.PaymentType;
import br.com.payment.api.service.payment.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.EnumMap;

@Component
@Getter
public class PaymentServiceFactory {

    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private BankSlipService bankSlipService;
    @Autowired
    private PixService pixService;
    @Autowired
    private PaypalService paypalService;

    private final EnumMap<PaymentType, PaymentService> factoryMap = new EnumMap<>(PaymentType.class);

    @PostConstruct
    private void init() {
        factoryMap.put(PaymentType.CREDIT_CARD, creditCardService);
        factoryMap.put(PaymentType.BANK_SLIP, bankSlipService);
        factoryMap.put(PaymentType.PIX, pixService);
        factoryMap.put(PaymentType.PAYPAL, paypalService);
    }

    public PaymentService getPaymentService(PaymentType paymentType) {
        return  getFactoryMap().get(paymentType);
    }

}
