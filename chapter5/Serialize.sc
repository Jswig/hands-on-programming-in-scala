trait StrWriter[T] {
  def write(v: T): String
}

object StrWriter {
  implicit object WriteInt extends StrWriter[Int] {
    def write(x: Int): String = x.toString
  }
  implicit object WriteBoolean extends StrWriter[Boolean] {
    def write(x: Boolean): String = x.toString
  }
  implicit object WriteDouble extends StrWriter[Double] {
    def write(x: Double): String = x.toString
  }
}

implicit def WriteSeq[T](implicit w: StrWriter[T]) = new StrWriter[Seq[T]] {
  def write(x: Seq[T]) = x.map(w.write).mkString("[", ",", "]")
}

implicit def WriteTuple[T, V](implicit w1: StrWriter[T], w2: StrWriter[V]) =
  new StrWriter[(T, V)] {
    def write(x: (T, V)): String = {
      val (left, right) = x
      val (leftStr, rightStr) = (w1.write(left), w2.write(right))
      s"[$leftStr,$rightStr]"
    }
  }

def writeToString[T](x: T)(implicit writer: StrWriter[T]) = {
  writer.write(x)
}

def writeToConsole[T](t: T)(implicit writer: StrWriter[T]): Unit = {
  scala.Console.out.println(writer.write(t))
}
