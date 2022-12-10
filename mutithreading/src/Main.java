public class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new MyThread();
        thread.setName("Revolt-GPG");
        thread.start();
    }

    public static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("The current thread running is "+this.getName());
        }
    }
}