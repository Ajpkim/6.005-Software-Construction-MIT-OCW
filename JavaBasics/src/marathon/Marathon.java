package marathon;
class Marathon {
    public static void main (String[] arguments) {
        String[] names = {
            "Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt", "Alex",
            "Emma", "John", "James", "Jane", "Emily", "Daniel", "Neda",
            "Aaron", "Kate"
        };

        int[] times = {
            341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393, 299,
            343, 317, 265
        };

        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + ": " + times[i]);
        }
        fastestRunner(names, times);
        secondFastest(names, times);
    }
    
    public static int fastestIndex(int[] times) {
        // takes in int array and returns the index of the lowest int
        int i;
        int res = 0; // Why do I need to initialize to 0 here? (otherwise I get an error)
        int fastestTime = 10000;
        for (i = 0; i < times.length; i++) {
            if (times[i] < fastestTime) { 
                fastestTime = times[i];
                res = i;}
        }
        return res;
    }
    
    public static void fastestRunner(String[] names, int[] times) {
        // Takes in array of strings(names) and array of int (times)
        // Prints out the name and time corresponding to lowest int
        int index = fastestIndex(times);
        String name = names[index];
        int time = times[index];
        
        System.out.println("Fastest runner: " + name + ", " + time);
    }

    public static void secondFastest(String[] names, int[] times) {
        /// Take in array of strings and ints and prints out string and int corresponding
        /// to the second lowest int
        int fastestTime = times[fastestIndex(times)];
        int secondFastest = 1000;
        int index = 0;
        for (int i = 0; i < times.length; i++) {
            if (times[i] != fastestTime) {
                if (times[i] < secondFastest) {
                    secondFastest = times[i];
                    index = i;       
                }   
            }
        }
        String name = names[index];
        int time = times[index]; 
        
        System.out.println("Second fastest runner: " + name + ", " + time);
    }
}
    
