package com.jaru.app.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaru.app.networt.Dto.LoginDto
import com.jaru.app.service.RetrofitHlelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginVieModel : ViewModel(){

    val LoginRequesResultLiveData = MutableLiveData<Boolean>()
    fun login(email : String, password :String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val autoServise =  RetrofitHlelper.getAuthService()
                val response = autoServise.login(LoginDto(email,password))
                if(response.isSuccessful){
                    val tokenDto = response.body()!!
                    Log.d("Developer","response tokenDto : $tokenDto")
                }
                LoginRequesResultLiveData.postValue(response.isSuccessful)

            }catch (e: Exception){
                Log.e("Developer","error",e)
            }
        }
    }
}