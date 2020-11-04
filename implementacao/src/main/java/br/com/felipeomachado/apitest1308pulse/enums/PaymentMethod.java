package br.com.felipeomachado.apitest1308pulse.enums;

public enum PaymentMethod {
    A_VISTA(1),
    BOLETO(2),
    CARTAO(3);

    private int code;

    private PaymentMethod(int code) {
        this.code = code;
    }

    public static PaymentMethod valueOf(int code) {

        for (PaymentMethod value : PaymentMethod.values()) {
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
