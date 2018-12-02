package jose.com.bookworm.extensions

import android.view.View
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun ChipGroup.addChips(listTitles: List<String>, action: (View) -> Unit = {}) {
    for (name in listTitles) {
        val chip = Chip(this.context).apply {
            text = name
            isClickable = true
            isCheckable = true
            setPadding(12,2,12,2)
            onClick(action)
        }
        this.addView(chip)
    }
}
