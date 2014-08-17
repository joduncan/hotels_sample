package com.joduncan.hotels
/**
 * Created by joshua on 8/17/2014.
 */
class Location {
    Integer location_id
    String name
    Float location_x, location_y, location_z
    List<LocationTax> taxes
    List<Hotel> hotels

    def distance_from_me = [
            compare: { x, y ->
                x.pythagorean_distance_from(location_x, location_y, location_z) \
                <=> \
                y.pythagorean_distance_from(location_x, location_y, location_z)
                }
    ] as Comparator

    // finish_jsonslurpers_job() may be helpful to some, because when jsonslurper passes a map to groovy's
    // default class constructor, groovy isn't smart enough (or just doesn't care) about any class's
    // instance variables' types. Nested objects (or nested lists of objects) will be represented
    // as a groovy.json.internal.LazyMap representation of the json data, not as the relevant
    // POGO object type(s).

    // basically, if you do something like: (pardon the horrible abbreviated messiness)
    // my_location = new Location(new JsonSlurper().parse(new File("my_json_data.json")))
    // you should do this immediately afterward:
    // my_location.finish_jsonslurpers_job()
    // if you actually care about using real POGO's. otherwise you'll need to convert
    // the slurped maps into POGO's as step one before any further data processing
    // (such as calling class methods).

    // having to call this manually (and having to know if any contained classes need further POGO-ification)
    // is perhaps a violation of some OOP principle, but I'm willing to bend any such rule a little bit since
    // it makes converting the JSON representatoin->POGO's so dang fast and easy.

    // NOTE: given how Groovy accesses maps and objects, almost all of my implementation and tests actually
    // work no matter whether you call finish_jsonslurpers_job() or not. The only exception is
    // distance-from-location searching, since that requires some math in the Hotels library. But if you
    // haven't dug into Groovy before you might have wondered WTF seems to be going on in my code or in
    // the actual execution of it by the Groovy compiler, since I have these classes but Groovy doesn't
    // convert JSON->POGO's by default when nested.

    // P.P.S.S sorry for such a long block of comments. I felt it was better to be verbose than to leave
    // details missing at the potential risk of confusion or misunderstanding. hopefully my words have
    // helped clarify, rather than adding to the confusion.
    def finish_jsonslurpers_job() {
        hotels = hotels.collect { it as Hotel }
        taxes = taxes.collect { it as LocationTax }
        hotels.each { it.finish_jsonslurpers_job() }
    }
}
