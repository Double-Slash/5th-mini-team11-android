package doubleslash05.mini.team11.model.data

import doubleslash05.mini.team11.util.LogUtils

data class RecipeVideoData(val path: String, val sections: Array<Int>) {
    fun getSectionIndex(ms: Int): Int {
        for (i in sections.size - 1 downTo 0) {
            if (sections[i] <= ms) {
                return i + 1
            }
        }
        return 0
    }

    fun getCurrentSction(currentMs: Int): Int {
        val index = getSectionIndex(currentMs)
        if (index == 0) return 0
        return sections[index - 1]
    }


    fun getNextSection(currentMs: Int): Int {
        val index = getSectionIndex(currentMs)
        if (index == sections.size) return sections[index - 1]
        return sections[index]
    }

    fun getPrevSection(currentMs: Int): Int {
        val index = getSectionIndex(currentMs)
        if (index <= 1) return 0
        return sections[index - 2]
    }
}