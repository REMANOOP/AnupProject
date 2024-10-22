import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nit3213project.model.DashboardResponse
import com.example.nit3213project.model.Entity
import com.example.nit3213project.network.ApiService
import com.example.nit3213project.ui.details.EntityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@ExperimentalCoroutinesApi
class EntityViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: EntityViewModel
    private lateinit var apiService: ApiService
    private lateinit var mockObserver: Observer<List<Entity>>

    @Before
    fun setUp() {
        apiService = mock(ApiService::class.java) // Mock the ApiService
        viewModel = EntityViewModel(apiService) // Initialize ViewModel with the mocked service
        mockObserver = mock(Observer::class.java) as Observer<List<Entity>>
        viewModel.entities.observeForever(mockObserver) // Observe LiveData
    }

    @Test
    fun `test fetchDashboardData updates entities successfully`() = runTest {
        // Arrange: Create a mock response
        val mockEntities = listOf(
            Entity("Artist 1", "Album 1", 2020, "Rock", 10, "Description 1", "Track 1", 0),
            Entity("Artist 2", "Album 2", 2021, "Pop", 12, "Description 2", "Track 2", 0)
        )
        val dashboardResponse = DashboardResponse(entities = mockEntities, entityTotal = mockEntities.size)

        // Mock the ApiService response
        val callMock = mock(Call::class.java) as Call<DashboardResponse>
        `when`(apiService.getDashboardData("someKeypass")).thenReturn(callMock)

        // Simulate a successful response
        val callbackCaptor = ArgumentCaptor.forClass(Callback::class.java)
        verify(apiService).getDashboardData("someKeypass")

        // This line is crucial - make sure to cast it to the correct type
        val callback: Callback<DashboardResponse> = callbackCaptor.capture() as Callback<DashboardResponse>
        callback.onResponse(callMock, Response.success(dashboardResponse)) // Simulate a successful response

        // Act: Call the method to fetch data
        viewModel.fetchDashboardData("someKeypass")

        // Assert: Verify that the observer was notified with the correct data
        verify(mockObserver).onChanged(mockEntities)
    }

    @Test
    fun `test fetchDashboardData handles failure`() = runTest {
        // Arrange: Mock the ApiService to simulate a failure
        val callMock = mock(Call::class.java) as Call<DashboardResponse>
        `when`(apiService.getDashboardData("someKeypass")).thenReturn(callMock)

        // Simulate a failure response
        val callbackCaptor = ArgumentCaptor.forClass(Callback::class.java)
        verify(apiService).getDashboardData("someKeypass")
        val callback: Callback<DashboardResponse> = callbackCaptor.capture() as Callback<DashboardResponse>
        callback.onFailure(callMock, Throwable("Network Error")) // Simulate a failure

        // Act: Call the method to fetch data
        viewModel.fetchDashboardData("someKeypass")

        // Assert: Verify that the observer was notified with an empty list
        verify(mockObserver).onChanged(emptyList())
    }
}
