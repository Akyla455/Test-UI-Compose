package com.example.listcharacters

import com.google.gson.annotations.SerializedName


data class ListCharacters(

    @SerializedName("info") var info: Info? = Info(),
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()
)