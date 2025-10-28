# Learning Center Platform — REST API Technical Stories

## Overview
This document contains API-focused technical stories intended for frontend or mobile developers integrating with the Learning Center Platform REST API (Java, Spring Boot).

Common conventions
- Base path: `/api/v1`

---

### TS-C001 — Create a Course
As a frontend developer, I want to create a new course through the API so that I can add courses to the system as a feature in my application.

Acceptance criteria:
- Scenario: Successful create
    - Given a POST request to `/api/v1/courses` is received with a request body containing the create-course attributes: title, description
    - When the API validates and persists the course
    - Then the API responds with `201 Created` and returns the created course with attributes: id (Long), title (String), description (String).
- Scenario: Validation error
    - Given a POST request to `/api/v1/courses` is received with missing or invalid create-course attributes (e.g., empty title or description)
    - When the API validates the request and detects validation errors
    - Then the API responds with `400 Bad Request` and returns an error payload describing validation errors.

---

### TS-C002 — Get a Course by id
As a frontend developer, I want to fetch a course by its `{courseId}` through the API, so that I can show course information as a feature in my application.

Acceptance criteria:
- Scenario: Found
    - Given a GET request to `/api/v1/courses/{courseId}` is received
    - When the API finds the course
    - Then the API responds `200 OK` and returns the course with attributes: id (Long), title (String), description (String).
- Scenario: Not found
    - Given a GET request to `/api/v1/courses/{courseId}` is received for a non-existent `{courseId}`
    - When the API does not find the course
    - Then the API responds `404 Not Found` and returns an error payload.

---

### TS-C003 — Get all Courses
As a frontend developer, I want to list all courses through the API so that I can show them in a catalogue as a feature in my application.

Acceptance criteria:
- Scenario: Courses exist
    - Given a GET request to `/api/v1/courses` is received
    - When the API finds one or more courses
    - Then the API responds `200 OK` and returns a list where each item contains the course attributes: id, title, description.
- Scenario: No courses found
    - Given a GET request to `/api/v1/courses` is received and there are no courses in the system
    - When the API searches for courses and finds none
    - Then the API responds `404 Not Found` (per current controller behavior) and returns an appropriate error payload.

---

### TS-LP001 — Add a Tutorial to a Course Learning Path
As a frontend developer, I want to add a tutorial to a course's learning path through the API so that instructors can curate course content as a feature in my application.

Acceptance criteria:
- Scenario: Successful add
    - Given a POST request to `/api/v1/courses/{courseId}/learning-path-items/{tutorialId}` is received
    - When the API successfully attaches the tutorial
    - Then the API responds `201 Created` and returns the learning-path item with attributes: learningPathItemId (Long), courseId (Long), tutorialId (Long).
- Scenario: Course or tutorial not found
    - Given a POST request to `/api/v1/courses/{courseId}/learning-path-items/{tutorialId}` is received where the referenced course or tutorial does not exist
    - When the API cannot find the course or tutorial
    - Then the API responds `404 Not Found` and returns an error payload.

---

### TS-S001 — Create a Student
As a frontend developer, I want to create a student record through the API so that users can enroll in courses as a feature in my application.

Acceptance criteria:
- Scenario: Successful create
    - Given a POST request to `/api/v1/students` is received with a request body containing the create-student attributes: firstName, lastName, email, street, number, city, postalCode, country
    - When the API validates and creates the student
    - Then the API responds `201 Created` and returns the created student with attributes: acmeStudentRecordId (String), profileId (Long), totalCompletedCourses (Integer), totalCompletedTutorials (Integer).
- Scenario: Validation error
    - Given a POST request to `/api/v1/students` is received with missing or invalid student attributes (e.g., blank firstName or invalid email)
    - When the API validates the request and detects validation errors
    - Then the API responds `400 Bad Request` and returns an error payload describing validation errors.

---

### TS-S002 — Get Student by Acme Student Record ID
As a frontend developer, I want to fetch a student by `{studentRecordId}` through the API so that I can show student profile pages as a feature in my application.

Acceptance criteria:
- Scenario: Found
    - Given a GET request to `/api/v1/students/{studentRecordId}` is received
    - When the API finds the student
    - Then the API responds `200 OK` and returns the student with attributes: acmeStudentRecordId (String), profileId (Long), totalCompletedCourses (Integer), totalCompletedTutorials (Integer).
- Scenario: Not found
    - Given a GET request to `/api/v1/students/{studentRecordId}` is received for a non-existent `{studentRecordId}`
    - When the API does not find the student
    - Then the API responds `404 Not Found` and returns an error payload.

---

### TS-E001 — Request Enrollment
As a frontend developer, I want to request a new enrollment through the API so that students can request access to a course as a feature in my application.

Acceptance criteria:
- Scenario: Successful request
    - Given a POST request is received to `/api/v1/enrollments` with a request body containing the request-enrollment attributes: studentRecordId, courseId
    - When the API validates and persists the enrollment request
    - Then the API responds with a successful status (controller may return `200 OK` or `201 Created` depending on the flow) and returns the enrollment with attributes: enrollmentId (Long), studentRecordId (String), courseId (Long), status (String).
- Scenario: Bad request
    - Given a POST request is received to `/api/v1/enrollments` with missing or invalid enrollment attributes
    - When the API validates the request and detects validation errors
    - Then the API responds `400 Bad Request` and returns an error payload describing validation errors.
- Scenario: Enrollment not found after creation
    - Given a POST request is received to `/api/v1/enrollments` is completed but the created enrollment cannot be retrieved (e.g., persistence inconsistency)
    - When the API attempts to fetch the newly created enrollment and does not find it
    - Then the API responds `404 Not Found` and returns an error payload — the frontend should treat this as an error.

---

### TS-E002 — Confirm / Reject / Cancel Enrollment
As a frontend developer, I want to confirm, reject or cancel an enrollment through the API so that I can support the enrollment lifecycle as a feature in my application.

Acceptance criteria:
- Confirm
    - Given a POST request to `/api/v1/enrollments/{enrollmentId}/confirmations` is received
    - When the API confirms the enrollment successfully
    - Then the API responds `200 OK` and returns a message with attribute: message (String).
    - Given POST `/api/v1/enrollments/{enrollmentId}/confirmations` is received with invalid data or for a non-existent enrollment
    - When the API validates the request and cannot perform the confirmation
    - Then the API responds `400 Bad Request` and returns an error payload.
- Reject
    - Given a POST request to `/api/v1/enrollments/{enrollmentId}/rejections` is received
    - When the API rejects the enrollment
    - Then the API responds `200 OK` and returns a message with attribute: message (String).
    - Given POST `/api/v1/enrollments/{enrollmentId}/rejections` is received with invalid data or for a non-existent enrollment
    - When the API validates the request and cannot perform the rejection
    - Then the API responds `400 Bad Request` and returns an error payload.
- Cancel
    - Given a POST request to `/api/v1/enrollments/{enrollmentId}/cancellations` is received
    - When the API cancels the enrollment
    - Then the API responds `200 OK` and returns a message with attribute: message (String).
    - Given POST `/api/v1/enrollments/{enrollmentId}/cancellations` is received with invalid data or for a non-existent enrollment
    - When the API validates the request and cannot perform the cancellation
    - Then the API responds `400 Bad Request` and returns an error payload.

---

### TS-P001 — Create a Profile
As a frontend developer, I want to create user profiles through the API so that learners have personal profiles as a feature in my application.

Acceptance criteria:
- Scenario: Successful create
    - Given POST is received to `/api/v1/profiles` with a request body containing the create-profile attributes: firstName, lastName, email, street, number, city, postalCode, country
    - When the API validates and persists the profile
    - Then the API responds `201 Created` and returns the created profile with attributes: id (Long), fullName (String), email (String), streetAddress (String).
- Scenario: Validation error
    - Given POST `/api/v1/profiles` with missing or invalid profile attributes
    - When the API validates the request and detects validation errors
    - Then the API responds `400 Bad Request` and returns an error payload describing validation errors.

---

### TS-P002 — Get Profiles
As a frontend developer, I want to retrieve profiles through the API so that I can list profiles as a feature in my application.

Acceptance criteria:
- Get by id
    - Given GET request to `/api/v1/profiles/{profileId}` is received
    - When the API finds the profile
    - Then the API responds `200 OK` and returns the profile with attributes: id (Long), fullName (String), email (String), streetAddress (String); otherwise `404 Not Found`.
    - Given GET `/api/v1/profiles/{profileId}` is received for a non-existent id
    - When the API does not find the profile
    - Then the API responds `404 Not Found` and returns an error payload.
- Get all
    - Given GET `/api/v1/profiles` is received
    - When profiles exist
    - Then API responds `200 OK` with a list of items containing: id, fullName, email, streetAddress.
    - Given GET `/api/v1/profiles` is received and no profiles exist
    - When the API finds no profiles
    - Then API responds `404 Not Found` (per controller) and returns an error payload.

---

### TS-U001 — List Users and Get User by id
As a frontend developer, I want to list users and retrieve a user by id through the API so that I can implement admin views as a feature in my application.

Acceptance criteria:
- List users
    - Given GET request is sent to `/api/v1/users`
    - When users exist
    - Then API responds `200 OK` with a list of items containing user attributes: id (Long), username (String), roles (List<String>).
- Get user by id
    - Given GET `/api/v1/users/{userId}` is received
    - When user exists
    - Then API responds `200 OK` and returns the user with attributes: id (Long), username (String), roles (List<String>).
    - Given GET `/api/v1/users/{userId}` is received for a non-existent id
    - When the API does not find the user
    - Then API responds `404 Not Found` and returns an error payload.

---

### TS-IAM001 — Sign-up through the API
As a frontend developer, I want to create a new user account through the API so that users can register in my application as a feature in my application.

Acceptance criteria:
- Scenario: Successful sign-up
    - Given a POST `/api/v1/authentication/sign-up` with request attributes: username (String), password (String), roles (List<String>)
    - When the API validates the request and creates the user
    - Then the API responds `201 Created` and returns the created user with attributes: id (Long), username (String), roles (List<String>).
- Scenario: Validation error
    - Given a POST `/api/v1/authentication/sign-up` with missing or invalid attributes (e.g., blank username or password)
    - When the API validates the request and detects validation errors
    - Then the API responds `400 Bad Request` and returns an error payload describing validation issues.
- Scenario: Duplicate username or create failure
    - Given a POST `/api/v1/authentication/sign-up` with a username that already exists or the creation fails
    - When the user creation cannot be completed
    - Then the API responds `400 Bad Request` (per current controller behavior) and returns an error payload explaining the failure.

---

### TS-IAM002 — Sign-in through the API
As a frontend developer, I want to sign in users through the API so that I can obtain authentication tokens for my application as a feature in my application.

Acceptance criteria:
- Scenario: Successful sign-in
    - Given a POST `/api/v1/authentication/sign-in` with credentials attributes: username (String), password (String)
    - When the API validates credentials successfully
    - Then the API responds `200 OK` and returns the authenticated user with attributes: id (Long), username (String), token (String).
- Scenario: User not found or invalid credentials
    - Given a POST `/api/v1/authentication/sign-in` with invalid credentials or non-existent user
    - When the API fails to authenticate the credentials
    - Then the API responds `404 Not Found` (per current controller behavior) and returns an error payload.

---