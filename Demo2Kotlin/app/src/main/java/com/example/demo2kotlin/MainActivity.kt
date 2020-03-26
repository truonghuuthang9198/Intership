@file:Suppress("DEPRECATION")

package com.example.demo2kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_details.*
import kotlinx.android.synthetic.main.layout_details.view.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    lateinit var et_name: EditText 
    lateinit var btn_send: Button
    lateinit var et_CMND: EditText
    lateinit var ra_trungcap: RadioButton
    lateinit var ra_caodang: RadioButton
    lateinit var ra_daihoc: RadioButton
    lateinit var cb_DocBao: CheckBox
    lateinit var cb_DocSach: CheckBox
    lateinit var cb_DocCode: CheckBox
    lateinit var radio_grop: RadioGroup
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }
    private fun initView()
    {
        et_name = findViewById(R.id.editTextHoTen)
        et_CMND = findViewById(R.id.editTextCMND)
        btn_send = findViewById(R.id.BtnGuiThongTin)
        ra_trungcap = findViewById(R.id.rdBtnTrungCap)
        ra_caodang = findViewById(R.id.rdBtnCaoDang)
        ra_daihoc = findViewById(R.id.rdBtnDaiHoc)
        cb_DocBao = findViewById(R.id.ckBoxDocBao)
        cb_DocCode = findViewById(R.id.ckBoxDocCoding)
        cb_DocSach = findViewById(R.id.ckBoxDocSach)
        radio_grop = findViewById(R.id.radioGroup)

        btn_send.setOnClickListener{
            getInfo()
        }
    }

    private fun getInfo(){
        if (et_name.text.length>3 && checkNumber(et_CMND.text.toString()))
        {
            Log.d("Thang","Length name: "+et_name.text.length + "Check number: "+checkNumber(et_CMND.text.toString()))
            if (!cb_DocBao.isChecked && !cb_DocSach.isChecked && !cb_DocCode.isChecked)
            {
                Log.d("Thang","Not check")
            }
            else
            {
                Log.d("Thang","Đọc báo: "+cb_DocBao.isChecked+ "Đọc sách: "+cb_DocSach.isChecked+" Đọc code: "+cb_DocCode.isChecked)
            }
            val mDialogview = LayoutInflater.from(this).inflate(R.layout.layout_details,null)
            mDialogview.EdtDetailHoTen.setText(et_name.text.toString())
            mDialogview.EdtCMND.setText(et_CMND.text.toString())
            try {
                val radio: RadioButton = findViewById(radio_grop.checkedRadioButtonId)
                if (radio ==null)
                {
                    Toast.makeText(this,"Chưa chọn bằng cấp",Toast.LENGTH_SHORT).show()
                }
                else {
                    mDialogview.EdtBangCap.setText(radio.text)
                    if(ckBoxDocBao.isChecked){
                        mDialogview.EdtSoThich.setText(ckBoxDocBao.text)
                    }
                    val mBuilder = AlertDialog.Builder(this).setView(mDialogview)
                    val mAlertDialog = mBuilder.show();
                    mDialogview.BtnDong.setOnClickListener{
                        mAlertDialog.dismiss()
                    }
                }

            }
            catch (e: Exception){}
        }
        else
        {
            Log.d("Thang","Length name: "+et_name.text.length + "Check number: "+checkNumber(et_CMND.text.toString()))
        }
    }

    private fun checkNumber(num: String?): Boolean {
        try {
            num?.toInt()
            return true
        } catch (e: NumberFormatException){
            this
        }
        return false
    }
}
