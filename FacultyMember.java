/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookbridge_tsa1;

/**
 *
 * @author Gail Jacinto
 */
public class FacultyMember extends Member {

    public FacultyMember(String memberId, String memberName) {
        super(memberId, memberName);
    }

    @Override
    public int getMaximumLoanDays() {
        return 30;
    }

    @Override
    public double getDailyFine() {
        return 2.0;
    }

    @Override
    public String getMembershipType() {
        return "FACULTY";
    }
}