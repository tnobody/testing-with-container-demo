import {BoardField} from "./BoardField";
import React, {useState} from "react";
import {BoardArray, BoardItem, emptyBoard, updateBoardAt} from "./board.types";
import {nextTurn} from "./next-turn";
import {NpcTurnOverlay} from "./NpcTurnOverlay";
import {GameOverOverlay} from "./GameOverOverlay";


export const Board = () => {
    const [board, setBoard] = useState<BoardArray>(emptyBoard());
    const [winner, setWinner] = useState<BoardItem>("");
    const [isNpcTurn, setIsNpcTurn] = useState(false);

    async function handleOnTurn(position: number) {
        if (!isNpcTurn) {
            const updateCmd = updateBoardAt('1', position)(board);
            setBoard(updateCmd);

            setIsNpcTurn(true);
            setTimeout(async () => {
                const afterNpcTurn = await nextTurn(updateCmd);
                setBoard(afterNpcTurn.board);
                setWinner(afterNpcTurn.winner);
                setIsNpcTurn(false);
            }, 500);
        }
    }

    function handleRestart() {
        setBoard(emptyBoard());
        setWinner("");
    }

    return (
        <div className="game-board-wrapper">
            {isNpcTurn
                ? <NpcTurnOverlay />
                : null
            }
            {winner !== ""
                ? <GameOverOverlay winner={winner} onRestart={handleRestart}/>
                : null
            }
            <div className="game-board">
                {board.map((item, position) => (
                    <BoardField item={item} position={position} onTurn={handleOnTurn}>
                        {item}
                    </BoardField>
                ))}
            </div>
        </div>
    )
}