/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gail Jacinto
 */
public class PaymentConcern extends ServiceRequest {

    public PaymentConcern(Student student, int priority, String description) {
        super(student, priority, description);
    }

    @Override
    public int getBaseDays() {
        return 1;
    }

    @Override
    public String getCategory() {
        return "PAYMENT";
    }
}
