package com.example.liquorshops.ui.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liquorshops.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.Executor;

import static android.app.Activity.RESULT_OK;

public class UserProfileFrag extends Fragment {

    private FirebaseAuth firebaseAuth;
    private TextView usernameTextView, emailTextView;
    private ImageView userProfileImageView;
    private SignInButton googleSingInButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        usernameTextView = view.findViewById(R.id.id_text_username);
//        emailTextView = view.findViewById(R.id.id_text_email);
        userProfileImageView = view.findViewById(R.id.id_image_user_profile);
        googleSingInButton = view.findViewById(R.id.id_button_google_singin);
        if(!usernameTextView.getText().toString().equals("User"))
            googleSingInButton.setVisibility(View.GONE);
        googleSingInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });
    }

    private void googleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        Intent signInIntent = GoogleSignIn.getClient(getActivity(), gso).getSignInIntent();
        startActivityForResult(signInIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (resultCode == RESULT_OK)
            if (requestCode == 1) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account);
                } catch (ApiException e) {
                    // Google Sign In failed, update UI appropriately
                    Log.e("some ", "Google sign in failed", e);
                    // ...
                }
            }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {

        final AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener( getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getContext(), "Successful Sign In", Toast.LENGTH_SHORT).show();
                            Log.e("user details ", acct.getEmail() + acct.getPhotoUrl() + acct.getGivenName());
                            googleSingInButton.setVisibility(View.GONE);
                            usernameTextView.setText(acct.getGivenName());
                            emailTextView.setText(acct.getEmail());
                            Glide.with(getContext()).load(acct.getPhotoUrl()).into(userProfileImageView);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getContext(), "Unsuccessful Sign In", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
