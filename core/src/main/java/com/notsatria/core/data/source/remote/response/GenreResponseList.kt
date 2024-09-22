package com.notsatria.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreResponseList(

	@field:SerializedName("genres")
	val genres: List<GenreResponse>
) : Parcelable