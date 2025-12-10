package com.ssu.portfolio.expensetrackerapp.views.fragements

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emanager.adapters.AccountsAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssu.portfolio.expensetrackerapp.R
import com.ssu.portfolio.expensetrackerapp.adaptor.AccountClickListener
import com.ssu.portfolio.expensetrackerapp.adaptor.CategoryAdapter
import com.ssu.portfolio.expensetrackerapp.adaptor.CategoryClickListener
import com.ssu.portfolio.expensetrackerapp.databinding.FragmentAddTransictionFragementBinding
import com.ssu.portfolio.expensetrackerapp.databinding.ListDailogBinding
import com.ssu.portfolio.expensetrackerapp.model.Account
import com.ssu.portfolio.expensetrackerapp.model.Category
import com.ssu.portfolio.expensetrackerapp.utils.Constants.categories
import java.util.Calendar

class AddTransictionFragement : BottomSheetDialogFragment(), CategoryClickListener {

    private var _binding: FragmentAddTransictionFragementBinding? = null
    private val binding get() = _binding!!
    private var categoryDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTransictionFragementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.incomeBtn.setOnClickListener {
            binding.incomeBtn.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.income_selector)
            binding.expenseBtn.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.default_selector)
            binding.incomeBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.greencolor))
            binding.expenseBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.textcolor))
        }

        binding.expenseBtn.setOnClickListener {
            binding.expenseBtn.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.expense_selector)
            binding.incomeBtn.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.default_selector)
            binding.expenseBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.textcolor))
            binding.incomeBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.redcolor))
        }

        binding.dateEdit.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    binding.dateEdit.setText("$day/${month + 1}/$year")
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        binding.categoryEdit.setOnClickListener {

            val dialogBinding = ListDailogBinding.inflate(layoutInflater)

            categoryDialog = AlertDialog.Builder(requireContext())
                .setView(dialogBinding.root)
                .create()

            val adapter = CategoryAdapter(requireContext(), categories, this)

            dialogBinding.categoryrecycleview.layoutManager =
                GridLayoutManager(requireContext(), 3, LinearLayoutManager.VERTICAL, false)
            dialogBinding.categoryrecycleview.adapter = adapter

            categoryDialog?.show()
        }
        binding.accountEdit.setOnClickListener {

            val dialogBinding = ListDailogBinding.inflate(layoutInflater)
            val accountsDialog = AlertDialog.Builder(requireContext()).create()
            accountsDialog.setView(dialogBinding.root)

            val accounts = arrayListOf(
                Account(0.0, "Cash"),
                Account(0.0, "Bank"),
                Account(0.0, "Paytm"),
                Account(0.0, "EasyPay"),
                Account(0.0, "Other")
            )

            val accountsAdapter = AccountsAdapter(
                requireContext(),
                accounts,
                object : AccountClickListener {
                    override fun onAccountClicked(account: Account) {
                        binding.accountEdit.setText(account.name)
                        accountsDialog.dismiss()
                    }
                }
            )

            dialogBinding.categoryrecycleview.layoutManager = LinearLayoutManager(context)
            dialogBinding.categoryrecycleview.adapter = accountsAdapter
            accountsDialog.show()
            accountsDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

    }

    override fun onCategoryClicked(category: Category) {
        binding.categoryEdit.setText(category.categoryName)
        categoryDialog?.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
