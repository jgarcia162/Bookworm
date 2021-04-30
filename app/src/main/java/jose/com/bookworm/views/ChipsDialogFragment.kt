package jose.com.bookworm.views

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.chip.Chip
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.databinding.DialogButtonsLayoutBinding
import jose.com.bookworm.databinding.FragmentChipsDialogBinding
import jose.com.bookworm.extensions.addChips
import jose.com.bookworm.extensions.onClick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChipsDialogFragment : DialogFragment() {
  private lateinit var prefHelper: SharedPreferencesHelper
  private val selectedLists: MutableSet<String> = mutableSetOf()
  private var fragmentChipsDialogBinding: FragmentChipsDialogBinding? = null
  private var dialogButtonsBinding: DialogButtonsLayoutBinding? = null
  private val chipsBinding get() = fragmentChipsDialogBinding!!
  private val buttonsBinding get() = dialogButtonsBinding!!
  
  var chipTitles: Set<String> = emptySet()
  var listener: ChipsListener? = null
  
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    fragmentChipsDialogBinding = FragmentChipsDialogBinding.inflate(inflater, container, false)
    dialogButtonsBinding = DialogButtonsLayoutBinding.inflate(inflater, container, false)
    return chipsBinding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    prefHelper = SharedPreferencesHelper(view.context)
    
    prefHelper.getFilters()?.let { selectedLists.addAll(it) }
    
    val chips = chipTitles.map {
      Chip(this.context).apply {
        text = it
        isClickable = true
        isCheckable = true
        setPadding(12, 2, 12, 2)
        isChecked = selectedLists.contains(it)
      }
    }
    
    chipsBinding.bestSellersChipgroup.addChips(chips) {
      it as Chip
      if (it.isChecked) {
        selectedLists.add(it.text.toString())
      } else {
        selectedLists.remove(it.text.toString())
      }
    }
    
    buttonsBinding.doneButton.onClick {
      CoroutineScope(Dispatchers.IO).launch {
        if (selectedLists.size < 1) {
          listener?.getOverviewList()
        } else {
          listener?.getMultipleLists(selectedLists)
        }
        prefHelper.saveFilters(selectedLists)
        CoroutineScope(Dispatchers.Main).launch {
          dismiss()
        }
      }
    }
    
    buttonsBinding.clearButton.onClick {
      selectedLists.clear()
      for (child in 0 until chipsBinding.bestSellersChipgroup.childCount) {
        (chipsBinding.bestSellersChipgroup.getChildAt(child) as Chip).isChecked = false
      }
    }
    
    buttonsBinding.cancelButton.onClick {
      dismiss()
    }
  }
  
  override fun onDismiss(dialog: DialogInterface) {
    super.onDismiss(dialog)
    listener = null
  }
  
  interface ChipsListener {
    suspend fun getOverviewList()
    
    fun getMultipleLists(selectedLists: MutableSet<String>)
  }
}
