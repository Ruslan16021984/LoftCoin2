package com.carbit3333333.loftcoin2.util;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import timber.log.Timber;

public class DebugTree extends Timber.DebugTree {
    @Override
    protected void log(int priority, String tag, @NotNull String message, Throwable t) {
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        final StackTraceElement stc = stackTrace[5];
        super.log(priority, tag, String.format(Locale.US, "[%s] %s.%s(%s:%d) : %s",
                Thread.currentThread().getName(),
                stc.getClassName(),
                stc.getMethodName(),
                stc.getFileName(),
                stc.getLineNumber(),
                message), t);
    }
}
