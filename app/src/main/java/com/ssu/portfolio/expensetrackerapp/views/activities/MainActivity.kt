package com.ssu.portfolio.expensetrackerapp.views.activities

import Transaction
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emanager.adapters.TransactionsAdapter
import com.google.firebase.auth.FirebaseAuth
import com.ssu.portfolio.expensetrackerapp.R
import com.ssu.portfolio.expensetrackerapp.databinding.ActivityMainBinding
import com.ssu.portfolio.expensetrackerapp.utils.Constants
import com.ssu.portfolio.expensetrackerapp.views.fragements.AddTransictionFragement
import io.realm.kotlin.Realm
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainToolbar) { view, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            view.setPadding(0, topInset, 0, 0)
            insets
        }

        auth= FirebaseAuth.getInstance()
        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = "Transactions"
        bottomSheetFragmentButton()
        mainScreenCalendarButtonWorkingFunction()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    private fun bottomSheetFragmentButton() {
        binding.floatingActionButton.setOnClickListener {
            AddTransictionFragement().show(supportFragmentManager, null)
        }
    }

    private fun mainScreenCalendarButtonWorkingFunction() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        fun updateDateDisplay() {
            binding.txtDate.text = dateFormat.format(calendar.time)
        }

        updateDateDisplay()

        binding.nxtBtnDate.setOnClickListener {
            calendar.add(Calendar.DATE, 1)
            updateDateDisplay()
        }

        binding.btnPrevDate.setOnClickListener {
            calendar.add(Calendar.DATE, -1)
            updateDateDisplay()
        }

        val transactions = mutableListOf(
            Transaction(
                type = Constants.INCOME,
                category = "Business",
                account = "Cash",
                note = "Some note here",
                date = Date(),
                amount = 900.0,
                id = 1
            ),
            Transaction(
                type = Constants.EXPENSE,
                category = "Investment",
                account = "Bank",
                note = "Some note here",
                date = Date(),
                amount = 500.0,
                id = 4
            ),
            Transaction(
                type = Constants.EXPENSE,
                category = "Rent",
                account = "Cards",
                note = "Some note here",
                date = Date(),
                amount = 400.0,
                id = 2
            ),
            Transaction(
                type = Constants.INCOME,
                category = "Business",
                account = "Cash",
                note = "Some note here",
                date = Date(),
                amount = 200.0,
                id = 3
            )
        )

        val transactionsAdapter = TransactionsAdapter(this, transactions as ArrayList<Transaction>)
        binding.transictionlist.layoutManager = LinearLayoutManager(this)
        binding.transictionlist.adapter = transactionsAdapter
    }

}
