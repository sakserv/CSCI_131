package com.shanekumpf.assignment4;

/**
 * Provides the ability to track the number of occurrences of a word.
 * 
 * @author Shane Kumpf
 * @version 0.1 10/30/12
 * @since 1.6
 */
public class WordCount implements Comparable<String> {

    String word;
    int count;

    /**
     * Set the word within this WordCount object
     * @param word	string word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Get the word from this object
     * @return	the word previously set
     */
    public String getWord() {
        return word;
    }

    /** 
     * Set the number of occurrence of this word
     * @param count	number of occurrences
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Get the number of occurrences of this word
     * @return	the count of the number of occurrences previously set
     */
    public int getCount() {
        return count;
    }

    /**
     * String representation of this WordCount object
     */
    public String toString() {
        return word + ":" + count;
    }

    /**
     * If the string provided matches the word string in this WordCount, they are equal.
     */
    @Override
    public int compareTo(String s) {
        return word.compareTo(s);
    }

}