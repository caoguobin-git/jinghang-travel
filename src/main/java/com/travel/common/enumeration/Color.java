package com.travel.common.enumeration;

public enum Color {
    RED("红色",1),
    BLUE("绿色",2),
    GREEN("蓝色",3);

    private String name;
    private int index;

    Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
