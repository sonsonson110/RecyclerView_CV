package com.example.pson.recyclerviewcv.data

import com.example.pson.recyclerviewcv.R
import com.example.pson.recyclerviewcv.model.ProfileItem

class Datasource {
    fun loadProfileData(): List<ProfileItem> {
        return listOf(
            ProfileItem(R.string.profile1),
            ProfileItem(R.string.profile2),
            ProfileItem(R.string.profile3),
            ProfileItem(R.string.profile4),
            ProfileItem(R.string.profile5),
            ProfileItem(R.string.profile6),
            ProfileItem(R.string.profile7),
            ProfileItem(R.string.profile8),
            ProfileItem(R.string.dummy_text),
            ProfileItem(R.string.dummy_text),
            ProfileItem(R.string.dummy_text),
            ProfileItem(R.string.dummy_text),
            ProfileItem(R.string.dummy_text),
            ProfileItem(R.string.dummy_text),
        )
    }
}