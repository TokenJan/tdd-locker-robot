package cn.xpbootcamp.gilded_rose

import spock.lang.Specification

class LockerSystemTest extends Specification{

    void "should return ticket and open the locker when store given locker system is not full"() {
        given:
        def lockerSystem  = new LockerSystem(10)

        when:
        def ticket = lockerSystem.store()

        then:
        def locker = lockerSystem.findLockerByTicker(ticket)
        lockerSystem.getFreeLockerCount() == 9
        locker.isOpen()
        !locker.isFree()
        ticket.getId() != null
    }

    void "should not return ticket when store given locker system is full"() {
        given:
        def lockerSystem = new LockerSystem(10)
        for (int i = 0; i < 10; i++) {
            lockerSystem.store()
        }

        when:
        lockerSystem.store()

        then:
        lockerSystem.getFreeLockerCount() == 0
        thrown(LockerFullException)
    }

    void "should open the locker when take given valid ticket"() {
        given:
        def lockerSystem = new LockerSystem(10)
        def ticket = lockerSystem.store()

        when:
        def locker = lockerSystem.take(ticket)

        then:
        lockerSystem.getFreeLockerCount() == 10
        locker.isOpen()
        locker.isFree()
    }

    def "should throw invalid ticket exception when take given used ticket"() {
        given:
        def lockerSystem = new LockerSystem(10)
        def ticket = lockerSystem.store()
        lockerSystem.take(ticket)

        when:
        lockerSystem.take(ticket)

        then:
        thrown(InvalidTicketException)
    }

    def "should throw invalid ticket exception when take given invalid ticket"() {
        given:
        def lockerSystem = new LockerSystem(10)
        def invalidTicket = new Ticket()

        when:
        lockerSystem.take(invalidTicket)

        then:
        thrown(InvalidTicketException)
    }
}
