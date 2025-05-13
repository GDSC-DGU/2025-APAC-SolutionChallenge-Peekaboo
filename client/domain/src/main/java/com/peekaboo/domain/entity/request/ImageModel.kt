package com.peekaboo.domain.entity.request

import java.io.File

data class ImageModel(
    val imageFile: File? = null,
    val imgMultiPartName: String = "",
)