package common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MathOpsTest {

    @Test
    void bounded() {
        assertThat(MathOps.bounded(1, 1, 9)).isEqualTo(1);
        assertThat(MathOps.bounded(9, 1, 9)).isEqualTo(9);
        assertThat(MathOps.bounded(10, 1, 9)).isEqualTo(1);
    }

}
