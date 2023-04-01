package com.example.myapiapplication.network
import com.squareup.moshi.Json
data class Character (
    @Json(name = "name")
    var name:String,
    @Json(name="image")
    var img:String,
    )
data class CharacterResponse(@Json(name="results")
val result :List<Character>)