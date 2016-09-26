package com.raywenderlich.galacticon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ylf951 on 16/9/25.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>{
    //Member variable declaration: An arraylist of photos to display
    private ArrayList<Photo> mPhotos;


    //Create static inner class PhotoHolder for view references
    //Make the class extend RecyclerView.ViewHolder, allowing it to be used as a ViewHolder for the adapter
    public static class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Add a list of references to the lifecycle of the object, to avoid querying the same information repeatedly
        private ImageView mItemImage;
        private TextView mItemDate;
        private TextView mItemDescription;
        private Photo mPhoto;

        //3
        private static final String PHOTO_KEY = "PHOTO";

        //Constructor for PhotoHolder
        public PhotoHolder(View v) {
            super(v);
            //Handle references to various subviews of the photo item layout, which are defined in recyclerview_item_row.xml
            mItemImage = (ImageView) v.findViewById(R.id.item_image);
            mItemDate = (TextView) v.findViewById(R.id.item_date);
            mItemDescription = (TextView) v.findViewById(R.id.item_description);
            v.setOnClickListener(this);
        }

        //Implement the required method for View.OnClickListener
        @Override
        public void onClick(View v) {

            //Get current context of the item view
            Context context = itemView.getContext();

            //Create an intent to show a new activity
            Intent showPhotoIntent = new Intent(context, PhotoActivity.class);

            //Let the PhotoActivity know which photo to display
            showPhotoIntent.putExtra(PHOTO_KEY, mPhoto);

            //Launch the PhotoActivity by passing the intent object to the startActivity() method
            context.startActivity(showPhotoIntent);
        }

        //Bind the photo to the PhotoHolder
        public void bindPhoto(Photo photo) {
            mPhoto = photo;
            Picasso.with(mItemImage.getContext()).load(photo.getUrl()).into(mItemImage);
            mItemDate.setText(photo.getHumanDate());
            mItemDescription.setText(photo.getExplanation());
        }
    }

    //Constructor
    public RecyclerAdapter(ArrayList<Photo> photos) {
        mPhotos = photos;
    }


    //3 required methods for RecyclerView.Adapter<PhotoHolder>

    //1st required method: onCreateViewHolder()
    //Since sometimes there are no ViewHolders available, RecyclerView will ask this method from RecyclerView.Adapter to make a new ViewHolder.
    @Override
    public RecyclerAdapter.PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_row, parent, false);
        return new PhotoHolder(inflatedView);
    }

    //2nd required method: onBindViewHolder()
    //Use the parameter position to look up for the photo from the list, and bind the recipe to ItemHolder
    @Override
    public void onBindViewHolder(RecyclerAdapter.PhotoHolder holder, int position) {
        Photo itemPhoto = mPhotos.get(position);
        holder.bindPhoto(itemPhoto);
    }

    //3rd required method: getItemCount()
    //Get the total number of items to display
    @Override
    public int getItemCount() {
        return mPhotos.size();
    }
}
