// SPDX-FileCopyrightText: 2015 - 2026 Rime community
//
// SPDX-License-Identifier: GPL-3.0-or-later

package com.osfans.trime.ime.composition

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CandidatesViewTest :
    StringSpec({
        "candidate position is clamped inside the visible parent" {
            clampCandidatePosition(-100f, 1000f, 300f, 5f) shouldBe 5f
            clampCandidatePosition(900f, 1000f, 300f, 5f) shouldBe 695f
            clampCandidatePosition(200f, 1000f, 300f, 5f) shouldBe 200f
        }

        "oversized candidate remains anchored at the parent origin" {
            clampCandidatePosition(100f, 200f, 300f, 5f) shouldBe 0f
        }

        "invalid editor coordinates fall back to a visible position" {
            clampCandidatePosition(Float.NaN, 1000f, 300f, 5f) shouldBe 5f
            clampCandidatePosition(Float.POSITIVE_INFINITY, 1000f, 300f, 5f) shouldBe 5f
        }
    })
