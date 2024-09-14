import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
	public static void main(String[]args) {
		int ind = 0;
		String champion = "";

		while(!StdIn.isEmpty()) {
			String word = StdIn.readString();
			boolean isChamp = StdRandom.bernoulli(1/(ind + 1.0));
			
			if(isChamp) {
				champion = word;
			}
			ind++;
		}
		StdOut.println(champion);
	}
} 
