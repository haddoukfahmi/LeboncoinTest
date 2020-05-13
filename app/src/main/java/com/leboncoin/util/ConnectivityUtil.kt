package com.leboncoin.util

import android.content.*
import android.net.ConnectivityManager
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leboncoin.R

object ConnectivityUtil {
    class CheckConnectivity(private val mContext: Context) : LiveData<Boolean?>() {

        companion object {
            private const val CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"
        }

        private var networkReceiver: NetworkReceiver? = null


        init {
            Log.d("Main","Connectivity")
            networkReceiver = NetworkReceiver()
        }

        override fun onActive() {
            super.onActive()
            mContext.registerReceiver(
                networkReceiver,
                IntentFilter(CONNECTIVITY_ACTION)
            )
        }

        override fun onInactive() {
            super.onInactive()
            mContext.unregisterReceiver(networkReceiver)
        }

        private fun updateConnection() {
            if (mContext.isConnected) {
                postValue(true)
            } else
                postValue(false)
        }

        internal inner class NetworkReceiver : BroadcastReceiver() {
            override fun onReceive(
                context: Context,
                intent: Intent
            ) {
                if (intent.action == CONNECTIVITY_ACTION) {
                    updateConnection()
                }
            }
        }

        private val Context.isConnected: Boolean
            get() = (getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.activeNetworkInfo?.isConnected == true

    }

    val isAlbumDaoAvailable = MutableLiveData<Boolean>(false)
    fun showNoConnectionDialog(mContext: Context) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(mContext).apply {
            setCancelable(true)
            setIcon(R.drawable.ic_load_failed)
            setMessage(R.string.error_offline_text)
            setTitle(R.string.no_connection_title)
            setPositiveButton(
                R.string.settings_button_text,
                DialogInterface.OnClickListener { dialog, which ->
                    mContext.startActivity(
                        Intent(
                            Settings.ACTION_WIRELESS_SETTINGS
                        )
                    )
                })
            setNegativeButton(
                R.string.cancel_button_text,
                DialogInterface.OnClickListener { dialog, which -> return@OnClickListener })
            setOnCancelListener(DialogInterface.OnCancelListener { return@OnCancelListener })
        }
        builder.show()
    }

}

