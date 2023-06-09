/**@Gilbert CS145 GuitarString
 *@version 1.0 (05/31/2023)
 *@see GuitarString class*/
public class Guitar37 implements Guitar {
   public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; // keyboard layout
   private GuitarString[] stringCollection;
   private int count;

   public Guitar37() {
      stringCollection = new GuitarString[37];
      for (int i = 0; i < stringCollection.length; i++) {
         double frequency = 440.0 * Math.pow(2, (i - 24) / 12.0);
         stringCollection[i] = new GuitarString(frequency);
      }
      count = 0;
   }

   /*Plays the note of the given pitch
   @param pitch the pitch of the note to be played
   */
   public void playNote(int pitch) {
      int pStart = pitch + 24;
      if (pStart >= 0 && pStart < stringCollection.length) stringCollection[pStart].pluck();
   }

   /*Checks if the keyboard has the given key
   @param key the key to be checked
   @return boolean indicating if the key exists
   */
   public boolean hasString(char key) {
      char[] aChars = KEYBOARD.toCharArray();
      for (char c: aChars) {
         if (key == c) {
            return true;
         }
      }
      return false;
   }

   /*Plucks the guitar string corresponding to the given key
   @param key the key corresponding to the string to be plucked
   */
   public void pluck(char key) {
      if (!hasString(key)) throw new IllegalArgumentException();

      int index = KEYBOARD.indexOf(key);
      if (index != -1) stringCollection[index].pluck();

   }

   /*Samples the sound of the guitar
   @return double the sum of the samples of all the strings
   */
   public double sample() {
      double sTotal = 0.0;

      for (GuitarString s: stringCollection) {
         sTotal += s.sample();
      }
      return sTotal;
   }

   public void tic() {
      for (GuitarString s: stringCollection) {
         s.tic();
      }
      count++;
   }

   /*Gets the current time
   @return int the current time
   */
   public int time() {
      return count;
   }
}