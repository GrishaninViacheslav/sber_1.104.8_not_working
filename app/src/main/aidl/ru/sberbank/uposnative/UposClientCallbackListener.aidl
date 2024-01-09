package ru.sberbank.uposnative;

interface UposClientCallbackListener {

    void onTransactionResponse(int transactionCode, String response);
}
