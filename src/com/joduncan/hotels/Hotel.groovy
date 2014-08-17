package com.joduncan.hotels
/**
 * Created by joshua on 8/17/2014.
 */
class Hotel {
    String name
    Float location_x, location_y, location_z
    List<RoomRate> rates
    Integer star_rating
    List<UserRating> user_ratings

    Float pythagorean_distance_from(Float x, Float y, Float z) {
        Float delta_x = Math.abs(x-location_x),
              delta_y = Math.abs(y-location_y),
              delta_z = Math.abs(z-location_z)

        Math.sqrt(delta_x * delta_x +
                  delta_y * delta_y +
                  delta_z * delta_z)
    }

    def finish_jsonslurpers_job() {
        rates = rates.collect { it as RoomRate }
        user_ratings = user_ratings.collect { it as UserRating }
    }
}
