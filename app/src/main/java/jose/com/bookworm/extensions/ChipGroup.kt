package jose.com.bookworm.extensions

import android.view.View
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun ChipGroup.addChips(chips: List<Chip>, action: (View) -> Unit = {}) {
    chips.map {
        it.onClick(action)
        this@addChips.addView(it)
    }
}
