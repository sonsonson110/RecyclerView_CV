package com.example.pson.recyclerviewcv

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pson.recyclerviewcv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var profileRecyclerView: RecyclerView
    private var isGridLayout = true

    //hàm sẽ được gọi trước khi onDestroy để lưu data kiểu BUNDLE cho lần onCreate sắp tới
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //bundle là key value string-value: tên-value
        outState.putBoolean(GRID, isGridLayout)
        Log.d(TAG, "onSave called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")
        //binding view
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileRecyclerView = binding.recyclerView
        chooseLayout()

        //cập nhật data từ onSave nếu vừa bị destroy
        if (savedInstanceState != null) {
            isGridLayout = savedInstanceState.getBoolean(GRID, false)
            chooseLayout()
            Log.d(TAG, "apply saved changes")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

    // khởi tạo thanh menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        // Calls code to set the icon based on the LinearLayoutManager of the RecyclerView
        setIcon(layoutButton)

        return true
    }

    //logic khi ấn đúng vào option item (grid/linear icon)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_switch_layout -> {
                //đổi biến boolean isGrid để thay đổi lại bố cục
                isGridLayout = !isGridLayout
                //set lại layout và icon
                chooseLayout()
                setIcon(item)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // hàm khởi tạo loại layout cho recycler view
    private fun chooseLayout() {
        if (isGridLayout) {
            profileRecyclerView.layoutManager = GridLayoutManager(this,2)
        } else {
            profileRecyclerView.layoutManager = LinearLayoutManager(this)
        }
        profileRecyclerView.adapter = ProfileListAdapter(this)
    }
    //hàm set icon chế độ grid/linear
    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null) return

        menuItem.icon = when (isGridLayout) {
            true -> ContextCompat.getDrawable(this, R.drawable.ic_grid)
            else -> ContextCompat.getDrawable(this, R.drawable.ic_linear)
        }
    }

    companion object {
        const val TAG = "MainActivity"
        const val GRID = "isGrid"
    }
}