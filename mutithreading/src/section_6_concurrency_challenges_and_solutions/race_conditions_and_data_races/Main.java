package section_6_concurrency_challenges_and_solutions.race_conditions_and_data_races;

public class Main {

    public static void main(String[] args) {

        SharedClass sharedClass = new SharedClass();
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.increment();
            }
        });
        Thread dataRaceCheckerThread = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.checkForDataRace();
            }
        });

        incrementThread.start();
        dataRaceCheckerThread.start();
    }

    public static class SharedClass {
        private int x = 0;
        private int y = 0;

        public void increment() {
            x++;
            y++;
        }

        public void checkForDataRace() {
            if (y > x) {
                System.out.println(" Y > x -> Data race detected.");
            }
        }
    }
}
