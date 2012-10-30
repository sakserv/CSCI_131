
package com.shanekumpf.lab11;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Random;
 
import csci130.KeyboardReaderNG;
 
public class SortSortSort {
 
        private static final String FILENAME = "E:/IOTEST/small_file.txt";
        private static HashSet<String> hashSet;
 
        /**
        * Display the menu to the user and get the response.
        *
        * @return      sort type
        */
        private static int getUserResponse() {
                int resp = 0;
                while (resp < 1 || resp > 3) {
                        System.out.println("Sort the file using " +
                                                           "1) Bubble sort or " +
                                                           "2) Insertion sort " +
                                                           "3) Random sort");
                        resp = KeyboardReaderNG.readInt();
                }
                return resp;
        }
 
        /**
        * Prints all elements in the supplied array
        *
        * @param array array to be printed.
        */
        private static void print(String[] array) {
                for (int i = 0; i < array.length; i++) {
                        System.out.print(array[i] + " ");
                }
                System.out.println();
        }
 
        /**
        * Determine if the array is sorted.
        *
        * @param array array to check
        * @return      true if the array is sorted.
        */
        private static boolean isSorted(String[] array) {
                for (int i = 1; i < array.length; i++) {
                        if (array[i - 1].compareTo(array[i]) > 0) {
                                return false;
                        }
                }
                return true;
        }
 
        /**
        * Get all word tokens from the supplied file and remove punctuation
        * 
        * @return hashset containing all words in the file.
        */
        private static HashSet<String> getWordsFromFile() {
                BufferedReader reader = null;
                hashSet = new HashSet<String>();
 
                try {
                        reader = new BufferedReader(new FileReader(FILENAME));
 
                        String line = "";
                        while ((line = reader.readLine()) != null) {
                                for (String word : line.split("\\s+")) {
                                        word = word.replaceAll("\\W", "");
                                        if (word.length() > 0) {
                                                hashSet.add(word);
                                        }
                                }
                        }
                } catch (IOException ioe) {
                        ioe.printStackTrace(System.err);
                } finally {
                        try {
                                reader.close();
                        } catch (Exception e) {
                        }
                }
                return hashSet;
        }
 
        /**
        * Bubble sort the supplied array, print timings
        *
        * @param words set of words to be sorted
        * @return      sorted array of strings
        */
        private static String[] bubbleSort(AbstractSet<String> words) {
                String[] wordsToSort = words.toArray(new String[words.size()]);
 
                // bubble sort
                int i, j = 0;
                String temp = "";
                boolean sorted = true;
 
                long startTime = System.currentTimeMillis();
                for (i = 0; i < wordsToSort.length; i++) {
                        sorted = true;
                        for (j = 1; j < (wordsToSort.length - i); j++) {
                                if (wordsToSort[j - 1].compareTo(wordsToSort[j]) > 0) {
                                        sorted = false;
                                        temp = wordsToSort[j - 1];
                                        wordsToSort[j - 1] = wordsToSort[j];
                                        wordsToSort[j] = temp;
                                }
                        }
                        if (sorted) {
                                break;
                        }
                }
 
                long endTime = System.currentTimeMillis();
                System.out.println("Bubble Sort Took " + (endTime - startTime) + " ms");
 
                return wordsToSort;
        }
 
        /**
        *
        * @param words
        * @return
        */
        private static String[] insertionSort(AbstractSet<String> words) {
                String[] wordsToSort = words.toArray(new String[words.size()]);
               
                String target = "";
                int i,j = 1;
               
                long startTime = System.currentTimeMillis();
                for (i = 1; i < wordsToSort.length; i++) {
                        target = wordsToSort[i];
                        for(j = i - 1; (j >= 0) && (wordsToSort[j].compareTo(target) > 0); j--) {
                                wordsToSort[j + 1] = wordsToSort[j];
                        }
                        wordsToSort[j + 1] = target;
                }
 
                long endTime = System.currentTimeMillis();
                System.out.println("Insertion Sort Took " + (endTime - startTime) + " ms");
 
                return wordsToSort;
        }
 
        /**
        * Sorts the array using randomization
        *
        * @param words set of words to be sorted
        * @return      sorted array of strings
        */
        private static String[] randomSort(AbstractSet<String> words) {
                Random random = new Random();
                String[] wordsToSort = words.toArray(new String[words.size()]);
                int index1 = 0;
                int index2 = 0;
                String temp = "";
 
                long startTime = System.currentTimeMillis();
                while (!isSorted(wordsToSort)) {
                        for (int i = 0; i < wordsToSort.length; i++) {
                                index1 = random.nextInt(wordsToSort.length);
                                do {
                                        index2 = random.nextInt(wordsToSort.length);
                                } while (index1 == index2);
 
                                temp = wordsToSort[index2];
                                wordsToSort[index2] = wordsToSort[index1];
                                wordsToSort[index1] = temp;
                        }
                }
 
                long endTime = System.currentTimeMillis();
                System.out.println("Random sort Took " + (endTime - startTime) + " ms");
 
                return wordsToSort;
        }
 
        public static final void main(String[] args) {
 
                String[] sorted = null;
 
                int resp = getUserResponse();
                if (resp == 1) {
                        sorted = bubbleSort(getWordsFromFile());
                        print(sorted);
                } else if (resp == 2) {
                        sorted = insertionSort(getWordsFromFile());
                        print(sorted);
                } else {
                        sorted = randomSort(getWordsFromFile());
                        print(sorted);
                }
 
        }
 
}
 
