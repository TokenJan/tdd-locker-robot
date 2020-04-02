package cn.xpbootcamp.gilded_rose


import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException
import cn.xpbootcamp.gilded_rose.exception.LockerFullException
import spock.lang.Specification

class LockerTest extends Specification{

    void "should store the bag in the 1st locker when store bag given the 1st locker is not full"() {
        def locker = new Locker(5, 5)
        def lockers = (1..4).collect {
            new Locker(5, 5)
        }
        given:
        def robot = new LockerRobot([locker, lockers])
        def bag = new Bag()

        when:
        def ticket = robot.store(bag)

        then:
        ticket != null
        robot.getLockers().get(0).take(ticket) == bag
    }

    void "should store the bag in the 1st locker when take bag from the 1st locker and store bag given 1st locker is full and 2nd locker is not full"() {
        given:
        def firstLocker = new Locker(1, 1)
        def secondLocker = new Locker(1, 1)
        def robot = new LockerRobot([firstLocker, secondLocker])
        def ticket = firstLocker.store(new Bag())
        def bag = new Bag()

        when:
        firstLocker.take(ticket)
        def newTicket = robot.store(bag)

        then:
        robot.getLockers().get(0).take(newTicket) == bag
    }

    void "should throw locker full exception when store bag given all lockers are full"() {
        given:
        def lockers = (1..3).collect {
            new Locker(1, 0)
        }
        def robot = new LockerRobot(lockers)

        when:
        robot.store(new Bag())

        then:
        thrown(LockerFullException)
    }

    void "should return the bag when take given valid ticket"() {
        given:
        def lockers = (1..3).collect {
            new Locker(1, 1)
        }
        def robot = new LockerRobot(lockers)
        def bag = new Bag()
        def ticket = robot.store(bag)

        when:
        def myBag = robot.take(ticket)

        then:
        bag == myBag
    }

    def "should throw invalid ticket exception when take given invalid ticket"() {
        given:
        def lockers = (1..3).collect {
            new Locker(1, 1)
        }
        def robot = new LockerRobot(lockers)
        def invalidTicket = new Ticket()

        when:
        robot.take(invalidTicket)

        then:
        thrown(InvalidTicketException)
    }
}
