// SPDX-FileCopyrightText: 2015 - 2026 Rime community
//
// SPDX-License-Identifier: GPL-3.0-or-later

package com.osfans.trime.ime.core

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class InputDeviceManagerTest :
    StringSpec({
        "TYPE_NULL editor can restore an active candidates window" {
            shouldForceShowInputView(
                isNullInputType = true,
                isCandidatesView = true,
                isPrintingKey = true,
                hasNoModifiers = true,
            ) shouldBe true
        }

        "TYPE_NULL editor does not force an inactive input window" {
            shouldForceShowInputView(
                isNullInputType = true,
                isCandidatesView = false,
                isPrintingKey = true,
                hasNoModifiers = true,
            ) shouldBe false
        }

        "regular editor can restore its input window" {
            shouldForceShowInputView(
                isNullInputType = false,
                isCandidatesView = false,
                isPrintingKey = true,
                hasNoModifiers = true,
            ) shouldBe true
        }

        "modified and non-printing keys do not force the input window" {
            shouldForceShowInputView(
                isNullInputType = false,
                isCandidatesView = true,
                isPrintingKey = true,
                hasNoModifiers = false,
            ) shouldBe false
            shouldForceShowInputView(
                isNullInputType = false,
                isCandidatesView = true,
                isPrintingKey = false,
                hasNoModifiers = true,
            ) shouldBe false
        }
    })
