package section_3;

public class ExampleOne {
    /*
     * When we can Interrupt a Thread
     *
     * Example 1: by executing a method that throws an interrupted exception
     */
    public static void main(String[] args){
        Thread thread = new Thread(new BlockingTask());
        thread.start();

        thread.interrupt();
    }

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking Thread");
            }
        }
    }
}
