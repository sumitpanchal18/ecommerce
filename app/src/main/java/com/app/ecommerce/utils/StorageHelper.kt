package com.app.ecommerce.utils

import android.content.SharedPreferences

object StorageHelper {

    private var sharedPref: SharedPreferences? = null

    private const val KEY_DEVICE_TOKEN = "deviceToken"
    private const val KEY_HEADER_TOKEN = "headerToken"
    private const val KEY_LOGIN_USER_DATA = "loginUserData"
    private const val KEY_SETTING_DATA = "settingData"
    private const val KEY_USER_ROLE = "userRole"
    private const val KEY_NOTIFICATION_PERMISSION_PERFORMED = "notificationPermission"
    private const val KEY_CMS_TEXT = "cmsText"
    private const val KEY_LATCH_CMS_TEXT = "latchCmsText"
    private const val KEY_CAL_TOTAL_DAYS = "calTotalDays"


    fun clearData() {
        val deviceToken = getDeviceToken()
        val settingData = getSettingData()
        sharedPref?.edit()?.clear()?.apply()

        // reset required data after clear all data
        saveDeviceToken(deviceToken)
        saveSettingData(settingData)
    }

    fun saveDeviceToken(token: String?) {
        sharedPref?.edit()?.putString(KEY_DEVICE_TOKEN, token)?.apply()
    }

    fun getDeviceToken(): String {
        return sharedPref?.getString(KEY_DEVICE_TOKEN, "") ?: ""
    }

    fun saveHeaderToken(token: String?) {
        sharedPref?.edit()?.putString(KEY_HEADER_TOKEN, token)?.apply()
    }

    fun getHeaderToken(): String {
        return sharedPref?.getString(KEY_HEADER_TOKEN, "") ?: ""
    }

    fun saveLoginData(loginData: String?) {
        sharedPref?.edit()?.putString(KEY_LOGIN_USER_DATA, loginData)?.apply()
    }

    fun getLoginData(): String? {
        return sharedPref?.getString(KEY_LOGIN_USER_DATA, null)
    }

    fun saveUserRole(role: String?) {
        sharedPref?.edit()?.putString(KEY_USER_ROLE, role)?.apply()
    }

    fun getUserRole(): String {
        return sharedPref?.getString(KEY_USER_ROLE, "") ?: ""
    }

    fun saveSettingData(settingData: String?) {
        sharedPref?.edit()?.putString(KEY_SETTING_DATA, settingData)?.apply()
    }

    fun getSettingData(): String? {
        return sharedPref?.getString(KEY_SETTING_DATA, null)
    }

    fun saveNotificationPermissionAction(isActionTaken: Boolean) {
        sharedPref?.edit()?.putBoolean(KEY_NOTIFICATION_PERMISSION_PERFORMED, isActionTaken)
            ?.apply()
    }

    fun getNotificationPermissionAction(): Boolean? {
        return sharedPref?.getBoolean(KEY_NOTIFICATION_PERMISSION_PERFORMED, false)
    }


}