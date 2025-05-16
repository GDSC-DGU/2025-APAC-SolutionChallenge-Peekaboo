package com.peekaboo.multipart

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

object FormDataUtil {

    fun getImageMultipart(file: File, partName: String): MultipartBody.Part {
        return MultipartBody.Part.createFormData(
            name = partName,
            filename = file.name,
            body = file.asRequestBody("image/*".toMediaType())
        )
    }

    fun getImageMultipartWithNull(file: File?, partName: String): MultipartBody.Part? {
        return if (file != null) {
            MultipartBody.Part.createFormData(
                name = partName,
                filename = file.name,
                body = file.asRequestBody("image/*".toMediaType())
            )
        } else {
            null
        }
    }

    fun getTextRequestBody(string: String): RequestBody {
        return string.toRequestBody("text/plain".toMediaType())
    }
}