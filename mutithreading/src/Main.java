import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int MAX_PASSWORD = 9999;
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));
        List<Thread> threads = new ArrayList<>();

        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerTthread(vault));
        threads.add(new PoliceThread());

        for (Thread thread : threads) {
            thread.start();
        }
    }

    public static class Vault{
        private int passsword;

        public Vault(int passsword) {
            this.passsword = passsword;
        }

        public  boolean isCorrectPassword(int guess){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return this.passsword == guess;
        }
    }

    private static abstract class HackerThread extends Thread{
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("Starting thread "+this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread{

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess=0; guess<MAX_PASSWORD; guess++){
                if(vault.isCorrectPassword(guess)){
                    System.out.println(this.getName()+" found the password "+guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerTthread extends HackerThread{

        public DescendingHackerTthread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess=MAX_PASSWORD; guess>=0; guess--){
                if(vault.isCorrectPassword(guess)){
                    System.out.println(this.getName()+" found the password "+guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread{

        @Override
        public void run() {
            for(int i=10; i>0; i--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(i);
            }
            System.out.println("Game over criminal!! :(");
            System.exit(0);
        }
    }
}