package com.zxy.zxymvvm.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zxy.zxymvvm.R
import com.zxy.zxymvvm.databinding.AdapterTestBinding

/**
 * Created by zsf on 2021/2/7 19:10
 * ******************************************
 * * DataBinding Adapter
 * ******************************************
 */
class TestAdapter: BaseQuickAdapter<String,BaseViewHolder>(R.layout.adapter_test) {
    override fun convert(holder: BaseViewHolder, item: String) {
        val binding = holder.getBinding<AdapterTestBinding>()
        binding.let {
            it?.value=item
            it?.executePendingBindings()
        }
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<AdapterTestBinding>(viewHolder.itemView)
    }
}