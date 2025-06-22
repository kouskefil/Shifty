package com.kouskefil.planning.utlis;

public enum EmployeeStatus {
    AVAILABLE,         // Disponible
    ON_LEAVE,          // En congé
    ON_SICK_LEAVE,     // En arrêt maladie
    ON_TRAINING,       // En formation
    ON_MATERNITY_LEAVE,// En congé maternité
    ON_PATERNITY_LEAVE,// En congé paternité
    ON_BREAK,          // En pause (momentanée)
    ABSENT,            // Absent (sans justification ou non planifié)
    WORKING_REMOTELY,  // En télétravail
    ON_MISSION,        // En déplacement professionnel
    TERMINATED,        // Fin de contrat / licencié
    SUSPENDED          // Suspendu temporairement
}
