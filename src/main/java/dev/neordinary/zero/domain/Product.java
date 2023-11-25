package dev.neordinary.zero.domain;

import dev.neordinary.zero.dto.ProductInfo;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

@Getter
@RedisHash(value = "product", timeToLive = 3600)
@AllArgsConstructor
@Builder
public class Product {
    @Id
    private String id;

    @Indexed
    private List<ProductInfo> productInfoList;

    public static Product createProduct(String id, List<ProductInfo> productInfoList) {
        return Product.builder()
                .id(id)
                .productInfoList(productInfoList)
                .build();
    }
}
