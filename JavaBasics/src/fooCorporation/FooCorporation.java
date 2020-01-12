package fooCorporation;

public class FooCorporation {
    
 public static void main(String[] args) {
        
        calcPay(35.0, 7.50);
        calcPay(47.0, 8.20);
        calcPay(73.0, 10.00);
 }
 
    // Sets the class min wage to $8.00 
    public static double minWage = 8.00;
    // Sets the class max weekly hours 
    public static double maxHours = 60.00;
    
    // Method to calculate pay based on hours and basePay parameter
    public static void calcPay(double hoursWorked, double basePay) {
        
        if (basePay < FooCorporation.minWage){
            System.out.println("Error: Base pay is below the minimum wage");
        } 
        else if (hoursWorked > FooCorporation.maxHours) {
            System.out.println("Error: Hours worked exceeds 60 hour limit");
        }
        else {
            double pay;
            if (hoursWorked <= 40) { 
                    pay = (basePay * hoursWorked);
            } else {
                    pay = (basePay * 40) + ((basePay * 1.5) * (hoursWorked - 40));       
            }
            System.out.println("Employee with base pay of " + basePay 
            + " and " + hoursWorked + " hours worked is due: $" + pay);
        }
    }
}







