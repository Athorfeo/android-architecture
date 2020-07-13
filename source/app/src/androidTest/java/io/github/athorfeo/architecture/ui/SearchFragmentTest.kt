package io.github.athorfeo.architecture.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.databinding.DataBindingComponent
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.hadilq.liveevent.LiveEvent
import io.github.athorfeo.architecture.R
import io.github.athorfeo.architecture.model.ErrorResource
import io.github.athorfeo.architecture.model.SearchItem
import io.github.athorfeo.architecture.testing.SingleFragmentActivity
import io.github.athorfeo.architecture.ui.item.search.ItemSearchAdapter
import io.github.athorfeo.architecture.ui.item.search.SearchFragment
import io.github.athorfeo.architecture.ui.item.search.SearchFragmentDirections
import io.github.athorfeo.architecture.ui.item.search.SearchViewModel
import io.github.athorfeo.architecture.util.RecyclerViewMatcher
import io.github.athorfeo.architecture.util.ViewModelUtil
import io.github.athorfeo.architecture.util.clickChild
import io.github.athorfeo.architecture.util.mock
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@MediumTest
@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {
    private lateinit var scenario: FragmentScenario<SearchFragment>
    private lateinit var viewModel: SearchViewModel

    private val isLoading = LiveEvent<Boolean>()
    private val isError = LiveEvent<ErrorResource>()
    private val searchQuery = ObservableField<String>()
    private val searchResults = MutableLiveData<List<SearchItem>>()

    @Before
    fun init() {
        viewModel = mock(SearchViewModel::class.java)

        `when`(viewModel.isLoading).thenReturn(isLoading)
        `when`(viewModel.isError).thenReturn(isError)
        `when`(viewModel.searchQuery).thenReturn(searchQuery)
        `when`(viewModel.searchResults).thenReturn(searchResults)

        scenario = launchFragmentInContainer(Bundle(), R.style.AppTheme){
            SearchFragment().apply {
                viewModelFactory = ViewModelUtil.createFor(viewModel)
            }
        }
    }

    @Test
    fun searchInputTest(){
        onView(withId(R.id.input_search)).perform(
            typeText("Motorola")
        )

        assertEquals("Motorola", viewModel.searchQuery.get())
    }


    @Test
    fun searchButtonTest(){
        onView(withId(R.id.button_search)).perform(click())

        verify(viewModel).search()
    }

    @Test
    fun goToDetailTest(){
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        val items = listOf(
            SearchItem("1", "Item 1", "http://mco-s1-p.mlstatic.com/656497-MCO41182169757_032020-I.jpg", 80000.0, 1.0),
            SearchItem("2", "Item 2", "http://mco-s1-p.mlstatic.com/656497-MCO41182169757_032020-I.jpg", 80000.0, 1.0)
        )

        searchResults.postValue(items)

        onView(withText("Item 1")).perform(click())
        //onView(withId(R.id.recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<ItemSearchAdapter.ViewHolder>(0, clickChild(R.id.item_content)))

        verify(navController).navigate(SearchFragmentDirections.toDetailFragment("1"))

    }

    class TestMainFragment : SearchFragment() {
    }
}