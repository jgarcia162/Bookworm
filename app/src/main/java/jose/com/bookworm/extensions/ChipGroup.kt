package jose.com.bookworm.extensions

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun ChipGroup.addChips(listTitles: List<String>) {
    for (name in listTitles) {
        val chip = Chip(this.context).apply {
            text = name
            isCheckable = true
            isClickable = true
            setPadding(2,2,2,2)
        }
        this.addView(chip)
    }
}
