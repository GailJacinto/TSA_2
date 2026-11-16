/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gail Jacinto
 */
public class EnrollmentRequest extends ServiceRequest {

    public EnrollmentRequest(Student student, int priority, String description) {
        super(student, priority, description);
    }

    @Override
    public int getBaseDays() {
        return 3;
    }

    @Override
    public String getCategory() {
        return "ENROLLMENT";
    }
}