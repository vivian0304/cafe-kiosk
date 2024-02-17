package sample.cafekiosk.unit.beverages;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AmericanoTest {

    @Test
    void getName() {
        Americano americano = new Americano();

        // assertEquals: junit의 API
        assertEquals("아메리카노", americano.getName());

        // assertThat: assertj의 API
            // 이제부터는 이 API를 사용
            // why? 메소드 체이닝을 통해 가독성이 좋아짐
        assertThat(americano.getName()).isEqualTo("아메리카노");

    }

    @Test
    void getPrice() {
        Americano americano = new Americano();

        // assertThat: assertj의 API
        assertThat(americano.getPrice()).isEqualTo(4000);
    }
}