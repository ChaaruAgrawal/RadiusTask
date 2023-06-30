package com.example.radius

import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.radius.data.repository.FacilityRepository
import com.example.radius.databinding.ActivityMainBinding
import com.example.radius.domain.models.Exclusion
import com.example.radius.domain.models.Facilities
import com.example.radius.domain.models.Option
import com.example.radius.util.Resource
import com.example.radius.util.viewBinding
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var facilityRepository: FacilityRepository

    private var facilities = Facilities()

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        (application as RadiusApplication).appComponent.inject(this)

        val mainViewModel = MainViewModel(facilityRepository)
        mainViewModel.getFacilities()
        mainViewModel.facilities.observe(this) {
            if (it is Resource.Success) {
                facilities = it.data ?: Facilities()
                setUpView()
            }
        }
    }

    private fun setUpView() {
        val facilitiesList = facilities.facilities
        val facilityNames = facilitiesList.map { it.name }
        binding.propertyType.text = facilityNames[0]
        binding.numberOfRooms.text = facilityNames[1]
        binding.otherFacilities.text = facilityNames[2]

        val propertyList = facilitiesList[0].options
        val propertyNames = propertyList.map { it.name }
        val propertyArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, propertyNames)
        binding.listPropertyType.setAdapter(propertyArrayAdapter)

        val numberOfRoomsList = facilitiesList[1].options
        val numberOfRooms = numberOfRoomsList.map { it.name }
        val numberOfRoomsArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, numberOfRooms)
        binding.listNumberOfRooms.setAdapter(numberOfRoomsArrayAdapter)

        val otherFacilitiesList = facilitiesList[2].options
        val otherFacilities = otherFacilitiesList.map { it.name }
        val otherFacilitiesArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, otherFacilities)
        binding.listOtherFacilities.setAdapter(otherFacilitiesArrayAdapter)

        setOnClickListeners(propertyList, numberOfRoomsList, otherFacilitiesList)
    }

    private fun setOnClickListeners(
        propertyList: List<Option>,
        numberOfRoomsList: List<Option>,
        otherFacilitiesList: List<Option>
    ) {
        var propertyIdSelected = -1
        var numberOfRoomsIdSelected = -1
        var otherFacilitiesSelected = -1

        binding.listPropertyType.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            propertyIdSelected = propertyList[position].id
            if (numberOfRoomsIdSelected != -1 && otherFacilitiesSelected != -1) {
                val exclusions = getExclusions(
                    arrayListOf(
                        Exclusion(facility_id = facilities.facilities[0].facility_id, options_id = propertyIdSelected),
                        Exclusion(facility_id = facilities.facilities[1].facility_id, options_id = numberOfRoomsIdSelected),
                        Exclusion(facility_id = facilities.facilities[2].facility_id, options_id = otherFacilitiesSelected)
                    )
                )
            }
        }

        binding.listNumberOfRooms.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            numberOfRoomsIdSelected = numberOfRoomsList[position].id
            if (propertyIdSelected != -1 && otherFacilitiesSelected != -1) {
                val exclusions = getExclusions(
                    arrayListOf(
                        Exclusion(facility_id = facilities.facilities[0].facility_id, options_id = propertyIdSelected),
                        Exclusion(facility_id = facilities.facilities[1].facility_id, options_id = numberOfRoomsIdSelected),
                        Exclusion(facility_id = facilities.facilities[2].facility_id, options_id = otherFacilitiesSelected)
                    )
                )
            }
        }

        binding.listOtherFacilities.onItemClickListener = OnItemClickListener { _, _, position,_ ->
            otherFacilitiesSelected = otherFacilitiesList[position].id
            if (propertyIdSelected != -1 && numberOfRoomsIdSelected != -1) {
                val exclusions = getExclusions(
                    arrayListOf(
                        Exclusion(facility_id = facilities.facilities[0].facility_id, options_id = propertyIdSelected),
                        Exclusion(facility_id = facilities.facilities[1].facility_id, options_id = numberOfRoomsIdSelected),
                        Exclusion(facility_id = facilities.facilities[2].facility_id, options_id = otherFacilitiesSelected)
                    )
                )
            }
        }
    }

    private fun getExclusions(optionsSelected: List<Exclusion>): List<Exclusion> {
        return optionsSelected // return list of exclusion from database
    }

}
