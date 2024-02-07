package com.umc.coumo.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.umc.coumo.R
import com.umc.coumo.databinding.ActivityMainBinding
import com.umc.coumo.domain.type.TabType
import com.umc.coumo.domain.viewmodel.MainViewModel
import com.umc.coumo.presentation.adapter.MainFragmentAdapter
import com.umc.coumo.utils.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val buttons = listOf(binding.btnHome, binding.btnCoupon, binding.btnCommunity, binding.btnAccount)

        val adapter = MainFragmentAdapter(this)
        binding.viewpager.adapter = adapter
        binding.viewpager.isUserInputEnabled = false //스와이프 방지

        setNaviButton()
        setObserver()

        setTestLogin()
    }

    //TODO(테스트 코드)
    private fun setTestLogin() {
        //viewModel.postJoin()
        viewModel.postLogin()
    }


    private fun setObserver () {
        viewModel.currentPageIndex.observe(this) {
            if (it == TabType.COUPON)
                binding.viewpager.setCurrentItem(1, true)
        }
    }

    private fun setNaviButton() {
        binding.btnHome.setOnClickListener {
            binding.viewpager.setCurrentItem(0, true)
            viewModel.changePageIndex(TabType.HOME)
        }

        binding.btnCoupon.setOnClickListener {
            binding.viewpager.setCurrentItem(1, true)
            viewModel.changePageIndex(TabType.COUPON)
        }

        binding.btnCommunity.setOnClickListener {
            binding.viewpager.setCurrentItem(2, true)
            viewModel.changePageIndex(TabType.COMMUNITY)
        }

        binding.btnAccount.setOnClickListener {
            binding.viewpager.setCurrentItem(3, true)
            viewModel.changePageIndex(TabType.ACCOUNT)
        }
    }
}