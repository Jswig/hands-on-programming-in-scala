import java.lang.Thread

def retry[T](max: Int, delayMilliseconds: Int = 100)(f: => T): T = {
  var tries = 0
  var result: Option[T] = None
  var currentDelayMilliseconds = delayMilliseconds
  while (result == None) {
    try { result = Some(f) }
    catch {
      case e: Throwable =>
        tries += 1
        if (tries > max) throw e
        else {
          currentDelayMilliseconds = currentDelayMilliseconds * 2
          println(s"failed, retry #$tries in $currentDelayMilliseconds ms")
          Thread.sleep(currentDelayMilliseconds)
        }
    }
  }
  result.get
}
