package com.basic.myspringboot.repository;

import com.basic.myspringboot.entity.Customer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // DB에 입력한 결과가 남지 않고 롤백된다.
//@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Rollback(value = false)
    void testUpdateCustomer() {
        Customer customer = customerRepository.findById(1L) // Optional<Customer>
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        //수정하려면 Entity의 setter 메소드 호출한다. dirty read
        // update customers set customer_id=?,customer_name=?,where id =? (@DynamicUpdate 적용 전)
        // update customers set customer_name=? where id=? (@DynamicUpdate 적용 후)
        customer.setCustomerName("홍길동");
//        customerRepository.save(customer); @Transaction을 걸면 따로 save 안해줘도 되는데 더티 리드임
        assertThat(customer.getCustomerName()).isEqualTo("홍길동");
    }

    @Test
    void testByNotFoundException() {
        // <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier)
        // Supplier의 추상메서드 T get()
        Customer customer = customerRepository.findByCustomerId("A004")
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
//        assertThat(customer.getCustomerName()).isEqualTo("A001");
    }

    @Test
    void testFindBy() {
        Optional<Customer> optionalCustomer = customerRepository.findById(1L);
//        assertThat(optionalCustomer).isNotEmpty();
        if(optionalCustomer.isPresent()){
            Customer existCustomer = optionalCustomer.get();
            assertThat(existCustomer.getId()).isEqualTo(1L);
        }
        // Optional 의 T orElseGet(Supplier<? extends T> supplier)
        // Supplier의 추상메서드 T get()
        // 고객 번호가 존재하는 경우
        Optional<Customer> optionalCustomer2 = customerRepository.findByCustomerId("A001");
        Customer a001customer = optionalCustomer2.orElseGet(() -> new Customer());
        assertThat(a001customer.getCustomerName()).isEqualTo("스프링");

        // 고객번호가 존재하지 않는 경우
        Customer notFoundCustomer = customerRepository.findByCustomerId("A004")
                .orElseGet(() -> new Customer());
        assertThat(notFoundCustomer.getCustomerName()).isNull();
    }

    @Test
    @Rollback(value = false) //Rollback 처리 하지 마세요
    @Disabled
    void testCreateCustomer() {
        //Given (준비 단계)
        Customer customer = new Customer();
        customer.setCustomerId("A002");
        customer.setCustomerName("스프링2");
        //When (실행 단계)
        Customer addCustomer = customerRepository.save(customer);
        //Then (검증 단계)
        assertThat(addCustomer).isNotNull();
        assertThat(addCustomer.getCustomerName()).isEqualTo("스프링2");
    }


}