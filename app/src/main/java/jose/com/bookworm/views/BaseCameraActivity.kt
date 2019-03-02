package jose.com.bookworm.views

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.bottomsheet.BottomSheetBehavior
import jose.com.bookworm.R
import jose.com.bookworm.extensions.onClick
import kotlinx.android.synthetic.main.activity_base_camera.*

abstract class BaseCameraActivity : AppCompatActivity(), LifecycleOwner, View.OnClickListener {

    override fun getLifecycle(): Lifecycle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val sheetBehavior: BottomSheetBehavior<CardView> by lazy {
        BottomSheetBehavior.from(bottom_sheet_layout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_camera)
        retry_button.setOnClickListener {
            if (camera_view.visibility == VISIBLE) showPreview() else hidePreview()
        }
        camera_view.setLifecycleOwner(this)//TODO implement lifecycle
        capture_button.onClick(this)
    }

    private fun hidePreview() {
        preview_frame.visibility = GONE
        camera_view.visibility = VISIBLE
    }

    private fun showPreview() {
        preview_frame.visibility = VISIBLE
        camera_view.visibility = GONE
    }
}
