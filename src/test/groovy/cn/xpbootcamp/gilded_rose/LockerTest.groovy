package cn.xpbootcamp.gilded_rose


import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException
import cn.xpbootcamp.gilded_rose.exception.LockerFullException
import spock.lang.Specification

class LockerTest extends Specification{

    void "should store the bag in the 1st locker when store bag given the 1st locker is not full"() {
        given:
        def robot = new Robot()
        def locker = new Locker(5)
        def lockers = (1..4).collect {
            new Locker(5)
        }
        def bag = new Bag()
        robot.addLocker(locker)
        robot.addLockers(lockers)

        when:
        def ticket = robot.store(bag)

        then:
        ticket != null
        locker.take(ticket) == bag
    }

    void "should store the bag in the 4th locker when store bag given the 1,2,3 locker is full and 4th locker is not full"() {
        given:
        def robot = new Robot()
        def locker = new Locker(1)
        def lockers = (1..3).collect {
            new Locker(1)
        }
        def bag = new Bag()
        robot.addLockers(lockers)
        robot.addLocker(locker)


        when:
        robot.store(new Bag())
        robot.store(new Bag())
        robot.store(new Bag())
        def ticket = robot.store(bag)

        then:
        ticket != null
        locker.take(ticket) == bag
    }

    void "should throw locker full exception when store bag given all lockers are full"() {
        given:
        def robot = new Robot()
        def lockers = (1..3).collect {
            new Locker(1)
        }
        robot.addLockers(lockers)

        when:
        robot.store(new Bag())
        robot.store(new Bag())
        robot.store(new Bag())
        robot.store(new Bag())

        then:
        thrown(LockerFullException)
    }

    void "should return the bag when take given valid ticket"() {
        given:
        def robot = new Robot()
        def locker = new Locker(10)
        robot.addLocker(locker)
        def bag = new Bag()
        def ticket = robot.store(bag)

        when:
        def myBag = robot.take(ticket)

        then:
        bag == myBag
    }

    def "should throw invalid ticket exception when take given used ticket"() {
        given:
        def robot = new Robot()
        def locker = new Locker(10)
        robot.addLocker(locker)
        def bag = new Bag()
        def ticket = locker.store(bag)
        robot.take(ticket)

        when:
        robot.take(ticket)

        then:
        thrown(InvalidTicketException)
    }

    def "should throw invalid ticket exception when take given invalid ticket"() {
        given:
        def robot = new Robot()
        def locker = new Locker(10)
        robot.addLocker(locker)
        def invalidTicket = new Ticket()

        when:
        robot.take(invalidTicket)

        then:
        thrown(InvalidTicketException)
    }
}
