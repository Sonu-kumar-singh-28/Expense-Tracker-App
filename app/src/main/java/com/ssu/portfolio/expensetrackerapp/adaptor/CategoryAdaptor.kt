package com.ssu.portfolio.expensetrackerapp.adaptor

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ssu.portfolio.expensetrackerapp.R
import com.ssu.portfolio.expensetrackerapp.databinding.SampleCategoryItemBinding
import com.ssu.portfolio.expensetrackerapp.model.Category

val categoryColors = listOf(
    R.color.category1,
    R.color.category2,
    R.color.category3,
    R.color.category4,
    R.color.category5,
    R.color.category6
)

class CategoryAdapter(
    private val context: Context,
    private val categories: ArrayList<Category>,
    private val categoryClickListener: CategoryClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: SampleCategoryItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = SampleCategoryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categories[position]
        holder.binding.categoryText.text = item.categoryName
        holder.binding.iconCategory.setImageResource(item.categoryImage)
        holder.binding.categoryText.setTextColor(ContextCompat.getColor(context, R.color.black))

        val colorRes = categoryColors[position % categoryColors.size]
        holder.binding.iconCategory.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))

        // Click listener
        holder.itemView.setOnClickListener {
            categoryClickListener.onCategoryClicked(item)
        }
    }

    override fun getItemCount(): Int = categories.size
}
