import java.util.*;

public class VirtualClassroomManager {
    private Map<String, List<String>> classrooms = new HashMap<>();
    private Map<String, List<String>> students = new HashMap<>();
    private Map<String, List<String>> assignments = new HashMap<>();

    // Add a new classroom
    public void addClassroom(String className) {
        if (classrooms.containsKey(className)) {
            System.out.println("Classroom " + className + " already exists.");
        } else {
            classrooms.put(className, new ArrayList<>());
            System.out.println("Classroom " + className + " has been created.");
        }
    }

    // Enroll a student in a classroom
    public void addStudent(String studentId, String className) {
        if (!classrooms.containsKey(className)) {
            System.out.println("Classroom " + className + " does not exist.");
        } else {
            if (students.containsKey(studentId)) {
                if (!students.get(studentId).contains(className)) {
                    students.get(studentId).add(className);
                }
            } else {
                students.put(studentId, new ArrayList<>(Collections.singletonList(className)));
            }
            classrooms.get(className).add(studentId);
            System.out.println("Student " + studentId + " has been enrolled in " + className + ".");
        }
    }

    // Schedule an assignment for a class
    public void scheduleAssignment(String className, String assignmentDetails) {
        if (!classrooms.containsKey(className)) {
            System.out.println("Classroom " + className + " does not exist.");
        } else {
            assignments.computeIfAbsent(className, k -> new ArrayList<>()).add(assignmentDetails);
            System.out.println("Assignment for " + className + " has been scheduled.");
        }
    }

    // Submit an assignment
    public void submitAssignment(String studentId, String className, String assignmentDetails) {
        if (!classrooms.containsKey(className)) {
            System.out.println("Classroom " + className + " does not exist.");
        } else if (!students.containsKey(studentId) || !students.get(studentId).contains(className)) {
            System.out.println("Student " + studentId + " is not enrolled in " + className + ".");
        } else if (!assignments.containsKey(className) || !assignments.get(className).contains(assignmentDetails)) {
            System.out.println("Assignment details do not match the scheduled assignments for " + className + ".");
        } else {
            System.out.println("Assignment submitted by Student " + studentId + " in " + className + ".");
        }
    }

    // List all classrooms
    public void listClassrooms() {
        if (classrooms.isEmpty()) {
            System.out.println("No classrooms available.");
        } else {
            classrooms.keySet().forEach(classroom -> System.out.println("Classroom: " + classroom));
        }
    }

    // List all students and their enrollments
    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students enrolled.");
        } else {
            students.forEach((studentId, classes) -> System.out.println("Student ID: " + studentId + ", Enrolled in: " + String.join(", ", classes)));
        }
    }

    // List all assignments
    public void listAssignments() {
        if (assignments.isEmpty()) {
            System.out.println("No assignments scheduled.");
        } else {
            assignments.forEach((className, assignmentList) -> System.out.println("Classroom: " + className + ", Assignments: " + String.join(", ", assignmentList)));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VirtualClassroomManager manager = new VirtualClassroomManager();

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 3);

            if (parts.length == 0) {
                System.out.println("No command provided.");
                continue;
            }

            String command = parts[0];

            switch (command) {
                case "add_classroom":
                    if (parts.length < 2) {
                        System.out.println("Class name missing.");
                    } else {
                        manager.addClassroom(parts[1]);
                    }
                    break;
                case "add_student":
                    if (parts.length < 3) {
                        System.out.println("Student ID or class name missing.");
                    } else {
                        manager.addStudent(parts[1], parts[2]);
                    }
                    break;
                case "schedule_assignment":
                    if (parts.length < 3) {
                        System.out.println("Class name or assignment details missing.");
                    } else {
                        manager.scheduleAssignment(parts[1], parts[2]);
                    }
                    break;
                case "submit_assignment":
                    if (parts.length < 3) {
                        System.out.println("Student ID, class name, or assignment details missing.");
                    } else {
                        String[] submitParts = input.split(" ", 4);
                        if (submitParts.length < 4) {
                            System.out.println("Assignment details missing.");
                        } else {
                            manager.submitAssignment(submitParts[1], submitParts[2], submitParts[3]);
                        }
                    }
                    break;
                case "list_classrooms":
                    manager.listClassrooms();
                    break;
                case "list_students":
                    manager.listStudents();
                    break;
                case "list_assignments":
                    manager.listAssignments();
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Unknown command.");
                    break;
            }
        }
    }
}
