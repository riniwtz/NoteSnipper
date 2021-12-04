package io.github.riniwtz.subjects;

import java.util.HashMap;

public class Subjects {
    // TODO: get all subjects category text channels
    // TODO: and it would automatically change the values depends on naming without re-coding software

    private final String[] subjects = {"AP", "CL", "ENG", "FIL", "ICT", "LIP", "MAPEH", "MATH", "RHGP", "SCI", "TLE"};
    private final HashMap<String, String> SUBJECTS_MAP = new HashMap<>();

    public Subjects() {
        SUBJECTS_MAP.put(subjects[0], "ap-notes");
        SUBJECTS_MAP.put(subjects[1], "cl-notes");
        SUBJECTS_MAP.put(subjects[2], "eng-notes");
        SUBJECTS_MAP.put(subjects[3], "fil-notes");
        SUBJECTS_MAP.put(subjects[4], "ict-notes");
        SUBJECTS_MAP.put(subjects[5], "lip-notes");
        SUBJECTS_MAP.put(subjects[6], "mapeh-notes");
        SUBJECTS_MAP.put(subjects[7], "math-notes");
        SUBJECTS_MAP.put(subjects[8], "rhgp-notes");
        SUBJECTS_MAP.put(subjects[9], "sci-notes");
        SUBJECTS_MAP.put(subjects[10], "tle-notes");
    }

    public HashMap<String, String> getSubject() {
        return SUBJECTS_MAP;
    }

    public String[] getSubjects() {
        return subjects;
    }
}
