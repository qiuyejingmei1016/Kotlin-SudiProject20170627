package com.ysr.express.ui.activity

import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import com.ysr.express.R
import com.ysr.express.ui.fragment.SearchFragment
import com.ysr.news.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**主页
 * Created by ysr
 * 邮箱 ysr200808@163.com
 */
class MainActivity : BaseActivity(), View.OnClickListener {
    //这样玩是找死的
    val color_black: Int = 0xffffffff as Int
    /**
     * Integer
     * Min_Value=0x80000000
     * Max_Value=0x7fffffff
     */
    var searchFragment: SearchFragment? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter() {

    }

    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.text_null)
        searchFragment = SearchFragment()
        val ll_search = findViewById(R.id.ll_search) as LinearLayout
        ll_search.setOnClickListener(this)
        ll_post.setOnClickListener(this)
        ll_sweep.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ll_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }
            R.id.ll_post -> {
                startActivity(Intent(this, PostActivity::class.java))
            }
            R.id.ll_sweep -> {
                startActivity(Intent(this, SweepActivity::class.java))
            }
        }
    }

}
