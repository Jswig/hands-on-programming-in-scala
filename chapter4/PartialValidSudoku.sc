def isValidSudoku(grid: Array[Array[Integer]]): Boolean = {
  !Range(0, 9).exists(i => {
    var isNotEmptyCell: Integer => Boolean = i => (if (i != 0) true else false)
    val row = (
      Range(0, 9)
        .map(grid(i)(_))
        .filter(isNotEmptyCell)
    )
    val col = (
      Range(0, 9)
        .map(grid(_)(i))
        .filter(isNotEmptyCell)
    )
    val square = (
      Range(0, 9)
        .map(j => grid((i % 3) * 3 + j % 3)((i / 3) * 3 + j / 3))
        .filter(isNotEmptyCell)
    )
    row.distinct.length != row.length ||
    col.distinct.length != col.length ||
    square.distinct.length != square.length
  })
}
