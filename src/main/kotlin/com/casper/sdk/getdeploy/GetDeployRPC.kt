package com.casper.sdk.getdeploy

import com.casper.sdk.ConstValues
import com.casper.sdk.getdeploy.ExecutableDeployItem.ExecutableDeployItem
import com.casper.sdk.getdeploy.ExecutableDeployItem.ExecutableDeployItem_ModuleBytes
import com.casper.sdk.getdeploy.ExecutableDeployItem.RuntimeArgs
import com.casper.sdk.getdeploy.ExecutionResult.ExecutionResult
import com.casper.sdk.getdeploy.ExecutionResult.JsonExecutionResult
import net.jemzart.jsonkraken.get
import net.jemzart.jsonkraken.toJson
import net.jemzart.jsonkraken.toJsonString
import net.jemzart.jsonkraken.values.JsonArray
import net.jemzart.jsonkraken.values.JsonObject
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class GetDeployRPC {
    var postURL:String = ""
    fun getDeployFromJsonStr(str:String):GetDeployResult {
        var getDeployResult:GetDeployResult = GetDeployResult()
        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
            .uri(URI.create(postURL))
            .POST((HttpRequest.BodyPublishers.ofString(str)))
            .header("Content-Type", "application/json")
            .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString());
        val json =response.body().toJson()
        val jsonResult:JsonObject = json.get("result") as JsonObject
        getDeployResult.api_version = jsonResult.get("api_version").toString()
        getDeployResult.deploy.hash = jsonResult.get("deploy").get("hash").toString()
        var executableDeployItem:ExecutableDeployItem = ExecutableDeployItem()
        val deployPayment = jsonResult.get("deploy").get("payment") as JsonObject
        println("------------------------------------Get PAYMENT!---------------------------------------------")
        getDeployResult.deploy.payment = ExecutableDeployItem.fromJsonToExecutableDeployItem(deployPayment)
        //Get session
        println("------------------------------------GET SESSION!---------------------------------------------")
        val deploySession :JsonObject = jsonResult.get("deploy").get("session") as JsonObject
        getDeployResult.deploy.session = ExecutableDeployItem.fromJsonToExecutableDeployItem(deploySession)
        //get execution result
        val listER:JsonArray = jsonResult.get("execution_results") as JsonArray
        val totalER:Int = listER.count()
        for(i in 0.. totalER-1) {
            var jer: JsonExecutionResult = JsonExecutionResult()
            val oneItem = listER[i]
            jer.blockHash = oneItem.get("block_hash").toString()
            jer.result = ExecutionResult.fromJsonToExecutionResult(oneItem.get("result") as JsonObject)
            println("ER, blockHash:${jer.blockHash}")
            getDeployResult.executionResults.add(jer)
        }
        return getDeployResult
    }
    fun String.utf8(): String = URLEncoder.encode(this, "UTF-8")
}