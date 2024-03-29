package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
// @SpringBootTest // Spring Boot를 사용한 테스트
@DataJpaTest // JPA 관련 설정만 로드하는 테스트
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    @Test
    void findAllBySellingStatusIn() {
        // given
        Product product1 = Product.builder()
                .productNumber("001")
                .name("아메리카노")
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .price(4000)
                .build();
        Product product2 = Product.builder()
                .productNumber("002")
                .name("카페라떼")
                .type(HANDMADE)
                .sellingStatus(HOLD)
                .price(4500)
                .build();
        Product product3 = Product.builder()
                .productNumber("003")
                .name("팥빙수")
                .type(HANDMADE)
                .sellingStatus(STOP_SELLING)
                .price(7000)
                .build();
        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

        // then
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "카페라떼", HOLD)
                );
    }

    @DisplayName("상품번호 리스트로 상품들을 조회한다.")
    @Test
    void findAllByProductNumberIn() {
        // given
        Product product1 = Product.builder()
                .productNumber("001")
                .name("아메리카노")
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .price(4000)
                .build();
        Product product2 = Product.builder()
                .productNumber("002")
                .name("카페라떼")
                .type(HANDMADE)
                .sellingStatus(HOLD)
                .price(4500)
                .build();
        Product product3 = Product.builder()
                .productNumber("003")
                .name("팥빙수")
                .type(HANDMADE)
                .sellingStatus(STOP_SELLING)
                .price(7000)
                .build();
        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        List<Product> products = productRepository.findAllByProductNumberIn(List.of("001", "002"));

        // then
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "카페라떼", HOLD)
                );
    }

}