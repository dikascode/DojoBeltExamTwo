package com.dikascode.beltexamtwo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dikascode.beltexamtwo.R
import com.dikascode.beltexamtwo.adapter.UserLocalStorageAdapter
import com.dikascode.beltexamtwo.base_interface.BaseView
import com.dikascode.beltexamtwo.database.SQLiteHelper
import com.dikascode.beltexamtwo.databinding.ActivityMainBinding
import com.dikascode.beltexamtwo.model.UserLocalDataModel
import com.dikascode.beltexamtwo.model.UserDataItem

class MainActivity : AppCompatActivity(), BaseView {
    private lateinit var binding: ActivityMainBinding

    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var studentListRecyclerView: RecyclerView
    lateinit var userLocalStorageAdapter: UserLocalStorageAdapter
    lateinit var studentData:UserLocalDataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initRecyclerView()

        val userDataObject:UserDataItem = intent.getSerializableExtra("userData") as UserDataItem
        displayUserDataFromMainScreen(userDataObject)

        sqLiteHelper = SQLiteHelper(this)
        binding.addBtn.setOnClickListener { addStudent() }
        binding.viewBtn.setOnClickListener { getStudents() }
        binding.updateBtn.setOnClickListener { updateStudentRecord() }
        userLocalStorageAdapter.setOnClickItem {
            binding.etName.setText(it.fName)
            binding.etLastName.setText(it.lName)
            binding.etEmail.setText(it.email)
            binding.etCity.setText(it.city)
            binding.etState.setText(it.state)
            studentData = it
        }

        userLocalStorageAdapter.setOnClickDeleteItem {
            deleteStudentRecord(it.id)
        }

        binding.returnHomeBtn.setOnClickListener {
            onBackPressed()
        }

        setContentView(binding.root)
    }

    private fun displayUserDataFromMainScreen(userDataObject: UserDataItem) {
        binding.etName.setText(userDataObject.firstName)
        binding.etLastName.setText(userDataObject.lastName)
        binding.etEmail.setText(userDataObject.email)
        binding.etCity.setText(userDataObject.address.city)
        binding.etState.setText(userDataObject.address.state)
    }


    private fun getStudents() {
        val studentList = sqLiteHelper.getAllStudents()
        //Add all students from database to adapter
        userLocalStorageAdapter.addItems(studentList)
    }

    private fun updateStudentRecord() {
        val name = binding.etName.text.toString()
        val email = binding.etEmail.text.toString()

        //Check if record hasn't changed
        if(name == studentData.fName && email == studentData.email) {
            showToast(R.string.record_unchanged)
            return
        }

        if(studentData == null) {
            return
        }else{
            val newStudentRecord = UserLocalDataModel(id = studentData.id, fName = name, email = email)
            val status = sqLiteHelper.updateStudents(newStudentRecord)
            if(status > -1) {
                clearEditText()
                getStudents()
            }else{
                showToast(R.string.update_failed)
            }
        }


    }

    private fun addStudent() {
        val fName = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val lName = binding.etLastName.text.toString().trim()
        val city = binding.etCity.text.toString().trim()
        val state = binding.etState.text.toString().trim()

        if (fName.isEmpty() || email.isEmpty() || lName.isEmpty() || city.isEmpty() || state.isEmpty()) {
            showToast(R.string.enter_required)
        } else {
            val userLocalData = UserLocalDataModel(fName = fName, lName = lName, email = email, city = city, state = state)
            val status = sqLiteHelper.insertStudent(userLocalData)

            if (status > -1) {
                showToast(R.string.added_successfully)
                clearEditText()

                //refresh list
                getStudents()
            } else {
                showToast(R.string.not_saved)
                clearEditText()
            }
        }
    }

    private fun deleteStudentRecord(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.sure_to_delete)
        builder.setCancelable(true)
        builder.setPositiveButton(R.string.yes) { dialog, _ ->
            sqLiteHelper.deleteStudentById(id)
            if(sqLiteHelper.deleteStudentById(id) > -1) {
                showToast(R.string.delete_success)
            }
            getStudents()
            dialog.dismiss()
        }

        builder.setNegativeButton(R.string.no) { dialog, _ ->
            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }

    private fun initRecyclerView() {
        studentListRecyclerView = binding.recyclerView
        studentListRecyclerView.layoutManager = LinearLayoutManager(this)
        userLocalStorageAdapter = UserLocalStorageAdapter()
        studentListRecyclerView.adapter = userLocalStorageAdapter
    }

    private fun clearEditText() {
        binding.etName.setText("")
        binding.etLastName.setText("")
        binding.etEmail.setText("")
        binding.etCity.setText("")
        binding.etState.setText("")
        binding.etName.requestFocus()
    }

    override fun showToast(message: Int) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }


}