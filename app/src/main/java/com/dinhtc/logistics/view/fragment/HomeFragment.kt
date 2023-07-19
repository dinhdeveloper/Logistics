package com.dinhtc.logistics.view.fragment

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dinhtc.logistics.R
import com.dinhtc.logistics.adapter.TableViewAdapter
import com.dinhtc.logistics.common.view.BaseFragment
import com.dinhtc.logistics.databinding.FragmentHomeBinding
import com.dinhtc.logistics.model.LogisticInfoModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        const val LOGISTIC_MODEL = "LOGISTIC_MODEL"
    }

    private val logisticListChecked: MutableList<LogisticInfoModel> = mutableListOf()

    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override fun viewCreatedFragment() {
        val tableViewAdapter = TableViewAdapter()

        tableViewAdapter.submitList(logisticInfoModels)

        viewBinding.recyclerViewMovieList.layoutManager = LinearLayoutManager(context)
        viewBinding.recyclerViewMovieList.setHasFixedSize(true)
        viewBinding.recyclerViewMovieList.adapter = tableViewAdapter

        tableViewAdapter.setOnClickItem(object : TableViewAdapter.OnItemClickListener{
            override fun onClickItem(logisticsModel: LogisticInfoModel?) {
                if (logisticsModel != null) {
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment,
                    bundleOf(LOGISTIC_MODEL to logisticsModel)
                    )
                }
            }

            override fun onClickAllItemEnable() {
                for (item in logisticInfoModels){
                    item.checkedOrder = true
                }
                logisticListChecked.addAll(logisticInfoModels)
                tableViewAdapter.submitList(logisticInfoModels)
                tableViewAdapter.notifyDataSetChanged()
            }

            override fun onClickAllItemDisable() {
                for (item in logisticInfoModels){
                    item.checkedOrder = false
                }
                logisticListChecked.addAll(logisticInfoModels)
                tableViewAdapter.submitList(logisticInfoModels)
                tableViewAdapter.notifyDataSetChanged()
            }
        })
        actionView()
    }

    private fun actionView() {

    }

    private val logisticInfoModels = ArrayList<LogisticInfoModel>().apply {
        add(
            LogisticInfoModel(
                1,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "1",false
            )
        )
        add(
            LogisticInfoModel(
                2,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "2",false
            )
        )
        add(
            LogisticInfoModel(
                3,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "3",false
            )
        )
        add(
            LogisticInfoModel(
                4,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "4",false
            )
        )
        add(
            LogisticInfoModel(
                5,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "5",false
            )
        )
        add(
            LogisticInfoModel(
                6,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "6",false
            )
        )
        add(
            LogisticInfoModel(
                7,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "7",false
            )
        )
        add(
            LogisticInfoModel(
                8,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "8",false
            )
        )
        add(
            LogisticInfoModel(
                9,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "9",false
            )
        )
        add(
            LogisticInfoModel(
                10,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "10",false
            )
        )
        add(
            LogisticInfoModel(
                11,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "11",false
            )
        )
        add(
            LogisticInfoModel(
                12,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "12",false
            )
        )
        add(
            LogisticInfoModel(
                13,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "13",false
            )
        )
        add(
            LogisticInfoModel(
                14,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "14",false
            )
        )
        add(
            LogisticInfoModel(
                15,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "15",false
            )
        )
        add(
            LogisticInfoModel(
                16,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "16",false
            )
        )
        add(
            LogisticInfoModel(
                17,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "17",false
            )
        )
        add(
            LogisticInfoModel(
                18,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "18",false
            )
        )
        add(
            LogisticInfoModel(
                19,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "19",false
            )
        )
        add(
            LogisticInfoModel(
                20,
                "1237965457",
                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
                "Trần Cảnh Dinh",
                "Đã cân",
                "20",false
            )
        )

    }
}