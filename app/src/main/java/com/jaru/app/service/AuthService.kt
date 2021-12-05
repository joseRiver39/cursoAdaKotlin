package com.jaru.app.service

import com.jaru.app.dto.TokenDto
import com.jaru.app.dto.loginDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {

    @POST("auth")
    suspend fun login(@Body loginDto: loginDto): Response<TokenDto>
}