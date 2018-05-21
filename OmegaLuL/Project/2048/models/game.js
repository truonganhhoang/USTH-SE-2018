var size = 4
var score = 0
var board = new Array(size)
for (var i = 0; i < size; i++){
	board[i] = new Array(size)
	for (var j = 0; j < size; j++){
		board[i][j] = 0
	}
}


function print_board(){
	
	var return_string = "" 
	var css = 'odd'
		for (var i = 0; i<size; i++){
			return_string = return_string + "<div class='row'>"

			for (var j = 0; j < size; j++){
				return_string = return_string + '<div class="board-slot tile-' + board[i][j] + '" > ' + board[i][j] + '</div>';
			}

			return_string = return_string + "</div>"
		}
	return return_string
}

 function get_empty_space(){
	var empty = new Array();
	var count = 0;
	for (var i = 0; i < size; i++){
 		for (var j = 0; j<size; j++){
			if (board[i][j] == 0){
				empty[count] = [i, j];
				count = count + 1;
			}
		}
	}
    return empty;
}


function new_number(){
    var empty = get_empty_space();
    var len = empty.length;
    if (len == 0){
    	return
    }
    if (len == 1){
		board[empty[0][0]][empty[0][1]] = 2;
		
		return;
	}
	var x = Math.floor((Math.random() * len));
	var y = Math.floor((Math.random() * len));
	while (x == y){
		x = Math.floor((Math.random() * len));
	}
            
	board[empty[x][0]][empty[x][1]] = 2;
	board[empty[y][0]][empty[y][1]] = 2;

}


function move_left(){

	for (var i = 0; i<size; i++){
		for (var j = 1; j<size; j++){
			if (board[i][j] == 0){
				continue;
			}
			else{
				for (var k = j - 1; k >=-1; k--){
					if (k == -1){
						
						board[i][k+1] = board[i][j];
						board[i][j] = 0;
						break;
					}
					if (board[i][k] == 0){
						
						continue;
					}
					else if (board[i][k] == board[i][j]){
					
						board[i][k] = board[i][k]  + board[i][j];
						score = score + board[i][k]
						board[i][j] = 0;
						break;
					}
					else if (board[i][k] != board[i][j]){
					
						var temp = board[i][j];
						board[i][j] = 0;
						board[i][k+1] = temp;
						break;
					}
				}
			}
		}
	}
}

function move_up(){
	for (var i = 0; i<size; i++){
		for (var j = 1; j<size; j++){
			if (board[i][j] == 0){
				continue;
			}
			else{
				for (var k = j - 1; k >= -1; k--){
					if (k == -1){
						board[k+1][i] = board[j][i];
						board[j][i] = 0;
						break;
					}
					if (board[k][i] == 0){
						continue;
					}
					else if (board[k][i] == board[j][i]){
						board[k][i] = board[k][i] + board[j][i];
						score = score + board[k][i]
						board[j][i] = 0;
						break;
					}
					else if (board[k][i] != board[j][i]){
						var temp = board[j][i];
						board[j][i] = 0;
						board[k+1][i] = temp;
						break;
					}
				}
			}
		}
	}
}

function move_down(){
	for (var i = 0; i<size; i++){
		for (var j = size -2; j>=0; j--){
			if (board[j][i] == 0){
				continue;
			}
			else{
				for (var k = j+1; k<=size; k++){
					if (k == size){
						board[k-1][i] = board[j][i];
						board[j][i] = 0;
						break;
					}
					if (board[k][i] == 0){
						continue;
					}
					else if (board[k][i] == board[j][i]){
						board[k][i] = board[k][i] + board[j][i];
						score = score + board[j][i]
						board[j][i] = 0;
						break;
					}
					else if (board[k][i] != board[j][i]){
						var temp = board[j][i];
						board[j][i] = 0;
						board[k-1][i] = temp;
						break;
					}
				}
			}
		}
	}
}


function move_right(){

	for (var i = 0; i<size; i++){
		
		for (var j = size - 2; j >=0; j--){
			if (board[i][j] == 0){
				continue;
            }
			else{
				for (var k = j +1; k <= size; k++){
					if (k == size){
						
                        board[i][k-1] = board[i][j];
                        board[i][j] = 0;
                        break;
					}
					if (board[i][k] == 0){
                       
                        continue;
					}
					else if (board[i][k] == board[i][j]){
                      
						score = score + board[i][k]
                        board[i][k] =  board[i][k]+board[i][j];
                        board[i][j] = 0;
                        break;
                    }
                     else if (board[i][k] != board[i][j]){
                      
                        var temp = board[i][j];
                        board[i][j] = 0;
                        board[i][k-1] = temp;
                        
                        break;
                    }
				}
			}
		}
	}
}

function is_equal(array1, array2){
	for (var i = 0; i<size; i++){
		for (var j = 0; j<size; j++){
			if (array1[i][j] != array2[i][j]){
				return false
			}
		}
	}
	return true
}

function clone_array(array1){
	var array2 = []
	for (var i = 0; i<size; i++){
		array2[i] = array1[i].slice()
	}
	return array2
}

function check_game_over(){
	second_board = clone_array(board)
	move_left()
	if (is_equal(second_board, board) == false){
		board = clone_array(second_board)
		return false
	}
	board = clone_array(second_board)
	move_right()
	if (is_equal(second_board, board) == false){
		board = clone_array(second_board)
		return false
	}
	board = clone_array(second_board)
	move_up()
	if (is_equal(second_board, board) == false){
		board = clone_array(second_board)
		return false
	}
	board = clone_array(second_board)
	move_down()
	if (is_equal(second_board, board) == false){
		board = clone_array(second_board)
		return false
	}
	board = clone_array(second_board)
	return true
}