## Пин-пад не реагирует на вызовы uposnative_debug_stub_ver.name-1.104.8_ver.code-215.apk

Версия 1.104.8 "Универсальный драйвер Сбербанк" - не работает.<br/>
Эту версию установил путём удаления магазинной версии
```
adb shell pm uninstall ru.sberbank.upos_driver_test
```
и установки apk полученного от Сбера:
```
adb install -r uposnative_debug_stub_ver.name-1.104.8_ver.code-215.apk
```
При использовании этой версии пин-пад никак не реагирует на вызовы.<br/>
Полный лог при использовании версии 1.104.8: [logcat_1.104.8.txt](logcat_1.104.8.txt)
```
SberUposExample: initUpos button pressed
SberUposExample: bindUposService()
SberUposExample: bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE): true
SberUposExample: onServiceConnected(name = ComponentInfo{ru.sberbank.upos_driver_test/ru.sberbank.uposnative.aidl.UposVspClientAidlService}, service = android.os.BinderProxy@7a8539f)
SberUposExample: registerAdapter(uposInterface: UposClientAidlInterface)
SberUposExample: serviceMenu button pressed
SberUposExample: uposCoreInterface.startServiceMenu()
SberUposExample: onTransactionResponse(transactionCode = 113, response = null)
SberUposExample: doSomething button pressed
SberUposExample: uposCoreInterface.doSomething("{\"OPERATION\":\"20\"}")
SberUposExample: onTransactionResponse(transactionCode = 113, response = null)
```

При этом версия 1.103.1 "Универсальный драйвер Сбербанк" из магазина - работает.<br/>
Полный лог при использовании версии 1.103.1: [logcat_1.103.1.txt](logcat_1.103.1.txt)
```
SberUposExample: initUpos button pressed
SberUposExample: bindUposService()
SberUposExample: bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE): true
SberUposExample: onServiceConnected(name = ComponentInfo{ru.sberbank.upos_driver_test/ru.sberbank.uposnative.aidl.UposVspClientAidlService}, service = android.os.BinderProxy@68a993e)
SberUposExample: registerAdapter(uposInterface: UposClientAidlInterface)
SberUposExample: serviceMenu button pressed
SberUposExample: uposCoreInterface.startServiceMenu()
SberUposExample: onTransactionResponse(transactionCode = 115, response = null)
SberUposExample: onTransactionResponse(transactionCode = 0, response = {"REQUEST_ID":"64978253","FFI":"0","TSN":"0","JNI_PROCESS_CALL_ID":"64978253","TOKEN_IS_OWN":"0","ENTRY_MODE":"D","ERROR":"0"})
SberUposExample: doSomething button pressed
SberUposExample: uposCoreInterface.doSomething("{\"OPERATION\":\"20\"}")
SberUposExample: onTransactionResponse(transactionCode = 0, response = {"PAN":"************5736","HASH":"FA1AEED44A17970F53C0620278E1D4EA26299FD8","HASH256":"A957D59B575E43F14909C13C4B0FA86DDD788A2A86EDF9FF237D396C4F501168","REQUEST_ID":"171166996","FFI":"0","TSN":"0","JNI_PROCESS_CALL_ID":"171166996","HASH_ALGO":"sha1","IS_OWN":"0","TOKEN_IS_OWN":"0","DATE":"00.00.2024","TID":"00500436","ENTRY_MODE":"D","AMOUNT":"0","AMOUNT_C":"0","FLAGS":"0","EXP_DATE":"0600","PIL_OP_TYPE":"255","ERROR":"0","CARD_ID":"10"})
SberUposExample: sha1: FA1AEED44A17970F53C0620278E1D4EA26299FD8
```
