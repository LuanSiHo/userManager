package com.hosiluan.usermanager;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.messaging.FirebaseMessaging;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SocialNetworkActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 001;

    private TextView userInfoTextView;

    private CallbackManager callbackManager;

    private Button loginButton, shareButton;

    private GoogleApiClient mGoogleApiClient;

    private TwitterAuthClient twitterAuthClient;
    private FirebaseMessaging firebaseMessaging;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().subscribeToTopic("JV-IT");

        // init facebooksdk
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create(); // config for facebook

        // init twitter
        Twitter.initialize(this);
        twitterAuthClient = new TwitterAuthClient(); //config for twitter


        // init for google
        configureForGoogleLogin();

        setContentView(R.layout.activity_social_network);
        setView();
        setEvent();

    }

    private void configureForGoogleLogin() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
    }

    private void setView() {

        loginButton = (Button) findViewById(R.id.btn_login);
        shareButton = (Button) findViewById(R.id.btn_share);
        userInfoTextView = (TextView) findViewById(R.id.tv_user_info);
    }

    private void setEvent() {

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SocialNetworkActivity.this);
                View view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_social_network_dialog, null);
                builder.setView(view1);

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getWindow().setGravity(Gravity.BOTTOM);


                // login with facebook
                Button loginWithFBButton = (Button) view1.findViewById(R.id.custom_fb_login_button);
                loginWithFBButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoginManager.getInstance().logInWithReadPermissions(SocialNetworkActivity.this, Arrays.asList("public_profile"));

                        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                Log.d("Luan", "success custom");
                                setFacebookLoginResult();
                            }

                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onError(FacebookException error) {

                            }
                        });
                        alertDialog.dismiss();
                        return;
                    }
                });


                // login with google
                Button loginWithGoogleButton = (Button) view1.findViewById(R.id.custom_google_login_button);
                loginWithGoogleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signInByGoogleAccount();
                        alertDialog.dismiss();
                        return;
                    }
                });


                //login with twitter
                Button loginWithTwitterButton = (Button) view1.findViewById(R.id.custom_twitter_login_button);
                loginWithTwitterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        twitterAuthClient.authorize(SocialNetworkActivity.this, new Callback<TwitterSession>() {
                            @Override
                            public void success(Result<TwitterSession> result) {
                                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();

                                TwitterAuthToken authToken = session.getAuthToken();
                                String token = authToken.token;
                                String secret = authToken.secret;
                                Log.d("Luan", session.getUserId() + " " + session.getUserName());
                                userInfoTextView.setText(session.getUserName());
                            }

                            @Override
                            public void failure(TwitterException exception) {

                            }
                        });
                        alertDialog.dismiss();
                        return;
                    }
                });

            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SocialNetworkActivity.this);

                View view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_social_network_dialog, null);

                builder.setView(view1);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getWindow().setGravity(Gravity.BOTTOM);

                Button shareToFaceBookButton = (Button) view1.findViewById(R.id.custom_fb_login_button);
                shareToFaceBookButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SocialNetworkActivity.this, "share to fb", Toast.LENGTH_SHORT).show();
                        ShareLinkContent content = new ShareLinkContent.Builder().setContentUrl(Uri.parse("https://developers.facebook.com")).setContentDescription("some description").build();
                        ShareDialog shareDialog = new ShareDialog(SocialNetworkActivity.this);
                        shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
                        alertDialog.dismiss();
                        return;
                    }
                });

                Button shareToGoogleButton = (Button) view1.findViewById(R.id.custom_google_login_button);
                shareToGoogleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SocialNetworkActivity.this, "share to google", Toast.LENGTH_SHORT).show();
                        Intent shareIntent = new PlusShare.Builder(SocialNetworkActivity.this).setType("text/plain").setText("Welcome to the Google+ platform.").setContentUrl(Uri.parse("https://developers.google.com/+/")).getIntent();

                        startActivityForResult(shareIntent, 0);
                        alertDialog.dismiss();
                        return;

                    }
                });

                Button shareToTwitterButton = (Button) view1.findViewById(R.id.custom_twitter_login_button);
                shareToTwitterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SocialNetworkActivity.this, "share to twitter", Toast.LENGTH_SHORT).show();

                        TweetComposer.Builder builder = new TweetComposer.Builder(SocialNetworkActivity.this).text("just setting up my Twitter Kit.");
                        builder.show();
                        alertDialog.dismiss();
                        return;
                    }
                });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //callback for facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // callback for google
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            Log.d("Luan", "i;m here");

            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                Log.d("Luan", acct.getDisplayName() + " / " + acct.getEmail());
                userInfoTextView.setText(acct.getDisplayName());
            }
        }

        twitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }

    private void setFacebookLoginResult() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("Luan", response.getJSONObject().toString());
                try {
                    String userName = response.getJSONObject().getString("name");
                    Log.d("Luan", userName);
                    userInfoTextView.setText(userName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        graphRequest.executeAsync();
    }


    /**
     * Connection fail for log in by google
     *
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("Luan", connectionResult + " fail");
    }

    private void signInByGoogleAccount() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.d("Luan", "success " + mGoogleApiClient.isConnected());
    }
}
