package com.ali.trinity.ui.utils

import android.content.Context
import android.content.res.AssetManager
import java.io.IOException
import java.io.InputStream
import java.util.regex.Pattern

object Utils {


    fun shortName(fullName: String): String {
        return shortNameWithEmojiSupport(fullName)
    }

    fun fullText(fullName: String): String {
        return fullName
    }

    private fun shortNameWithEmojiSupport(cardFullName: String): String {
        val emo_regex =
            "(?:[\\uD83C\\uDF00-\\uD83D\\uDDFF]|[\\uD83E\\uDD00-\\uD83E\\uDDFF]|[\\uD83D\\uDE00-\\uD83D\\uDE4F]|[\\uD83D\\uDE80-\\uD83D\\uDEFF]|[\\u2600-\\u26FF]\\uFE0F?|[\\u2700-\\u27BF]\\uFE0F?|\\u24C2\\uFE0F?|[\\uD83C\\uDDE6-\\uD83C\\uDDFF]{1,2}|[\\uD83C\\uDD70\\uD83C\\uDD71\\uD83C\\uDD7E\\uD83C\\uDD7F\\uD83C\\uDD8E\\uD83C\\uDD91-\\uD83C\\uDD9A]\\uFE0F?|[\\u0023\\u002A\\u0030-\\u0039]\\uFE0F?\\u20E3|[\\u2194-\\u2199\\u21A9-\\u21AA]\\uFE0F?|[\\u2B05-\\u2B07\\u2B1B\\u2B1C\\u2B50\\u2B55]\\uFE0F?|[\\u2934\\u2935]\\uFE0F?|[\\u3030\\u303D]\\uFE0F?|[\\u3297\\u3299]\\uFE0F?|[\\uD83C\\uDE01\\uD83C\\uDE02\\uD83C\\uDE1A\\uD83C\\uDE2F\\uD83C\\uDE32-\\uD83C\\uDE3A\\uD83C\\uDE50\\uD83C\\uDE51]\\uFE0F?|[\\u203C\\u2049]\\uFE0F?|[\\u25AA\\u25AB\\u25B6\\u25C0\\u25FB-\\u25FE]\\uFE0F?|[\\u00A9\\u00AE]\\uFE0F?|[\\u2122\\u2139]\\uFE0F?|\\uD83C\\uDC04\\uFE0F?|\\uD83C\\uDCCF\\uFE0F?|[\\u231A\\u231B\\u2328\\u23CF\\u23E9-\\u23F3\\u23F8-\\u23FA]\\uFE0F?)"

        var cardFullName = cardFullName
        cardFullName = cardFullName.trim { it <= ' ' }
        var shortName = ""
        if (cardFullName.isNotEmpty() && cardFullName.contains(" ")) {
            val nameStr =
                cardFullName.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            var firstName = nameStr[0]
            if (Character.isLetter(nameStr[0][0])) {
                firstName = nameStr[0].substring(0, 1)
            }

            var lastName = nameStr[nameStr.size - 1]
            if (Character.isLetter(nameStr[nameStr.size - 1][0])) {
                lastName = nameStr[nameStr.size - 1].substring(0, 1)
            }

            val firstNameMatcher = Pattern.compile(emo_regex).matcher(firstName)
            var isFirstEmoji = false
            var firstData = ""

            while (firstNameMatcher.find()) {
                firstData = firstNameMatcher.group()
                isFirstEmoji = true
                break
            }

            shortName = if (isFirstEmoji) {
                firstData
            } else {
                firstName.substring(0, 1)
            }

            val matcher = Pattern.compile(emo_regex).matcher(lastName)
            var isEmji = false
            var data = ""
            while (matcher.find()) {
                data = matcher.group()
                isEmji = true
                break
            }

            if (isEmji) {
                shortName += data
            } else {
                shortName += lastName.substring(0, 1)
            }
            return shortName.toUpperCase()
        } else if (cardFullName.isNotEmpty()) {
            val nameStr =
                cardFullName.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            var firstName = nameStr[0]
            if (Character.isLetter(nameStr[0][0])) {
                firstName = nameStr[0].substring(0, 1)
            }

            val firstNameMatcher = Pattern.compile(emo_regex).matcher(firstName)
            var isFirstEmoji = false
            var firstData = ""

            while (firstNameMatcher.find()) {
                firstData = firstNameMatcher.group()
                isFirstEmoji = true
                break
            }

            shortName = if (isFirstEmoji) {
                firstData
            } else {
                firstName.substring(0, 1)
            }
            return shortName.toUpperCase()
        }
        return shortName.toUpperCase()
    }


    @Throws(IOException::class)
    fun loadJSONFromAsset(context: Context, jsonFileName: String): String {
        val manager: AssetManager = context.assets
        val `is`: InputStream = manager.open(jsonFileName)
        val size: Int = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        return String(buffer)
    }


}