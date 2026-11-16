/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookbridge_tsa1;

/**
 *
 * @author Gail Jacinto
 */
public class StudentMember extends Member {

    public StudentMember(String memberId, String memberName) {
        super(memberId, memberName);
    }

    @Override
    public int getMaximumLoanDays() {
        return 14;
    }

    @Override
    public double getDailyFine() {
        return 5.0;
    }

    @Override
    public String getMembershipType() {
        return "STUDENT";
    }
}
