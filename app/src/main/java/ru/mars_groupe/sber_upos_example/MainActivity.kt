package ru.mars_groupe.sber_upos_example

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.RemoteException
import android.util.Log
import android.widget.Toast
import ru.evotor.framework.core.IntegrationActivity
import ru.evotor.framework.core.action.event.receipt.payment.combined.result.PaymentDelegatorCanceledAllEventResult
import ru.mars_groupe.sber_upos_example.databinding.ActivityMainBinding
import ru.sberbank.uposnative.UposClientAidlInterface
import ru.sberbank.uposnative.UposClientCallbackListener
import ru.sberbank.uposnative.UposVspClientAidlInterface
import ru.sberbank.uposnative.UposVspClientCallbackListener

class MainActivity : IntegrationActivity() {
    private lateinit var binding: ActivityMainBinding

    private var isAdapterRegistered = false
    private var isServiceBound = false
    private var isServiceFound = false

    private var uposCoreClientAidlInterface: UposClientAidlInterface? = null
    private val uposCoreInterface: UposClientAidlInterface
        get() = uposCoreClientAidlInterface!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        lunchUpos.setOnClickListener {
            lunchUpos()
        }
    }

    private fun lunchUpos() {
        if (!isServiceBound) {
            bindUposService()
            if (isServiceFound) {
                showMessage("showLoadingScreen")
            } else {
                showMessage(getString(R.string.error_connecting_upos))
            }
        } else {
            showUposScreen()
        }
    }


    private fun bindUposService() {
        showMessage("bindUposService()")
        isAdapterRegistered = false
        val intent = Intent( "ru.sberbank.uposnative.UposClientAidlInterface")
        try {
            intent.component = ComponentName(
                "ru.sberbank.uposnative",
                "ru.sberbank.uposnative.aidl.UposNativeClientAidlService"
            )
            isServiceFound = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
            showMessage("bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE): $isServiceFound")
            if (!isServiceFound) {
                showMessage(getString(R.string.error_connecting_upos))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showMessage("Ошибка bindUposService $e")
        }
    }

    private var serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            showMessage("onServiceConnected(name = $name, service = $service)")
            uposCoreClientAidlInterface = UposClientAidlInterface.Stub.asInterface(service)
            isServiceBound = true
            showUposScreen()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            showMessage("onServiceDisconnected(name = $name)")
            isServiceBound = false
            uposCoreClientAidlInterface = null
        }
    }

    private fun showUposScreen() {
        val uposInterface = uposCoreClientAidlInterface
        if (uposInterface == null) {
            showMessage(getString(R.string.error_connecting_upos))
            return
        }

        if (!isAdapterRegistered) {
            registerAdapter(uposInterface)
        }

        uposCoreInterface.doSomething("{\"OPERATION\":\"20\"}")
        showMessage("uposCoreInterface.doSomething(\"{\\\"OPERATION\\\":\\\"20\\\"}\")")
    }

    private fun registerAdapter(uposInterface: UposClientAidlInterface) {
        showMessage("registerAdapter(uposInterface: UposClientAidlInterface)")
        try {
            uposInterface.registerUposClientCallbackListener(object :
                UposClientCallbackListener.Stub() {
                override fun onTransactionResponse(transactionCode: Int, response: String) {
                    showMessage("onTransactionResponse(transactionCode = $transactionCode)")
                }
            })
            isAdapterRegistered = true
        } catch (e: RemoteException) {
            showMessage("Error while register UposClientCallbackListener: $e")
        }
    }

    private fun showMessage(message: String) {
        Handler(Looper.getMainLooper()).post {
            Log.d("SberUposExample", message)
            binding.message.setText("${binding.message.text}\n\n$message")
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        cancelEvotorSell()
    }

    private fun cancelEvotorSell() {
        setIntegrationResult(PaymentDelegatorCanceledAllEventResult(null))
        finish()
    }
}