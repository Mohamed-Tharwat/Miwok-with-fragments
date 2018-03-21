package com.example.android.miwokwithfragment;

/**
 * Created by m.sarwat on 3/20/2018.
 */

public class Word {

    private String mMiwokTranslation;
    private String mDefaultTranslation;  // translate the miwok word to your default language
    private int mImage = 1;     //refering to image resource as integer
    private int mAudio = 1;

    public Word(String DefaultTranslation, String MiwokTranslation) {

        mMiwokTranslation = MiwokTranslation;
        mDefaultTranslation = DefaultTranslation;
    }

    public Word(String DefaultTranslation, String MiwokTranslation, int imageId) {

        mMiwokTranslation = MiwokTranslation;
        mDefaultTranslation = DefaultTranslation;
        mImage = imageId;
    }

    public Word(String DefaultTranslation, String MiwokTranslation, int imageId, int AudioId) {

        mMiwokTranslation = MiwokTranslation;
        mDefaultTranslation = DefaultTranslation;
        mImage = imageId;
        mAudio = AudioId;
    }

    public String getDefaultTranslation() {

        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {

        return mMiwokTranslation;
    }

    public int getWordImage() {
        return mImage;
    }

    public int getAudio() {
        return mAudio;
    }

    public boolean hasImage() {

        return mImage != 1;
    }

    public boolean hasAudio() {

        return mAudio != 1;
    }
}
