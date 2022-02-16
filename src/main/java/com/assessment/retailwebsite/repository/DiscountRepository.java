package com.assessment.retailwebsite.repository;

import com.assessment.retailwebsite.model.Discount;
import com.assessment.retailwebsite.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount,Long> {

    Optional<Discount> findByRole(Role roleId);
}
