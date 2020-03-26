package cn.xpbootcamp.gilded_rose

import spock.lang.Specification

class LockerSystemTest extends Specification{

    void "should return ticket and open the locker when store given locker system is not full"() {
        given:
        def lockerSystem  = new LockerSystem(10)

        when:
        def ticket = lockerSystem.store()

        then:
        lockerSystem.getLockerCount() == 9
        ticket.getLocker().isOpen()
        !ticket.getLocker().isEmpty()
        ticket.getId() != null
    }

    void "should not return ticket when store given locker system is full"() {
        given:
        def lockerSystem = new LockerSystem(10)
        for (int i = 0; i < 10; i++) {
            lockerSystem.store()
        }

        when:
        def ticket = lockerSystem.store()

        then:
        lockerSystem.getLockerCount() == 0
        ticket == null
    }
}
