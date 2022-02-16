package com.assessment.retailwebsite;

import com.assessment.retailwebsite.model.Discount;
import com.assessment.retailwebsite.model.Role;
import com.assessment.retailwebsite.model.enums.RoleType;
import com.assessment.retailwebsite.repository.DiscountRepository;
import com.assessment.retailwebsite.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StartUpService {

    private final RoleRepository roleRepository;

    private final DiscountRepository discountRepository;

    public void saveRole(){
        for (RoleType roleType: RoleType.values()) {
            Optional<Role> optionalRole = roleRepository.findByName(roleType.name());
            if(optionalRole.isEmpty()){
                Role role = new Role();
                role.setName(roleType.name());
                roleRepository.save(role);
            }
        }
    }

    public void populateDiscount(){
        List<Role> userRoles = roleRepository.findAll();
        for (Role role:userRoles) {
            Optional<Discount> existingDiscount= discountRepository.findByRole(role);
            if(existingDiscount.isEmpty()){
                Discount discount = new Discount();
                discount.setRole(role);
                if(role.getName().equalsIgnoreCase("Affiliate")) discount.setPercentage(10);
                else if(role.getName().equalsIgnoreCase("Employee")) discount.setPercentage(30);
                else if(role.getName().equalsIgnoreCase("LongTermCustomer")) discount.setPercentage(5);
                else if(role.getName().equalsIgnoreCase("Customer")) discount.setPercentage(0);
                discountRepository.save(discount);
            }

        }

    }

    public void populateProduct(){

    }

}
