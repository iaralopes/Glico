package com.example.iaralopes.glico.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.iaralopes.glico.R

class ErrorDialogFragment : androidx.fragment.app.DialogFragment() {

    var okButton : Button? = null
    var titleDialog : TextView? = null
    var textDialog : TextView? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var view = activity?.layoutInflater
            ?.inflate(R.layout.dialog_error, null)

        okButton = view?.findViewById(R.id.btn_ok)
        titleDialog = view?.findViewById(R.id.txt_title)
        textDialog = view?.findViewById(R.id.txt_message)


        val alert = AlertDialog.Builder(activity)
        alert.setView(view)
        alert.setCancelable(false)

        titleDialog?.text = arguments?.getString("titleDialog")
        textDialog?.text = arguments?.getString("textDialog")

        okButton?.setOnClickListener {
            dismiss()
        }

        return alert.create()
    }
}