package ir.homework.hw7problem2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class UserAnswer : Parcelable {
    NO_ANSWER,
    TRUE,
    FALSE
}