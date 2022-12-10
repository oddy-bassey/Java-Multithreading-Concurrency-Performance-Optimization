public class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("I will break down this thread completely!");
            }
        });
        thread.setName("Revolt-GPG");
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread "+t.getName()+". The error is: "+e.getLocalizedMessage());
            }
        });
        thread.start();
    }
}