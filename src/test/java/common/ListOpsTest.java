package common;

import common.collections.ListOps;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ListOpsTest {

    @Test
    void grouping_shortest_list() {
        var sample = List.of(1, 2);
        assertThat(ListOps.grouping(sample, 2)).isEqualTo(List.of(sample));
    }

    @Test
    public void grouping_longer_list() {
        var sample = List.of(3, 4, 9, 10);
        assertThat(ListOps.grouping(sample, 2)).containsExactly(
                List.of(3, 4),
                List.of(4, 9),
                List.of(9, 10)
        );
    }

    @Test
    public void foldLeft() {
        var sample = List.of("Hello", "Bob", "Long word");
        var count = ListOps.foldLeft(sample, 0, (acc, element) -> acc + element.length());
        assertThat(count).isEqualTo(17);
    }

}
