package com.samplejoaTest.event;

/**
 * Created by hoyoung on 2016-06-01.
 */
public class EventData implements Event{

    private String name;

    public EventData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
