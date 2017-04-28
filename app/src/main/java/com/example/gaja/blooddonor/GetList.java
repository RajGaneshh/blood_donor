package com.example.gaja.blooddonor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by gaja on 4/24/2017.
 */

public class GetList extends Activity {

        private static final String TAG = "PostListFragment";

        // [START define_database_reference]
        private DatabaseReference mDatabase;
        // [END define_database_reference]
        private ProgressDialog mProgressDialog;

      /*  private FirebaseRecyclerAdapter<Post, ProjectViewHolder> mAdapter;
        private RecyclerView mRecycler;
        private LinearLayoutManager mManager;

        public PostListFragment() {}

        @Override
        public void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.id.);

            // [START create_database_reference]
            mDatabase = FirebaseDatabase.getInstance().getReference();
            // [END create_database_reference]

            mRecycler = (RecyclerView) findViewById(R.id.messages_list);
            mRecycler.setHasFixedSize(true);

            return rootView;
        }



        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            // Set up Layout Manager, reverse layout
            mManager = new LinearLayoutManager(getActivity());
            mManager.setReverseLayout(true);
            mManager.setStackFromEnd(true);
            mRecycler.setLayoutManager(mManager);

            // Set up FirebaseRecyclerAdapter with the Query
            Query postsQuery = getQuery(mDatabase);
            mAdapter = new FirebaseRecyclerAdapter<Post, ProjectViewHolder>(Post.class, R.layout.item_post,
                    ProjectViewHolder.class, postsQuery) {
                @Override
                protected void populateViewHolder(final ProjectViewHolder viewHolder, final Post model, final int position) {
                    final DatabaseReference postRef = getRef(position);

                    // Set click listener for the whole post view
                    final String postKey = postRef.getKey();
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Launch PostDetailActivity
                            Intent intent = new Intent(getActivity(), ProjectDetails.class);
                            intent.putExtra(ProjectDetails.EXTRA_POST_KEY, postKey);
                            startActivity(intent);
                        }
                    });
                    viewHolder.bindToPost(model);
                }
            };
            mRecycler.setAdapter(mAdapter);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if (mAdapter != null) {
                mAdapter.cleanup();
            }
        }



        public String getUid() {
            return FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        public abstract Query getQuery(DatabaseReference databaseReference);
    }*/



}
