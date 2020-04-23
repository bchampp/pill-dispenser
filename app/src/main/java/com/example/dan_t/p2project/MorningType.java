package com.example.dan_t.p2project;

public enum MorningType {

    AM("AM"),

    PM("PM");

    MorningType(String text) {
        this.text = text;
    }

    String text;

    public static MorningType forText(String text) {
        MorningType type = null;
        for(MorningType t : values()) {
            if (t.getText().equalsIgnoreCase(text)) {
                type = t;
            }
        }
        return type;
    }

    public String getText() {
        return text;
    }
}
