package com.game.pokerdual.utils.AssetLoader.StorageDownableContent;

import android.util.Pair;

import com.google.firebase.storage.FileDownloadTask;

import java.io.File;

import io.reactivex.functions.Consumer;

public class StorageItemPropertiesBuilder {

    private Consumer<? super Pair<String, File>> onComplete;
    private Consumer<? super FileDownloadTask.TaskSnapshot> onNext;
    private Consumer<? super Exception> onError;

    private String relPath;
    private String url;



    public StorageItemPropertiesBuilder RelPath(String relPath ) {
        this.relPath = relPath;
        return this;
    }

    public StorageItemPropertiesBuilder Url(String url) {
        this.url = url;
        return this;
    }

    public StorageItemPropertiesBuilder OnError(Consumer<? super Exception> onError) {
        this.onError = onError;
        return this;
    }

    public StorageItemPropertiesBuilder OnComplete(Consumer<? super Pair<String, File> > onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    public StorageItemPropertiesBuilder OnNext(Consumer<? super FileDownloadTask.TaskSnapshot> onNext) {
        this.onNext = onNext;
        return this;
    }

    public StorageItemProperties build () {
        StorageItemProperties storageItemProperties = new StorageItemProperties();
        storageItemProperties.onNext = this.onNext;
        storageItemProperties.onError = this.onError;
        storageItemProperties.onComplete = this.onComplete;

        storageItemProperties.relPath = this.relPath;
        storageItemProperties.url = this.url;

        return storageItemProperties;
    }
}