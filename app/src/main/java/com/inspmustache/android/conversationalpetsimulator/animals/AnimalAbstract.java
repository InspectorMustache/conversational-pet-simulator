package com.inspmustache.android.conversationalpetsimulator.animals;

import java.util.Random;

public abstract class AnimalAbstract {
    private String[] vowels;
    private String[] consonants;
    private String[] startLetters;
    private String[] endLetters;
    private String[] specialStrings;
    // these boundaries are rather vague and the actual string length can be a couple chars longer
    private int minLength;
    private int maxLength;

    // chars that may end an utterance
    private String[] finalizers;

    // boolean that defines whether or not output can feature consonants before the finalizer
    private boolean consonantBeforeFinalizerAllowed;

    // global random number generator
    private Random numberGenerator;

    AnimalAbstract () {
        this.finalizers = new String[]{"!", "?", "...", ".", "!!!", "???"};
        this.minLength = 2;
        this.maxLength = 12;
        this.numberGenerator = new Random();
        this.consonantBeforeFinalizerAllowed = false;
    }

    // setters for child classes
    public void setVowels(String[] vowels) {
        this.vowels = vowels;
    }

    public void setConsonants(String[] consonants) {
        this.consonants = consonants;
    }

    public void setStartLetters(String[] startLetters) {
        this.startLetters = startLetters;
    }

    public void setEndLetters(String[] endLetters) {
        this.endLetters = endLetters;
    }

    public void setSpecialStrings(String[] specialStrings) {
        this.specialStrings = specialStrings;
    }

    public void setConsonantBeforeFinalizerAllowed(boolean consonantBeforeFinalizerAllowed) {
        this.consonantBeforeFinalizerAllowed = consonantBeforeFinalizerAllowed;
    }
    //end of setters

    public String outputSpeech() {
        // every once in a while return the special string
        if (Math.random() <= 0.22) {
            return getRandomItem(this.specialStrings);
        }

        // otherwise we'll create a custom string
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getRandomItem(this.startLetters));

        // alternate between adding vowels and consonants
        boolean vowelNext = true;
        int loopLength = getrandomSize();
        for (int i = 0; i <= loopLength; i++) {
            // break if we're at the last iteration, and an illegal consonant would be inserted
            if ( (i == loopLength) && (! this.consonantBeforeFinalizerAllowed) && (! vowelNext)) {
                break;
            }

            if (vowelNext) {
                stringBuilder.append(getRandomItem(this.vowels));
                vowelNext = false;
            } else {
                stringBuilder.append(getRandomItem(this.consonants));
                vowelNext = true;
            }
        }

        // add a final letter and a finalizer and return the finished string
        stringBuilder.append(getRandomItem(this.endLetters));
        stringBuilder.append(getRandomItem(this.finalizers));

        return stringBuilder.toString();
    }

    private String getRandomItem(String[] stringArray) {
        int itemPosition = numberGenerator.nextInt(stringArray.length);
        return stringArray[itemPosition];
    }

    private int getrandomSize() {
        return numberGenerator.nextInt(this.maxLength) + this.minLength;
    }
}
