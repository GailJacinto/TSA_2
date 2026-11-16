/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookbridge_tsa1;

/**
 *
 * @author Gail Jacinto
 */
public class Reservation {
    private final String reservationId;
    private final Member member;
    private final LibraryBook book;

    public Reservation(String reservationId, Member member, LibraryBook book) {
        if (reservationId == null || reservationId.trim().isEmpty()) {
            throw new IllegalArgumentException("Reservation ID cannot be empty.");
        }
        if (member == null || book == null) {
            throw new IllegalArgumentException("Member and Book must not be null.");
        }
        this.reservationId = reservationId;
        this.member = member;
        this.book = book;
    }

    public String getReservationId() {
        return reservationId;
    }

    public Member getMember() {
        return member;
    }

    public LibraryBook getBook() {
        return book;
    }
}
