/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bookbridge_tsa1;

/**
 * Interface defining contract for objects that can be renewed.
 */
public interface Renewable {
    /**
     * Checks if the current loan record is eligible for renewal.
     * @return true if renewable, false otherwise.
     */
    boolean isRenewable();

    /**
     * Attempts to extend the loan period by additional days.
     * @param additionalDays Number of days to extend.
     * @return true if renewal was successful within member limit, false otherwise.
     */
    boolean renew(int additionalDays);
}