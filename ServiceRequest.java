/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gail Jacinto
 */
public abstract class ServiceRequest implements Prioritizable {
    private static int nextTicketSequence = 1001;

    private final String ticketId;
    private final Student student;
    private final int priority;
    private final String description;

    public ServiceRequest(Student student, int priority, String description) {
        if (student == null) {
            throw new IllegalArgumentException("Student reference cannot be null.");
        }
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty or null.");
        }

        this.ticketId = "TKT-" + (nextTicketSequence++);
        this.student = student;
        this.priority = priority;
        this.description = description.trim();
    }

    public int estimateDays() {
        int days = getBaseDays();
        if (this.priority == 5) {
            days -= 1;
        }
        return Math.max(1, days);
    }

    public abstract int getBaseDays();

    public abstract String getCategory();

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public boolean isUrgent() {
        return priority >= 4;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Student getStudent() {
        return student;
    }

    public String getDescription() {
        return description;
    }

    public static int getNextTicketSequence() {
        return nextTicketSequence;
    }

    public void printTicket() {
        System.out.println("\n>> --- TICKET ACCEPTED [" + ticketId + "] ---");
        System.out.println(">> Student: " + student.getStudentName() + " (" + student.getStudentId() + ")");
        System.out.println(">> Category: " + getCategory() + " | Priority: " + priority + (isUrgent() ? " [URGENT]" : ""));
        System.out.println(">> Description: " + description);
        System.out.println(">> Estimated Completion: " + estimateDays() + " working day(s)\n");
    }
}
