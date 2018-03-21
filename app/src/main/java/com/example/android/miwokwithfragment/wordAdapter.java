package com.example.android.miwokwithfragment;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by m.sarwat on 3/20/2018.
 */

public class wordAdapter extends ArrayAdapter<Word> {
    private int mColor;

    public wordAdapter(Activity context, ArrayList<Word> words, int colorBackground) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mColor = colorBackground;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.miwok_text, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWordValue = getItem(position);
        // set the Background color of parent view of text
        LinearLayout linearLayout = listItemView.findViewById(R.id.linearParent);
        // get the color to be used as background
        int color = ContextCompat.getColor(getContext(), mColor);
        linearLayout.setBackgroundColor(color);

        // Find the TextView in the list_item.xml l ayout with the ID version_number
        TextView englishView = listItemView.findViewById(R.id.english);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        englishView.setText(currentWordValue.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokView = listItemView.findViewById(R.id.miwok);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        miwokView.setText(currentWordValue.getMiwokTranslation());

        ImageView imageView = listItemView.findViewById(R.id.image);

        if (currentWordValue.hasImage()) {
            imageView.setImageResource(currentWordValue.getWordImage());
            // Return the whole list item layout (containing 2 TextViews and an ImageView)
            // so that it can be shown in the ListView
            //    imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        return listItemView;
    }

}