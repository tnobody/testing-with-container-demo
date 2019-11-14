import React, {FC} from "react";
import {BoardItem} from "./board.types";

export const GameOverOverlay: FC<{winner: BoardItem, onRestart: () => void}> = ({winner, onRestart}) => {
    return (
        <div className="game-board-overlay">
            <div className={"animated fade-in npc-is-turning game-board-overlay__backdrop col"}>
                <span>
                    <strong>{winner}</strong> is the winner
                </span>
                <button className={'restart-button'} onClick={onRestart}>Restart</button>
            </div>
        </div>
    )
}