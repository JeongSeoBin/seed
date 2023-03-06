package com.innowireless.web.api;

import java.util.ArrayList;
import java.util.List;

public class ListResult {

    private final String[] names;
    private final ArrayList<Object[]> items;

    public ListResult(String... names) {
        this.items = new ArrayList<>();
        this.names = names;
    }

    public ListResult(int itemCount, String... names) {
        this.items = new ArrayList<>(itemCount);
        this.names = names;
    }

    public void addItem(Object... elems) {
        if (elems.length != names.length)
            throw new IllegalArgumentException(
                "ListResult: number of names and elems mismatch");

        this.items.add(elems);
    }

    // getter
    public String[] getNames() {
        String[] safeArray = null;
        if (this.names != null) {
            safeArray = new String[this.names.length];
            System.arraycopy(this.names, 0, safeArray, 0, this.names.length);
        }
        return safeArray;
    }

    public List<Object[]> getItems() {
        return items;
    }
}