package ru.sberbank.uposnative;

interface UposVspClientCallbackListener {

    void onTransactionArrayResponse(int transactionCode, in byte[] response);

    void onTransactionResponse(int transactionCode, String response);

    void onFullTransactionResponse(int transactionCode, in byte[] response, String json);

    void onMasterCallTransactionResponse(int transactionCode, in byte[] response, String json);

    void onAdditionalAbstractResponse(int type, in byte[] response);
}