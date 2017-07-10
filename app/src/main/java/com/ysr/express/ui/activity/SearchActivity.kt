package com.ysr.express.ui.activity

import com.ysr.express.R
import com.ysr.news.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*

/**查询
 * Created by ysr on 2017/6/30 11:24.
 * 邮箱 ysr200808@163.com
 */
class SearchActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun initPresenter() {

    }

    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.text_null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        as_srl.isEnabled=false
    }


}
