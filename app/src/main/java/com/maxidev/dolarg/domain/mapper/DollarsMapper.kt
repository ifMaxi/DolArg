package com.maxidev.dolarg.domain.mapper

import com.maxidev.dolarg.data.remote.dto.DollarDTOItem
import com.maxidev.dolarg.domain.model.Dollars

fun DollarDTOItem.toExternalModel() = Dollars(
    name = nombre,
    buy = compra,
    sale = venta,
    lastUpdate = fechaActualizacion
)