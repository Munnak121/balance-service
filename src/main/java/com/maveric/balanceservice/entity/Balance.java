package com.maveric.balanceservice.entity;

import lombok.*;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;


import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "balance")
public class Balance {
    @Id
    private String _id;
    private String accountId;
    private Integer amount;
    private String currency;

    @CreatedDate
    private DateTime createdAt;

    @LastModifiedDate
    private DateTime updatedAt;


}
