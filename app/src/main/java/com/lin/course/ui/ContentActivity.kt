package com.lin.course.ui

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import com.lin.course.R
import com.lin.course.base.BaseActivity
import com.lin.course.bean.model.DbModel
import com.lin.course.utils.DataManager
import kotlinx.android.synthetic.main.activity_content.*

/**
 * Author by ljz
 * PS:
 */
class ContentActivity : BaseActivity() {

    lateinit var link: String
    lateinit var title: String

    override fun initResource(): Int {
        return R.layout.activity_content
    }

    override fun initData() {
        link = intent.getStringExtra("link")
        title = intent.getStringExtra("title")

        setToolbar(toolbar, true, title)

        initWeb()
        val body = DataManager.getBodyData(this, link)
        refresh(body)
    }

    private fun refresh(body: String?) {
        System.out.print(body)
        val html = "<html>\n" +
                "<head>\n" +
                "<link rel=\"stylesheet\" href=\"file:///android_asset/style.css?v=1.147\" type=\"text/css\" media=\"all\" />" +
                "</head>" +
                "<body>" +
                "<div class=\"container mian\">" +
                "<div class=\"row\">" +
                "<div class=\"col middle-column\">" +
                "<div class=\"article\">" +
                "<div class=\"article-body\">" +
                "<div class=\"article-intro\" id=\"content\">" + body + "</div></div></div></div></div></div>" + "" +
                "<script src=\"file:///android_asset/main.js?v=1.188\"></script>\n" +
                "<script src=\"//static.runoob.com/assets/libs/hl/run_prettify.js\"></script>" +
                "</body>\n" +
                "</html>"



        webContent.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)
//        webContent.loadUrl("https://www.runoob.com/jquery/jquery-hide-show.html")
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWeb() {
//        // 开启JavaScript支持
//        webContent.settings.javaScriptEnabled = true
//        // 设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放
//        webContent.settings.setSupportZoom(false)
//        // 设置WebView是否使用其内置的变焦机制，该机制集合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
//        webContent.settings.builtInZoomControls = true
//        // 设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM storage API
//        webContent.settings.domStorageEnabled = true
//        // 触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
//        webContent.requestFocus()


        webContent.settings.setDefaultTextEncodingName("utf-8")
        //启用数据库
        webContent.settings.setDatabaseEnabled(true)
        val dir = this.getDir("database", Context.MODE_PRIVATE).getPath()
        //启用地理定位
        webContent.settings.setGeolocationEnabled(true)
        //设置定位的数据库路径
        webContent.settings.setGeolocationDatabasePath(dir)
        //最重要的方法，一定要设置，这就是出不来的主要原因
        //配置权限（同样在WebChromeClient中实现）
        webContent.settings.setDomStorageEnabled(true) // 开启 DOM storage API 功能
        webContent.settings.setJavaScriptEnabled(true)  //支持js
        webContent.settings.setAllowFileAccess(true)  //设置可以访问文件
        webContent.settings.setUseWideViewPort(false)  //将图片调整到适合webview的大小
        webContent.settings.setSupportZoom(true)  //支持缩放
        webContent.settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN) //支持内容重新布局
        webContent.settings.setNeedInitialFocus(true) //当webview调用requestFocus时为webview设置节点
        webContent.settings.setJavaScriptCanOpenWindowsAutomatically(true) //支持通过JS打开新窗口
        webContent.settings.setLoadWithOverviewMode(true) // 缩放至屏幕的大小
        webContent.settings.setLoadsImagesAutomatically(true)  //支持自动加载图片
        webContent.settings.setCacheMode(WebSettings.LOAD_NO_CACHE) // 不加载缓存内容
        webContent.settings.setTextZoom(100) // 字体大小不跟随系统
    }

    override fun onDbSuccess(tag: Int, t: DbModel) {
        super.onDbSuccess(tag, t)
        refresh(t.sectionEntity!!.body)
    }


}
