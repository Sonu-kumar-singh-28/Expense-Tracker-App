package com.example.emanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssu.portfolio.expensetrackerapp.adaptor.AccountClickListener
import com.ssu.portfolio.expensetrackerapp.databinding.RowAccountBinding
import com.ssu.portfolio.expensetrackerapp.model.Account

class AccountsAdapter(
    private val context: Context,
    private val accountArrayList: ArrayList<Account>,
    private val listener: AccountClickListener
) : RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder>() {

    inner class AccountsViewHolder(val binding: RowAccountBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsViewHolder {
        val binding = RowAccountBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AccountsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountsViewHolder, position: Int) {
        val account = accountArrayList[position]

        holder.binding.accountName.text = account.name

        holder.itemView.setOnClickListener {
            listener.onAccountClicked(account)
        }
    }

    override fun getItemCount() = accountArrayList.size
}
