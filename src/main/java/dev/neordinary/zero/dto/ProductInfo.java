package dev.neordinary.zero.dto;

import lombok.Builder;

@Builder
public record ProductInfo(
        String productName,

        Double productSugar,

        Integer productKcal,

        Integer productSize
) {
}
