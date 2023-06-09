/**@Gilbert CS145 GuitarString
 *@version 1.0 (05/31/2023)
 *@see GuitarString class*/
import java.util.*;

public class GuitarString {
   private Queue < Double > ringBuffer;
   private double N;
   public double decayFactor = 0.996;

   /*Initializes a new guitar String of the given frequency
   @param frequency is the frequency of the string
   @throws IllegalArgumentException if the frequency is less than or equal to 0
   */
   public GuitarString(double frequency) {
      ringBuffer = new LinkedList < > ();
      this.N = 44100 / frequency;
      int roundedN = (int) Math.round(N);
      for (int i = 0; i < roundedN; i++) {
         ringBuffer.add(0.0);
      }
      if (frequency <= 0 || ringBuffer.size() < 2) throw new IllegalArgumentException();

   }

   /*Constructs a guitar string and initializes the contents of the ring buffer
   @param init is the array of values to initialize the ring buffer
   @throws IllegalArgumentException if the length of the array is less than 2
   */
   public GuitarString(double[] init) {
      if (init.length < 2) throw new IllegalArgumentException();

      ringBuffer = new LinkedList < > ();
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
   }

   public void pluck() {
      Random rand = new Random();
      for (int i = 0; i < N; i++) {
         ringBuffer.remove();
         double randDouble = -0.5 + rand.nextDouble();
         ringBuffer.add(randDouble);
      }
   }

   public void tic() {
      double frontBuff = ringBuffer.remove();
      double endBuff = ringBuffer.peek();
      double avgBuff = (frontBuff + endBuff) / 2;
      double avgFactorBuff = avgBuff * decayFactor;
      ringBuffer.add(avgFactorBuff);
   }

   public double sample() {
      return ringBuffer.peek();
   }
}