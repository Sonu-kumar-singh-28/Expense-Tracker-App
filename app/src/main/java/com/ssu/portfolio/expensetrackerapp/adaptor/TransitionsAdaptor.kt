package com.example.emanager.adapters

import Helper
import Transaction
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ssu.portfolio.expensetrackerapp.R
import com.ssu.portfolio.expensetrackerapp.databinding.RowTransictionBinding
import com.ssu.portfolio.expensetrackerapp.utils.Constants

class TransactionsAdapter(
    private val context: Context,
    private val transactions: ArrayList<Transaction>
) : RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    private val helper = Helper()

    inner class TransactionViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = RowTransictionBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_transiction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]

        holder.binding.transictionammount.text = transaction.amount.toString()
        holder.binding.accountLBL.text = transaction.account
        holder.binding.transictioncategory.text = transaction.category
        holder.binding.transictiondate.text = helper.formatDate(transaction.date)

        val accountColor = getAccountsColor(transaction.account)
        holder.binding.accountLBL.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(context, accountColor))

        if (transaction.type == Constants.INCOME) {
            holder.binding.transictionammount.setTextColor(
                ContextCompat.getColor(context, R.color.greencolor)
            )
        } else if (transaction.type == Constants.EXPENSE) {
            holder.binding.transictionammount.setTextColor(
                ContextCompat.getColor(context, R.color.redcolor)
            )
        }

        val categoryDetails = Constants.getCategoryDetails(transaction.category)
        if (categoryDetails != null) {
            holder.binding.categoryIcon.setImageResource(categoryDetails.categoryImage)
            holder.binding.categoryIcon.backgroundTintList =
                ContextCompat.getColorStateList(context, categoryDetails.categoryColor)
        } else {
            holder.binding.categoryIcon.setImageResource(R.drawable.default_selector)
            holder.binding.categoryIcon.backgroundTintList =
                ContextCompat.getColorStateList(context, R.color.green)
        }
    }

    override fun getItemCount(): Int = transactions.size

    fun getAccountsColor(accountName: String): Int {
        return when (accountName) {
            "Bank" -> R.color.bank_color
            "Cash" -> R.color.cash_color
            "Card" -> R.color.card_color
            else -> R.color.default_color
        }
    }
}
