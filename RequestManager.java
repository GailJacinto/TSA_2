/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gail Jacinto
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestManager {
    private final List<ServiceRequest> requestList = new ArrayList<>();

    public void addRequest(ServiceRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Cannot add a null service request.");
        }
        requestList.add(request);
    }

    public List<ServiceRequest> getRequestList() {
        return Collections.unmodifiableList(requestList);
    }

    public void printSummary() {
        int countEnrollment = 0;
        int countDocument = 0;
        int countPayment = 0;
        int countAdvising = 0;
        int highestPriority = 0;
        double totalDays = 0.0;

        for (ServiceRequest req : requestList) {
            switch (req.getCategory()) {
                case "ENROLLMENT" -> countEnrollment++;
                case "DOCUMENT" -> countDocument++;
                case "PAYMENT" -> countPayment++;
                case "ADVISING" -> countAdvising++;
            }

            if (req.getPriority() > highestPriority) {
                highestPriority = req.getPriority();
            }

            totalDays += req.estimateDays();
        }

        double averageDays = requestList.isEmpty() ? 0.0 : totalDays / requestList.size();

        System.out.println("      CAMPUSCONNECT FINAL SUMMARY       ");
        System.out.println("Requests per Category:");
        System.out.println("  ENROLLMENT: " + countEnrollment);
        System.out.println("  DOCUMENT:   " + countDocument);
        System.out.println("  PAYMENT:    " + countPayment);
        System.out.println("  ADVISING:   " + countAdvising);
        System.out.println("Highest Priority Logged: " + highestPriority);
        System.out.println("Average Estimated Days:  " + String.format("%.2f", averageDays));
    }
}
