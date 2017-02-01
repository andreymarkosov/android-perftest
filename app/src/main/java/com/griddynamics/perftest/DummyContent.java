package com.griddynamics.perftest;

import java.util.ArrayList;
import java.util.List;


class DummyContent {

    static final List<DummyItem> ITEMS = new ArrayList<>();
    private static final int COUNT = 250;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "item " + position);
    }

    static class DummyItem {
        public final String id;
        final String content;

        DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
