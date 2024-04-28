package com.example.testuigames.model

import com.example.testuigames.model.Info
import com.example.testuigames.model.Results
import com.google.gson.annotations.SerializedName
data class ListCharacters(

    @SerializedName("info")
    var info: Info? = Info(),
    @SerializedName("results")
    var results: ArrayList<Results> = arrayListOf()
)