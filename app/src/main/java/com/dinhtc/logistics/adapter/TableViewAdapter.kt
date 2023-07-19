package com.dinhtc.logistics.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dinhtc.logistics.R
import com.dinhtc.logistics.model.LogisticInfoModel


class TableViewAdapter : RecyclerView.Adapter<TableViewAdapter.RowViewHolder>() {

    private val TYPE_HEADER: Int = 0
    private val TYPE_LIST: Int = 1
    var onItemClickListener: OnItemClickListener? = null
    private var selectedPosition = -1

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        }
        return TYPE_LIST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        return if (viewType == TYPE_HEADER) {
            val header =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.table_list_header, parent, false)
            RowViewHolder(header)
        } else {
            val header = LayoutInflater.from(parent.context)
                .inflate(R.layout.table_list_item, parent, false)
            RowViewHolder(header)
        }
    }

    private fun setHeaderBgAndColor(txtView: TextView) {
        txtView.setBackgroundResource(R.drawable.table_header_cell_bg)
        txtView.setTextColor(Color.WHITE)
    }

    private fun setContentBgAndColor(txtView: TextView) {
        txtView.setBackgroundResource(R.drawable.table_content_cell_bg)
        txtView.setTextColor(Color.BLACK)
    }
    private fun setContentBgAndColorOnClick(txtView: TextView, status: Boolean) {
        if (status){
            txtView.setBackgroundResource(R.drawable.table_cell_bg_onclick)
            txtView.setTextColor(Color.WHITE)
        }else{
            txtView.setBackgroundResource(R.drawable.table_content_cell_bg)
            txtView.setTextColor(Color.BLACK)
        }
    }


    interface OnItemClickListener {
        fun onClickItem(logisticsModel: LogisticInfoModel?)
        fun onClickAllItemEnable()
        fun onClickAllItemDisable()
    }

    fun setOnClickItem(onClickListener: OnItemClickListener) {
        this.onItemClickListener = onClickListener
    }

    private val diffCallback = object : DiffUtil.ItemCallback<LogisticInfoModel>() {
        override fun areItemsTheSame(
            oldItem: LogisticInfoModel,
            newItem: LogisticInfoModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LogisticInfoModel,
            newItem: LogisticInfoModel
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<LogisticInfoModel>) = differ.submitList(list)

    override fun onBindViewHolder(holder: RowViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val resources: Resources = holder.itemView.context.resources
        when (position) {
            TYPE_HEADER -> {
                // Header Cells. Main Headings appear here
                holder.apply {
                    setHeaderBgAndColor(txtId)
                    setHeaderBgAndColor(txtIdOrder)
                    setHeaderBgAndColor(txtLocation)
                    setHeaderBgAndColor(txtPerson)
                    setHeaderBgAndColor(txtStatus)
                    setHeaderBgAndColor(txtPrioritize)
                    //imgChecked.setBackgroundResource(R.drawable.table_header_cell_bg)

                    txtId.text = "#"
                    txtIdOrder.text = "Đơn gom"
                    txtLocation.text = "Địa điểm"
                    txtPerson.text = "Người"
                    txtStatus.text = "Tình trạng"
                    txtPrioritize.text = "Ưu tiên"
//                    imgChecked.setPadding(25, 25, 25, 25)
//
//                    imgChecked.setOnClickListener {
//                        if (isCheckedAllItem) {
//                            onItemClickListener?.onClickAllItemEnable()
//                            imgChecked.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_check, null))
//                            imgChecked.setPadding(25, 25, 25, 25)
//                        } else {
//                            onItemClickListener?.onClickAllItemDisable()
//                            imgChecked.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_add, null))
//                            imgChecked.setPadding(25, 25, 25, 25)
//
//                        }
//                        isCheckedAllItem = !isCheckedAllItem
//                    }
                }
            }

            else -> {
                val modal = differ.currentList[position - 1]
                holder.apply {
                    setContentBgAndColor(txtId)
                    setContentBgAndColor(txtIdOrder)
                    setContentBgAndColor(txtLocation)
                    setContentBgAndColor(txtPerson)
                    setContentBgAndColor(txtStatus)
                    setContentBgAndColor(txtPrioritize)
                    //imgChecked.setBackgroundResource(R.drawable.table_content_cell_bg)

                    txtId.text = modal.id.toString()
                    txtIdOrder.text = modal.idOrder.toString()
                    txtLocation.text = modal.locationOrder.toString()
                    txtPerson.text = modal.personOrder.toString()
                    txtStatus.text = modal.statusOrder.toString()
                    txtPrioritize.text = modal.prioritizeOrder.toString()

                    holder.itemView.setOnClickListener {
                        selectedPosition = position
                        onItemClickListener?.onClickItem(modal)
                        notifyDataSetChanged()
                    }
                    if (selectedPosition == position) {
                        setContentBgAndColorOnClick(txtId,true)
                        setContentBgAndColorOnClick(txtIdOrder,true)
                        setContentBgAndColorOnClick(txtLocation,true)
                        setContentBgAndColorOnClick(txtPerson,true)
                        setContentBgAndColorOnClick(txtStatus,true)
                        setContentBgAndColorOnClick(txtPrioritize,true)
                    } else {
                        setContentBgAndColorOnClick(txtId,false)
                        setContentBgAndColorOnClick(txtIdOrder,false)
                        setContentBgAndColorOnClick(txtLocation,false)
                        setContentBgAndColorOnClick(txtPerson,false)
                        setContentBgAndColorOnClick(txtStatus,false)
                        setContentBgAndColorOnClick(txtPrioritize,false)
                    }


//                    if (modal.checkedOrder) {
//                        imgChecked.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_check, null))
//                        imgChecked.setPadding(35, 35, 35, 35)
//                    } else {
//                        imgChecked.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_add, null))
//                        imgChecked.setPadding(35, 35, 35, 35)
//                    }
//
//                    imgChecked.setOnClickListener {
//                        modal.checkedOrder = !(modal.checkedOrder)
//
//                        if (modal.checkedOrder) {
//                            onItemClickListener?.onClickItem(modal)
//                            imgChecked.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_check, null))
//                            imgChecked.setPadding(35, 35, 35, 35)
//                        } else {
//                            onItemClickListener?.onClickItem(modal)
//                            imgChecked.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_add, null))
//                            imgChecked.setPadding(35, 35, 35, 35)
//                        }
//                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size + 1 // one more to add header row
    }

    inner class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtId: TextView = itemView.findViewById(R.id.txtId)
        val txtIdOrder: TextView = itemView.findViewById(R.id.txtIdOrder)
        val txtLocation: TextView = itemView.findViewById(R.id.txtLocation)
        val txtPerson: TextView = itemView.findViewById(R.id.txtPerson)
        val txtStatus: TextView = itemView.findViewById(R.id.txtStatus)
        val txtPrioritize: TextView = itemView.findViewById(R.id.txtPrioritize)
        //val itemRoot: LinearLayout = itemView.findViewById<LinearLayout>(R.id.itemRoot)
        //val imgChecked: ImageView = itemView.findViewById(R.id.txtChecked)
    }

}