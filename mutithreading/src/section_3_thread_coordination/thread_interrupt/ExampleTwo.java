package section_3_thread_coordination.thread_interrupt;

import java.math.BigInteger;

public class ExampleTwo {
    /*
     * When we can Interrupt a Thread
     *
     * Example 2: If the thread we're trying to interrupt is handling the interrupt signal explicitly
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputation(BigInteger.valueOf(2), BigInteger.TEN));
        thread.setDaemon(true);
        thread.start();

        thread.interrupt();
    }

    private static class LongComputation implements Runnable{

        private BigInteger base;
        private BigInteger power;

        public LongComputation(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.printf("%d ^ %d = %d", base, power, pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger  power){
            BigInteger result = BigInteger.ONE;
            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {

                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Prematurely interrupted computation!");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}
