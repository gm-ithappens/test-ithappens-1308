package br.com.felipeomachado.apitest1308pulse.paymentMethod.enums;

public enum PaymentType {
    A_VISTA(1),
    BOLETO(2),
    CARTAO(3);

    private int code;

    private PaymentType(int code) {
        this.code = code;
    }

    public static PaymentType valueOf(int code) {

        for (PaymentType value : PaymentType.values()) {
            if(value.getCode() == code) {
                return  value;
            }
        }

        throw new IllegalArgumentException("Invalid PaymentMethod code");
    }

    public int getCode() {
        return code;
    }
}
