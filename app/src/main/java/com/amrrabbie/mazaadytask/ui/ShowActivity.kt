package com.amrrabbie.mazaadytask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.amrrabbie.mazaadytask.R
import com.amrrabbie.mazaadytask.databinding.ActivityDataBinding
import com.amrrabbie.mazaadytask.databinding.ActivityShowBinding
import com.amrrabbie.mazaadytask.model.valuesModel.SelectedValues
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowActivity : AppCompatActivity() {

    lateinit var binding: ActivityShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_show)
        binding=ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedvalues = intent.getSerializableExtra("selectedvalues") as? SelectedValues

        if(selectedvalues != null){
            showViews(selectedvalues)
        }

        binding.btnok.setOnClickListener {
            startActivity(Intent(this,DesignActivity::class.java))
        }


    }

    private fun showViews(selectedvalues: SelectedValues) {
        if(selectedvalues?.category != null){
            binding.categoryll.visibility=View.VISIBLE
            binding.txtcategory.text=selectedvalues?.category
        }else{
            binding.categoryll.visibility=View.GONE
        }

        if(selectedvalues?.subcategory != null){
            binding.subcategoryll.visibility=View.VISIBLE
            binding.txtsubcategory.text=selectedvalues?.subcategory
        }else{
            binding.subcategoryll.visibility=View.GONE
        }

        if(selectedvalues?.processtype != null){
            binding.processtypell.visibility=View.VISIBLE
            binding.txtprocesstype.text=selectedvalues?.processtype
        }else{
            binding.processtypell.visibility=View.GONE
        }

        if(selectedvalues?.brand != null){
            binding.brandll.visibility=View.VISIBLE
            binding.txtbrand.text=selectedvalues?.brand
        }else{
            binding.brandll.visibility=View.GONE
        }

        if(selectedvalues?.transmissiontype != null){
            binding.transmissiontypell.visibility=View.VISIBLE
            binding.txttransmissiontype.text=selectedvalues?.transmissiontype
        }else{
           binding.transmissiontypell.visibility=View.GONE
        }

        if(selectedvalues?.fueltype != null){
            binding.fueltypell.visibility=View.VISIBLE
            binding.txtfueltype.text=selectedvalues?.fueltype
        }else{
            binding.fueltypell.visibility=View.GONE
        }

        if(selectedvalues?.condition != null){
            binding.conditionll.visibility=View.VISIBLE
            binding.txtcondition.text=selectedvalues?.condition
        }else{
            binding.conditionll.visibility=View.GONE
        }

        if(selectedvalues?.color != null){
            binding.colorll.visibility=View.VISIBLE
            binding.txtcolor.text=selectedvalues?.color
        }else{
            binding.colorll.visibility=View.GONE
        }

    }


}