package com.example.swvlmobilechallenge.ui.details

import com.example.swvlmobilechallenge.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

//
// Created by Abdul Basit on 11/8/2020.
//

class MockInterceptors : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            val responseString = when {
                uri.endsWith("starred") -> getListOfReposBeingStarredJson
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(201)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString.toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }
}

const val getListOfReposBeingStarredJson = """
{"photos":{"page":1,"pages":1933,"perpage":10,"total":"19322","photo":[{"id":"50577482453","owner":"8026096@N04","secret":"4ba1d47e44","server":"65535","farm":66,"title":"\u201cGo With Me Into This Great Fight for the Dear Life of the Nation\u201d","ispublic":1,"isfriend":0,"isfamily":0},{"id":"50575022918","owner":"79597081@N07","secret":"0689a87bc8","server":"65535","farm":66,"title":"Warkworth Peagant 1983","ispublic":1,"isfriend":0,"isfamily":0},{"id":"50575894017","owner":"79597081@N07","secret":"ec56aaf40d","server":"65535","farm":66,"title":"Warkworth Peagant 1983","ispublic":1,"isfriend":0,"isfamily":0},{"id":"50575893967","owner":"79597081@N07","secret":"ecae4333c5","server":"65535","farm":66,"title":"Warkworth Peagant 1983","ispublic":1,"isfriend":0,"isfamily":0},{"id":"50575756126","owner":"79597081@N07","secret":"999a7c3137","server":"65535","farm":66,"title":"Warkworth Peagant 1983","ispublic":1,"isfriend":0,"isfamily":0},{"id":"50575893852","owner":"79597081@N07","secret":"6d58e51ea0","server":"65535","farm":66,"title":"Warkworth Peagant 1983","ispublic":1,"isfriend":0,"isfamily":0},{"id":"50575268891","owner":"126251698@N03","secret":"de6144d5e0","server":"65535","farm":66,"title":"City of Hollywood, Broward County, Florida, USA","ispublic":1,"isfriend":0,"isfamily":0},{"id":"50572033793","owner":"126251698@N03","secret":"5a6956bb25","server":"65535","farm":66,"title":"City of Hollywood, Broward County, Florida, USA","ispublic":1,"isfriend":0,"isfamily":0},{"id":"50564034016","owner":"126251698@N03","secret":"1d5c1726a1","server":"65535","farm":66,"title":"City of Hollywood, Broward County, Florida, USA","ispublic":1,"isfriend":0,"isfamily":0},{"id":"50562674843","owner":"126251698@N03","secret":"333df126f5","server":"65535","farm":66,"title":"City of Hollywood, Broward County, Florida, USA","ispublic":1,"isfriend":0,"isfamily":0}]},"stat":"ok"}
"""