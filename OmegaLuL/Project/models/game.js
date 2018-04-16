



var size = 4
var board = new Array(size)
for (var i = 0; i < size; i++){
	board[i] = new Array(size)
	for (var j = 0; j < size; j++){
		board[i][j] = 1
	}
}

console.log("test")

function print_board(){
	var return_string = ""
	for (var i = 0; i<size; i++){
		for (var j = 0; j < size; j++){
			return_string = return_string + board[i][j]
		}
		return_string = return_string + "\n"
	}
	return return_string
}

