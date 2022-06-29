package dev.megashopper.common.repository;

import dev.megashopper.common.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository  extends JpaRepository<Cart, String> {

    boolean existsByItemId(String itemId);
    boolean existsByCustomerId(String customerId);
}
