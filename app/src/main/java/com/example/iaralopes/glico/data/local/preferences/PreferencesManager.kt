package com.example.iaralopes.glico.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class PreferencesManager(context: Context) {

    private val mPref: SharedPreferences?

    init {
        val PREF_NAME = ""
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setValue(key: String, value: Long) {
        mPref!!.edit()
            .putLong(key, value)
            .apply()
    }

    fun setValue(key: String, value: Int) {
        mPref!!.edit()
            .putInt(key, value)
            .apply()
    }

    fun setValue(key: String, value: Float) {
        mPref!!.edit()
            .putFloat(key, value)
            .apply()
    }

    fun setValue(key: String, value: String) {
        mPref!!.edit()
            .putString(key, value)
            .apply()
    }

    fun setValue(key: String, value: Boolean) {
        mPref!!.edit()
            .putBoolean(key, value)
            .apply()
    }

    fun getLong(key: String): Long {
        return mPref!!.getLong(key, 0)
    }


    fun getInt(key: String): Int {
        return mPref!!.getInt(key, 0)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return mPref!!.getInt(key, defaultValue)
    }

    fun getFloat(key: String): Float {
        return mPref!!.getFloat(key, 0f)
    }


    fun getString(key: String): String {
        return mPref!!.getString(key, "")
    }

    fun getBoolean(key: String): Boolean {
        return mPref!!.getBoolean(key, false)
    }


    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return mPref!!.getBoolean(key, defaultValue)
    }

    fun <T> getObject(key: String, clazz: Class<T>): T? {
        if (mPref != null) {
            val gson = Gson()
            val json = mPref.getString(key, "")
            return gson.fromJson(json, clazz)
        } else
            return null
    }

    fun saveObject(key: String, anyObject: Any) {
        if (mPref != null) {
            val gson = Gson()
            val json = gson.toJson(anyObject)
            mPref.edit().putString(key, json).apply()
        }
    }

    fun remove(key: String) {
        mPref!!.edit()
            .remove(key)
            .apply()
    }

    fun clear(): Boolean {
        return mPref!!.edit()
            .clear()
            .commit()
    }
}
