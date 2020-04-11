package cn.xpbootcamp.gilded_rose

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException
import cn.xpbootcamp.gilded_rose.exception.LockerFullException
import spock.lang.Specification

class SmartLockerRobotTest extends Specification {

    void "should store the bag in the 1st locker when store bag given 2 lockers in total and the 1st locker has 2 free spaces and the 2nd locker has 1 free space"() {
        given:
        def firstLocker = new Locker(2)
        def secondLocker = new Locker(1)
        def smartLockerRobot = new SmartLockerRobot([firstLocker, secondLocker])
        def bag = new Bag()

        when:
        def ticket = smartLockerRobot.store(bag)

        then:
        ticket != null
        bag == firstLocker.take(ticket)
    }

    void "should store the bag in the 2nd locker when store bag given 2 lockers in total and the 1st locker has 1 free spaces and the 2nd locker has 2 free space"() {
        given:
        def firstLocker = new Locker(1)
        def secondLocker = new Locker(2)
        def smartLockerRobot = new SmartLockerRobot([firstLocker, secondLocker])
        def bag = new Bag()

        when:
        def ticket = smartLockerRobot.store(bag)

        then:
        ticket != null
        bag == secondLocker.take(ticket)
    }

    void "should store the bag in the 1st locker when store bag given 2 lockers in total and the both lockers have 2 free space"() {
        given:
        def firstLocker = new Locker(2)
        def secondLocker = new Locker(2)
        def smartLockerRobot = new SmartLockerRobot([firstLocker, secondLocker])
        def bag = new Bag()

        when:
        def ticket = smartLockerRobot.store(bag)

        then:
        ticket != null
        bag == firstLocker.take(ticket)
    }

    void "should throw locker full exception when store bag given all lockers are full"() {
        given:
        def lockers = (1..5).collect {
            getFullLocker()
        }
        def robot = new LockerRobot(lockers)

        when:
        robot.store(new Bag())

        then:
        thrown(LockerFullException)
    }

    void "should return my bag when take bag given valid ticket and stored the bag in the 1st locker"() {
        given:
        def firstLocker = new Locker(2)
        def secondLocker = new Locker(2)
        def smartLockerRobot = new SmartLockerRobot([firstLocker, secondLocker])
        def bag = new Bag()

        when:
        def ticket = firstLocker.store(bag)

        then:
        ticket != null
        bag == smartLockerRobot.take(ticket)
    }

    void "should return my bag when take bag given valid ticket and stored the bag in the 2st locker"() {
        given:
        def firstLocker = new Locker(2)
        def secondLocker = new Locker(2)
        def smartLockerRobot = new SmartLockerRobot([firstLocker, secondLocker])
        def bag = new Bag()

        when:
        def ticket = secondLocker.store(bag)

        then:
        ticket != null
        bag == smartLockerRobot.take(ticket)
    }

    def "should throw invalid ticket exception when take given invalid ticket"() {
        given:
        def lockers = (1..3).collect {
            new Locker(1)
        }
        def robot = new LockerRobot(lockers)
        def invalidTicket = new Ticket()

        when:
        robot.take(invalidTicket)

        then:
        thrown(InvalidTicketException)
    }

    Locker getFullLocker() {
        def locker = new Locker(1)
        locker.store(new Bag())
        return locker
    }
}
