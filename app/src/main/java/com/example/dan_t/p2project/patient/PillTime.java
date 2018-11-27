package com.example.dan_t.p2project.patient;

import com.example.dan_t.p2project.MorningType;

public final class PillTime {

    private int hour;

    private int minute;

    private MorningType type;

    public PillTime(int hour, int minute, MorningType type) {
        this.hour = hour;
        this.minute = minute;
        this.type = type;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public MorningType getType() {
        return type;
    }
}
