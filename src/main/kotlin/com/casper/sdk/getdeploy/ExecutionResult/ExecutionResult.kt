package com.casper.sdk.getdeploy.ExecutionResult

import com.casper.sdk.ConstValues
import com.casper.sdk.common.classes.U512Class
import net.jemzart.jsonkraken.toJsonString
import net.jemzart.jsonkraken.values.JsonArray
import net.jemzart.jsonkraken.values.JsonObject

class ExecutionResult {
    //use default as ExecutionResult success
    var itsType:String = ConstValues.EXECUTION_RESULT_SUCCESS
    var cost:U512Class = U512Class()
    var errorMessage:String = ""
    var effect:ExecutionEffect = ExecutionEffect()
    var transfers:MutableList<String> = mutableListOf()
    companion object {
        fun fromJsonToExecutionResult(from:JsonObject):ExecutionResult {
            var ret:ExecutionResult = ExecutionResult()
            val successJson = from["Success"].toJsonString()
            if(successJson != "null") {
                val successJsonObject:JsonObject = from["Success"] as JsonObject
                ret.itsType = ConstValues.EXECUTION_RESULT_SUCCESS
                ret.cost = U512Class.fromStringToU512(successJsonObject["cost"].toString())
                println("Success, Cost is ${ret.cost.itsValue}")
                val transferArray : JsonArray = successJsonObject["transfers"] as JsonArray
                ret.effect = ExecutionEffect.fromJsonToExecutionEffect(successJsonObject["effect"] as JsonObject)
                if (transferArray.count() > 0) {

                } else {
                    println("TransferArray  empty")
                }
            } else {
                ret.itsType = ConstValues.EXECUTION_RESULT_FAILURE
                val failureJsonObject:JsonObject = from["Failure"] as JsonObject
                ret.cost = U512Class.fromStringToU512(failureJsonObject["cost"].toString())
                ret.errorMessage = failureJsonObject["error_message"].toString()
                println("Failure, Cost is ${ret.cost.itsValue}, errorMessage:${ret.errorMessage}")
            }
            return ret
        }
    }
}