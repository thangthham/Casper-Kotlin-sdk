# CSPR-Kotlin-SDK

Kotlin SDK library for interacting with a CSPR node.

## What is CSPR-Kotlin-SDK?

SDK  to streamline the 3rd party Kotlin client integration processes. Such 3rd parties include exchanges & app developers. 

## System requirement

- The SDK is built with Kotlin language versioned 1.5

To build and test the SDK you need the flowing things installed in your computer:

- Java JDK version 11 or above, Java JDK 18 is suggested

- Maven latest version.

## Build and test

The package can be built and tested from IntelliJ IDEA

### Build and test in IntelliJ IDEA

Download the latest IntelliJ IDEA from https://www.jetbrains.com/idea/

The SDK is developed with IntelliJ IDEA 2021.3.3 (Community Edition). Then a Community Edition is good enough to test the SDK.

Download or clone the code from github,  then open it with IntelliJ IDEA.

#### Set up environment for ItelliJ IDEA

The first thing to do is to set up the SDK for the Project.

In IntelliJ IDEA hit "File-> Project Structure..." 

<img width="1440" alt="Screen Shot 2022-04-20 at 06 12 33" src="https://user-images.githubusercontent.com/94465107/164116163-5844aa6c-75e3-4a0a-a92f-d82578785b3a.png">

You will see this screen shown up

<img width="1440" alt="Screen Shot 2022-04-20 at 06 13 39" src="https://user-images.githubusercontent.com/94465107/164116186-e67ff39f-8fac-4ffc-aef3-0be8b955aed3.png">

Click on tab "SDKs" in the left panel to see the list of SDK being installed in your ItelliJ IDEA. You need to install the SDK from 11 or above, the suggested SDK is "openjdk-18".

<img width="1440" alt="Screen Shot 2022-04-20 at 06 17 16" src="https://user-images.githubusercontent.com/94465107/164116476-9a07e449-28d5-4751-bde9-8f1ddf607f6a.png">

By default, the SDKs list is just "Kotlin SDK". To install more SDK, hit the "+" button and choose "Download JDK ..."

<img width="1440" alt="Screen Shot 2022-04-20 at 06 26 28" src="https://user-images.githubusercontent.com/94465107/164117325-3a36cde3-791b-45a0-ba8d-20dee1fceb36.png">

Select your JDK version. You can choose any option in the list box, but the suggested version is 18, then hit the "Download" button

<img width="1440" alt="Screen Shot 2022-04-20 at 06 29 07" src="https://user-images.githubusercontent.com/94465107/164117560-135a3666-697e-4090-b5b2-af2b3b4d7352.png">

After the downloading is completed, you'll see the "openjdk-18" item (if you install JDK version 18) together with the Kotlin SDK.

Add more JDK version with the same step if you wish to test on more version of the JDK.

Next in the tab Project, choose the SDK for the project. In this case the choosen SDK is "openjdk-18"

<img width="1440" alt="Screen Shot 2022-04-20 at 06 31 20" src="https://user-images.githubusercontent.com/94465107/164117795-965a4138-2088-45aa-8087-5cfe171283e4.png">

Just do this for the SDK configuration, left the other configurations with default values. Make sure that the "Language level" is "SDK default" as in the image below.

<img width="1440" alt="Screen Shot 2022-04-20 at 09 17 17" src="https://user-images.githubusercontent.com/94465107/164136336-6900e4cd-bcca-41fd-97b7-f31e5aad76ee.png">


Click "Apply" and then "OK" to save the configruation. Now you are ready to build and test the project in IntelliJ IDEA.

#### Import jars file to the SDK

Get the jars file from this link https://drive.google.com/drive/folders/1oflaJ_1m3HX9NYI_5d0PuoT2WNGy6dPq?usp=sharing

Download all the jars file to your local computer, you will have two folders with name "bcutils" and "otherjars" containing the jar files that will be later imported to the SDK.

In IntelliJ IDEA, hit "File-> Project Structure ..."

<img width="1440" alt="Screen Shot 2022-06-28 at 10 15 14" src="https://user-images.githubusercontent.com/94465107/176084432-af7e4018-d3bc-4192-b2d4-d7b726a0ce97.png">

A new window appears, click on "Libraries" and then "Maven: net.jemzart:jsonkraken:1.2.0", then click the "+" button

<img width="1440" alt="Screen Shot 2022-06-28 at 10 16 45" src="https://user-images.githubusercontent.com/94465107/176084923-a25c72bb-091c-46df-9d9a-f8b3b4fce19f.png">


#### Build the sdk: 

To build the project, in IntelliJ IDEA, hit "Build->Build Project" as in the image below

<img width="1440" alt="Screen Shot 2022-04-18 at 20 31 31" src="https://user-images.githubusercontent.com/94465107/163815577-87bddf54-9489-4cb0-b627-e8a95680f3cf.png">

#### Test the sdk:

To run the test, in the Project panel that show the files and folders, under the folder "test", right click on the "kotlin" folder (folder marked with green in the below image), hit "Run 'All Tests'".

<img width="1440" alt="Screen Shot 2022-04-19 at 17 19 50" src="https://user-images.githubusercontent.com/94465107/163983309-711a270a-33c9-4d31-bb51-664b6904047e.png">


The test list is in the right panel, the result is in the left panel, as shown in the image:

<img width="1440" alt="Screen Shot 2022-04-19 at 17 18 28" src="https://user-images.githubusercontent.com/94465107/163983055-31730b51-c730-4878-99a4-c7aa104b3ce5.png">


# Documentation for classes and methods

* [List of classes and methods](./Docs/Help.md#list-of-rpc-methods)

  -  [Get State Root Hash](/Docs/Help.md#i-get-state-root-hash)

  -  [Get Peer List](./Docs/Help.md#ii-get-peers-list)

  -  [Get Deploy](./Docs/Help.md#iii-get-deploy)
  
  -  [Get Status](./Docs/Help.md#iv-get-status)
  
  -  [Get Block Transfers](./Docs/Help.md#v-get-block-transfers)
  
  -  [Get Block](./Docs/Help.md#vi-get-block)
  
  -  [Get Era Info By Switch Block](./Docs/Help.md#vii-get-era-info-by-switch-block)
  
  -  [Get Item](./Docs/Help.md#vii-get-item)
  
  -  [Get Dictionary Item](./Docs/Help.md#ix-get-dictionaray-item)
  
  -  [Get Balance](./Docs/Help.md#x-get-balance)
  
  -  [Get Auction Info](./Docs/Help.md#xi-get-auction-info)
 
 # Coding standard
 
 The code in the SDK follow the coding convention at this address
 
 https://kotlinlang.org/docs/coding-conventions.html#immutability
