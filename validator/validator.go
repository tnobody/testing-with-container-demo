package main

import (
	"encoding/json"
	"log"
	"fmt"
	"errors"
	"net/http"
	"strings"
)

type Result struct {
	Winner string `json:"winner"`
}

func checkForValue(value string, values []string) bool {
	var hasValue bool = true
	for _, v := range values {
		hasValue = hasValue && strings.ToLower(v) == strings.ToLower(value)
	}
	return hasValue

}


func checkBoard(board []string) (winner string, err error) {
	if len(board) == 9 {
		checkRanges := [][]string {
			board[:3],
			board[3:6],
			board[6:9],
			{board[0], board[4], board[8]},
			{board[2], board[4], board[6]},
			{board[0], board[3], board[6]},
			{board[1], board[4], board[7]},
			{board[2], board[5], board[8]},
		}
		for _, player := range []string{"0", "1"} {
			for _, checkRange := range checkRanges {
				if checkForValue(player, checkRange) {
					fmt.Printf("%s in range %s\n", player, checkRange);
					return player, nil
				}
				fmt.Printf("%s not in range %s\n", player, checkRange);
			}
		}
		return "", nil
	} else {
		return "", errors.New("board must have length of 9")
	}

}

func main() {
	http.HandleFunc("/api/validate", func(w http.ResponseWriter, r *http.Request) {
		board, ok := r.URL.Query()["board"];
		var result Result
		if !ok || len(board[0]) < 1 {
			log.Println("Url Param 'board' is missing")
			result = Result{Winner: ""}
		} else {
			s := strings.Split(board[0], ",")
			winner, err := checkBoard(s);

			if err != nil {
				result = Result{Winner: ""}
			} else {
				result = Result{Winner: winner}
			 }
		}
		json.NewEncoder(w).Encode(result)
	})

	log.Fatal(http.ListenAndServe(":8080", nil))
}
