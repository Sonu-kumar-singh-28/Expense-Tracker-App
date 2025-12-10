
import com.ssu.portfolio.expensetrackerapp.R
import com.ssu.portfolio.expensetrackerapp.model.Account
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Helper {

    fun formatDate(date: Date): String {
        return SimpleDateFormat(
            "dd MMMM, yyyy",
            Locale.getDefault()
        ).format(date)
    }

}
