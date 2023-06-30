package com.example.radius

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radius.data.repository.FacilityRepository
import com.example.radius.domain.models.Facilities
import com.example.radius.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val facilityRepository: FacilityRepository) : ViewModel() {

    private val _facilities = MutableLiveData<Resource<Facilities>>(Resource.Initialized())
    val facilities: LiveData<Resource<Facilities>> = _facilities

    fun getFacilities() {
        viewModelScope.launch {
            facilityRepository.getFacilities()
                .collect {
                    _facilities.postValue(it)
                    Log.d("RESULT", "" + _facilities.value?.data)
                }
        }
    }
}
