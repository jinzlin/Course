package com.lin.course;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebViewActivity extends AppCompatActivity {

    WebView mWebView;

    String url = "https://www.runoob.com/";
//    String url = "https://www.runoob.com/php/php-string.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        init();


        new Thread(new Runnable() {
            @Override
            public void run() {

                test3();
            }
        }).start();

    }

    private void test3() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements1 = doc.select("[class=col middle-column-home]");
            Elements elements2 = elements1.select("[class~=^codelist codelist-desktop cate]");
            Elements elements3 = elements2.select("h2");
            List<String> list = elements3.eachText();
            Log.e("------size", "-" + list.size());
            for (int i = 0; i < list.size(); i++) {
                Elements elements4 = elements2.get(i).select("a");
                Log.e("------main", "-" + list.get(i));
                List<String> list2 = elements4.eachText();
                for (int j = 0; j < list2.size(); j++) {
                    Log.e("------title", elements4.get(j).select("h4").text() + "-" + elements4.get(j).select("strong").text());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void test2() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements1 = doc.select("[class=col middle-column]");
            final String str = elements1.get(0).text();
            final String str2 = elements1.outerHtml();
            Log.e("----------", "--" + str);
            Log.e("----------2", "--" + elements1.outerHtml());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String html = "<html>\n" +
                            "<head>\n" +
                            "<link rel=\"stylesheet\" href=\"file:///android_asset/style.css?v=1.147\" type=\"text/css\" media=\"all\" />\t\n" +
                            "</head>" + str2 + "\n" +
                            "</body>\n" +
                            "</html>";
                    mWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
//                    mWebView.loadUrl("file:///android_asset/node.html");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        mWebView = (WebView) findViewById(R.id.WebView);
        // 开启JavaScript支持
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.addJavascriptInterface(new InJavaScriptLocalObj(), "java_obj");

        // 设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放
//        mWebView.getSettings().setSupportZoom(true);

        // 设置WebView是否使用其内置的变焦机制，该机制集合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
        mWebView.getSettings().setBuiltInZoomControls(true);

        // 设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM storage API
        mWebView.getSettings().setDomStorageEnabled(true);

        // 触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
        mWebView.requestFocus();

        // 设置此属性,可任意比例缩放,设置webview推荐使用的窗口
//        mWebView.getSettings().setUseWideViewPort(true);

        // 设置webview加载的页面的模式,缩放至屏幕的大小
//        mWebView.getSettings().setLoadWithOverviewMode(true);

        // 加载链接
//        mWebView.loadUrl("https://www.runoob.com/");
//        mWebView.loadUrl("https://m.qiaocat.com/topic-618_topic/topicIndex");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // 在开始加载网页时会回调
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 拦截 url 跳转,在里边添加点击链接跳转或者操作
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // 在结束加载网页时会回调

                // 获取页面内容
                view.loadUrl("javascript:window.java_obj.showSource("
                        + "document.getElementsByTagName('html')[0].innerHTML);");


                // 获取解析<meta name="share-description" content="获取到的值">
                view.loadUrl("javascript:window.java_obj.showDescription("
                        + "document.querySelector('div[class=\"col middle-column-home\"]').innerHTML);");
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                // 加载错误的时候会回调，在其中可做错误处理，比如再请求加载一次，或者提示404的错误页面
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view,
                                                              WebResourceRequest request) {
                // 在每一次请求资源时，都会通过这个函数来回调
                return super.shouldInterceptRequest(view, request);
            }

        });
    }

    public final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            System.out.println("====1>html=" + html);
        }

        @JavascriptInterface
        public void showDescription(final String str) {
            System.out.println("====2>html=" + str);

//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    mWebView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
//                }
//            });
        }
    }
}
