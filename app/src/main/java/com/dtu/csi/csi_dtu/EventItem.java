package com.dtu.csi.csi_dtu;

public class EventItem {
    int header, thumbnail;
    String event;

    public EventItem (int header, int thumbnail, String event) {
        this.header = header;
        this.thumbnail = thumbnail;
        this.event = event;
    }

}
