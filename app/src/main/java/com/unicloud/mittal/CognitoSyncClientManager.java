package com.unicloud.mittal;

/**
 * Created by Mittal on 26-Jan-15.
 */

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.amazonaws.mobileconnectors.s3.transfermanager.*;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.cognito.CognitoSyncManager;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import android.os.StrictMode;

public class CognitoSyncClientManager extends AsyncTask<Void, Void, Void> {
    private Context mContext;
    public CognitoSyncClientManager(Context context) {
        mContext = context;
    }

    /**

     * account id and pool id associated with the app see the readme for details
     * on what to fill these fields in with
     */
    private static final String AWS_ACCOUNT_ID = "223342001177";
    private static final String IDENTITY_POOL_ID = "eu-west-1:2527c55f-bc60-4015-a3ae-8124811ac231";

    /**
     * the role arn to be assumed. You can provide a role arn for unauthorized
     * user and one for authorized.
     */
    private static final String UNAUTH_ROLE_ARN = "arn:aws:iam::223342001177:role/Cognito_UniCloudAuth_DefaultRole";
    private static final String AUTH_ROLE_ARN = "arn:aws:iam::223342001177:role/Cognito_UniCloudAuth_DefaultRole";

    private static CognitoSyncManager client;
    private static CognitoCachingCredentialsProvider provider;

    /* static way to get context */
//    private static Context context;

    /**
     * Initializes the CognitoClient. This must be called before getInstance().
     *
//     * @param context a context of the app
     */
//    public static void init(Context context) {
//        provider = new CognitoCachingCredentialsProvider(context,
//                AWS_ACCOUNT_ID, IDENTITY_POOL_ID, UNAUTH_ROLE_ARN, AUTH_ROLE_ARN,Regions.EU_WEST_1);
//
//        client = new CognitoSyncManager(context, IDENTITY_POOL_ID, Regions.EU_WEST_1, provider);
//        Log.d("loginWithGooglePlus", "my ID is ");

//    }

    @Override
    protected Void doInBackground(Void... params) {
        provider = new CognitoCachingCredentialsProvider(mContext,
                AWS_ACCOUNT_ID, IDENTITY_POOL_ID, UNAUTH_ROLE_ARN, AUTH_ROLE_ARN,Regions.EU_WEST_1);

        client = new CognitoSyncManager(mContext, IDENTITY_POOL_ID, Regions.EU_WEST_1, provider);
        String BUCKET_NAME = "uni-cloud";
//        String key = "";
        TransferManager transferManager = new TransferManager(provider);

//        AmazonS3Client mClient = new AmazonS3Client(provider);
//        List<S3ObjectSummary> summaries = mClient.listObjects(BUCKET_NAME).getObjectSummaries();
//        String[] keys = new String[summaries.size()];
//        for(int i = 0; i < keys.length; i++) {
//            keys[i] = summaries.get(i).getKey();
//        }
//        Log.i("keys", keys[0]);


        Log.e("Cognito Provider ID","Data " + provider.getIdentityId());

//        File file = new File(Environment.getExternalStorageDirectory()
//                +File.separator
//                +"myDirectory" //folder name
//                +File.separator
//                +"myFile");
//        Download download = transferManager.download(BUCKET_NAME, keys[0], file);

        return null;
    }

    /**
     * Sets the login so that you can use authorized identity. This requires a
     * network request. Please call it in a background thread.
     *
     * @param providerName the name of 3rd identity provider
     * @param token openId token
     */
    public static void addLogins(String providerName, String token) {
//        Log.i("1", "inside add logins");
        if (client == null) {
//            Log.i("2", "inside add logins");
            throw new IllegalStateException("client not initialized yet");
        }
//        Log.i("3", "inside add logins");
        Map<String, String> logins = provider.getLogins();
//        Log.i("4", "inside add logins");
        if (logins == null) {
//            Log.i("5", "inside add logins");
            logins = new HashMap<String, String>();
//            Log.i("6", "inside add logins");
        }
//        Log.i("7", "inside add logins");
        logins.put(providerName, token);
//        Log.i("8", "inside add logins");
        provider.setLogins(logins);
//        Log.i("9", "inside add logins");
    }

    /**
     * Gets the singleton instance of the CognitoClient. init() must be call
     * prior to this.
     *
     * @return an instance of CognitoClient
     */
    public static CognitoSyncManager getInstance() {
        if (client == null) {
            throw new IllegalStateException("client not initialized yet");
        }
        return client;
    }
}
