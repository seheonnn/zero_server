package dev.neordinary.zero.controller;

import dev.neordinary.zero.base.BaseResponse;
import dev.neordinary.zero.service.ProductService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/v1/product")
    public ResponseEntity<BaseResponse> getProductResponse(@RequestParam @NotBlank String keyword,
                                                           @RequestParam(defaultValue = "0", required = false) Integer lastProductId) {
        return BaseResponse.toResponseEntityContainsResult(productService.getProductResponse(keyword, lastProductId));
    }
}
