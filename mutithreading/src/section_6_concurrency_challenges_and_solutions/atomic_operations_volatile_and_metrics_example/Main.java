package section_6_concurrency_challenges_and_solutions.atomic_operations_volatile_and_metrics_example;

import java.util.Random;

public class Main {

    public static void main (String[] args) {
        Metrics metrics = new Metrics();
        BusinessLogic logic1 = new BusinessLogic(metrics);
        BusinessLogic logic2 = new BusinessLogic(metrics);
        MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);

        logic1.start();
        logic2.start();
        metricsPrinter.start();
    }

    public static class MetricsPrinter extends Thread {
        private Metrics metrics;

        public MetricsPrinter (Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                double currentAverage = metrics.getAverage();
                System.out.println("Current Average is :" + currentAverage);
            }
        }
    }

    public static class BusinessLogic extends Thread {
        private Metrics metrics;
        private Random random = new Random();

        public BusinessLogic (Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                long start = System.currentTimeMillis();

                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                long end = System.currentTimeMillis();
                metrics.addSample(end - start);
            }
        }
    }

    public static class Metrics {
        private long count = 0;
        private volatile double average = 0.0;

        public synchronized void addSample (long sample) {
            double currentSample = average * count;
            count++;
            average = (currentSample + sample) / count;
        }

        public double getAverage() {
            return average;
        }
    }
}
