package com.smart.consumption;

import java.time.LocalDateTime;
import java.util.Comparator;

public class MeasureDateComparator implements Comparator<LocalDateTime> {
    @Override
    public int compare(final LocalDateTime o1, final LocalDateTime o2) {
        return o1.compareTo(o2);
    }
}
