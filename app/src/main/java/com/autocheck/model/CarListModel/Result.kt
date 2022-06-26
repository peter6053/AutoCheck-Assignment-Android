package com.autocheck.model.CarListModel

import android.os.Parcel
import android.os.Parcelable

data class Result(
    val bodyTypeId: String?,
    val city: String?,
    val depositReceived: Boolean,
    val fuelType: String?,
    val hasFinancing: Boolean,
    val hasThreeDImage: Boolean,
    val hasWarranty: Boolean,
    val id: String?,
    val imageUrl: String?,
    val installment: Double,
    val licensePlate: String?,
    val loanValue: Double,
    val marketplaceOldPrice: Double,
    val marketplacePrice: Double,
    val marketplaceVisibleDate: String?,
    val mileage: Double,
    val mileageUnit: String?,
    val sellingCondition: String?,
    val sold: Boolean,
    val state: String?,
    val stats: Stats?,
    val title: String?,
    val transmission: String?,
    val websiteUrl: String?,
    val year: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readParcelable(Stats::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(bodyTypeId)
        parcel.writeString(city)
        parcel.writeByte(if (depositReceived) 1 else 0)
        parcel.writeString(fuelType)
        parcel.writeByte(if (hasFinancing) 1 else 0)
        parcel.writeByte(if (hasThreeDImage) 1 else 0)
        parcel.writeByte(if (hasWarranty) 1 else 0)
        parcel.writeString(id)
        parcel.writeString(imageUrl)
        parcel.writeDouble(installment)
        parcel.writeString(licensePlate)
        parcel.writeDouble(loanValue)
        parcel.writeDouble(marketplaceOldPrice)
        parcel.writeDouble(marketplacePrice)
        parcel.writeString(marketplaceVisibleDate)
        parcel.writeDouble(mileage)
        parcel.writeString(mileageUnit)
        parcel.writeString(sellingCondition)
        parcel.writeByte(if (sold) 1 else 0)
        parcel.writeString(state)
        parcel.writeParcelable(stats, flags)
        parcel.writeString(title)
        parcel.writeString(transmission)
        parcel.writeString(websiteUrl)
        parcel.writeInt(year)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}