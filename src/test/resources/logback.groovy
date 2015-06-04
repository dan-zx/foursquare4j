import org.slf4j.bridge.SLF4JBridgeHandler
import ch.qos.logback.classic.jul.LevelChangePropagator

def lcp = new LevelChangePropagator()
lcp.context = context
lcp.resetJUL = true
context.addListener(lcp)

java.util.logging.LogManager.getLogManager().reset()
SLF4JBridgeHandler.removeHandlersForRootLogger()
SLF4JBridgeHandler.install()
java.util.logging.Logger.getLogger('global').setLevel(java.util.logging.Level.FINEST)

appender('console', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = '[%d{yyyy/MM/dd HH:mm:ss.SSS}] {%thread} %-5level in %logger: %msg%n'
    }
}

logger('com.squareup.okhttp.mockwebserver', WARN)

root(ALL, ['console'])