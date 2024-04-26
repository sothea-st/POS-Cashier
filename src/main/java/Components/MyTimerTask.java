
package Components;

import java.util.Timer;
import java.util.Date;
import java.util.TimerTask;


public class MyTimerTask{

    public static void main(String[] args) {
        Timer timer = new Timer();

        // Set the desired date and time for the first execution
        Date firstExecutionTime = new Date(System.currentTimeMillis() + 5000); // 5 seconds from now

        // Schedule the task to run at the desired time and repeat every 2 seconds
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Task logic here
                System.out.println("Task executed at: " + new Date());
                // Add your code to be executed here
            }
        }, firstExecutionTime, 1000); // 2000 milliseconds (2 seconds) interval between executions

        System.out.println("Task scheduled for execution at: " + firstExecutionTime);
    }
}
