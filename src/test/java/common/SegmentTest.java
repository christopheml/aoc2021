package common;

import common.grid.Point;
import common.grid.Segment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SegmentTest {

    @Test
    void getPoints_horizontal() {
        var segment = new Segment(new Point(1, 2), new Point(3, 2));
        assertThat(segment.getPoints()).containsExactly(new Point(1, 2), new Point(2, 2), new Point(3, 2));
    }

    @Test
    void getPoints_vertical() {
        var segment = new Segment(new Point(3, 2), new Point(3, 1));
        assertThat(segment.getPoints()).containsExactly(new Point(3, 2), new Point(3, 1));
    }

    @Test
    void getPoints_diagonal() {
        var segment = new Segment(new Point(9, 7), new Point(7, 9));
        assertThat(segment.getPoints()).containsExactly(new Point(9, 7), new Point(8, 8), new Point(7, 9));
    }
}
