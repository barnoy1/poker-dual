/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.game.pokerdual.utils;

import com.game.pokerdual.BuildConfig;

import java.text.MessageFormat;

import timber.log.Timber;

/**
 * Created by amitshekhar on 15/02/17.
 */

public class AppLogger {
    private static final int STACK_TRACE_FRAME_INDEX = 4;

    public static String AssembleMessage(String message)
    {
        StackTraceElement currentStackTraceElement =
                Thread.currentThread().getStackTrace()[STACK_TRACE_FRAME_INDEX];

        String fullClassName = currentStackTraceElement.getClassName();
        int index = fullClassName.lastIndexOf(".");
        String classSimpleName = fullClassName.substring(index + 1);

        String methodName = currentStackTraceElement.getMethodName();
        String fileName = currentStackTraceElement.getFileName();
        int lineNumber = currentStackTraceElement.getLineNumber();

        return MessageFormat.format("[ {0} - {1}#{2} line {3} ] => {4}",
                fileName, classSimpleName, methodName, lineNumber, message);

    }
    public static void init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }

    public static void d(String msg, Object... objects) {
        String entry = AssembleMessage(msg);
        Timber.d(entry, objects);
    }

    public static void d(Throwable throwable, String msg, Object... objects) {
        String entry = AssembleMessage(msg);
        Timber.d(throwable, entry, objects);
    }
    public static void i(String msg, Object... objects) {
        String entry = AssembleMessage(msg);
        Timber.i(entry, objects);
    }

    public static void i(Throwable throwable, String msg, Object... objects) {
        String entry = AssembleMessage(msg);
        Timber.i(throwable, entry, objects);
    }

    public static void w(String msg, Object... objects) {
        String entry = AssembleMessage(msg);
        Timber.w(entry, objects);
    }

    public static void w(Throwable throwable, String msg, Object... objects) {
        String entry = AssembleMessage(msg);
        Timber.w(throwable, entry, objects);
    }

    public static void e(String msg, Object... objects) {
        String entry = AssembleMessage(msg);
        Timber.e(entry, objects);
    }

    public static void e(Throwable throwable, String msg, Object... objects) {
        String entry = AssembleMessage(msg);
        Timber.e(throwable, entry, objects);
    }

}
