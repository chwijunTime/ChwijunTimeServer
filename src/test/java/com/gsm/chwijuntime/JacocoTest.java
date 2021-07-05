package com.gsm.chwijuntime;

import com.gsm.chwijuntime.ex.Jacoco;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Jacoco Class method test
 */
@SpringBootTest(properties = "spring.config.location=" +
        "classpath:/application.yml"
)
@WebAppConfiguration
public class JacocoTest {

    /**
     * sumTest
     */
    @Test
    public void sumTest() {
        Assertions.assertThat(new Jacoco().sum(1, 2)).isEqualTo(3);
    }

    /**
     * subtractTest
     */
    @Test
    public void subtractTest() {
        Assertions.assertThat(new Jacoco().subtract(1, 2)).isEqualTo(-1);
    }

    /**
     * iNullTest
     */
    @Test
    public void isNullTest() {
        Assertions.assertThat(new Jacoco().isNull(null)).isTrue();
        Assertions.assertThat(new Jacoco().isNull(new Object())).isFalse();
    }
}