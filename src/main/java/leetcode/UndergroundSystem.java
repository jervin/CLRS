package leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UndergroundSystem {

    record CheckIn (String station, int t) {}
    record CheckOut (int id, int t) {}

    Map<Integer, CheckIn> checkIns = new HashMap<>();
    Map<String, List<CheckOut>> checkOuts = new HashMap<>();

    public UndergroundSystem() {}

    public void checkIn(int id, String stationName, int t) {
        var checkIn = new CheckIn(stationName, t);
        checkIns.put(id, checkIn);
    }

    public void checkOut(int id, String stationName, int t) {
        var checkOut = new CheckOut(id, t);
        var list = checkOuts.computeIfAbsent(stationName, k -> new ArrayList<CheckOut>());
        list.add(checkOut);
    }

    public double getAverageTime(String startStation, String endStation) {
        var sum = 0.0;
        if (checkOuts.get(endStation) == null)
            return 0.0;
        var noOfTravelers = 0;
        for (CheckOut co : checkOuts.get(endStation)) {
            CheckIn ci = checkIns.get(co.id);
            if (ci == null || !ci.station.equals(startStation))
                continue;
            sum += co.t - ci.t;
            noOfTravelers++;
        }
        if (sum == 0.0)
            return 0.0;
        return sum/noOfTravelers;
    }

    @Test
    void test1() {
        checkIn(596854, "A", 13);
        checkIn(29725, "B", 17);
        checkOut(596854, "C", 115);
        //assertEquals(102.0d, getAverageTime("A","C"));
        checkIn(579716, "A", 145);
        checkOut(579716, "C", 199);
        checkOut(29725, "C", 295);
        checkIn(939079, "D", 371);
        assertEquals(78.0d, getAverageTime("A", "C"));
    }
}
