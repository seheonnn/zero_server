package dev.neordinary.zero.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductResponse(
        List<ProductInfo> productInfoList,

        Integer lastProductId
) {
}
