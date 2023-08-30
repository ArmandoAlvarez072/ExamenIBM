package armando.alvarez.examenibm

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import armando.alvarez.examenibm.databinding.DialogFragmentLoadingBinding

class LoadingDialogFragment : DialogFragment() {

    private var binding: DialogFragmentLoadingBinding? = null

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let { activity ->
            binding = DialogFragmentLoadingBinding.inflate(LayoutInflater.from(context))

            binding?.let {
                val builder = AlertDialog.Builder(activity)
                    .setView(it.root)
                    .setCancelable(false)

                val dialog = builder.create()
                dialog.setCanceledOnTouchOutside(false)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                return dialog
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

}