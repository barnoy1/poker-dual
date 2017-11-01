package com.game.pokerdual.utils.AssetLoader.StorageDownableContent;

import android.util.Pair;

import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import io.reactivex.functions.Consumer;

/**
 * The type App downable storage item.
 */
public class AppStorageItem implements StorageItem {


    private static final String TAG = AppStorageItem.class.getSimpleName();
    private Consumer<? super FileDownloadTask.TaskSnapshot> onNext;
    private Consumer<? super Exception> onError;
    private Consumer<? super Pair<String, File>> onComplete;

    private String url;
    private String relPath;

    /**
     * Instantiates a new App downable storage item.
     *
     * @param storageItemProperties the downable storage item properties
     */
    public AppStorageItem(StorageItemProperties storageItemProperties) {
        this.url = storageItemProperties.url;
        this.relPath = storageItemProperties.relPath;

        this.onComplete = storageItemProperties.onComplete;
        this.onError = storageItemProperties.onError;
        this.onNext = storageItemProperties.onNext;
    }

    @Override
    public Void call() throws Exception {
        FirebaseStorage storage = FirebaseStorage.getInstance();

        String fullUrl = String.format("%s/%s", url, relPath);
        StorageReference fileRef = storage.getReferenceFromUrl(fullUrl);

        try {

            String[] fileNameArr = relPath.split("\\.");
            String fileName = fileNameArr[0];
            String postfix = fileNameArr[1];
            final File localFile = File.createTempFile(fileName, postfix);

            fileRef.getFile(localFile).addOnSuccessListener(taskSnapshot -> {

                try {
                    AppStorageItem.this.onComplete.accept(new Pair<>(relPath, localFile));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).addOnFailureListener(exception -> {

                try {
                    AppStorageItem.this.onError.accept(exception);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).addOnProgressListener(taskSnapshot -> {

                try {
                    AppStorageItem.this.onNext.accept(taskSnapshot);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

}
