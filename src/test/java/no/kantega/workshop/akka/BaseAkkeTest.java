package no.kantega.workshop.akka;

import akka.actor.ActorSystem;
import akka.testkit.JavaTestKit;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * This tests uses one Akka-system for all tests, to save duplicating this code in all the tests.
 */
public abstract class BaseAkkeTest extends JavaTestKit {

    protected static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void teardown() {
        JavaTestKit.shutdownActorSystem(system);
        system.awaitTermination();
        system = null;
    }

    protected BaseAkkeTest() {
        super(system);
    }
}
