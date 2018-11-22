package jose.com.bookworm.extensions

import android.support.design.chip.Chip
import android.support.design.chip.ChipGroup

fun ChipGroup.addChips(chips: List<Chip>){
    for(chip in chips){
        this.addView(chip)
    }
}
