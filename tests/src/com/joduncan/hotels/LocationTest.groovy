package com.joduncan.hotels
import groovy.json.JsonSlurper

/**
 * Created by joshua on 8/17/2014.
 */
class LocationTest extends GroovyTestCase {
    def test_location
    void setUp() {
        super.setUp()
        def data_file = new File("sample_data.json")
        def slurper = new JsonSlurper()
        test_location = new Location(slurper.parse(data_file))
    }

    void test_finish_jsonslurpers_job() {
        test_location.hotels.each { assert it.class != Hotel }
        test_location.finish_jsonslurpers_job()
        test_location.hotels.each { assert it.class == Hotel }
    }

    void test_distance_from_me() {
        test_location.finish_jsonslurpers_job()
        def hotels = test_location.hotels
        Collections.sort(hotels, test_location.distance_from_me)
        def names = hotels.collect { it.name }
        assertEquals([
                "Hyatt Place of Babylon 5",
                "Crowne Plaza of Betazed",
                "aloft Caprica City Center",
                "Bespin Level 1 at Cloud City",
                "DoubleTree of Manassas, Reach"
        ], names)
    }
}
