package com.fan.fanweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fan.fanweather.logic.Repository
import com.fan.fanweather.logic.model.Place

class PlaceViewModel:ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData){ query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query:String){
        searchLiveData.value = query

    }



}