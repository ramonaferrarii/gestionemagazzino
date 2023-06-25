package com.example.gestionemagazzino.fragments;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContactProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.contacts";
    private static final String BASE_PATH = "contacts";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final int CONTACTS = 1;
    private static final int CONTACT_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, CONTACTS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", CONTACT_ID);
    }

    private ContentResolver resolver;

    @Override
    public boolean onCreate() {
        resolver = getContext().getContentResolver();
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(ContactsContract.Contacts.CONTENT_URI.getLastPathSegment());

        switch (uriMatcher.match(uri)){
            case CONTACTS:
                break;
            case CONTACT_ID:
                queryBuilder.appendWhere(ContactsContract.Contacts._ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Invalid URI: " + uri);
        }

        Cursor cursore = resolver.query(uri, projection, selection, selectionArgs, sortOrder);
        return cursore;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                return ContactsContract.Contacts.CONTENT_TYPE;
            case CONTACT_ID:
                return ContactsContract.Contacts.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Invalid URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return resolver.insert(ContactsContract.Contacts.CONTENT_URI, values);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return resolver.delete(ContactsContract.Contacts.CONTENT_URI, selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return resolver.update(ContactsContract.Contacts.CONTENT_URI, values, selection, selectionArgs);
    }
}