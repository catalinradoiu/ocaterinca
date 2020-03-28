package com.ocaterinca.ocaterinca.utils

import androidx.annotation.StringRes
import com.ocaterinca.ocaterinca.Caterincapp


fun grabString(@StringRes res: Int): String {
    return Caterincapp.instance.getString(res)
}