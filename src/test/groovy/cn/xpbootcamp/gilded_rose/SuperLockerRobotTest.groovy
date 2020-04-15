package cn.xpbootcamp.gilded_rose

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
}
