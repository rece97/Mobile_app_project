package com.example.test.ui.dashboard

import android.net.LocalSocketAddress.Namespace
import android.net.eap.EapSessionConfig.EapAkaPrimeConfig
import com.google.gson.annotations.SerializedName

data class Bug(
    @SerializedName("file-name") val file_name: String,
    val name: Names,
    val availability: Availabilities,
    val price: Int,
    @SerializedName("price-flick") val price_flick: Int,
    @SerializedName("catch-phrase") val catchphrase: String,
    @SerializedName("museum-phrase") val museum_phrase: String,
    @SerializedName("image_uri") val image_url: String,
    @SerializedName("icon_uri") val icon_url: String
)

data class Names(
    @SerializedName("name-USen") val name: String
)

data class Availabilities(
    @SerializedName("month-northern") val months: String,
    val time: String,
    val isAllDay: Boolean,
    val isAllYear: Boolean,
    val rarity: String
)

/*
{
  "id": 1,
  "file-name": "common_butterfly",
  "name": {
    "name-USen": "common butterfly",
    "name-EUen": "common butterfly",
    "name-EUde": "Kohlweißling",
    "name-EUes": "mariposa común",
    "name-USes": "mariposa común",
    "name-EUfr": "piéride de la rave",
    "name-USfr": "piéride de la rave",
    "name-EUit": "farfalla comune",
    "name-EUnl": "koolwitje",
    "name-CNzh": "白粉蝶",
    "name-TWzh": "白粉蝶",
    "name-JPja": "モンシロチョウ",
    "name-KRko": "배추흰나비",
    "name-EUru": "белянка"
  },
  "availability": {
    "month-northern": "9-6",
    "month-southern": "3-12",
    "time": "4am - 7pm",
    "isAllDay": false,
    "isAllYear": false,
    "location": "Flying",
    "rarity": "Common",
    "month-array-northern": [
      9,
      10,
      11,
      12,
      1,
      2,
      3,
      4,
      5,
      6
    ],
    "month-array-southern": [
      3,
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      11,
      12
    ],
    "time-array": [
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      11,
      12,
      13,
      14,
      15,
      16,
      17,
      18
    ]
  },
  "price": 160,
  "price-flick": 240,
  "catch-phrase": "I caught a common butterfly! They often flutter by!",
  "museum-phrase": "The common butterfly would have you believe it is but a beautiful friend flitting prettily about the flowers. Bah, I say! They may seem innocent things with their pretty white wings, but they hide a dark side! The common butterfly caterpillar is called a cabbage worm, you see, and it's a most voracious pest. The ravenous beasts chew through cabbage, broccoli, kale and the like with a devastating gusto. And my feathers! Their green coloring is truly GROSS! A hoo-rrific hue, I say.",
  "image_uri": "https://acnhapi.com/v1/images/bugs/1",
  "icon_uri": "https://acnhapi.com/v1/icons/bugs/1"
}
 */