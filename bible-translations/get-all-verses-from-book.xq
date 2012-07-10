let $trans := string(/bible/@translation)
let $nl := "&#10;"
let $book_name := "Joshua"
for $chapter in /bible/book[@name=$book_name]/chapter
	let $verses := for $verse in $chapter/verse
		return concat($nl, $trans, ", ", "OT", ", ", $book_name, ", ", string($chapter/@name), ", ", string($verse/@name), ", ", string($verse))
	return ($verses)