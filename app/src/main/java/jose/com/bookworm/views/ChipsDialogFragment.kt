package jose.com.bookworm.views

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import jose.com.bookworm.R
import jose.com.bookworm.di.Injector
import jose.com.bookworm.extensions.addChips
import jose.com.bookworm.presentations.FeedPresentation
import jose.com.bookworm.presenters.FeedPresenter
import javax.inject.Inject
//TODO persist categories so they are not reset to none when this fragment is created
class ChipsDialogFragment: DialogFragment(){
    @Inject lateinit var presenter: FeedPresenter
    var chipTitles: List<String> = emptyList()
    private lateinit var chipGroup: ChipGroup
    var listener: FeedPresentation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Injector.applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_category_chips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chipGroup = view.findViewById(R.id.best_sellers_chipgroup)
        chipGroup.addChips(chipTitles){
            listener?.getBestSellerList((it as Chip).text.toString())
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        listener = null
    }
}