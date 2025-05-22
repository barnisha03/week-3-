public class CircularTour {
    public static int findStartingPump(int[] petrol, int[] distance) {
        int n = petrol.length;
        int start = 0, deficit = 0, surplus = 0;
        
        for (int i = 0; i < n; i++) {
            surplus += petrol[i] - distance[i];
            
            if (surplus < 0) {
                deficit += surplus;
                surplus = 0;
                start = i + 1;
            }
        }
        
        return (surplus + deficit) >= 0 ? start : -1;
    }
    
    public static void main(String[] args) {
        int[] petrol = {6, 3, 7};
        int[] distance = {4, 6, 3};
        
        int startPump = findStartingPump(petrol, distance);
        if (startPump == -1) {
            System.out.println("No possible start point for the circular tour.");
        } else {
            System.out.println("Start the tour at pump index: " + startPump);
        }
    }
}











