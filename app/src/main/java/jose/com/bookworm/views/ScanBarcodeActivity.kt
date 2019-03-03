package jose.com.bookworm.views

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.otaliastudios.cameraview.CameraListener
import kotlinx.android.synthetic.main.activity_base_camera.*

class ScanBarcodeActivity : BaseCameraActivity() {
    private val barCodeList = arrayListOf<FirebaseVisionBarcode>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        camera_view.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(jpeg: ByteArray?) {
                val bitmap = jpeg?.size?.let { BitmapFactory.decodeByteArray(jpeg, 0, it) }
                bitmap?.let { runBarcodeScanner(it) }
                super.onPictureTaken(jpeg)
            }
        })
    }

    override fun onClick(v: View?) {
        camera_progress_bar.visibility = View.VISIBLE
        camera_view.captureSnapshot()
    }

    private fun runBarcodeScanner(bitmap: Bitmap) {
        val image = FirebaseVisionImage.fromBitmap(bitmap)

        val options = FirebaseVisionBarcodeDetectorOptions.Builder().apply {
            setBarcodeFormats(
                FirebaseVisionBarcode.FORMAT_EAN_8,
                FirebaseVisionBarcode.FORMAT_EAN_13
            )
        }.build()

        val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options)
        detector.detectInImage(image)
            .addOnSuccessListener {
                barCodeList.clear()
                barCodeList.addAll(it)
                camera_progress_bar.visibility = View.GONE
            }
            .addOnFailureListener { camera_progress_bar.visibility = View.GONE }
            .addOnCompleteListener { camera_progress_bar.visibility = View.GONE }
    }
}
