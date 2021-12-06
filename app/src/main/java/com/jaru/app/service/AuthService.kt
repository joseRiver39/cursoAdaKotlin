package com.jaru.app.service


import com.jaru.app.networt.Dto.LoginDto
import com.jaru.app.networt.Dto.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {

    @POST("auth")
    suspend fun login(@Body loginDto: LoginDto): Response<TokenDto>
}