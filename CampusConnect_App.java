/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gail Jacinto
 */
import java.util.Scanner;

public class CampusConnect_App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RequestManager requestManager = new RequestManager();

        final int MAX_REQUESTS = 5;
        final int MIN_PRIORITY = 1;
        final int MAX_PRIORITY = 5;

        System.out.println(" CampusConnect System Initialization \n");

        for (int i = 0; i < MAX_REQUESTS; i++) {
            System.out.println("--- Entering Request " + (i + 1) + " ---");

            String studentId;
            do {
                System.out.print("Enter Student ID: ");
                studentId = scanner.nextLine().trim();
            } while (studentId.isEmpty());

            String studentName;
            do {
                System.out.print("Enter Student Name: ");
                studentName = scanner.nextLine().trim();
            } while (studentName.isEmpty());

            Student student = new Student(studentId, studentName);

            String category;
            do {
                System.out.print("Enter Category (ENROLLMENT, DOCUMENT, PAYMENT, ADVISING): ");
                category = scanner.nextLine().trim().toUpperCase();
            } while (!validateCategory(category));

            int priority;
            do {
                System.out.print("Enter Priority (1-5): ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Invalid input. Enter Priority (1-5): ");
                    scanner.next();
                }
                priority = scanner.nextInt();
                scanner.nextLine();
            } while (priority < MIN_PRIORITY || priority > MAX_PRIORITY);

            String description;
            do {
                System.out.print("Enter Short Concern Description: ");
                description = scanner.nextLine().trim();
            } while (description.isEmpty());

            ServiceRequest request = createRequest(category, student, priority, description);
            requestManager.addRequest(request);

            request.printTicket();
        }

        requestManager.printSummary();

        runPolymorphismTests();

        scanner.close();
    }

    public static boolean validateCategory(String category) {
        return "ENROLLMENT".equals(category) ||
               "DOCUMENT".equals(category) ||
               "PAYMENT".equals(category) ||
               "ADVISING".equals(category);
    }

    public static ServiceRequest createRequest(String category, Student student, int priority, String description) {
        return switch (category) {
            case "ENROLLMENT" -> new EnrollmentRequest(student, priority, description);
            case "DOCUMENT" -> new DocumentRequest(student, priority, description);
            case "PAYMENT" -> new PaymentConcern(student, priority, description);
            case "ADVISING" -> new AdvisingRequest(student, priority, description);
            default -> throw new IllegalArgumentException("Unknown request category: " + category);
        };
    }

    private static void runPolymorphismTests() {
        System.out.println(" RUNTIME POLYMORPHISM & INTERFACE TESTS  ");

        Student testStudent = new Student("TEST-999", "Polymorphic Test Student");

        System.out.println("\nTest 6: Dynamic Method Dispatch via ServiceRequest Reference");
        ServiceRequest[] polymorphicArray = new ServiceRequest[] {
            new EnrollmentRequest(testStudent, 3, "Prerequisites override"),
            new DocumentRequest(testStudent, 5, "Rush transcript request"),
            new PaymentConcern(testStudent, 1, "Tuition breakdown inquiry"),
            new AdvisingRequest(testStudent, 5, "Graduation audit")
        };

        for (ServiceRequest req : polymorphicArray) {
            System.out.println("Subtype: " + req.getClass().getSimpleName());
            System.out.println("  -> Ticket ID: " + req.getTicketId());
            System.out.println("  -> Category: " + req.getCategory());
            System.out.println("  -> Base Days: " + req.getBaseDays() + " | Final Est. Days: " + req.estimateDays());
            System.out.println("  -> Priority: " + req.getPriority() + " | Urgent? " + req.isUrgent());
        }

        System.out.println("\nTest 7: Interface Verification (Prioritizable)");
        Prioritizable p1 = new DocumentRequest(testStudent, 5, "High priority transcript");
        Prioritizable p2 = new PaymentConcern(testStudent, 2, "Standard payment query");

        System.out.println("p1 (Priority " + p1.getPriority() + ") is Urgent: " + p1.isUrgent());
        System.out.println("p2 (Priority " + p2.getPriority() + ") is Urgent: " + p2.isUrgent());
    }
}
