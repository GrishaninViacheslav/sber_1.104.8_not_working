package ru.sberbank.uposnative;

import ru.sberbank.uposnative.UposVspClientCallbackListener;
import ru.sberbank.uposnative.UposLoggerCallbackListener;

interface UposVspClientAidlInterface {

    void startPayment(String amount, String json); // no

    void startRefund(String amount, String json);

    void startReversal(String amount, String json);

    void startReconciliation();

    void cashout(String cashBack, String json);

    void purchaseWithCashback(String amount, String cashBack, String json);

    void setDefaultTerminal(boolean isDefaultTerminal);

    void registerUposClientCallbackListener(UposVspClientCallbackListener uposClientCallbackListener);

    void unregisterUposClientCallbackListener(UposVspClientCallbackListener uposClientCallbackListener);

    void startServiceMenu();

    void addTestConfiguration();

    void getApplicationLog(UposLoggerCallbackListener uposLoggerCallbackListener);

    void getUposLog(UposLoggerCallbackListener uposLoggerCallbackListener);

    void startOperation(in byte [] inputData); // same

    void startOperationWithAdditionalData(in byte [] inputData, String json); // same

    int getPid(); // nothing

    void killApp();

    void getTid();

    void readCardNumber();

    void startCoreReconciliation(String json);

    void startCoreServiceMenu(String json);

    void setMasterCallData(in byte [] inputData, String json);

    void setServiceInformation(String connectedPort, boolean isVsp);

    String getConnectionSettings(int type);

    void getTechnicalAccount();

    void getTimeout();

    void doSomething(String json);
}

