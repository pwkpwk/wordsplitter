package com.example.prefixtree

/**
 * @param words Collection of substrings for checks with the check method
 *
 * Important: Words can contain only lowercase latin letters
 */
class PrefixTree(words: Iterable<String>) {

    private val root: Node = Node().also {
        for (word in words) {
            it.populate(word, 0)
        }
    }

    /**
     * Check if a word can be split into substrings passed to the constructor
     */
    fun check(word: String): Boolean {
        return checkWithOffset(word, 0)
    }

    private fun checkWithOffset(word: String, offset: Int): Boolean {

        if (offset >= word.length) {
            return true
        }

        val prefixes = root.collectPrefixes(word, offset)

        var found = false

        for (prefix in prefixes) {
            // Need to check all prefixes
            if (checkWithOffset(word, offset + prefix.length)) {
                found = true
            }
        }

        return found
    }

    private class Node {
        private val letters: Array<Node?> = arrayOfNulls(alphabetSize)

        var word: String? = null

        fun collectPrefixes(word: String, offset: Int): List<String> =
            mutableListOf<String>().also { list ->
                var node: Node? = this
                var letterIndex = offset

                while (node != null && letterIndex < word.length) {
                    node = node.letters[word[letterIndex].code - aCode]

                    node?.let { n ->

                        n.word?.let { list.add(it) }
                        ++letterIndex
                    }
                }
            }

        fun populate(inputWord: String, offset: Int) {
            if (offset < inputWord.length) {

                val letter = inputWord[offset].code - aCode
                val letterNode = letters[letter] ?: Node().also { letters[letter] = it }

                if (offset == inputWord.length - 1) {
                    letterNode.word = inputWord
                }

                letterNode.populate(inputWord, offset + 1)
            }
        }

        private companion object {
            const val aCode = 'a'.code
            const val alphabetSize = 'z'.code - aCode
        }
    }
}
