package com.joduncan.hotels
import groovy.json.JsonSlurper

/**
 * Created by joshua on 8/17/2014.
 */
class HotelSortingAlgorithmsTest extends GroovyTestCase {
    def test_location

    void setUp() {
        super.setUp()
        def data_file = new File("sample_data.json")
        def slurper = new JsonSlurper()
        test_location = new Location(slurper.parse(data_file))
    }

    // TODO: write a generic test_algorithm() API that takes a closure for sorting, and a
    // list of expected results, and does most of the legwork that each of these test cases
    // is currently repeating.

    void test_min_room_price() {
        def hotels = test_location.hotels
        Collections.sort(hotels, HotelSortingAlgorithms.min_room_price)
        def names = hotels.collect { it.name }
        assertEquals([
                          "Bespin Level 1 at Cloud City",
                          "Crowne Plaza of Betazed",
                          "aloft Caprica City Center",
                          "Hyatt Place of Babylon 5",
                          "DoubleTree of Manassas, Reach"
                      ], names)
    }

    void test_max_room_price() {
        def hotels = test_location.hotels
        Collections.sort(hotels, HotelSortingAlgorithms.max_room_price)
        def names = hotels.collect { it.name }
        assertEquals([
                        "Bespin Level 1 at Cloud City",
                        "Crowne Plaza of Betazed",
                        "aloft Caprica City Center",
                        "Hyatt Place of Babylon 5",
                        "DoubleTree of Manassas, Reach"
                    ], names)
    }

    void test_star_rating() {
        def hotels = test_location.hotels
        Collections.sort(hotels, HotelSortingAlgorithms.star_rating)
        def names = hotels.collect { it.name }
        assertEquals([
                        "Crowne Plaza of Betazed",
                        "aloft Caprica City Center",
                        "DoubleTree of Manassas, Reach",
                        "Bespin Level 1 at Cloud City",
                        "Hyatt Place of Babylon 5"
                    ], names)
    }

    void test_mean_user_rating() {
        def hotels = test_location.hotels
        Collections.sort(hotels, HotelSortingAlgorithms.mean_user_rating)
        def names = hotels.collect { it.name }
        assertEquals([
                "Bespin Level 1 at Cloud City",
                "Hyatt Place of Babylon 5",
                "DoubleTree of Manassas, Reach",
                "Crowne Plaza of Betazed",
                "aloft Caprica City Center"
        ], names)
    }

    void test_num_user_ratings() {
        def hotels = test_location.hotels
        Collections.sort(hotels, HotelSortingAlgorithms.num_user_ratings)
        def names = hotels.collect { it.name }
        assertEquals([
                "Bespin Level 1 at Cloud City",
                "Crowne Plaza of Betazed",
                "DoubleTree of Manassas, Reach",
                "aloft Caprica City Center",
                "Hyatt Place of Babylon 5"
        ], names)
    }

    void test_by_name() {
        def hotels = test_location.hotels
        Collections.sort(hotels, HotelSortingAlgorithms.by_name)
        def names = hotels.collect { it.name }
        assertEquals([
                "aloft Caprica City Center",
                "Bespin Level 1 at Cloud City",
                "Crowne Plaza of Betazed",
                "DoubleTree of Manassas, Reach",
                "Hyatt Place of Babylon 5"
        ], names)
    }
}