package com.inspmustache.android.conversationalpetsimulator.animals;

import android.content.Context;
import android.content.res.Resources;

import com.inspmustache.android.conversationalpetsimulator.R;

/**
 * Created by nicolai on 02.12.17.
 */

public class Cat extends AnimalAbstract {

    public Cat(Context context) {
        super();
        Resources resources = context.getResources();
        setVowels(resources.getStringArray(R.array.cat_vowels));
        setConsonants(resources.getStringArray(R.array.cat_consonants));
        setStartLetters(resources.getStringArray(R.array.cat_start_letters));
        setEndLetters(resources.getStringArray(R.array.cat_end_letters));
        setSpecialStrings(resources.getStringArray(R.array.cat_special_strings));

    }
}
