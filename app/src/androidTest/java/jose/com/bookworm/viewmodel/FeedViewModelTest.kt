package jose.com.bookworm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.reactivex.rxjava3.schedulers.TestScheduler
import jose.com.bookworm.MainCoroutineRule
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.repository.BookRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject


@HiltAndroidTest
class FeedViewModelTest {
  private lateinit var viewModel: FeedViewModel
  private val hiltRule = HiltAndroidRule(this)
  private val instantTaskExecutorRule = InstantTaskExecutorRule()
  private val coroutineRule = MainCoroutineRule()
  private val testIOScheduler = TestScheduler()
  private val testMainScheduler = TestScheduler()
  
  @get:Rule
  val rule: RuleChain = RuleChain
    .outerRule(hiltRule)
    .around(instantTaskExecutorRule)
    .around(coroutineRule)
  
  @Inject
  lateinit var bookRepository: BookRepository
  
  @Inject
  lateinit var sharedPreferencesHelper: SharedPreferencesHelper
  
  @Before
  fun setUp() {
    hiltRule.inject()
    
    viewModel = FeedViewModel(
      bookRepository,
      sharedPreferencesHelper,
      testIOScheduler,
      testMainScheduler
    )
  }
  
  @Test
  fun `Test that getBooks is called`() {
  
  }
  
  @After
  fun tearDown() {
  
  }
}
