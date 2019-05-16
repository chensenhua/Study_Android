package com.sen.study_android.okHttp;

import com.sen.study_android.utils.Slog;
import com.sen.study_android.utils.Tlog;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class OkHttpManager {
    private static final String tag = OkHttpManager.class.getSimpleName();

    public static OkHttpManager mInstance = new OkHttpManager();

    private OkHttpClient client;


    private OkHttpManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.eventListener(new EventListener() {
            @Override
            public void callEnd(@NotNull Call call) {
                super.callEnd(call);
                Slog.e(tag,"callend-1->"+call.toString());

            }

            @Override
            public void callStart(@NotNull Call call) {
                super.callStart(call);
                Slog.e(tag,"callStart-1->"+call.toString());

            }
        });
        builder.eventListener(new EventListener() {
            @Override
            public void callEnd(@NotNull Call call) {
                super.callEnd(call);
                Slog.e(tag,"callend-2->"+call.toString());

            }

            @Override
            public void callStart(@NotNull Call call) {
                super.callStart(call);
                Slog.e(tag,"callStart-2->"+call.toString());

            }
        });
        client = builder.build();

    }

    public static OkHttpManager getInstance() {
        return mInstance;
    }

    public void testGet() throws IOException {
        String baseUrl = "https://api.douban.com/v2/movie/top250";
        Request request = new Request.Builder()
                .url(baseUrl)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Slog.i(tag, "request failed");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Slog.i(tag, "respondse -->" + response.toString());
            }
        });


    }


    public void testPost() {

    }


    public void testWebSocket(){
        client.newWebSocket(new Request.Builder().url("").build(), new WebSocketListener() {
            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                super.onMessage(webSocket, text);
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                super.onOpen(webSocket, response);
            }
        });
    }
}
