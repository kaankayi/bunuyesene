package knb.bunuyesene.features.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import knb.bunuyesene.features.profile.data.User

class ProfilViewModel : ViewModel() {

    private var _refresh = MutableStateFlow(false)
    private val refresh = _refresh.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val profilUiState: StateFlow<ProfilEkraniUiState> = combine(refresh) { _ ->
        getUserInfo()
    }.flatMapLatest { it }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ProfilEkraniUiState()
        )

    private fun getUserInfo(): Flow<ProfilEkraniUiState> = flow {
        emit(
            ProfilEkraniUiState(
                isLoading = true
            )
        )

        //call your api here
        delay(1000)

        emit(
            ProfilEkraniUiState(
                userInfo = User(
                    id = 1,
                    name = "Kaan",
                    email = "kaan@gmail.com",
                    myTarifCount = 20,
                    favoriTarifCount = 10,
                    followers = 140
                ),
                isLoading = false,
            )
        )
    }

    fun refresh() {
        _refresh.value = true
    }

}