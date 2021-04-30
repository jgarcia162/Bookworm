package jose.com.bookworm.views

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import jose.com.bookworm.databinding.ActivityBaseCameraBinding
import jose.com.bookworm.extensions.onClick

abstract class BaseCameraActivity : AppCompatActivity(), LifecycleOwner, View.OnClickListener {
    private lateinit var binding: ActivityBaseCameraBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.retryButton.setOnClickListener {
            if (binding.cameraView.visibility == VISIBLE) showPreview() else hidePreview()
        }
        binding.cameraView.setLifecycleOwner(this)//TODO implement lifecycle
        binding.captureButton.onClick(this)
    }

    private fun hidePreview() {
        binding.previewFrame.visibility = GONE
        binding.cameraView.visibility = VISIBLE
    }

    private fun showPreview() {
        binding.previewFrame.visibility = VISIBLE
        binding.cameraView.visibility = GONE
    }
}
