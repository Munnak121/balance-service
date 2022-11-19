package com.maveric.balanceservice.entity;

import com.maveric.balanceservice.enums.Currency;
import lombok.*;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "balance")
public class Balance {
    @Id
    private String _id;
    @NotNull(message = "Account Id cannot be null")
    private String accountId;

    @NotNull(message = "amount  cannot be null")
    private Integer amount;

    @NotNull(message = "Currency Cannot be null")
    private Currency currency;

    @CreatedDate
    private DateTime createdAt;

    @LastModifiedDate
    private DateTime updatedAt;


}
