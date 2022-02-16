package com.assessment.retailwebsite.service.serviceImpl;

import com.assessment.retailwebsite.model.Discount;
import com.assessment.retailwebsite.model.Product;
import com.assessment.retailwebsite.model.Role;
import com.assessment.retailwebsite.model.User;
import com.assessment.retailwebsite.payload.request.ProductOrderRequest;
import com.assessment.retailwebsite.payload.request.SalesOrder;
import com.assessment.retailwebsite.repository.DiscountRepository;
import com.assessment.retailwebsite.repository.ProductRepository;
import com.assessment.retailwebsite.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalesServiceImplTest {

    @Mock
    private DiscountRepository discountRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SalesServiceImpl salesService;

    private User user;

    private Role affiliate;
    private Role customer;
    private Role longTerm;
    private Role employee;

    private Discount affiliateDiscount;
    private Discount customerDiscount;
    private Discount employeeDiscount;
    private Discount longTermDiscount;

    private Product product;
    private SalesOrder salesOrder;

    @BeforeEach
    void setUp() {
        affiliate = new Role(1l, "Affiliate");
        customer = new Role(4l, "Customer");
        employee = new Role(3l, "Employee");
        longTerm = new Role(2l, "LongTermCustomer");

        user = new User(20l, "Test", "Case", "affiliate@email.com", "123abc", affiliate);

        affiliateDiscount = new Discount(1l, affiliate, 10);
        customerDiscount = new Discount(1l, customer, 0);
        employeeDiscount = new Discount(1l, employee, 30);
        longTermDiscount = new Discount(1l, longTerm, 5);

        product = new Product(1l, "Carlo Rose", "Lovely red wine",new BigDecimal(1500), "Wine");

        salesOrder = new SalesOrder();
        salesOrder.setUserId(20l);
        salesOrder.setProductOrderList(List.of(
                new ProductOrderRequest(1l, 2), new ProductOrderRequest(1l, 1)
        ));
    }

    @Test
    void processAffiliatePrice() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(discountRepository.findByRole(user.getRole())).thenReturn(Optional.of(affiliateDiscount));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        var res = salesService.processSales(salesOrder);
        assertEquals("3847.5", String.valueOf(res.getTotalPrice()));
    }

    @Test
    void processEmployeePrice() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(discountRepository.findByRole(user.getRole())).thenReturn(Optional.of(employeeDiscount));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        var res = salesService.processSales(salesOrder);
        assertEquals("2992.5", String.valueOf(res.getTotalPrice()));
    }

    @Test
    void processCustomerPrice() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(discountRepository.findByRole(user.getRole())).thenReturn(Optional.of(customerDiscount));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        var res = salesService.processSales(salesOrder);
        assertEquals("4275", String.valueOf(res.getTotalPrice()));
    }

    @Test
    void processLongTermCustomerPrice() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(discountRepository.findByRole(user.getRole())).thenReturn(Optional.of(longTermDiscount));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        var res = salesService.processSales(salesOrder);
        assertEquals("4061.25", String.valueOf(res.getTotalPrice()));
    }
}