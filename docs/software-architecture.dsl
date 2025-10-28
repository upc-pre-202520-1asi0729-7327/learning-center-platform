workspace "Learning Center Platform - Angular/Spring Boot Stack Edition" "Software architecture diagrams for the Learning Center application" {
model{
# People
admin = person "Course Administrator" "A person who manages and maintains course content"
student = person "Student" "A person who views and enrolls in courses"

# Software System
learningCenter = softwareSystem "Learning Center Platform" "Allows administrators to manage online courses and students to view course offerings" {
# Containers
webApplication = container "Web Application" "Delivers the static content and the Learning Center single page application" "Angular, TypeScript" "webapp"

singlePageApplication = container "Single Page Application" "Provides all course management functionality to administrators and students via their web browser" "TypeScript, Angular, NGX-Translate, Angular Material" "spa" {
tags "Web Browser"
}

apiApplication = container "API Application" "Provides course management functionality via RESTful API" "Spring Boot, Java" "api" {
tags "API"
iam = component "IAM Bounded Context" "Manages authentication and access control" "Spring Component"
learning = component "Learning Bounded Context" "Handles courses, lessons, and learning activities" "Spring Component"
profiles = component "Profiles Bounded Context" "Manages user profile information" "Spring Component"
shared = component "Shared Bounded Context" "Provides shared utilities and abstractions" "Spring Component"

}

database = container "Database" "Stores course information, categories, and metadata" "MySQL Server" "database" {
tags "Database"
}
}

# Relationships between people and system
admin -> learningCenter "Manages courses using"
student -> learningCenter "Views and searches courses using"

# Relationships between containers
admin -> webApplication "Visits learning-center.com using" "HTTPS"
student -> webApplication "Visits learning-center.com using" "HTTPS"

webApplication -> singlePageApplication "Delivers to the customer's web browser"

singlePageApplication -> apiApplication "Makes API calls to" "JSON/HTTPS" "REST API"

apiApplication -> database "Reads from and writes to" "Spring Data JPA"

# Component-level relationships (within API Application)
iam -> database "Reads from and writes to"
learning -> database "Reads from and writes to"
profiles -> database "Reads from and writes to"
# Shared does NOT access the database directly

# Show inter-dependencies between bounded contexts
learning -> profiles "Create the Profile of a new Student / Get the Profile ID of a Student by Email"
profiles -> iam "Get a User by Username / Get a User ID / Create a new User"
# Show dependencies of other bounded contexts on Shared
iam -> shared "Implements interfaces / extends classes from"
learning -> shared "Implements interfaces / extends classes from"
profiles -> shared "Implements interfaces / extends classes from"

# Show App-Component relationships

singlePageApplication -> learning "Creates Courses, Categories and Learning Paths, Enrolls Students into Courses"
singlePageApplication -> profiles "Creates and Get Profiles"
singlePageApplication -> iam "Sign-Up and Sign-In"
}

views {
systemContext learningCenter "SystemContext" "The system context diagram for the Learning Center" {
include *
autoLayout lr
}

container learningCenter "Containers" "The container diagram for the Learning Center" {
include *
autoLayout lr
}

component apiApplication "Components" {
include *
autoLayout
}

styles {
element "Person" {
shape "Person"
background "#08427b"
color "#ffffff"
}
element "Software System" {
background "#1168bd"
color "#ffffff"
}
element "Container" {
background "#438dd5"
color "#ffffff"
}
element "Web Browser" {
shape "WebBrowser"
background "#438dd5"
color "#ffffff"
}
element "API" {
background "#438dd5"
color "#ffffff"
}
element "Database" {
shape "Cylinder"
background "#438dd5"
color "#ffffff"
}
    }

theme default
}

properties {
structurizr.groupSeparator "/"
}
