package com.example.prefixtree

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PrefixTreeTests {
    @Test
    fun nonoverlappingSubstrings_checkSplittable_returnsTrue() {
        assertTrue { PrefixTree(listOf("cat", "dog", "it", "is")).check("catdogiscatdog") }
    }

    @Test
    fun overlappingSubstrings_checkSplittable_returnsTrue() {
        assertTrue { PrefixTree(listOf("ca", "c", "cat", "dog", "is", "d")).check("catdogiscatdog") }
        assertTrue { PrefixTree(listOf("ca", "c", "cat", "dog", "is", "d")).check("catdogiscatddog") }
    }

    @Test
    fun overlappingSubstrings_checkUnsplittable_returnsFalse() {
        assertFalse { PrefixTree(listOf("ca", "c", "cat", "dog")).check("catdogiscatdog") }
    }
}