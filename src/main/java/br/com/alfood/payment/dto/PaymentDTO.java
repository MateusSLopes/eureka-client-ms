package br.com.alfood.payment.dto;

import br.com.alfood.payment.model.Status;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDTO {
    private Long id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expiration;
    private String code;
    private Status status;
    private Long orderId;
    private Long paymentMethodId;
}
