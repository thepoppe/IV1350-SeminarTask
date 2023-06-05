package util;
import java.util.List;
import java.util.Random;


public class RandomWithComposition {
    private Random random;
    public RandomWithComposition(){
        random = new Random();
    }

    /**
     * Creates an random integer within the intervall.
     * @param lowerBound the least value that can be returned
     * @param upperBound the upper bound (inclusive) for the returned value
     * @return the returned integer
     */
    public int nextInt(int lowerBound, int upperBound){
        while (true){
            int randomNumber = random.nextInt(upperBound+1);
            if (randomNumber > lowerBound){
                return randomNumber;
            }
        }
    }

    /**
     * An adaption of nextInt that verifies that the random number is not already in the provided list and returns it.
     * @param takenNumbers the list of already taken integers in the intervall.
     * @param lowerBound the least value that can be returned
     * @param upperBound the upper bound (inclusive) for the returned value
     * @return the returned integer
     */
    public int nextInt(List<Integer> takenNumbers, int lowerBound, int upperBound){
        while (true) {
            int randomNumber = random.nextInt(lowerBound, upperBound+1);
            if (!takenNumbers.contains(randomNumber)) {
                return randomNumber;
            }
        }
    }

}
