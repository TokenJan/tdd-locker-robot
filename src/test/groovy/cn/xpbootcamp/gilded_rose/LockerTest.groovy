package cn.xpbootcamp.gilded_rose

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException
import cn.xpbootcamp.gilded_rose.exception.LockerFullException
import spock.lang.Specification

class LockerTest extends Specification {

    void "should return ticket when store bag given the locker is not full"() {
        given:
        def locker  = new Locker(10)
        def bag = new Bag()

        when:
        def ticket = locker.store(bag)

        then:
        ticket != null
    }

    void "should throw locker full exception when store bag given locker is full"() {
        given:
        def fullLocker = getFullLocker()

        when:
        fullLocker.store(new Bag())

        then:
        thrown(LockerFullException)
    }

    void "should get my bag when take out bag from the locker given valid ticket"() {
        given:
        def locker = new Locker(10)
        def bagIn = new Bag()
        def ticket = locker.store(bagIn)

        when:
        def bagOut = locker.take(ticket)

        then:
        bagIn == bagOut
    }

    def "should throw invalid ticket exception when take out bag given used ticket"() {
        given:
        def locker = new Locker(10)
        def bag = new Bag()
        def ticket = locker.store(bag)
        locker.take(ticket)

        when:
        locker.take(ticket)

        then:
        thrown(InvalidTicketException)
    }

    def "should throw invalid ticket exception when take given invalid ticket"() {
        given:
        def locker = new Locker(10)
        def invalidTicket = new Ticket()

        when:
        locker.take(invalidTicket)

        then:
        thrown(InvalidTicketException)
    }

    Locker getFullLocker() {
        def locker = new Locker(1)
        locker.store(new Bag())
        return locker
    }
}
