/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gail Jacinto
 */
public interface Prioritizable {
    /**
     * Gets the priority rating level (1-5).
     * @return priority integer value
     */
    int getPriority();

    /**
     * Determines whether the request is classified as high-priority/urgent.
     * @return true if priority is 4 or 5, false otherwise.
     */
    boolean isUrgent();
}
