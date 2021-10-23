package com.fan.activitytest.bean

import android.os.Parcel
import android.os.Parcelable

data class Teacher(val name: String, val age: Int) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Teacher> {
        override fun createFromParcel(parcel: Parcel): Teacher {
            return Teacher(parcel)
        }

        override fun newArray(size: Int): Array<Teacher?> {
            return arrayOfNulls(size)
        }
    }

}
