package com.dinhtc.logistics.view.fragment

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dinhtc.logistics.R
import com.dinhtc.logistics.adapter.TableViewAdapter
import com.dinhtc.logistics.common.view.BaseFragment
import com.dinhtc.logistics.databinding.FragmentHomeBinding
import com.dinhtc.logistics.model.LogisticInfoModel
import com.dinhtc.logistics.utils.LoadingScreen
import com.dinhtc.logistics.utils.SharedPreferencesManager
import com.dinhtc.logistics.utils.UiState
import com.dinhtc.logistics.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private var statusUser: String = "TOI"
    private var radioPersonLocal: String = SharedPreferencesManager.instance.getString(SharedPreferencesManager.USERNAME, null)
    private var radioTaskLocal: String = "1"
    private lateinit var radioTodayDate: String

    private val customerViewModel: CustomerViewModel by viewModels()

    private val logisticInfoModels = ArrayList<LogisticInfoModel>()

    companion object {
        const val LOGISTIC_MODEL = "LOGISTIC_MODEL"
    }

    private val logisticListChecked: MutableList<LogisticInfoModel> = mutableListOf()

    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override fun viewCreatedFragment() {
        getTodayLocalDate()

        actionView()
        radioAction()

        customerViewModel.getTaskByCustomer(
            userTask = radioPersonLocal,
            taskStatus = radioTaskLocal,
            timeTask = radioTodayDate,
            statusUser = statusUser
        )

        observeData()
    }

    private fun observeData() {
        customerViewModel.uiState.observe(this) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    LoadingScreen.hideLoading()
                    if (logisticInfoModels != null) logisticInfoModels.clear()
                    if (uiState.data.data != null){
                        for (dataModel in uiState.data.data){
                            val dataLogistic = LogisticInfoModel(
                                id = dataModel!!.id,
                                idOrder = dataModel.taskName,
                                locationOrder =dataModel.taskName,
                                personOrder = dataModel.userTask,
                                statusOrder = dataModel.taskStatus,
                                prioritizeOrder =dataModel.taskStatus,
                                checkedOrder = true
                            )
                            logisticInfoModels.add(dataLogistic)
                        }

                        setupAdapterLogistic()
                    }
                }

                is UiState.Error -> {
                    val errorMessage = uiState.message
                    Log.e("SSSSSSSSSSS", errorMessage)
                }

                UiState.Loading -> {
                    LoadingScreen.displayLoadingWithText(
                        context,
                        "Please wait...",
                        false
                    )
                }
            }
        }
    }

    private fun setupAdapterLogistic() {
        val tableViewAdapter = TableViewAdapter()
        tableViewAdapter.submitList(logisticInfoModels)
        viewBinding.recyclerViewMovieList.layoutManager = LinearLayoutManager(context)
        viewBinding.recyclerViewMovieList.setHasFixedSize(true)
        viewBinding.recyclerViewMovieList.adapter = tableViewAdapter

        tableViewAdapter.setOnClickItem(object : TableViewAdapter.OnItemClickListener {
            override fun onClickItem(logisticsModel: LogisticInfoModel?) {
                if (logisticsModel != null) {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_addTaskFragment,
                        bundleOf(LOGISTIC_MODEL to logisticsModel)
                    )
                }
            }

            override fun onClickAllItemEnable() {
                for (item in logisticInfoModels) {
                    item.checkedOrder = true
                }
                logisticListChecked.addAll(logisticInfoModels)
                tableViewAdapter.submitList(logisticInfoModels)
                tableViewAdapter.notifyDataSetChanged()
            }

            override fun onClickAllItemDisable() {
                for (item in logisticInfoModels) {
                    item.checkedOrder = false
                }
                logisticListChecked.addAll(logisticInfoModels)
                tableViewAdapter.submitList(logisticInfoModels)
                tableViewAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun radioAction() {
        viewBinding.apply {
            //Radio Hàng 1
            radioCuaToi.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    statusUser = "TOI"
                }
            }
            radioNhom.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    statusUser = "NHOM"
                }
            }
            radioTatCaNguoi.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    statusUser = "TATCA"
                }
            }
            //Radio Hàng 2
            radioChuaXong.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    radioTaskLocal = "1" // task chưa xong
                }
            }
            radioDaXong.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    radioTaskLocal = "0" //task đã xong
                }
            }
            radioTatCaTask.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    radioTaskLocal = "-1" // tất cả task
                }
            }
            //Radio Hàng 3
            radioHomNay.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    val todayLocalDate = getTodayLocalDate()
                    val year = todayLocalDate.year
                    val month = todayLocalDate.monthValue
                    val day = todayLocalDate.dayOfMonth

                    radioTodayDate = "$year-$month-$day"

                    Log.d("SSSSSSSSSSSSS","Ngày hôm nay là: $year-$month-$day")
                }
            }
            radioHomQua.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    val yesterdayLocalDate = getYesterdayLocalDate()
                    val year = yesterdayLocalDate.year
                    val month = yesterdayLocalDate.monthValue
                    val day = yesterdayLocalDate.dayOfMonth

                    radioTodayDate = "$year-$month-$day"

                    Log.e("SSSSSSSSSSS","Ngày hôm qua là: $year-$month-$day")
                }
            }
        }
    }

    private fun getYesterdayLocalDate(): LocalDate {
        radioTodayDate = LocalDate.now().minusDays(1).toString()
        return LocalDate.now().minusDays(1)
    }
    private fun getTodayLocalDate(): LocalDate {
        radioTodayDate = LocalDate.now().toString()
        return LocalDate.now()
    }

    private fun actionView() {
        viewBinding.btnSearch.setOnClickListener {
            customerViewModel.getTaskByCustomer(
                userTask = radioPersonLocal,
                taskStatus = radioTaskLocal,
                timeTask = radioTodayDate,
                statusUser = statusUser
            )
        }
    }

//    private val logisticInfoModels = ArrayList<LogisticInfoModel>().apply {
//        add(
//            LogisticInfoModel(
//                1,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "1",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                2,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "2",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                3,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "3",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                4,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "4",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                5,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "5",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                6,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "6",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                7,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "7",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                8,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "8",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                9,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "9",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                10,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "10",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                11,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "11",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                12,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "12",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                13,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "13",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                14,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "14",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                15,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "15",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                16,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "16",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                17,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "17",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                18,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "18",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                19,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "19",false
//            )
//        )
//        add(
//            LogisticInfoModel(
//                20,
//                "1237965457",
//                "860/29 Xô Viết Nghệ Tĩnh, phường 25, quận Bình Thạnh, TPHCM",
//                "Trần Cảnh Dinh",
//                "Đã cân",
//                "20",false
//            )
//        )
//
//    }
}