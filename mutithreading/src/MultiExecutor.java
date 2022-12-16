import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    /*
     * Thread Creation - MultiExecutor
     * In this exercise we are going to implement a  MultiExecutor .
     *
     * The client of this class will create a list of Runnable tasks and provide that list into MultiExecutor's constructor.
     *
     * When the client runs the  executeAll(),  the MultiExecutor,  will execute all the given tasks.
     *
     * To take full advantage of our multicore CPU, we would like the MultiExecutor to execute all the tasks in parallel, by passing each task to a different thread.
     *
     * Please implement the MultiExecutor below
     */

    // Add any necessary member variables here
    private List<Runnable> tasks;

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        // Complete your code here
        for (Runnable task : tasks)  {
            new Thread(task).start();
        }
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        // complete your code here
        tasks = new ArrayList<>();
        for (int i = 5; i<6; i++){
            int value = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Executing some work: "+ value);
                }
            };
            tasks.add(task);
        }

        new MultiExecutor(tasks);
    }
}