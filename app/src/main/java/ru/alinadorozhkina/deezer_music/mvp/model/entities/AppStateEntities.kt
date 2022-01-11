package ru.alinadorozhkina.deezer_music.mvp.model.entities

import com.google.gson.annotations.SerializedName
import ru.alinadorozhkina.deezer_music.mvp.contract.AppStateEntity

data class Chart(
    @SerializedName("tracks")
    val tracks: TopTracks,
    @SerializedName("albums")
    val albums: TopAlbums,
    @SerializedName("artists")
    val artists: TopArtists
) : AppStateEntity

data class TopTracks(
    @SerializedName("data")
    val data: List<Track>,
    @SerializedName("total")
    val total: Int
)

data class Track(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String,
    @SerializedName("position")
    val position: Int?,
    @SerializedName("preview")
    val preview: String?,
    @SerializedName("artist")
    val artist: Artist
) : AppStateEntity

data class TopAlbums(
    @SerializedName("data")
    val data: List<Album>,
    @SerializedName("total")
    val total: Int

) : AppStateEntity

data class Album(
    @SerializedName("title")
    val title: String,
    @SerializedName("cover_medium")
    val cover: String,
    @SerializedName("tracklist")
    val tracklist: String,
    @SerializedName("position")
    val position: Int,
    @SerializedName("artist")
    val artist: Artist
) : AppStateEntity

data class TopArtists(
    @SerializedName("data")
    val data: List<Artist>,
    @SerializedName("total")
    val total: Int

) : AppStateEntity

data class Artist(
    @SerializedName("name")
    val name: String,
    @SerializedName("tracklist")
    val tracklist: String?,
    @SerializedName("position")
    val position: Int?,
    @SerializedName("picture_medium")
    val picture: String
) : AppStateEntity

data class Radio(
    @SerializedName("data")
    val data: List< Genre>
             
) : AppStateEntity

data class Genre(
    @SerializedName("title")
    val title: String,
    @SerializedName("tracklist")
    val tracklist: String,
) : AppStateEntity

data class TrackList(
    @SerializedName("data")
    val data: List<Track>
) : AppStateEntity

data class CategoryModel(
    val title: String,
    val tracks: List<Track>
) : AppStateEntity

data class Category(
    val data: List<CategoryModel>
) : AppStateEntity


