package com.dinhtc.logistics.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dinhtc.logistics.R
import com.dinhtc.logistics.model.SuggestionModel
import java.util.Locale

class TagViewAdapter(
    private val mContext: Context, private val mResourceId: Int,
    private val mList: List<SuggestionModel>
) : ArrayAdapter<SuggestionModel>(mContext, mResourceId, mList) {

    private var mTempList: List<SuggestionModel> = ArrayList(mList)
    private var mSuggestionList: MutableList<SuggestionModel> = ArrayList()

    override fun getItem(position: Int): SuggestionModel {
        return mList[position]
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getFilter(): Filter {
        return filter
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(mResourceId, parent, false)
        }
        val model = getItem(position)
        val avatarImageView = view!!.findViewById<ImageView>(R.id.imageView_avatar)
        val nameTextView = view.findViewById<TextView>(R.id.textView_name)
        val usernameTextView = view.findViewById<TextView>(R.id.textView_username)
        Glide.with(mContext.applicationContext).load(model.avatar).apply(
            RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_user_default_24dp)
        ).into(avatarImageView)
        nameTextView.text = model.name
        usernameTextView.text = model.email
        return view
    }

    private val filter: Filter = object : Filter() {
        override fun convertResultToString(resultValue: Any): CharSequence {
            val model = resultValue as SuggestionModel
            return model.tag
        }

        override fun performFiltering(charSequence: CharSequence): FilterResults {
            return if (charSequence != null) {
                val query: String = charSequence.toString().lowercase(Locale.getDefault())
                mSuggestionList.clear()
                for (model in mTempList) {
                    // Use can filter yourself Id here
                    /*
                            if (model.getId() == Self.get().getID()) {
                                continue;
                            }
                            */
                    if (model.label.lowercase(Locale.getDefault()).contains(query) || ("@" + model.email).lowercase(Locale.getDefault()).contains(query)) {
                        mSuggestionList.add(model)
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = mSuggestionList
                filterResults.count = mSuggestionList.size
                return filterResults
            } else {
                FilterResults()
            }
        }

        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
            clear()
            if (filterResults.count > 0) {
                this@TagViewAdapter.addAll((filterResults.values as ArrayList<SuggestionModel?>))
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }
    }

}