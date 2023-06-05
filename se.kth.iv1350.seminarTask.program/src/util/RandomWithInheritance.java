package util;


import java.util.List;

/**
 * This class is an adaption of the class java.util.Random.
 * It uses inheritance to extend the features of Random.
 */
public class RandomWithInheritance extends java.util.Random{

    public RandomWithInheritance(){};

    /**
     * Creates a random number between the lower and upper bound, small adaption with inclusive upperbound
     * @param lowerBound the least value that can be returned
     * @param upperBound the upper bound (inclusive) for the returned value
     *
     * @return the random number
     */
     @Override
    public int nextInt(int lowerBound, int upperBound){
        while (true) {
            int randomNumber = super.nextInt(upperBound+1);
            if (randomNumber >= lowerBound) {
                return randomNumber;
            }
        }
    }

    /**
     * An adaption of nextInt that verifies that the random number is not already in the
     * provided list and returns it.
     * @param takenNumbers the list of already taken integers in the intervall
     * @param lowerBound the least value that can be returned
     * @param upperBound the upper bound (exclusive) for the returned value
     * @return the random number
     */
    public int nextInt(List<Integer> takenNumbers, int lowerBound, int upperBound){
        while (true) {
            int randomNumber = super.nextInt(lowerBound, upperBound+1);
            if (!takenNumbers.contains(randomNumber)) {
                return randomNumber;
            }
        }
    }


}
