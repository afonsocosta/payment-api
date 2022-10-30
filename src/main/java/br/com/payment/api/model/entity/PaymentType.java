package br.com.payment.api.model.entity;

import java.util.stream.Stream;

public enum PaymentType {

    CREDIT_CARD,
    PIX,
    PAYPAL,
    BANK_SLIP;


    public static PaymentType getByName(String type) {
        return Stream.of(PaymentType.values()).filter(p -> p.name().equals(type)).findFirst().orElse(null);
    }


    public static void main(String[] args) {
        String teste = "CREDIT_CARD";
        PaymentType paymentType = PaymentType.getByName(teste);
        System.out.println(paymentType);
    }
}
