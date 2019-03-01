package jose.com.bookworm.views

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import com.otaliastudios.cameraview.CameraListener
import kotlinx.android.synthetic.main.activity_base_camera.*

class ScanBarcodeActivity : BaseCameraActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        camera_view.addCameraListener(object: CameraListener(){
            override fun onPictureTaken(jpeg: ByteArray?) {
                val bitmap = jpeg?.size?.let { BitmapFactory.decodeByteArray(jpeg, 0, it) }
                bitmap?.let { runBarcodeScanner(it) }

                super.onPictureTaken(jpeg)
            }
        })
    }

    private fun runBarcodeScanner(bitmap: Bitmap) {

    }
}
