package com.gerasimchuk.exceptions.driverexceptions;

/** Too many hours worked for order Exception Class
 * @author Reaper
 * @version 1.0
 */

public class TooManyHoursWorkedForOrderException extends DriverException {

    public TooManyHoursWorkedForOrderException() {
        super("Driver hours worked value is too big to execute order");
    }

    public TooManyHoursWorkedForOrderException(String s) {
        super(s);
    }


}
