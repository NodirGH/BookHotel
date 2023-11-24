package my.booking.bookhotel.mobile.utils

import android.text.Editable
import android.text.TextWatcher

class PhoneNumberFormattingTextWatcher : TextWatcher {

    private val MAX_PHONE_NUMBER_LENGTH = 11

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        if (s == null || s.isEmpty()) {
            return
        }

        val digits = s.toString().replace("[^\\d]".toRegex(), "")

        val formatted = buildString {
            append("+7 (")
            append(digits.substring(1, minOf(4, digits.length)))
            if (digits.length > 4) {
                append(") ")
                append(digits.substring(4, minOf(7, digits.length)))
            }
            if (digits.length > 7) {
                append("-")
                append(digits.substring(7, minOf(9, digits.length)))
            }
            if (digits.length > 9) {
                append("-")
                append(digits.substring(9, minOf(MAX_PHONE_NUMBER_LENGTH, digits.length)))
            }
        }

        if (s.toString() != formatted) {
            s.replace(0, s.length, formatted, 0, formatted.length)
        }
    }
}