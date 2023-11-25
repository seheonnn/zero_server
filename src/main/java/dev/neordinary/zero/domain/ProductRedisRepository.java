package dev.neordinary.zero.domain;

import org.springframework.data.repository.CrudRepository;

public interface ProductRedisRepository extends CrudRepository<Product, String> {
}
