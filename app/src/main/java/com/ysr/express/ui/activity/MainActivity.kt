package com.ysr.express.ui.activity

import android.view.View
import com.ysr.express.R
import com.ysr.express.ui.fragment.SearchFragment
import com.ysr.news.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**主页
 * Created by ysr
 * 邮箱 ysr200808@163.com
 */
class MainActivity : BaseActivity(), View.OnClickListener {


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

        ll_search.setOnClickListener(this)
        ll_post.setOnClickListener(this)
        ll_sweep.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ll_search -> {

            }
            R.id.ll_post -> {

            }
            R.id.ll_sweep -> {

            }
        }
    }

}
