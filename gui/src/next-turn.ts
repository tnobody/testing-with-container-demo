import {BoardArray, BoardItem} from "./board.types";

export interface NextTurnResult {
    winner: BoardItem,
    board: BoardArray
}

export const nextTurn = async (board: BoardArray): Promise<NextTurnResult> => {
    try {
        return await fetch(`api/next-turn?board=${board.join(',')}`)
            .then(r => r.json());
    } catch (e) {
        return ({
            winner: '',
            board
        })
    }
}