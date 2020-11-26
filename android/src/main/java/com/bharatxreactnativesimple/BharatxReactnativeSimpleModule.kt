package com.bharatxreactnativesimple

import androidx.fragment.app.FragmentActivity
import com.facebook.react.bridge.*
import tech.bharatx.simple.BharatXTransactionManager
import tech.bharatx.simple.data_classes.PayeeBankDetails
import kotlin.math.roundToLong

class BharatxReactnativeSimpleModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "BharatxReactnativeSimple"
  }

  @ReactMethod
  fun initialize(partnerId: String, partnerApiKey: String, promise: Promise) {
    BharatXTransactionManager.initialize(currentActivity!!, partnerId, partnerApiKey)
    promise.resolve(null)
  }

  @JvmOverloads
  @ReactMethod
  fun startTransaction(transactionId: String?,
                 userId: String?,
                 phoneNumber: String,
                 amountInPaise: Double,
                 payeeBankDetailsMap: ReadableMap? = null,
                 transactionCallback: Callback? = null) {
    val payeeBankDetails = if(payeeBankDetailsMap == null) null else {
      PayeeBankDetails(payeeBankDetailsMap.getString("beneficiaryName")!!,
        payeeBankDetailsMap.getString("accountNumber")!!,
        payeeBankDetailsMap.getString("ifscCode")!!)
    }
    BharatXTransactionManager.startTransaction((currentActivity!! as FragmentActivity),
      transactionId, userId, phoneNumber, amountInPaise.roundToLong(), payeeBankDetails, object : BharatXTransactionManager.TransactionListener() {
      override fun onSuccess() {
        transactionCallback?.invoke("onSuccess")
      }

      override fun onCancelled() {
        transactionCallback?.invoke("onCancelled")
      }

      override fun onFailure() {
        transactionCallback?.invoke("onFailure")
      }
    })
  }

  @ReactMethod
  fun startTransaction(transactionId: String?,
                       userId: String?,
                       phoneNumber: String,
                       amountInPaise: Double,
                       transactionCallback: Callback) {
    startTransaction(transactionId, userId, phoneNumber, amountInPaise, null, transactionCallback)
  }

}
