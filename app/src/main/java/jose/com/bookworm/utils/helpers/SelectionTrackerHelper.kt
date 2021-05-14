package jose.com.bookworm.utils.helpers

import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import jose.com.bookworm.adapter.DetailsLookup
import jose.com.bookworm.utils.helpers.SelectionTrackerHelper.Companion

/**
 * Provides static helper functions for [SelectionTracker] through a [Companion] object.
 * */
class SelectionTrackerHelper {
  companion object {
    
    /**
     * Returns an instance of a [SelectionTracker] typed to [Long]
     *
     * @param selectionId [String] indicating the id of the selection
     * @param recyclerView the [RecyclerView] to set the tracker on
     * */
    fun getLongTracker(
      selectionId: String,
      recyclerView: RecyclerView
    ): SelectionTracker<Long> {
      return SelectionTracker.Builder(
        selectionId,
        recyclerView,
        StableIdKeyProvider(recyclerView),
        DetailsLookup(recyclerView),
        StorageStrategy.createLongStorage()
      ).withSelectionPredicate(
        SelectionPredicates.createSelectAnything()
      ).build()
    }
  }
}