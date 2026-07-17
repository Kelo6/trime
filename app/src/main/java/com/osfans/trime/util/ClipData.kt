/*
 * SPDX-FileCopyrightText: 2015 - 2026 Rime community
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package com.osfans.trime.util

import android.content.ClipData
import android.content.Context
import androidx.core.text.HtmlCompat

/**
 * Resolves the first non-empty text representation in this clip.
 *
 * Some cross-device clipboard providers expose synchronized text as HTML, a content URI, or an
 * Intent while leaving [ClipData.Item.getText] empty. Android's [ClipData.Item.coerceToText]
 * returns that empty value before trying the other representations, so rebuild those items before
 * coercion and continue through all clip items when necessary.
 */
fun ClipData.coerceToTextWithFallback(context: Context): CharSequence? {
    for (index in 0 until itemCount) {
        val item = getItemAt(index)
        item.text?.takeUnless(CharSequence::isEmpty)?.let { return it }
        item.htmlText
            ?.takeUnless(String::isEmpty)
            ?.let { return HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
        item.uri?.let { uri ->
            ClipData.Item(uri).coerceToNonEmptyText(context)?.let { return it }
        }
        item.intent?.let { intent ->
            ClipData.Item(intent).coerceToNonEmptyText(context)?.let { return it }
        }
    }
    return null
}

private fun ClipData.Item.coerceToNonEmptyText(context: Context): CharSequence? = try {
    coerceToText(context)?.takeUnless(CharSequence::isEmpty)
} catch (_: Exception) {
    null
}
