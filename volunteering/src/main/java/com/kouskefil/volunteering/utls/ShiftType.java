package com.kouskefil.volunteering.utls;

public enum ShiftType {
    MORNING,
    EVENING,
    FULL_DAY;
    public String getLabel() {
        return switch (this) {
            case MORNING -> "Morning (08:00 - 12:00)";
            case EVENING -> "Evening (14:00 - 18:00)";
            case FULL_DAY -> "Full day (08:00 - 20:00)";
        };
    }
}
