package com.ssu.portfolio.expensetrackerapp.utils

import com.ssu.portfolio.expensetrackerapp.R
import com.ssu.portfolio.expensetrackerapp.model.Category

object Constants {
    const val INCOME = "INCOME"
    const val EXPENSE = "EXPENSE"

    val categories = arrayListOf(
        Category("Salary", R.drawable.ic_salary, categoryColor = R.color.category1),
        Category("Business", R.drawable.ic_business, categoryColor = R.color.category2),
        Category("Investment", R.drawable.ic_investment, categoryColor =  R.color.category3),
        Category("Loan", R.drawable.ic_loan, categoryColor = R.color.category4),
        Category("Rent", R.drawable.ic_rent, categoryColor = R.color.category5),
        Category("Other", R.drawable.ic_other, categoryColor = R.color.category6)
    )

    fun getCategoryDetails(categoryName: String): Category? {
        return categories.firstOrNull { it.categoryName == categoryName }
    }
}
