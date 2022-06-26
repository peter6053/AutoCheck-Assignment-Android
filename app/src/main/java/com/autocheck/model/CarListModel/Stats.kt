package com.autocheck.model.CarListModel

import android.os.Parcel
import android.os.Parcelable

data class Stats(
    val appViewCount: Int,
    val appViewerCount: Int,
    val interestCount: Int,
    val processedLoanCount: Int,
    val testDriveCount: Int,
    val webViewCount: Int,
    val webViewerCount: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(appViewCount)
        parcel.writeInt(appViewerCount)
        parcel.writeInt(interestCount)
        parcel.writeInt(processedLoanCount)
        parcel.writeInt(testDriveCount)
        parcel.writeInt(webViewCount)
        parcel.writeInt(webViewerCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Stats> {
        override fun createFromParcel(parcel: Parcel): Stats {
            return Stats(parcel)
        }

        override fun newArray(size: Int): Array<Stats?> {
            return arrayOfNulls(size)
        }
    }
}