/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Gail Jacinto
 */
package com.mycompany.bookbridge_tsa1;

public abstract class Member {
    private final String memberId;
    private final String memberName;

    public Member(String memberId, String memberName) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty.");
        }
        if (memberName == null || memberName.trim().isEmpty()) {
            throw new IllegalArgumentException("Member Name cannot be null or empty.");
        }
        this.memberId = memberId.trim();
        this.memberName = memberName.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public abstract int getMaximumLoanDays();

    public abstract double getDailyFine();
    
    public abstract String getMembershipType();

    @Override
    public String toString() {
        return getMembershipType() + " [ID=" + memberId + ", Name=" + memberName + "]";
    }
}
