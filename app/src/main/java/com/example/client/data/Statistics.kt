package com.example.client.data

import android.graphics.Color
import com.example.client.R

class Statistics {

}
public fun getCHartColor(seed: Int): Int{
    when (seed){
        1 -> return R.color.black
        2 -> return Color.rgb(68,85,194)
        3 -> return Color.rgb(255,22,32)
        4 -> return Color.rgb(246,209,127)
        5 -> return Color.rgb(0,160,180)
        6 -> return Color.rgb(191,27,146)
        7 -> return Color.rgb(241,164,94)
        8 -> return Color.rgb(0,162,103)
        9 -> return Color.rgb(100,43,151)
        10 -> return Color.rgb(255,137,81)
        11 -> return Color.rgb(117,196,79)
        12 -> return Color.rgb(89,59,158)
        13 -> return Color.rgb(240,214,0)
        14 -> return Color.rgb(20,45,186)
        15 -> return Color.rgb(215,13,18)
        else -> return Color.rgb(181,181,181)
    }
}