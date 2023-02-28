
package com.example.expandableList.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.expandableList.R

/**
 * A data class to represent the information presented in the User card
 */
data class User(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val hobbies: Int
)

val Users = listOf(
    User(R.drawable.ic_logo, R.string.user_name_1, 22, R.string.user_description_1),
    User(R.drawable.ic_logo, R.string.user_name_2, 16, R.string.user_description_2),
    User(R.drawable.ic_logo, R.string.user_name_3, 32, R.string.user_description_3),
    User(R.drawable.ic_logo, R.string.user_name_4, 18, R.string.user_description_4),
    User(R.drawable.ic_logo, R.string.user_name_5, 48, R.string.user_description_5),
    User(R.drawable.ic_logo, R.string.user_name_6, 14, R.string.user_description_6),
    User(R.drawable.ic_logo, R.string.user_name_7, 52, R.string.user_description_7),
    User(R.drawable.ic_logo, R.string.user_name_8, 27, R.string.user_description_8),
    User(R.drawable.ic_logo, R.string.user_name_9, 44, R.string.user_description_9)
)