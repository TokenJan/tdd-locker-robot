package cn.xpbootcamp.gilded_rose

import spock.lang.Specification

class LockerSystemTest extends Specification{

    void "should return ticket and open the locker when store given lockers are not full"() {
        given:
        def lockerSystem  = new LockerSystem(10)

        when:
        def ticket = lockerSystem.store()

        then:
        lockerSystem.getLockerCount() == 9
        ticket.getLocker().isOpen()
        ticket.getId() != null
    }
}
