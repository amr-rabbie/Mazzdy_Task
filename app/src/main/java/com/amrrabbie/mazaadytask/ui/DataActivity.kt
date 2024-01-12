package com.amrrabbie.mazaadytask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amrrabbie.mazaadytask.R
import com.amrrabbie.mazaadytask.adapters.BrandsAdapter
import com.amrrabbie.mazaadytask.adapters.CategoriesAdapter
import com.amrrabbie.mazaadytask.adapters.ProcessTypesAdapter
import com.amrrabbie.mazaadytask.adapters.SubCategoriesAdapter
import com.amrrabbie.mazaadytask.databinding.ActivityDataBinding
import com.amrrabbie.mazaadytask.model.categoriesModel.CategoriesItem
import com.amrrabbie.mazaadytask.model.categoriesModel.ChildrenItem
import com.amrrabbie.mazaadytask.model.propetiesModel.OptionsItem
import com.amrrabbie.mazaadytask.model.valuesModel.SelectedValues
import com.amrrabbie.mazaadytask.utils.Constants
import com.amrrabbie.mazaadytask.viewmodel.CategoriesViewModel
import com.amrrabbie.mazaadytask.viewmodel.PropetiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataActivity : AppCompatActivity() {

    lateinit var binding:ActivityDataBinding

    lateinit var viewmodel:CategoriesViewModel
    lateinit var propetiesviewmodel:PropetiesViewModel

    lateinit var categoriesList:List<CategoriesItem>
    var mcategory:String?=null
    var msubcategory:String?=null
    var mprocesstype:String?=null
    var mbrand:String?=null
    var mtransmissiontype:String?=null
    var mfueltype:String?=null
    var mcondition:String?=null
    var mcolor:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_data)

        binding=ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel=ViewModelProvider(this).get(CategoriesViewModel::class.java)

        propetiesviewmodel=ViewModelProvider(this).get(PropetiesViewModel::class.java)

        getCategories()

        binding.btnsubmit.setOnClickListener{
            if(mcategory != null && msubcategory != null) {
                var selectedvalues = SelectedValues(
                    mcategory,
                    msubcategory,
                    mprocesstype,
                    mbrand,
                    mtransmissiontype,
                    mfueltype,
                    mcondition,
                    mcolor
                )
                var intent = Intent(this, ShowActivity::class.java)
                intent.putExtra("selectedvalues", selectedvalues)
                startActivity(intent)
            }else{
                Toast.makeText(this,"You must re select sub category",Toast.LENGTH_LONG).show()
            }

        }


    }

    private fun getCategories() {
        viewmodel.getCategories()

        viewmodel.categoriesListData.observe(this, Observer { response ->
            if(! response.data?.categories.isNullOrEmpty()){
                categoriesList=ArrayList<CategoriesItem>()
                categoriesList= response.data?.categories as List<CategoriesItem>
                fillCategoriesSpinner()
            }
        })
    }

    private fun fillCategoriesSpinner() {
        val adp = CategoriesAdapter(this, categoriesList)
        binding.categoriesSpinner.setAdapter(adp)

        binding.categoriesSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val category: CategoriesItem = categoriesList.get(i)
                var catId = category.id
                mcategory=category.slug
                getSubCategories(catId)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })
    }

    private fun getSubCategories(catId: Int?) {
        var subcategoriesList=ArrayList<ChildrenItem>()
        for(item in categoriesList){
            if(item.id == catId){
                subcategoriesList.addAll(item.children?.filterNotNull()?: arrayListOf())
                fillgetSubCategoriesSpinner(subcategoriesList)
            }
        }
    }

    private fun fillgetSubCategoriesSpinner(subcategoriesList: ArrayList<ChildrenItem>) {
        val adp = SubCategoriesAdapter(this, subcategoriesList)
        binding.subcategoriesSpinner.setAdapter(adp)

        binding.subcategoriesSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val subcategory: ChildrenItem = subcategoriesList.get(i)
                var subcatId = subcategory.id
                msubcategory=subcategory.slug
                if (subcatId != null) {
                    getPropeties(subcatId)
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })
    }

    private fun getPropeties(subcatId: Int) {
        propetiesviewmodel.getProperties(subcatId)

        hideViews()

        propetiesviewmodel.propetiesListData.observe(this, Observer { response ->
            if(! response.data.isNullOrEmpty()){
                for(item in response.data){

                    if(item?.slug == "process-type"){
                        val processTypesList=item?.options
                        if(!processTypesList.isNullOrEmpty()) {
                            fillProcessTypesSpinner(processTypesList as List<OptionsItem>)
                            binding.procestypesfl.visibility=View.VISIBLE
                        }else{
                            binding.procestypesfl.visibility=View.GONE
                        }
                    }
                    if(item?.slug == "brand"){
                        val brandList=item?.options
                        if(!brandList.isNullOrEmpty()) {
                            fillBrandsSpinner(brandList as List<OptionsItem>)
                            binding.brandsfl.visibility=View.VISIBLE
                        }else{
                            binding.brandsfl.visibility=View.GONE
                        }
                    }
                    if(item?.slug == "transmission-type"){
                        val transmissiontypeList=item?.options
                        if(!transmissiontypeList.isNullOrEmpty()) {
                            fillTransmissiontypesSpinner(transmissiontypeList as List<OptionsItem>)
                            binding.transmissiontypefl.visibility=View.VISIBLE
                        }else{
                            binding.transmissiontypefl.visibility=View.GONE
                        }
                    }
                    if(item?.slug == "fuel-type"){
                        val fueltypeList=item?.options
                        if(!fueltypeList.isNullOrEmpty()) {
                            fillfueltypeflSpinner(fueltypeList as List<OptionsItem>)
                            binding.fueltypefl.visibility=View.VISIBLE
                        }else{
                            binding.fueltypefl.visibility=View.GONE
                        }
                    }
                    if(item?.slug == "condition"){
                        val conditionList=item?.options
                        if(!conditionList.isNullOrEmpty()) {
                            fillconditionSpinner(conditionList as List<OptionsItem>)
                            binding.conditionfl.visibility=View.VISIBLE
                        }else{
                            binding.conditionfl.visibility=View.GONE
                        }
                    }
                    if(item?.slug == "color"){
                        val colorList=item?.options
                        if(!colorList.isNullOrEmpty()) {
                            fillcolorSpinner(colorList as List<OptionsItem>)
                            binding.colorfl.visibility=View.VISIBLE
                        }else{
                            binding.colorfl.visibility=View.GONE
                        }
                    }
                }

            }
        })
    }

    private fun hideViews() {
        binding.procestypesfl.visibility=View.GONE
        binding.brandsfl.visibility=View.GONE
        binding.transmissiontypefl.visibility=View.GONE
        binding.fueltypefl.visibility=View.GONE
        binding.conditionfl.visibility=View.GONE
        binding.colorfl.visibility=View.GONE
    }


    private fun fillProcessTypesSpinner(processTypesList: List<OptionsItem>) {

        val adp = ProcessTypesAdapter(this, processTypesList)
        binding.processtypesSpinner.setAdapter(adp)

        binding.processtypesSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val options: OptionsItem = processTypesList.get(i)
                var optionId = options.id
                mprocesstype=options.slug
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })
    }

    private fun fillBrandsSpinner(brandssItems: List<OptionsItem>) {
        val adp = BrandsAdapter(this, brandssItems)
        binding.brandsSpinner.setAdapter(adp)

        binding.brandsSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val options: OptionsItem = brandssItems.get(i)
                var brandId = options.id
                mbrand=options.slug
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })
    }

    private fun fillTransmissiontypesSpinner(optionsItems: List<OptionsItem>) {
        val adp = BrandsAdapter(this, optionsItems)
        binding.transmissiontypeSpinner.setAdapter(adp)

        binding.transmissiontypeSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val options: OptionsItem = optionsItems.get(i)
                var TransmissiontypeId = options.id
                mtransmissiontype=options.slug
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })
    }

    private fun fillfueltypeflSpinner(optionsItems: List<OptionsItem>) {
        val adp = BrandsAdapter(this, optionsItems)
        binding.fueltypeSpinner.setAdapter(adp)

        binding.fueltypeSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val options: OptionsItem = optionsItems.get(i)
                var fueltypeId = options.id
                mfueltype=options.slug
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })
    }

    private fun fillconditionSpinner(optionsItems: List<OptionsItem>) {
        val adp = BrandsAdapter(this, optionsItems)
        binding.conditionSpinner.setAdapter(adp)

        binding.conditionSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val options: OptionsItem = optionsItems.get(i)
                var conditionId = options.id
                mcondition=options.slug
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })
    }

    private fun fillcolorSpinner(optionsItems: List<OptionsItem>) {
        val adp = BrandsAdapter(this, optionsItems)
        binding.colorSpinner.setAdapter(adp)

        binding.colorSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val options: OptionsItem = optionsItems.get(i)
                var colorId = options.id
                mcolor=options.slug
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        //mcategory=null
        msubcategory=null
        mprocesstype=null
        mbrand=null
        mtransmissiontype=null
        mfueltype=null
        mcondition=null
        mcolor=null
    }



}