package com.example.iaralopes.glico.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.base.OnItemDialogFragmentClickListener

class SelectOptDialogFragment : androidx.fragment.app.DialogFragment() {

    var positiveButton : Button? = null
    var negativeButton : Button? = null
    var titleDialog : TextView? = null
    var textDialog : TextView? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var view = activity?.layoutInflater
            ?.inflate(R.layout.dialog_option, null)

        positiveButton = view?.findViewById(R.id.btn_positive)
        negativeButton = view?.findViewById(R.id.btn_negative)
        titleDialog = view?.findViewById(R.id.txt_title)
        textDialog = view?.findViewById(R.id.txt_message)


        val alert = AlertDialog.Builder(activity)
        alert.setView(view)
        alert.setCancelable(false)

        titleDialog?.text = arguments?.getString("titleDialog")
        textDialog?.text = arguments?.getString("textDialog")


        positiveButton?.setOnClickListener {
            (activity as(OnItemDialogFragmentClickListener))
                .onItemDialogClick(R.id.btn_positive)
            dismiss()
        }
        negativeButton?.setOnClickListener {
            dismiss()
        }

        return alert.create()
    }
}