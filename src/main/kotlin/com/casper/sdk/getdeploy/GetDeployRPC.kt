package com.casper.sdk.getdeploy

import com.casper.sdk.ConstValues
import com.casper.sdk.getdeploy.ExecutableDeployItem.ExecutableDeployItem
import com.casper.sdk.getdeploy.ExecutionResult.ExecutionResult
import com.casper.sdk.getdeploy.ExecutionResult.JsonExecutionResult
import net.jemzart.jsonkraken.get
import net.jemzart.jsonkraken.toJson
import net.jemzart.jsonkraken.values.JsonArray
import net.jemzart.jsonkraken.values.JsonObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
/**Class built for info_get_deploy RPC call */
class GetDeployRPC {
    var methodURL: String = ConstValues.TESTNET_URL
    /**
     * This function initiate the process of sending POST request with given parameter in JSON string format
     * The input parameterStr is used to send to server as parameter of the POST request to get the result back.
     * The input parameterStr is somehow like this:
     * {"id" :  1, "method" :  "info_get_deploy", "params" :  {"deploy_hash" :  "6e74f836d7b10dd5db7430497e106ddf56e30afee993dd29b85a91c1cd903583"}, "jsonrpc" :  "2.0"}
     * The parameterStr is generated by the GetDeployParams class, declared in file GetDeployParams.kotlin
     * Then the GetDeployResult is retrieved by parsing JsonObject result
     * If the result is error,  then an exception is thrown
     * Else the GetDeployResult is taken by parsing the  retrieving JsonObject
     */
    @Throws(IllegalArgumentException:: class)
    fun getDeployFromJsonStr(str: String): GetDeployResult {
        val url = URL(methodURL)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        con.setRequestMethod("POST")
        con.setRequestProperty("Content-Type", "application/json")
        con.setRequestProperty("Accept", "application/json");
        con.doOutput = true
        con.outputStream.use { os ->
            val input: ByteArray = str.toByteArray()
            os.write(input, 0, input.size)
        }
        BufferedReader(
            InputStreamReader(con.inputStream, "utf-8")
        ).use {
            val response = StringBuilder()
            var responseLine: String? = null
            while (it.readLine().also { responseLine = it } != null) {
                response.append(responseLine!!.trim { it <= ' ' })
            }
            val json = response.toString().toJson()
            if(json.get("error") != null) {
                throw IllegalArgumentException("Error get state root hash")
            }
            val getDeployResult = GetDeployResult()
            val jsonResult: JsonObject = json.get("result") as JsonObject
            getDeployResult.api_version = jsonResult.get("api_version").toString()
            getDeployResult.deploy.header = DeployHeader.fromJsonToDeployHeader(jsonResult.get("deploy").get("header") as JsonObject)
            getDeployResult.deploy.hash = jsonResult.get("deploy").get("hash").toString()
            val deployPayment = jsonResult.get("deploy").get("payment") as JsonObject
            getDeployResult.deploy.payment = ExecutableDeployItem.fromJsonToExecutableDeployItem(deployPayment)
            val deploySession : JsonObject = jsonResult.get("deploy").get("session") as JsonObject
            getDeployResult.deploy.session = ExecutableDeployItem.fromJsonToExecutableDeployItem(deploySession)
            //get approvals
            getDeployResult.deploy.approvals = Deploy.fromJsonToListApprovals(jsonResult.get("deploy").get("approvals") as JsonArray)
            //get execution result
            val listER: JsonArray = jsonResult.get("execution_results") as JsonArray
            val totalER: Int = listER.count()
            for(i in 0.. totalER-1) {
                val jer = JsonExecutionResult()
                val oneItem = listER[i]
                jer.blockHash = oneItem.get("block_hash").toString()
                jer.result = ExecutionResult.fromJsonToExecutionResult(oneItem.get("result") as JsonObject)
                getDeployResult.executionResults.add(jer)
            }
            return getDeployResult
        }
    }
}