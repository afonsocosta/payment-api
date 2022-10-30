package br.com.payment.api.factory;

import br.com.payment.api.model.entity.PaymentType;
import br.com.payment.api.service.payment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceFactory {

    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private BankSlipService bankSlipService;
    @Autowired
    private PixService pixService;
    @Autowired
    private PaypalService paypalService;

    public PaymentService getPaymentService(PaymentType paymentType) {
        switch (paymentType){
            case CREDIT_CARD:
                return creditCardService;
            case PIX:
                return pixService;
            case PAYPAL:
                return paypalService;
            case BANK_SLIP:
                return bankSlipService;
        }

        return creditCardService;
    }

}
