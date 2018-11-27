package com.example.dan_t.p2project.patient;

import com.example.dan_t.p2project.R;

public enum Patient {

    BOB(new PatientCondition("Bob Wilkins", 72, R.drawable.bob)),
    JILL(new PatientCondition("Jill Tamfor", 73, R.drawable.jill)),
    WILL(new PatientCondition("Will Silver", 71, R.drawable.bob)),
    Dan(new PatientCondition("Dan Ran", 54, R.drawable.bob)),
    Rick(new PatientCondition("Rick Tick", 67, R.drawable.bob));




    Patient(PatientCondition condition) {
        this.condition = condition;
    }

    PatientCondition condition;

    public PatientCondition getCondition() {
        return condition;
    }
}
