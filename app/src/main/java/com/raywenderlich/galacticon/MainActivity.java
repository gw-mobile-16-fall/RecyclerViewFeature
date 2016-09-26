/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.galacticon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ImageRequester.ImageRequesterResponse {

  //Declare the member variables of MainActivity
  private ArrayList<Photo> mPhotosList;
  private ImageRequester mImageRequester;
  private RecyclerView mRecyclerView;
  private LinearLayoutManager mLinearLayoutManager;
  private RecyclerAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Reference RecyclerView
    mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    //LinearLayoutManager positions items to look like a standard ListView
    //Layout Managers comes in three default flavors: LinearLayoutManager, GridLayoutManager, and StaggerGridLayoutManager
    mLinearLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLinearLayoutManager);

    //Get photo list
    mPhotosList = new ArrayList<>();
    mImageRequester = new ImageRequester(this);
    //Create a RecyclerView Adapter
    mAdapter = new RecyclerAdapter(mPhotosList);
    //Attach the adapter to the RecyclerView
    mRecyclerView.setAdapter(mAdapter);

    //Add an onScrollListener to RecyclerView
    setRecyclerViewScrollListener();
  }

  //onScrollListener
  private void setRecyclerViewScrollListener() {
    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        //Retrieve the count of the items in its LayoutManager
        int totalItemCount = mRecyclerView.getLayoutManager().getItemCount();
        //Calculate the last visible photo index and compare index+1 with with the count of the items.
        //If they are the same, which means you scroll down to bottom of the RecyclerView, request new photo
        if (!mImageRequester.isLoadingData() && totalItemCount == getLastVisibleItemPosition() + 1) {
          requestPhoto();
        }
      }
    });
  }

  //Add a check to avoid having an empty screen
  @Override
  protected void onStart() {
    super.onStart();
    if (mPhotosList.size() == 0) {
      requestPhoto();
    }
  }

  //Get the index of the last visible item on the screen
  private int getLastVisibleItemPosition() {
    return mLinearLayoutManager.findLastVisibleItemPosition();
  }

  //Request a new photo
  private void requestPhoto() {
    try {
      mImageRequester.getPhoto();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //Notify the recycler adapter that an item is added
  @Override
  public void receivedNewPhoto(final Photo newPhoto) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        mPhotosList.add(newPhoto);
        mAdapter.notifyItemInserted(mPhotosList.size());
      }
    });
  }
}
