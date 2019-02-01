package com.example.iaralopes.glico.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.base.view.listeners.OnItemDialogFragmentClickListener

class AccessDialogFragment : androidx.fragment.app.DialogFragment() {

    var signInButton: Button? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var view = activity?.layoutInflater
            ?.inflate(R.layout.dialog_access, null)

        signInButton = view?.findViewById(R.id.btn_enter)

        val alert = AlertDialog.Builder(activity)
        alert.setView(view)
        alert.setCancelable(false)


        signInButton?.setOnClickListener {
            (activity as (OnItemDialogFragmentClickListener))
                .onItemDialogClick(R.id.btn_enter)
            dismiss()
        }

        val dialog = alert.create()
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }
}