package com.autocheck

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.autocheck.adapter.CarMediaAdapter
import com.autocheck.databinding.ActivityDetailBinding
import com.autocheck.model.CarListModel.Result
import com.autocheck.model.CarMediaModel.CarMedia
import com.autocheck.model.CarMediaModel.CarMediaModel
import com.autocheck.network.ApiInterface
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : AppCompatActivity() {

    private var binding: ActivityDetailBinding? = null
    private var mediaList: ArrayList<CarMedia>? = null
    private var mediaAdapter: CarMediaAdapter? = null
    private var result: Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        result = intent.getParcelableExtra("Car")
        result?.title?.let { Log.e("Name", it) }
        Glide.with(this).load(result?.imageUrl).placeholder(R.drawable.ic_launcher_foreground).into(
            binding!!.mainImage)
        binding!!.moreImagesRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mediaList = ArrayList<CarMedia>()
        mediaAdapter = CarMediaAdapter(mediaList!!, binding!!.mainImage, binding!!.mainVideo)
        binding!!.moreImagesRv.adapter = mediaAdapter
        presentData();
        loadMedia()
    }

    private fun presentData() {
        binding?.title?.text = result?.title ?: "Title"
        binding?.marketPrice?.text = result?.marketplacePrice.toString()
        binding?.soldButton?.isEnabled ?: !result?.sold!!
        binding?.bodyTypeId?.text = result?.bodyTypeId ?: "000"
        binding?.city?.text = result?.city ?: "City"
        binding?.state?.text = result?.state ?: "State"
        binding?.depositReceived?.text = (result?.depositReceived ?: "000").toString()
        binding?.fuelType?.text = result?.fuelType ?: "Fuel"
        binding?.hasFinancing?.text = (result?.hasFinancing ?: "False").toString()
        binding?.threeDImage?.text = (result?.hasThreeDImage ?: "False").toString()
        binding?.warranty?.text = (result?.hasWarranty ?: "False").toString()
        binding?.instalments?.text = (result?.installment ?: "False").toString()
        binding?.licensePlate?.text = result?.licensePlate ?: "H"
        binding?.loanValue?.text = (result?.loanValue ?: "000").toString()
        binding?.mpOldPrice?.text = (result?.marketplaceOldPrice ?: "000").toString()
        binding?.mpVisibleDate?.text = result?.marketplaceVisibleDate ?: "01/01/2000"
        binding?.mileage?.text = (result?.mileage ?: "000").toString()
        binding?.mileageUnit?.text = result?.mileageUnit ?: "KM"
        binding?.sellingCondition?.text = result?.sellingCondition ?: "New"
        binding?.transmission?.text = result?.transmission ?: "Transmission"
        binding?.year?.text = (result?.year ?: "2000").toString()
        binding?.websiteUrl?.text = result?.websiteUrl ?: "website"
        binding?.appViewCount?.text = (result?.stats?.appViewCount ?: "0").toString()
        binding?.appViewerCount?.text = (result?.stats?.appViewerCount ?: "0").toString()
        binding?.webViewCount?.text = (result?.stats?.webViewCount ?: "0").toString()
        binding?.webViewerCount?.text = (result?.stats?.webViewerCount ?: "0").toString()
        binding?.interstCount?.text = (result?.stats?.interestCount ?: "0").toString()
        binding?.processedLoanCount?.text = (result?.stats?.processedLoanCount ?: "0").toString()
        binding?.testDrive?.text = (result?.stats?.testDriveCount ?: "0").toString()

        binding?.websiteUrl?.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(result?.websiteUrl ?: "https://hashmac.com"))
            startActivity(browserIntent)
        }

        binding?.back?.setOnClickListener{
            finish()
        }
    }

    private fun loadMedia() {
        val apiInterface = ApiInterface.create().getCarMedia(result?.id.toString())
        apiInterface.enqueue(object : Callback<CarMediaModel> {
            override fun onResponse(call: Call<CarMediaModel>, response: Response<CarMediaModel>) {
                if (response.body() != null) {
                    mediaList?.addAll(response.body()!!.carMediaList)
                    Log.e("Size", mediaList?.size.toString())
                    mediaAdapter?.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<CarMediaModel>, t: Throwable) {
                t.message?.let { Log.e("Error", it) }
            }
        })
    }
}