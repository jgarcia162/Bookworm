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
import com.otaliastudios.cameraview.PictureResult
import jose.com.bookworm.databinding.ActivityBaseCameraBinding

class ScanBarcodeActivity : BaseCameraActivity() {
  private val barCodeList = arrayListOf<FirebaseVisionBarcode>()
  private lateinit var binding: ActivityBaseCameraBinding
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityBaseCameraBinding.inflate(layoutInflater)
    binding.cameraView.addCameraListener(object : CameraListener() {
      override fun onPictureTaken(result: PictureResult) {
        super.onPictureTaken(result)
        val bitmap = result.data.size.let { BitmapFactory.decodeByteArray(result.data, 0, it) }
        bitmap?.let { runBarcodeScanner(it) }
        super.onPictureTaken(result)
      }
    })
  }
  
  override fun onClick(v: View?) {
    binding.cameraProgressBar.visibility = View.VISIBLE
    binding.cameraView.takePictureSnapshot()
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
        binding.cameraProgressBar.visibility = View.GONE
      }
      .addOnFailureListener { binding.cameraProgressBar.visibility = View.GONE }
      .addOnCompleteListener { binding.cameraProgressBar.visibility = View.GONE }
  }
}
