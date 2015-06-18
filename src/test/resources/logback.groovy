/*
 * Copyright 2014-2015 Daniel Pedraza-Arcega
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
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