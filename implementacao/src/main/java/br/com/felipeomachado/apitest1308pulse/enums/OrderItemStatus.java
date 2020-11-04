package br.com.felipeomachado.apitest1308pulse.enums;

public enum OrderItemStatus {
    ACTIVE(1),
    CANCELED(2),
    PROCESSED(3);

    private int code;

    private OrderItemStatus(int code) {
        this.code = code;
    }

    public static OrderItemStatus valueOf(int code) {

        for (OrderItemStatus value : OrderItemStatus.values()) {
            if(value.getCode() == code) {
                return  value;
            }
        }

        throw new IllegalArgumentException("Invalid OrderItemStatus code");
    }

    public int getCode() {
        return code;
    }
}
