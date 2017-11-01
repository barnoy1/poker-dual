package com.game.pokerdual.utils.AssetLoader.StorageDownableContent;

import android.util.Pair;

import com.google.firebase.storage.FileDownloadTask;

import java.io.File;

import io.reactivex.functions.Consumer;

public class StorageItemProperties {

    public Consumer<? super FileDownloadTask.TaskSnapshot> onNext;
    public Consumer<? super Exception> onError;
    public Consumer<? super Pair<String, File>> onComplete;
    public String relPath;
    public String url;

    

}
