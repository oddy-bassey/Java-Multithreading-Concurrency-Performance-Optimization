package section_6_concurrency_challenges_and_solutions.exercise_min_max_metrics;

public class MinMaxMetrics {

    // Add all necessary member variables
    private volatile long minimumPrice;
    private volatile long maximumPrice;

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        // Add code here
        this.minimumPrice = Long.MAX_VALUE;
        this.maximumPrice = Long.MIN_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public synchronized void addSample(long newSample) {
        // Add code here
        this.minimumPrice = Math.min(newSample, this.minimumPrice);
        this.maximumPrice = Math.max(newSample, this.maximumPrice);
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        // Add code here
        return minimumPrice;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        // Add code here
        return maximumPrice;
    }
}
