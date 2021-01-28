package com.test.automobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.test.automobile.R
import com.test.automobile.model.carManufacturer.CarManufactures
import com.test.automobile.model.carModel.CarModels

class ExpandableListViewAutomobileAdapter internal constructor (
    private val context: Context,
    private val manufacturerList: List<CarManufactures>, private val modelsList: HashMap<String, List<CarModels>>) :BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return manufacturerList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
         return this.modelsList[this.manufacturerList[groupPosition].id]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
      return manufacturerList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.modelsList[this.manufacturerList[groupPosition].id]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val manufacture = getGroup(groupPosition) as CarManufactures
        if (convertView ==null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.manufacturer, null)
        }
        val brandName = convertView!!.findViewById<TextView>(R.id.brandName)
        val founderName = convertView.findViewById<TextView>(R.id.founderName)
        val foundationDate = convertView.findViewById<TextView>(R.id.foundationDate)

        brandName.text = manufacture.brand_name
        founderName.text = manufacture.founder_names.joinToString(", ")
        foundationDate.text = manufacture.foundationDate


        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val currentModel = getChild(groupPosition, childPosition) as CarModels
        if (convertView ==null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.models, null)
        }
        val model = convertView!!.findViewById<TextView>(R.id.model)
        val date = convertView!!.findViewById<TextView>(R.id.date)
        model.text = currentModel.modelName
        date.text = currentModel.releaseDate
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

}