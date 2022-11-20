package com.maveric.balanceservice.dto;

import com.maveric.balanceservice.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {
    private String id;
    @NotBlank(message = "Account Id cannot be null or Blank")
    private String accountId;
    @NotNull(message = "Amount cannot be null")
    private Integer amount;
    @NotNull(message = "Currency cannot be null")
    private Currency currency;

    private DateTime createdAt;


    private DateTime updatedAt;
}
