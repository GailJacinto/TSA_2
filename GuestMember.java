/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookbridge_tsa1;

/**
 *
 * @author Gail Jacinto
 */
public class GuestMember extends Member {

    public GuestMember(String memberId, String memberName) {
        super(memberId, memberName);
    }

    @Override
    public int getMaximumLoanDays() {
        return 7;
    }

    @Override
    public double getDailyFine() {
        return 10.0;
    }

    @Override
    public String getMembershipType() {
        return "GUEST";
    }
}