package com.example.deakyu.contentresolverdemo;

import android.Manifest;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.UserDictionary;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {

    public static final int READ_CONTACTS_PERMISSION = 1;
    public static final int READ_CONTACTS_LOADER = 1;

    private TextView wordsTextView;
    private Button loadContactsButton;
    private Button goToModifyActivityButton;
    private String[] mProjection = {ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                                    ContactsContract.Contacts.CONTACT_STATUS,
                                    ContactsContract.Contacts.HAS_PHONE_NUMBER};
    private String mSelectionClause = "";
    private String[] mSelectionArgs = {""};
    private String mSortOrder = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
    private boolean firstLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordsTextView = findViewById(R.id.words_text_view);
        loadContactsButton = findViewById(R.id.load_data_button);
        goToModifyActivityButton = findViewById(R.id.go_to_modify_activity_button);
        loadContactsButton.setOnClickListener(this);
        goToModifyActivityButton.setOnClickListener(this);
        executeRuntimePermission();
    }

    private void continueWithPermission() { }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.load_data_button) {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
            if(firstLoad) {
                getLoaderManager().initLoader(READ_CONTACTS_LOADER, null, this);
                firstLoad = false;
            } else getLoaderManager().restartLoader(READ_CONTACTS_LOADER, null, this);
        } else if(v.getId() == R.id.go_to_modify_activity_button) {
            Intent intent = new Intent(MainActivity.this, ModifyActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if(id == READ_CONTACTS_LOADER) {
            return new CursorLoader(MainActivity.this,
                                    ContactsContract.Contacts.CONTENT_URI,
                                    mProjection,
                                    null,
                                    null,
                                    null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(cursor != null && cursor.getCount() > 0) {
            StringBuilder sb = new StringBuilder();
            while(cursor.moveToNext()) {
                sb.append(cursor.getString(0) + "," + cursor.getString(1) + "," + cursor.getString(2) + "\n");
            }
            wordsTextView.setText(sb.toString());
        } else {
            wordsTextView.setText("No result found");
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {}

    // region Permission Stuff
    private void executeRuntimePermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Needs to ask permission runtime after M
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestStoragePermission();
            } else {
                continueWithPermission();
            }
        } else {
            continueWithPermission();
        }
    }

    private void requestStoragePermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
            new AlertDialog.Builder(this)
                    .setTitle("Contacts Permission")
                    .setMessage("This app requires contacts info permission")
                    .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_PERMISSION);
                        }
                    })
                    .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == READ_CONTACTS_PERMISSION) {
            if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // Permission denied
                Toast.makeText(this, "Bummer..", Toast.LENGTH_SHORT).show();
            } else {
                continueWithPermission();
            }
        }
    }
    // endregion
}
