package cn.vi1zen.transfereeimage.activity

import android.content.Intent
import android.view.View
import cn.vi1zen.transfereeimage.R

class MainActivity : BaseActivity() {
    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        findViewById<View>(R.id.btn_complex_demo).setOnClickListener { v: View? -> startActivity(Intent(this@MainActivity, ComplexDemoActivity::class.java)) }
        findViewById<View>(R.id.btn_friends_res).setOnClickListener { v: View? -> startActivity(Intent(this@MainActivity, FriendsCircleActivity::class.java)) }
        findViewById<View>(R.id.btn_universal_local).setOnClickListener { v: View? -> startActivity(Intent(this@MainActivity, LocalImageActivity::class.java)) }
        findViewById<View>(R.id.btn_recycler).setOnClickListener { v: View? -> startActivity(Intent(this@MainActivity, HeaderRecyclerActivity::class.java)) }
        findViewById<View>(R.id.btn_clear_universal).setOnClickListener { v: View? ->
            transferee.clear()
        }
    }

    override fun testTransferee() {}
}