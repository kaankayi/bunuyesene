package knb.bunuyesene.features.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GirisViewModel: ViewModel() {

    private val _girisState = MutableStateFlow<GirisState>(GirisState.Idle)
    val girisState = _girisState.asStateFlow()

    fun giris(email: String, password: String) {
        viewModelScope.launch {

            _girisState.value = GirisState.Loading

            try {

                delay(1500)

                if (email == "kaan@gmail.com" && password == "kaan123") {
                    _girisState.value = GirisState.Success
                } else {
                    _girisState.value = GirisState.Error(
                        "Hatalı e-mail veya şifre."
                    )
                }
            } catch (e: Exception) {
                _girisState.value = GirisState.Error(
                    "Giriş yaparken beklenmedik bir hata oluştu."
                )
            }
        }
    }

    fun resetState() {
        _girisState.value = GirisState.Idle
    }

}