public class CompareStringBufferBuilder {
    public static void main(String[] args) {
        int n = 1_000_000;
        String sample = "hello";

        // Test with StringBuffer
        StringBuffer sb = new StringBuffer();
        long startTimeSB = System.nanoTime();
        for (int i = 0; i < n; i++) {
            sb.append(sample);
        }
        long endTimeSB = System.nanoTime();
        long durationSB = endTimeSB - startTimeSB;

        // Test with StringBuilder
        StringBuilder sbd = new StringBuilder();
        long startTimeSBD = System.nanoTime();
        for (int i = 0; i < n; i++) {
            sbd.append(sample);
        }
        long endTimeSBD = System.nanoTime();
        long durationSBD = endTimeSBD - startTimeSBD;

        System.out.println("Time taken by StringBuffer: " + durationSB / 1_000_000 + " ms");
        System.out.println("Time taken by StringBuilder: " + durationSBD / 1_000_000 + " ms");
    }
}












