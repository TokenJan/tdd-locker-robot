package cn.xpbootcamp.gilded_rose


import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException
import cn.xpbootcamp.gilded_rose.exception.LockerFullException
import spock.lang.Specification

class LockerTest extends Specification{

    void "should return ticket and open the locker when store given locker is not full"() {
        given:
        def locker  = new Locker(10)
        def bag = new Bag()

        when:
        def ticket = locker.store(bag)

        then:
        ticket != null
    }

    void "should not return ticket when store given locker system is full"() {
        given:
        def locker = new Locker(1)
        def bag1 = new Bag()
        def bag2 = new Bag()

        when:
        locker.store(bag1)
        locker.store(bag2)

        then:
        thrown(LockerFullException)
    }

    void "should return the bag when take given valid ticket"() {
        given:
        def locker = new Locker(10)
        def bag = new Bag()
        def ticket = locker.store(bag)

        when:
        def myBag = locker.take(ticket)

        then:
        bag == myBag
    }

    def "should throw invalid ticket exception when take given used ticket"() {
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
}
