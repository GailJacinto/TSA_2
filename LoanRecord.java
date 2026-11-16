/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookbridge_tsa1;

/**
 *
 * @author Gail Jacinto
 */
public class LoanRecord implements Renewable {
    private static int nextLoanSequence = 1001;

    private final String loanId;
    private final Member member;
    private final LibraryBook book;
    private int requestedDays;
    private final int lateDays;
    private String status;
    private double estimatedFine;

    public LoanRecord(Member member, LibraryBook book, int requestedDays, int lateDays) {
        if (member == null) {
            throw new IllegalArgumentException("Member reference cannot be null.");
        }
        if (book == null) {
            throw new IllegalArgumentException("Book reference cannot be null.");
        }
        if (requestedDays < 1 || requestedDays > 30) {
            throw new IllegalArgumentException("Requested days must be between 1 and 30.");
        }
        if (lateDays < 0) {
            throw new IllegalArgumentException("Late days cannot be negative.");
        }

        this.loanId = "LN-" + (nextLoanSequence++);
        this.member = member;
        this.book = book;
        this.requestedDays = requestedDays;
        this.lateDays = lateDays;

        evaluateLoan();
    }

    private void evaluateLoan() {
        if (!book.isAvailable()) {
            this.status = "RESERVED";
            this.estimatedFine = 0.0;
        } else if (requestedDays <= member.getMaximumLoanDays()) {
            this.status = "APPROVED";
            this.estimatedFine = lateDays * member.getDailyFine();
        } else {
            this.status = "DENIED";
            this.estimatedFine = 0.0;
        }
    }

    @Override
    public boolean isRenewable() {
        return "APPROVED".equals(status) && book.isAvailable();
    }

    @Override
    public boolean renew(int additionalDays) {
        if (!isRenewable() || additionalDays <= 0) {
            return false;
        }
        int newTotalDays = this.requestedDays + additionalDays;
        if (newTotalDays <= member.getMaximumLoanDays()) {
            this.requestedDays = newTotalDays;
            evaluateLoan();
            return true;
        }
        return false;
    }

    public String getLoanId() {
        return loanId;
    }

    public Member getMember() {
        return member;
    }

    public LibraryBook getBook() {
        return book;
    }

    public int getRequestedDays() {
        return requestedDays;
    }

    public int getLateDays() {
        return lateDays;
    }

    public String getStatus() {
        return status;
    }

    public double getEstimatedFine() {
        return estimatedFine;
    }

    public static int getNextLoanSequence() {
        return nextLoanSequence;
    }
}
