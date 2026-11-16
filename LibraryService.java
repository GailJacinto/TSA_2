/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookbridge_tsa1;

/**
 *
 * @author Gail Jacinto
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibraryService {
    private final List<LoanRecord> loanRecords = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public LoanRecord processTransaction(Member member, LibraryBook book, int requestedDays, int lateDays) {
        LoanRecord loan = new LoanRecord(member, book, requestedDays, lateDays);
        loanRecords.add(loan);

        if ("RESERVED".equals(loan.getStatus())) {
            Reservation reservation = new Reservation("RES-" + loan.getLoanId(), member, book);
            reservations.add(reservation);
        }

        return loan;
    }

    public List<LoanRecord> getLoanRecords() {
        return Collections.unmodifiableList(loanRecords);
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public void printSummary() {
        int approvedCount = 0;
        int reservedCount = 0;
        int deniedCount = 0;
        int maxReqDays = 0;
        double totalFines = 0.0;

        for (LoanRecord record : loanRecords) {
            switch (record.getStatus()) {
                case "APPROVED" -> approvedCount++;
                case "RESERVED" -> reservedCount++;
                case "DENIED" -> deniedCount++;
            }

            if (record.getRequestedDays() > maxReqDays) {
                maxReqDays = record.getRequestedDays();
            }

            totalFines += record.getEstimatedFine();
        }

        System.out.println("      BOOKBRIDGE FINAL SUMMARY       ");
        System.out.println("Total Approved Transactions: " + approvedCount);
        System.out.println("Total Reserved Transactions: " + reservedCount);
        System.out.println("Total Denied Transactions:   " + deniedCount);
        System.out.println("Highest Requested Days:      " + maxReqDays);
        System.out.println("Total Estimated Late Charges: PHP " + String.format("%.2f", totalFines));
    }
}
