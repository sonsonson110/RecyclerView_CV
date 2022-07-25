package com.example.pson.recyclerviewcv

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pson.recyclerviewcv.data.Datasource
import com.example.pson.recyclerviewcv.model.ProfileItem

class ProfileListAdapter(private val context: Context) : RecyclerView.Adapter<ProfileListAdapter.ProfileItemViewHolder>() {
    //tạo list chứa data các dòng profile text
    private val dataset: List<ProfileItem> = Datasource().loadProfileData()

    //tạo class holder
    class ProfileItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        //trong đây chứa các properties của 1 item view
        val profileText: TextView = view.findViewById(R.id.text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemViewHolder {
        //copy-paste code
        val layout = LayoutInflater
            .from(parent.context)
            //chọn item_view xml cho adapter
            .inflate(R.layout.item_view, parent, false)
        return ProfileItemViewHolder(layout)
    }

    //viết các logic của properties ở đây
    override fun onBindViewHolder(holder: ProfileItemViewHolder, position: Int) {
        //constructor có sẵn holder (item hiện tại) và position của item
        //truyền profileText theo position trong list data
        val item = dataset[position] //profileText hiện tại
        //đưa vào item đang được hold (các biến đã được định nghĩa trong holder)
        //convert int id trong item -> string trong resources
        holder.profileText.text = context.resources.getString(item.stringResId)
        //nhảy sang detail view nếu click vào 1 item bất kì
        holder.profileText.setOnClickListener {
            val context = holder.view.context
            // gọi file activity muốn nhảy sang kèm extras
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.TITLE, holder.profileText.text)
            intent.putExtra(DetailActivity.POSITION, position)
            context.startActivity(intent)
        }

    }

    override fun getItemCount() = dataset.size
}