package com.furre.dev.wolt_internship_furre_dev.utils.mathutils

import kotlin.math.roundToInt

class Geo(private val lat: Double, private val lon: Double) {


    companion object {
        const val earthRadiusKm: Double = 6372.8

        const val minLat: Double = -90.0
        const val maxLat: Double = 90.0

        const val minLong: Double = -180.0
        const val maxLong: Double = 180.0

    }

    /**
     * Haversine formula. Giving great-circle distances between two points on a sphere from their longitudes and latitudes.
     * It is a special case of a more general formula in spherical trigonometry, the law of haversines, relating the
     * sides and angles of spherical "triangles".
     *
     * https://rosettacode.org/wiki/Haversine_formula#Java
     *
     * @return Distance in kilometers
     */

    fun haversine(destination: Geo): Int {
        val dLat = Math.toRadians(destination.lat - this.lat);
        val dLon = Math.toRadians(destination.lon - this.lon);
        val originLat = Math.toRadians(this.lat);
        val destinationLat = Math.toRadians(destination.lat);

        val a = Math.pow(Math.sin(dLat / 2), 2.toDouble()) + Math.pow(Math.sin(dLon / 2), 2.toDouble()) * Math.cos(originLat) * Math.cos(destinationLat);
        val c = 2 * Math.asin(Math.sqrt(a));

        //MODIFICATIONS:
        // Added (* 1000) to return meters instead of kilometers
        // roundToInt because I don't want to deal with decimals in meters format
        return ((earthRadiusKm * c) * 1000).roundToInt();
    }

}