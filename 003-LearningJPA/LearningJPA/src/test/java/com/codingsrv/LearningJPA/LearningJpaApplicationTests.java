package com.codingsrv.LearningJPA;

import com.codingsrv.LearningJPA.entity.ProductEntity;
import com.codingsrv.LearningJPA.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class LearningJpaApplicationTests {

    @Autowired
    private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

    @Test
    void testRepository(){    // To insert and save data into database table using hibernate.
        ProductEntity productEntity = ProductEntity.builder()
                .price(BigDecimal.valueOf(50))
                .quantity(3)
                .sku("Lahori_jeera")
                .title("Jeera")
                .createdAt(LocalDateTime.now())
                .build();

        ProductEntity savedProduct = productRepository.save(productEntity);
        System.out.println(savedProduct);
    }

    @Test
    void getRepository(){   //To fetch database table using hibernate.
        List<ProductEntity> entities = productRepository.findAll();
        System.out.println(entities);
    }

    @Test
    void getProductByTitle(){   // Query methods(based on method name hibernate generates query)
        List<ProductEntity> entities = productRepository.findByTitle("zero_pepsi");
        System.out.println(entities);
    }

    @Test
    void getProductCreatedAfter(){
        List<ProductEntity> entities = productRepository.findByCreatedAtAfter(LocalDateTime.of(2025,1,1,0,0));
        System.out.println(entities);
    }

    @Test
    void getProduct1r(){   //finding product by using name of two fields in query method.
        List<ProductEntity> entities = productRepository.findByQuantityAndPrice(4,BigDecimal.valueOf(15.4));
        System.out.println(entities);
    }

    @Test
    void getProduct2(){
        List<ProductEntity> entities = productRepository
                .findByQuantityGreaterThanOrPriceGreaterThan(4,BigDecimal.valueOf(15.4));
        System.out.println(entities);
    }

    @Test
    void getProduct3(){
        List<ProductEntity> entities = productRepository.findByTitleContaining("zero");
        System.out.println(entities);
    }

    @Test
    void getSingleFromRepository(){  // fetching a single element.
        Optional<ProductEntity> entities = productRepository
                .findByTitleAndPrice("zero_pepsi",BigDecimal.valueOf(25.40));
        entities.ifPresent(System.out::println);
    }

}
