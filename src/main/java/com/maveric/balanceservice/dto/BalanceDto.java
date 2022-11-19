package com.maveric.balanceservice.dto;

import com.maveric.balanceservice.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {
    private String _id;
    @NotNull(message = "Account Id cannot be null")
    private String accountId;
    @NotNull(message = "amount  cannot be null")
    private Integer amount;
    @NotNull(message = "currency cannot be null")
    private Currency currency;

    private DateTime createdAt;


    private DateTime updatedAt;
}
