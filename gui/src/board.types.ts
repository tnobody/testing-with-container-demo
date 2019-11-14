
export type BoardItem = "" | "1" | "0";
export type BoardArray = [BoardItem,BoardItem,BoardItem,BoardItem,BoardItem,BoardItem,BoardItem,BoardItem,BoardItem];

export const emptyBoard = (): BoardArray => ["","","","","","","","",""];

export const updateBoardAt = (item: BoardItem, position: number) => (board: BoardArray) => board.map((i, p) => p === position ? item : i) as BoardArray;