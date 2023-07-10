def renderSudokuRow(rowContents: Array[Int]): String = {
    var renderedRow = "| "
    for (i <- Range(0, 3)) {
        val renderedRowSingleSquare = (
            rowContents
            .slice(i*3, i*3+3)
            .map(x => if (x == 0) " " else s"$x")
            .mkString("", " ", " | ")
        )
        renderedRow += (renderedRowSingleSquare)
    }
    renderedRow += "\n"
    return renderedRow
}

def renderSudoku(contents: Array[Array[Int]]): String = {
    val boxDelimiterLine = (for (_ <- Range(0, 3)) yield "+-------").mkString(sep="") + "+\n"   
    var renderedSudoku = boxDelimiterLine
    for (i <- Range(0, 3)) {
        for (j <- Range(0, 3)) renderedSudoku += renderSudokuRow(contents(3*i+j))
        renderedSudoku += boxDelimiterLine
    }
    return renderedSudoku
}