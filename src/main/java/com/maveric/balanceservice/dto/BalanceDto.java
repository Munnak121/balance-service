package com.maveric.balanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {
    private String _id;
    private String accountId;
    private Integer amount;
    private String currency;

    private DateTime createdAt;


    private DateTime updatedAt;
}
