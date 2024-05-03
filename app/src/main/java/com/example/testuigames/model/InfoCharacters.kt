package com.example.testuigames.model

import android.os.Parcel
import android.os.Parcelable

data class InfoCharacters(
    val id: Int?,
    val name: String?,
    val image: String?,
    val species: String?,
    val episode  : ArrayList<String>
)