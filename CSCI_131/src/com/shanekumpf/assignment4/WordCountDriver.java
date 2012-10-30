package com.shanekumpf.assignment4;
import java.io.*;
import csci130.*;

/**
 * Provides the ability to count the occurrences of a word in a file.
 * 
 * @author Shane Kumpf
 * @version 0.1 10/30/12
 * @since 1.6
 *
 */
public class WordCountDriver {
	
	/**
	 * Returns the insertion location of the supplied WordCount object to maintain sorted order
	 * 
	 * @param words array of sorted WordCount objects
	 * @param word	WordCount object to be inserted
	 * @return	array position where WordCount object will be inserted
	 */
	private static int getInsertPosition(WordCount[] words, WordCount word) {
		
		// If the array is empty, just create and add the WordCount object
		if(words.length == 0) { // If empty, just add this object
			return 0;
		// If the array is not empty, determine placement in the array
		} else {
			int start = 0;
			int end = words.length - 1;
			int middle = words.length / 2;
			
			// Only 1 WordCount in the array
			if (start == end) {
				int compareWords = word.compareTo(words[0].getWord());
				if (compareWords < 0) { // LEFT
					return 0;
				}
				return 1;
			}
			
			// Multiple WordCount objects, compare to determine position
			// when start and end meet that will be the position where
			// this WordCount object should be inserted.
			WordCount middleWord = null;
			int compareWords = 0;
			while (start != end) {
				middleWord = words[middle];
				
				compareWords = word.compareTo(middleWord.getWord());
				if (compareWords != 0) {
					if (compareWords < 0) {  // LEFT
						end = middle;
					} else {  // RIGHT
						start = middle + 1;  
					}
				} else {
					break;
				}
				middle = (start + end) / 2;
			}
			return end;
		}	
	}
	
	/**
	 * Determine if the WordCount object is in the array and increment the counter
	 * if found.
	 * 
	 * @param words	where to check for the word
	 * @param word	the WordCount to be checked
	 * @return	true if found in the array and incremented
	 */
	private static boolean checkForWordCountAndIncrement(WordCount[] words, WordCount word) {
		int start = 0;
		int end = words.length - 1;
		int middle = words.length / 2;
		
		// Only 1 element in the array, compare to this word
		if (start == end) {
			if (word.compareTo(words[0].getWord()) == 0) {
				words[0].setCount(words[0].getCount() + 1);
				return true;
			}
			return false;
		}
		
		
		// Check the middle word to see if it matches this WordCount
		WordCount middleWord = null;
		int compareWords = 0;
		while (start != end) {
			middleWord = words[middle];
			
			compareWords = word.compareTo(middleWord.getWord());
			if (compareWords != 0) {
				if (compareWords < 0) {  // LEFT
					end = middle;
				} else {  // RIGHT
					start = middle + 1;  
				}
			} else { // EQUAL increment count
				middleWord.setCount(middleWord.getCount() + 1);
				return true;
			}
			middle = (start + end) / 2;
		}

		return false;
	}
	
	/**
	 * Determines if the WordCount object is in the array, if not get the position
	 * and add it to the appropriate location to maintain sorted order.
	 * 
	 * @param words	array where WordCount will be added/incremented
	 * @param word	word to add/increment
	 * @return	WordCount array containing the word provided.
	 */
	private static WordCount[] addToArray(WordCount[] words, WordCount word) {
		if(words.length == 0) {
			words = new WordCount[1];
			words[0] = word;
			return words;
		}
		
		if(!checkForWordCountAndIncrement(words, word)) {
			
			int position = getInsertPosition(words, word);
			WordCount[] tmpWords = new WordCount[words.length + 1];
	       
			System.arraycopy(words, 0, tmpWords, 0, position);
			System.arraycopy(words, position, tmpWords, position + 1, tmpWords.length - (position + 1));
			tmpWords[position] = word;
			
			words = tmpWords;
			return words;
		}
		
		return words;
	}
	
    public static final void main(String[] args) {

        System.out.print("WordCount: Enter the absolute path to the file: ");
        String resp = KeyboardReaderNG.readLine();

        WordCount[] words = new WordCount[0];

        Reader reader = null;
        StreamTokenizer tokenizer = null;

        try {
            reader = new BufferedReader(new FileReader(resp));
            tokenizer = new StreamTokenizer(reader);

            int token = 0;
            String targetToken = "";
            while ((token = tokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
                switch (token) {
                    case StreamTokenizer.TT_WORD:
                        targetToken = tokenizer.sval;
                        break;
                    case StreamTokenizer.TT_NUMBER:
                        targetToken = Double.toString(tokenizer.nval);
                        break;
                    default:
                        targetToken = tokenizer.sval;
                        break;
                }

                // Strip punctuation from end of the String
                if (targetToken != null) {
                	while(!Character.isLetterOrDigit(targetToken.charAt(targetToken.length() - 1))) {
                		targetToken = targetToken.substring(0, targetToken.length() - 1);
                	}
                } else {
                	continue;
                }
                
                // Perhaps needlessly created, but need to use WordCount compareTo
    			WordCount wordObj = new WordCount();
    			wordObj.setCount(1);
    			wordObj.setWord(targetToken);
    			
    			// Add the WordCount object to the array
                words = addToArray(words, wordObj);
            }
        } catch(IOException ioe) {
            ioe.printStackTrace(System.err);
        } finally {
            try {
                reader.close();
            } catch(Exception e) {}
        }
        
        // Display results
        for (int i = 0; i < words.length; i++) {
        	System.out.println(words[i]);
        }
        
    }
}