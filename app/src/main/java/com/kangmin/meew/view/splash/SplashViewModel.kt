package com.kangmin.meew.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.LoginUseCase
import com.kangmin.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _userInLocal = MutableLiveData<Boolean>()
    val userInLocal: LiveData<Boolean> = _userInLocal

    fun checkAutoLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.checkUserDataInLocal().catch {
                _userInLocal.postValue(false)
            }.collect {
                _userInLocal.postValue(it)
            }
        }
    }
}