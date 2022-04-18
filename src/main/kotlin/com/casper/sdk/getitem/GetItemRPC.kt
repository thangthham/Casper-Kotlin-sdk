package com.casper.sdk.getitem

import com.casper.sdk.ConstValues
import com.casper.sdk.getblock.GetBlockResult
import net.jemzart.jsonkraken.get
import net.jemzart.jsonkraken.toJson
import net.jemzart.jsonkraken.values.JsonObject
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

/** Class built for state_get_item RPC call */
class GetItemRPC {
    var methodURL: String = ConstValues.TESTNET_URL
    /**
     * This function initiate the process of sending POST request with given parameter in JSON string format
     * The input parameterStr is used to send to server as parameter of the POST request to get the result back.
     * The input parameterStr is somehow like this:
     * {"method" :  "state_get_item", "id" :  1, "params" : {"state_root_hash" :  "d360e2755f7cee816cce3f0eeb2000dfa03113769743ae5481816f3983d5f228", "key": "withdraw-df067278a61946b1b1f784d16e28336ae79f48cf692b13f6e40af9c7eadb2fb1", "path": []}, "jsonrpc" :  "2.0"}
     * The parameterStr is generated by the GetDictionaryItemParams class, declared in file GetDictionaryItemParams.kotlin
     * Then the GetItemResult is retrieved by parsing JsonObject result
     * If the result is error,  then an exception is thrown
     * Else the GetItemResult is taken by parsing the  retrieving JsonObject
     */
    @Throws(IllegalArgumentException:: class)
    fun getItem(parameterStr: String):  GetItemResult {
        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(this.methodURL))
            .POST((HttpRequest.BodyPublishers.ofString(parameterStr)))
            .header("Content-Type",  "application/json")
            .build()
        val response = client.send(request,  HttpResponse.BodyHandlers.ofString())
        val json =response.body().toJson()
        //Check for error
        if(json.get("error") != null) {
            throw IllegalArgumentException("Error get item")
        } else { //If not error then get the GetItemResult
            val ret:  GetItemResult = GetItemResult.fromJsonObjectToGetItemResult(json.get("result") as JsonObject)
            return ret
        }
    }
}