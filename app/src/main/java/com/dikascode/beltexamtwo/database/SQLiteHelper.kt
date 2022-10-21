package com.dikascode.beltexamtwo.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dikascode.beltexamtwo.model.UserLocalDataModel

class SQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createStudentTable =
            ("Create Table $STUDENT_TABLE($ID INTEGER PRIMARY KEY, $FIRST_NAME TEXT, $LAST_NAME TEXT, $EMAIL TEXT, $CITY TEXT, $STATE TEXT)")
        db?.execSQL(createStudentTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $STUDENT_TABLE")
        onCreate(db)
    }

    fun insertStudent(student: UserLocalDataModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, student.id)
        contentValues.put(FIRST_NAME, student.fName)
        contentValues.put(LAST_NAME, student.lName)
        contentValues.put(EMAIL, student.email)
        contentValues.put(CITY, student.city)
        contentValues.put(STATE, student.state)

        val success = db.insert(STUDENT_TABLE, null, contentValues)
        db.close()
        return success
    }


    fun getAllStudents(): ArrayList<UserLocalDataModel> {
        val studentList: ArrayList<UserLocalDataModel> = ArrayList()
        val selectQuery = "SELECT * FROM $STUDENT_TABLE"
        val db = this.readableDatabase

        val  cursor = db.rawQuery(selectQuery, null)

        var id: Int
        var fName: String
        var lName:String
        var email: String
        var city:String
        var state:String

        if(cursor.moveToFirst() && cursor.count >= 0) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                fName = cursor.getString(cursor.getColumnIndex("fname"))
                lName = cursor.getString(cursor.getColumnIndex("lname"))
                email = cursor.getString(cursor.getColumnIndex("email"))
                city = cursor.getString(cursor.getColumnIndex("city"))
                state = cursor.getString(cursor.getColumnIndex("state"))


                val student = UserLocalDataModel(id = id, fName = fName, lName = lName, email = email, city = city, state = state)
                studentList.add(student)
            }while (cursor.moveToNext())
        }

        return studentList
    }

    fun updateStudents(userLocalData: UserLocalDataModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, userLocalData.id)
        contentValues.put(FIRST_NAME, userLocalData.fName)
        contentValues.put(LAST_NAME, userLocalData.lName)
        contentValues.put(EMAIL, userLocalData.email)
        contentValues.put(CITY, userLocalData.city)
        contentValues.put(STATE, userLocalData.state)

        val success = db.update(STUDENT_TABLE, contentValues, "id=" + userLocalData.id, null)
        db.close()
        return success
    }

    fun deleteStudentById(id: Int): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(STUDENT_TABLE, "id=$id", null)
        db.close()
        return success
    }


    companion object {
        private const val DATABASE_NAME = "student_db"
        private const val DATABASE_VERSION = 1
        private const val STUDENT_TABLE = "student_table"
        private const val ID = "id"
        private const val FIRST_NAME = "fname"
        private const val LAST_NAME = "lname"
        private const val EMAIL = "email"
        private const val CITY = "city"
        private const val STATE = "state"
    }
}