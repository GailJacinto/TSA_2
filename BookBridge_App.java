/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookbridge_tsa1;

/**
 *
 * @author Gail Jacinto
 */
import java.util.Scanner;

public class BookBridge_App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryService libraryService = new LibraryService();
        final int MAX_TRANSACTIONS = 5;

        System.out.println("  BookBridge Prototype Initialization\n");

        for (int i = 0; i < MAX_TRANSACTIONS; i++) {
            System.out.println("--- Entering Transaction " + (i + 1) + " ---");

            String memberId;
            do {
                System.out.print("Enter Member ID: ");
                memberId = scanner.nextLine().trim();
            } while (memberId.isEmpty());

            String memberName;
            do {
                System.out.print("Enter Member Name: ");
                memberName = scanner.nextLine().trim();
            } while (memberName.isEmpty());

            String memberType;
            do {
                System.out.print("Enter Membership Type (STUDENT, FACULTY, GUEST): ");
                memberType = scanner.nextLine().trim().toUpperCase();
            } while (!validateMembershipType(memberType));

            Member member = createMember(memberType, memberId, memberName);

            String isbn;
            do {
                System.out.print("Enter Book ISBN: ");
                isbn = scanner.nextLine().trim();
            } while (isbn.isEmpty());

            String bookTitle;
            do {
                System.out.print("Enter Book Title: ");
                bookTitle = scanner.nextLine().trim();
            } while (bookTitle.isEmpty());

            int reqDays;
            do {
                System.out.print("Enter Requested Days (1-30): ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Invalid format. Enter Requested Days (1-30): ");
                    scanner.next();
                }
                reqDays = scanner.nextInt();
                scanner.nextLine();
            } while (reqDays < 1 || reqDays > 30);

            System.out.print("Is the book available? (true/false): ");
            while (!scanner.hasNextBoolean()) {
                System.out.print("Invalid format. Is the book available? (true/false): ");
                scanner.next();
            }
            boolean availability = scanner.nextBoolean();
            scanner.nextLine();

            int lateDays;
            do {
                System.out.print("Enter Estimated Late Days (>= 0): ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Invalid format. Enter Estimated Late Days (>= 0): ");
                    scanner.next();
                }
                lateDays = scanner.nextInt();
                scanner.nextLine();
            } while (lateDays < 0);

            LibraryBook book = new LibraryBook(isbn, bookTitle, availability);
            LoanRecord record = libraryService.processTransaction(member, book, reqDays, lateDays);

            System.out.println("\n>> Transaction " + (i + 1) + " Processed");
            System.out.println(">> Status: " + record.getStatus());
            System.out.println(">> Estimated Late Charge: PHP " + String.format("%.2f", record.getEstimatedFine()) + "\n");
        }

        libraryService.printSummary();

        runPolymorphismTests(libraryService);

        scanner.close();
    }

    public static boolean validateMembershipType(String type) {
        return "STUDENT".equals(type) || "FACULTY".equals(type) || "GUEST".equals(type);
    }

    public static Member createMember(String type, String id, String name) {
        return switch (type) {
            case "STUDENT" -> new StudentMember(id, name);
            case "FACULTY" -> new FacultyMember(id, name);
            case "GUEST" -> new GuestMember(id, name);
            default -> throw new IllegalArgumentException("Invalid membership type: " + type);
        };
    }

    private static void runPolymorphismTests(LibraryService service) {
        System.out.println(" RUNTIME POLYMORPHISM & INTERFACE TESTS  ");

        System.out.println("\nTest 6: Polymorphic Dynamic Dispatch");
        Member[] testMembers = new Member[] {
            new StudentMember("S999", "Alice Student"),
            new FacultyMember("F999", "Prof. Bob"),
            new GuestMember("G999", "Charlie Guest")
        };

        for (Member m : testMembers) {
            System.out.println("Member: " + m.getMemberName() + " | Type: " + m.getMembershipType());
            System.out.println("  -> Max Days: " + m.getMaximumLoanDays());
            System.out.println("  -> Daily Fine: PHP " + String.format("%.2f", m.getDailyFine()));
        }

        System.out.println("\nTest 7: Interface Behavior (Renewable)");
        Member student = new StudentMember("S100", "David");
        LibraryBook book = new LibraryBook("978-0134685991", "Effective Java", true);

        LoanRecord loan = service.processTransaction(student, book, 10, 0);
        System.out.println("Initial Loan Days: " + loan.getRequestedDays() + " | Status: " + loan.getStatus());
        System.out.println("Is Renewable? " + loan.isRenewable());

        boolean renewedSuccess = loan.renew(3); 
        System.out.println("Renew +3 days success: " + renewedSuccess + " | New Days: " + loan.getRequestedDays());

        boolean exceedRenewal = loan.renew(5); 
        System.out.println("Renew +5 days success (exceeds limit): " + exceedRenewal + " | Final Days: " + loan.getRequestedDays());
    }
}
