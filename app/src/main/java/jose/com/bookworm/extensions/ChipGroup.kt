package jose.com.bookworm.extensions

import android.support.design.chip.Chip
import android.support.design.chip.ChipGroup

fun ChipGroup.addChips(listTitles: List<String>) {
    for (name in listTitles) {
        val chip = Chip(this.context).apply {
            text = name
        }
        this.addView(chip)
    }
}
