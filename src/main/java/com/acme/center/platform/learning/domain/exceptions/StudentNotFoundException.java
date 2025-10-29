package com.acme.center.platform.learning.domain.exceptions;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(AcmeStudentRecordId studentRecordId) {
        super("Student with Acme student record id %s not found".formatted(studentRecordId.studentRecordId()));
    }
}
