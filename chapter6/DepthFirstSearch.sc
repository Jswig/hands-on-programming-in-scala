def findShortestPaths[T](start: T, graph: Map[T, Seq[T]]): Map[T, List[T]] = {
  val seen = collection.mutable.Map(start -> List(start))
  val queue = collection.mutable.ArrayDeque((start, List(start)))
  while (queue.nonEmpty) {
    val (currentVertex, path) = queue.removeHead()
    if (
      !seen.contains(currentVertex) || seen(currentVertex).length > path.length
    )
      seen(currentVertex) = path
    for (vertex <- graph(currentVertex)) queue.prepend((vertex, vertex :: path))
  }
  seen.toMap
}

def shortestPath[T](start: T, dest: T, graph: Map[T, Seq[T]]): Seq[T] = {
  val shortestReversedPaths = findShortestPaths(start, graph)
  shortestReversedPaths(dest).reverse
}
