package ru.sberbank.uposnative;

import ru.sberbank.uposnative.UposClientCallbackListener;
import ru.sberbank.uposnative.UposLoggerCallbackListener;

interface UposClientAidlInterface {

    void startPayment(String amount, String json);

    void startRefund(String amount, String json);

    void startReversal(String amount, String json);

    void startReconciliation();

    void cashout(String cashBack, String json);

    void purchaseWithCashback(String amount, String cashBack, String json);

    void setDefaultTerminal(boolean isDefaultTerminal);

    void registerUposClientCallbackListener(UposClientCallbackListener uposClientCallbackListener);

    void unregisterUposClientCallbackListener(UposClientCallbackListener uposClientCallbackListener);

    void startServiceMenu();

    void addTestConfiguration();

    void getApplicationLog(UposLoggerCallbackListener uposLoggerCallbackListener);

    void getUposLog(UposLoggerCallbackListener uposLoggerCallbackListener);

    void doSomething(String json);

    void loadTestConfiguration(String tlvName);
}

