package com.example.pson.recyclerviewcv

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.pson.recyclerviewcv.databinding.DetailViewBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        //thuộc tính từ recycler view chuyển sang
        const val TITLE = "Title"
        val POSITION = null
        //thuộc tính của email button
        const val EMAIL = "pson141002@gmail.com"
        const val SUBJECT = "Ghi tiêu đề ở đây :)"
        const val BODY = "Nội dung mà bạn muốn gửi"
        //thuộc tính fb button
        const val FACEBOOKID = "100006655110582"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //nạp xml
        val binding = DetailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get string từ put extra của item trong holder
        val titleId = intent?.extras?.getString(TITLE).toString()

        //get vị trí item được click từ put extra
        val positionId = intent?.extras?.getInt(POSITION)
        //update textView trong detail theo positionId, lấy từ stringArray trong resource
        val details = resources.getStringArray(R.array.details).toList()
        if (positionId != null) {
            if (positionId < details.size)
                binding.detailInfo.text = details[positionId]
        }

        //nếu vào trúng vào phần LIÊN HỆ thì implement 2 implicit intent cho 2 button
        if (positionId != null) {
            contactButtonClicked(positionId, binding, this)
        }

        //update properties title trong xml
        binding.detailTitle.text = titleId

        // update title của xml
        title = getString(R.string.detail_title)
    }

    private fun contactButtonClicked(positionId: Int, binding: DetailViewBinding, context: Context) {
        if (positionId == 7) {
            //set up email button
            binding.emailButton.setOnClickListener {
                //bố cục của email intent
                val emailIntent = Intent(Intent.ACTION_SEND).apply {
                    //putExtra các thành phần
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(EMAIL))
                    putExtra(Intent.EXTRA_SUBJECT, SUBJECT)
                    putExtra(Intent.EXTRA_TEXT, BODY)
                }
                val title = "Send email"
                //startActivity chỉ được gọi ở trong context
                context.startActivity(Intent.createChooser(emailIntent, title))
            }
            //set up fb link button
            binding.fbButton.setOnClickListener {
                val fbIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("fb://profile/$FACEBOOKID")
                }
                //nếu có ứng dụng fc thì chỉ thẳng vào, nếu k sẽ nhảy sang web
                try {
                    context.startActivity(fbIntent)
                } catch (e: ActivityNotFoundException) {
                    Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://www.facebook.com/pson02/")
                        context.startActivity(this)
                    }
                }
            }
        } else {
            //không trúng thì dấu cả 2 button đi
            binding.emailButton.isVisible = false
            binding.fbButton.isVisible = false
        }
    }
}
