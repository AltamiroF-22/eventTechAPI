package com.eventotech.api.domain.coupon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Future;

public record CouponRequestDTO( 
    @NotBlank(message = "Code cannot be blank")
    String      code,
    
    @NotNull(message = "Discount cannot be null")
    @Min(value = 0, message = "Discount must be greater than or equal to 0")
    @Max(value = 100, message = "Discount must be less than or equal to 100")
    Integer     discount,
    
    @NotNull(message = "Valid date cannot be null")
    Long        valid
) {
}

