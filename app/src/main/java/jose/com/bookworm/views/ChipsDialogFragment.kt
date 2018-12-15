package jose.com.bookworm.views

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.chip.Chip
import jose.com.bookworm.R
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.extensions.addChips
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.presentations.FeedPresentation
import kotlinx.android.synthetic.main.dialog_buttons_layout.*
import kotlinx.android.synthetic.main.fragment_chips_dialog.*

class ChipsDialogFragment : DialogFragment() {
    private lateinit var prefHelper: SharedPreferencesHelper
    private val selectedLists: MutableSet<String> = mutableSetOf()
    var chipTitles: List<String> = emptyList()
    var listener: FeedPresentation? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chips_dialog, container, false)
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

        best_sellers_chipgroup.addChips(chips) {
            it as Chip
            if (it.isChecked) {
                selectedLists.add(it.text.toString())
            } else {
                selectedLists.remove(it.text.toString())
            }
        }

        done_button.onClick {
            if (selectedLists.size < 1) {
                listener?.getOverviewList()
            } else {
                listener?.getMultipleLists(selectedLists)
            }
            prefHelper.saveFilters(selectedLists)
            dismiss()
        }

        clear_button.onClick {
            selectedLists.clear()
            for (child in 0 until best_sellers_chipgroup.childCount) {
                (best_sellers_chipgroup.getChildAt(child) as Chip).isChecked = false
            }
        }

        cancel_button.onClick {
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        listener = null
    }
}
