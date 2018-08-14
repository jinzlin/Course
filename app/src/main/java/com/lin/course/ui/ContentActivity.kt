package com.lin.course.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lin.course.R
import com.lin.course.utils.DataManager
import kotlinx.android.synthetic.main.activity_content.*

/**
 * Author by ljz
 * PS:
 */
class ContentActivity : AppCompatActivity() {

    lateinit var link: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        link = intent.getStringExtra("link")

        Thread(Runnable {

            val body = DataManager.getBodyData(link)

            Log.e("body--", body+"11")
            val html = "<html>\n" +
                    "<head>\n" +
                    "<link rel=\"stylesheet\" href=\"file:///android_asset/style.css?v=1.147\" type=\"text/css\" media=\"all\" />\t\n" +
                    "</head>" + body + "\n" +
                    "</body>\n" +
                    "</html>"
            runOnUiThread {
                webContent.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)

                // 开启JavaScript支持
                webContent.getSettings().setJavaScriptEnabled(true)

                // 设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放
//        mWebView.getSettings().setSupportZoom(true);

                // 设置WebView是否使用其内置的变焦机制，该机制集合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
                webContent.getSettings().setBuiltInZoomControls(true)

                // 设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM storage API
                webContent.getSettings().setDomStorageEnabled(true)

                // 触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
                webContent.requestFocus()
            }

        }).start()
    }
}
