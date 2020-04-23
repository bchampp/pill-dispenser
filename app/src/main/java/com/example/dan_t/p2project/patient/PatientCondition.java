package com.example.dan_t.p2project.patient;

import java.util.LinkedList;
import java.util.List;

public final class PatientCondition {

    private String name;

    private int age;

    private int imagePath;

    private int pills;

    private List<PillTime> times = new LinkedList<PillTime>();

    private PillTime lastTaken;

    public PatientCondition(String name, int age, int imagePath) {
        this.name = name;
        this.age = age;
        this.imagePath = imagePath;
    }

    public static PatientCondition forName(String name) {
        PatientCondition condition = null;
        for(Patient patient : Patient.values()) {
            if(patient.getCondition().getName().equals(name)) {
                condition = patient.getCondition();
            }
        }
        return condition;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getImagePath() {
        return imagePath;
    }

    public int getPills() {
        return pills;
    }

    public PillTime getLastTaken() {
        return lastTaken;
    }

    public List<PillTime> getPillTimes() {
        return times;
    }

    public void setPills(int pills) {
        this.pills = pills;
    }
}
