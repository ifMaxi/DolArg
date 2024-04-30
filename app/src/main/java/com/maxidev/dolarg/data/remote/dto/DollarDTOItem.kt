package com.maxidev.dolarg.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DollarDTOItem(
    val compra: Double?,
    val venta: Double?,
    val casa: String?,
    val nombre: String?,
    val moneda: String?,
    val fechaActualizacion: String?
)