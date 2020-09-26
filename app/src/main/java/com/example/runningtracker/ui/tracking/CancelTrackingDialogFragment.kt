package com.example.runningtracker.ui.tracking

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.runningtracker.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CancelTrackingDialogFragment: DialogFragment() {

    private var cancelListener: (() -> Unit)? = null

    fun setCancelListener(listener: (() -> Unit)) {
        cancelListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("Cancel the run")
            .setMessage("Do you want to cancel run?")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Yes") { _, _ ->
                cancelListener?.invoke()
            }
            .setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
    }
}