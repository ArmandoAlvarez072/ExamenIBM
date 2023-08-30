package armando.alvarez.examenibm

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView

class BaseActivity : AppCompatActivity() {

    private var dialogIsDisplayed = false
    private var dialogLoadingIsDisplayed = false
    private var loadingDialog: LoadingDialogFragment? = null

    @SuppressLint("InflateParams")
    fun showAlert(
        title: String,
        message: String,
        function: (() -> Unit)?
    ) {
        val layout = layoutInflater.inflate(R.layout.dialog_fragment_alert, null)

        val displayRectangle = Rect()
        val window: Window = this.window
        window.decorView.getWindowVisibleDisplayFrame(displayRectangle)
        layout.minimumWidth = (displayRectangle.width() * 0.9f).toInt()

        val dialog = Dialog(this)
        dialog.setContentView(layout)
        val btn = layout.findViewById<Button>(R.id.btnAlertAccept)
        btn.setOnClickListener {
            dialogIsDisplayed = false

            function?.let {
                it()
            }

            dialog.dismiss()
        }

        val text = layout.findViewById<MaterialTextView>(R.id.tvAlertBody)
        text.text = message

        val titleText = layout.findViewById<MaterialTextView>(R.id.tvAlertTitle)
        titleText.text = title

        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (!dialogIsDisplayed) {
            dialog.show()
            dialogIsDisplayed = true
        }
    }

    @SuppressLint("InflateParams")
    private fun showAlertYesNo(
        title: String,
        message: String,
        functionYes: (() -> Unit)?,
        functionNo: (() -> Unit)?,
    ) {
        val layout = layoutInflater.inflate(R.layout.dialog_fragment_yes_no, null)

        val displayRectangle = Rect()


        val window: Window = window
        window.decorView.getWindowVisibleDisplayFrame(displayRectangle)
        layout.minimumWidth = (displayRectangle.width() * 0.9f).toInt()

        val dialog = Dialog(this)
        dialog.setContentView(layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val titleText = layout.findViewById<MaterialTextView>(R.id.tvAlertTitle)
        titleText.text = title

        val text = layout.findViewById<MaterialTextView>(R.id.tvAlertBody)
        text.text = message

        val btn = layout.findViewById<Button>(R.id.btnAlertYes)
        btn.setOnClickListener {
            functionYes?.let {
                it()
            }
            dialog.dismiss()
        }

        val btnCancel = layout.findViewById<Button>(R.id.btnAlertNo)
        btnCancel.setOnClickListener {
            functionNo?.let {
                it()
            }
            dialog.dismiss()
        }

        dialog.show()

    }

    fun showLoading() {
        if (!dialogLoadingIsDisplayed) {
            dialogLoadingIsDisplayed = true
            loadingDialog = LoadingDialogFragment()
            loadingDialog?.show(
                supportFragmentManager,
                LoadingDialogFragment::class.java.simpleName
            )
        }
    }
}
