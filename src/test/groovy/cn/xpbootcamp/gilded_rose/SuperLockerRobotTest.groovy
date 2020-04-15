package cn.xpbootcamp.gilded_rose

import cn.xpbootcamp.gilded_rose.exception.LockerFullException
import spock.lang.Specification

class SuperLockerRobotTest extends Specification {

    void "should store the bag in the 1st locker when store bag given the super locker robot manages 2 lockers and the 1st locker's capacity is 1, vacancy rate is 100% while the 2nd locker's capacity is 4, vacancy rate is 50%"() {
        given:
        def firstLocker = new Locker(1)
        def secondLocker = new Locker(4)
        def superLockerRobot = new SuperLockerRobot([firstLocker, secondLocker])
        (1..2).forEach({ secondLocker.store(new Bag()) })
        def bag = new Bag()

        when:
        def ticket = superLockerRobot.store(bag)

        then:
        ticket != null
        bag == firstLocker.take(ticket)
    }

    void "should store the bag in the 1st locker when store bag given the super locker robot manages 2 lockers and the 1st locker's capacity is 4, vacancy rate is 50% while the 2nd locker's capacity is 1, vacancy rate is 100%"() {
        given:
        def firstLocker = new Locker(4)
        def secondLocker = new Locker(1)
        def superLockerRobot = new SuperLockerRobot([firstLocker, secondLocker])
        (1..2).forEach({ firstLocker.store(new Bag()) })
        def bag = new Bag()

        when:
        def ticket = superLockerRobot.store(bag)

        then:
        ticket != null
        bag == secondLocker.take(ticket)
    }

    void "should store the bag in the 1st locker when store bag given the super locker robot manages 2 lockers and the 1st locker's capacity is 4, vacancy rate is 50% while the 2nd locker's capacity is 2, vacancy rate is 50%"() {
        given:
        def firstLocker = new Locker(4)
        def secondLocker = new Locker(2)
        def superLockerRobot = new SuperLockerRobot([firstLocker, secondLocker])
        (1..2).forEach({ firstLocker.store(new Bag()) })
        secondLocker.store(new Bag())
        def bag = new Bag()

        when:
        def ticket = superLockerRobot.store(bag)

        then:
        ticket != null
        bag == firstLocker.take(ticket)
    }

    void "should throw full locker exception when store bag given the super locker robot manages 2 lockers with vacancy rate 0%"() {
        given:
        def firstLocker = getFullLocker()
        def secondLocker = getFullLocker()
        def superLockerRobot = new SuperLockerRobot([firstLocker, secondLocker])
        def bag = new Bag()

        when:
        def ticket = superLockerRobot.store(bag)

        then:
        thrown(LockerFullException)
    }

    Locker getFullLocker() {
        Locker locker = new Locker(1)
        locker.store(new Bag())
        return locker
    }

    void "should return the bag when given the ticket to robot and take out bag given ticket is valid and stored the bag in the 1st locker"() {
        given:
        def firstLocker = new Locker(1)
        def secondLocker = new Locker(1)
        def robot = new SuperLockerRobot([firstLocker, secondLocker])
        def bagIn = new Bag()
        def ticket = firstLocker.store(bagIn)

        when:
        def bagOut = robot.take(ticket)

        then:
        bagIn == bagOut
    }
}
