package bnk3r.droid.weatherforecast.modules.network.model

class WeatherDayObject(
        var dt: Long,
        var main: Main,
        var weather: List<Weather>,
        var clouds: Clouds,
        var wind: Wind,
        var rain: Rain,
        var sys: Sys,
        var dt_txt: String
) {

    class Sys(private var pod: String) {}
}