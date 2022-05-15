package com.example.airbnbcloneback.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseDTO {
    Long tenantID;
    Long propertyID;
    int leaseDuration;
    double leasePaymentAmount;
}
