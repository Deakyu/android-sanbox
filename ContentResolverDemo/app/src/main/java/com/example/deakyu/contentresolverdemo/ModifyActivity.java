package com.example.deakyu.contentresolverdemo;

import android.Manifest;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.deakyu.contentresolverdemo.MainActivity.READ_CONTACTS_LOADER;
import static com.example.deakyu.contentresolverdemo.MainActivity.READ_CONTACTS_PERMISSION;

public class ModifyActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    public static final int WRITE_CONTACTS_PERMISSION = 1;

    private TextView contactsTextView;
    private EditText contactsEditText;
    private Button insertButton;
    private Button updateButton;
    private Button deleteButton;

    private String[] mProjection = {ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                                    ContactsContract.Contacts.CONTACT_STATUS_LABEL,
                                    ContactsContract.Contacts.HAS_PHONE_NUMBER};
    private String mSelectionClause = "";
    private String[] mSelectionArgs = {""};
    private String mSortOrder = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        initWidgets();
        executeRuntimePermission();
        getLoaderManager().initLoader(READ_CONTACTS_LOADER, null, this);
    }

    private void initWidgets() {
        contactsTextView = findViewById(R.id.contacts_text_view);
        contactsEditText = findViewById(R.id.contacts_edit_text);
        insertButton = findViewById(R.id.insert_button);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);
        insertButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String input = contactsEditText.getText().toString();
        if(v.getId() == R.id.insert_button) {
            insertContact(input);
        } else if(v.getId() == R.id.update_button) {
            updateContact(input);
        } else if(v.getId() == R.id.delete_button) {
            deleteContact(input);
        }
    }

    private void insertContact(String input) {
        if(!TextUtils.isEmpty(input)) { // TODO: Not properly working yet
            Uri mNewUri;
            ContentValues v = new ContentValues();
            v.put(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY, input);
            mNewUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, v);
            Toast.makeText(this, String.valueOf(ContentUris.parseId(mNewUri)), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Empty input not allowed", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateContact(String input) {
        String[] updateData = input.split(",");
        if(updateData.length == 2) {
            mSelectionClause = ContactsContract.RawContacts._ID + " =?";
            mSelectionArgs = new String[] {updateData[0]};

            ContentValues vals = new ContentValues();
            vals.put(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY, updateData[1]);
            getContentResolver().update(
                    ContactsContract.RawContacts.CONTENT_URI,
                    vals,
                    mSelectionClause,
                    mSelectionArgs
            );
        }
    }

    private void deleteContact(String input) {
        mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " =?";
        mSelectionArgs = new String[] {input};
        getContentResolver().delete(
                ContactsContract.RawContacts.CONTENT_URI,
                mSelectionClause,
                mSelectionArgs
        );
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if(id == READ_CONTACTS_LOADER) {
            return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI,
                    mProjection, null, null, null);
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
            contactsTextView.setText(sb.toString());
        } else {
            contactsTextView.setText("Results not found");
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {}

    // region Permission Stuff
    private void executeRuntimePermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Needs to ask permission runtime after M
            if(ContextCompat.checkSelfPermission(ModifyActivity.this, Manifest.permission.WRITE_CONTACTS)
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
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CONTACTS)) {
            new AlertDialog.Builder(this)
                    .setTitle("Contacts Permission")
                    .setMessage("This app requires contacts info permission")
                    .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(ModifyActivity.this, new String[]{Manifest.permission.WRITE_CONTACTS}, WRITE_CONTACTS_PERMISSION);
                        }
                    })
                    .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, WRITE_CONTACTS_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == WRITE_CONTACTS_PERMISSION) {
            if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // Permission denied
                Toast.makeText(this, "Bummer..", Toast.LENGTH_SHORT).show();
            } else {
                continueWithPermission();
            }
        }
    }

    private void continueWithPermission() {}
    // endregion
}
