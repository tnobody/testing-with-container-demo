import React, {FC} from "react";
import {BoardItem} from "./board.types";

export interface BoardFieldProps {
    position: number;
    item: BoardItem;
    onTurn: (position: number) => void;
}

export const BoardField: FC<BoardFieldProps> = ({children, position, item, onTurn}) => {
    function handleClick() {
        if(item === "") {
            onTurn(position)
        }
    }
    const classNames = ['animated','box'];
    if(item != "") {
        classNames.push('pulse')
    }
    return (
        <button className={classNames.join(" ")} onClick={handleClick} >
            {children}
        </button>
    )
}