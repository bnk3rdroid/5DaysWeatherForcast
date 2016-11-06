package bnk3r.droid.weatherforecast.modules.network.model

class City(
        private var id: Int,
        private var name: String,
        private var coord: Coord,
        private var country: String,
        private var population: Int,
        private var sys: Sys) {
}

