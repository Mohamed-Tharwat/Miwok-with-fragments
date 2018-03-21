package com.example.android.miwokwithfragment;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {
    MediaPlayer mMediaPlayer;
    AudioManager maudioManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_phrases, container, false);

        final ArrayList<Word> words = new ArrayList<Word>(); //to indicate the words in inner methods
        words.add(new Word("Where are you going?", "minto wuksus", 1, R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", 1, R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset", 1, R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", 1, R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit", 1, R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", 1, R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", 1, R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm", 1, R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis", 1, R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", 1, R.raw.phrase_come_here));

        wordAdapter itemsAdapter = new wordAdapter(getActivity(), words, R.color.category_phrases);
        ListView listView = rootview.findViewById(R.id.list_Phrases);
        listView.setAdapter(itemsAdapter);
        maudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                MediaRelease();
                int mAudioFocusRequest = maudioManager.requestAudioFocus(maudioListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (mAudioFocusRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), words.get(position).getAudio());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            MediaRelease();
                        }
                    });
                }
            }
        });

        return rootview;
    }

    @Override
    public void onPause() {
        super.onPause();
        MediaRelease();
    }

    @Override
    public void onStop() {
        super.onStop();
        MediaRelease();
    }

    public void MediaRelease() {
        if (mMediaPlayer != null) {

            mMediaPlayer.release();
            mMediaPlayer = null;
            maudioManager.abandonAudioFocus(maudioListener);
        }
    }

    private AudioManager.OnAudioFocusChangeListener maudioListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int Focuschange) {
            switch (Focuschange) {
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                case AudioManager.AUDIOFOCUS_LOSS:
                    MediaRelease();
                case AudioManager.AUDIOFOCUS_GAIN:
                    mMediaPlayer.start();
            }
        }
    };

}
