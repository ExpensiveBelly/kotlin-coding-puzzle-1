package com.igorwojda.list.formattrainroute

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun formatTrainRoute(stations: List<String>): String {
    return "Train is calling at " +
            if (stations.size == 1) stations.first()
            else stations.dropLast(1).joinToString() + " and " + stations.last()
}

private class Test {
    @Test
    fun `formatTrainRoute list "Luton"`() {
        formatTrainRoute(listOf("Luton")) shouldBeEqualTo "Train is calling at Luton"
    }

    @Test
    fun `formatTrainRoute list "Luton", "Harpenden"`() {
        formatTrainRoute(listOf("Luton", "Harpenden")) shouldBeEqualTo "Train is calling at Luton and Harpenden"
    }

    @Test
    fun `formatTrainRoute list "Luton", "Harpenden", "London"`() {
        val list = listOf("Luton", "Harpenden", "London")
        formatTrainRoute(list) shouldBeEqualTo "Train is calling at Luton, Harpenden and London"
    }

    @Test
    fun `formatTrainRoute list "Luton", "Harpenden", "St Albans", "London"`() {
        val list = listOf("Luton", "Harpenden", "St Albans", "London")
        formatTrainRoute(list) shouldBeEqualTo "Train is calling at Luton, Harpenden, St Albans and London"
    }
}
