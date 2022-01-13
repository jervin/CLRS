package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RobotBounded {
    void move(int[] vector, int direction) {
        if (direction%360 == 0)
            vector[1] +=1;
        if (direction%360 == 90)
            vector[0] -=1;
        if (direction%360 == 180)
            vector[1] -=1;
        if (direction%360 == 270)
            vector[0] +=1;
    }

    public boolean isRobotBounded(String instructions) {
        int direction = 0;
        int[] vector = new int[2];

        for (char c : instructions.toCharArray()) {
            if (c == 'L') {
                direction = (direction + 90)%360;
            }
            if (c == 'R') {
                direction = (direction - 90)%360;
                if (direction <0)
                    direction +=360;
            }
            if (c == 'G') {
                move(vector, direction);
            }
            direction = direction % 360;
        }
        if (vector[0] == 0 && vector[1] == 0)
            return true;
        return direction != 0;
    }

    @Test
    void test1() {
        assertTrue(isRobotBounded("GGLLGG"));
    }
    @Test
    void test2() {
        assertFalse(isRobotBounded("GLGLGGLGL"));
    }
    @Test
    void test3() {
        assertTrue(isRobotBounded("LLGRL"));
    }
    @Test
    void test4() {
        assertTrue(isRobotBounded("GL"));
    }
}
