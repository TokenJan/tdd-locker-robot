package cn.xpbootcamp.gilded_rose


import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException
import cn.xpbootcamp.gilded_rose.exception.LockerFullException
import spock.lang.Specification

class LockerRobotTest extends Specification{

    void "should store the bag in the 1st locker when store bag given the 1st locker is not full"() {
        given:
        def firstLocker = new Locker(1)
        def restLockers = (1..4).collect {
            new Locker(1)
        }
        def robot = new LockerRobot([firstLocker, restLockers])
        def bag = new Bag()

        when:
        def ticket = robot.store(bag)

        then:
        ticket != null
        firstLocker.take(ticket) == bag
    }

    void "should store the bag in the 2nd locker when store bag given 1st locker is full and 2nd locker is not full"() {
        given:
        def firstLocker = getFullLocker()
        def secondLocker = new Locker(1)
        def restLockers = (1..3).collect {
            new Locker(1)
        }
        def robot = new LockerRobot([firstLocker, secondLocker, restLockers])
        def bag = new Bag()

        when:
        def ticket = robot.store(bag)

        then:
        secondLocker.take(ticket) == bag
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

    void "should return the bag when take bag given valid ticket and stored the bag in the 1st locker"() {
        given:
        def firstLocker = new Locker(1)
        def restLockers = (1..4).collect {
            new Locker(1)
        }
        def robot = new LockerRobot([firstLocker, restLockers])
        def bagIn = new Bag()
        def ticket = firstLocker.store(bagIn)

        when:
        def bagOut = robot.take(ticket)

        then:
        bagIn == bagOut
    }

    void "should return the bag when take bag given valid ticket and stored the bag in the 2nd locker"() {
        given:
        def firstLocker = new Locker(1)
        def secondLocker = new Locker(1)
        def restLockers = (1..3).collect {
            new Locker(1)
        }
        def robot = new LockerRobot([firstLocker, secondLocker, restLockers])
        def bagIn = new Bag()
        def ticket = secondLocker.store(bagIn)

        when:
        def bagOut = robot.take(ticket)

        then:
        bagIn == bagOut
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
