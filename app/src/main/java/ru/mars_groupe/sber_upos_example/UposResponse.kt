package ru.mars_groupe.sber_upos_example

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UposResponse(
    @SerializedName("PAN") val pan: String? = null,
    @SerializedName("HASH") val hash: String,
    @SerializedName("HASH256") var hash256: String? = null,
    @SerializedName("IS_OWN") val isOwn: String? = null,
    @SerializedName("HASH_ALGO") val hashAlgo: String? = null,
    @Expose val expDate: String? = null,
    @Expose var par: String? = null
)